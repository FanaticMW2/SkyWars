package com.skywars.Flippehh.Utilities;

import java.util.HashSet;
import java.util.Random;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import com.skywars.Flippehh.GameTypes.FourTeams;
import com.skywars.Flippehh.GameTypes.TwoTeams;

public class EventListener implements Listener {

	Location SPAWN_LOC = new Location(Bukkit.getWorld("world"), -1448.5, 34, -1898.5);
	Location NONDONOR_LOC = new Location(Bukkit.getWorld("world"), -1448.5, 34, -1898.5);
	Location INFORMATION_LOC = new Location(Bukkit.getWorld("world"), -1442.5, 34, -2047.5);
	Location REGULARDONOR_LOC = new Location(Bukkit.getWorld("world"), -1390.5, 34, -1930.5);
	Location HIGHDONOR_LOC = new Location(Bukkit.getWorld("world"), -1394.5, 34, -1993.5);
	Location HIGHESTDONOR_LOC = new Location(Bukkit.getWorld("world"), -1448.5, 34, -1898.5);
	private final JavaPlugin instance;

	public static HashSet<String> inLobby = new HashSet<String>();
	public static HashSet<String> tenpersonffa = new HashSet<String>();
	public static HashSet<String> fourTeamsof4 = new HashSet<String>();
	public static HashSet<String> twoTeamsof8 = new HashSet<String>();
	
	public static HashSet<String> nonDonor = new HashSet<String>();
	public static HashSet<String> donor = new HashSet<String>();
	public static HashSet<String> highDonor = new HashSet<String>();
	public static HashSet<String> highestDonor = new HashSet<String>();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		inLobby.add(player.getName());
		player.teleport(SPAWN_LOC);
			event.setJoinMessage(ChatColor.GRAY + player.getName()
					+ " has connected.");
	}

	@EventHandler
	public void tabColors(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player.hasPermission("sky.owner")) {
			player.setPlayerListName(ChatColor.DARK_RED + player.getName());
		} else if (player.hasPermission("sky.admin")) {
			player.setPlayerListName(ChatColor.RED + player.getName());
		} else if (player.hasPermission("sky.moderator")) {
			player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (inLobby.contains(player.getName())) {
			inLobby.remove(player.getName());
		}
		event.setQuitMessage(ChatColor.GRAY + player.getName()
				+ " has disconnected.");
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKicked(PlayerKickEvent event) {
		Player player = event.getPlayer();
		Bukkit.broadcastMessage(ChatColor.RED + player.getName()
				+ ChatColor.GRAY + " was kicked for: " + event.getReason());
	}

	@EventHandler
	public void inVoid(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player){
			Player player = (Player) event.getEntity();
		if (event.getCause() == DamageCause.VOID
				&& inLobby.contains(player.getName())) {
			player.teleport(SPAWN_LOC);
			event.setCancelled(true);
		}
		}else{
			return;
		}
	}

	@EventHandler
	public void noFall(EntityDamageEvent event) {
		Player player = (Player) event.getEntity();
		if (event.getCause() == DamageCause.FALL
				&& (inLobby.contains(player.getName()))) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onServerPing(ServerListPingEvent event) {
		event.setMotd(ChatColor.GOLD.toString() + ChatColor.BOLD
				+ "Welcome to SkyWars, have fun!");
	}

	@EventHandler
	// Type ID's 173:CoalBlock 152:RedStoneBlock 42:IronBlock 41:GoldBlock
	// 57:DiamondBlock 133:EmeraldBlock
	public void joinGame(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (event.getAction().equals(Action.PHYSICAL)) {
			((CraftPlayer)event.getPlayer()).getHandle().playerConnection.checkMovement = false;
			if (event.getClickedBlock().getTypeId() == 70
					&& event.getClickedBlock().getRelative(BlockFace.DOWN)
							.getTypeId() == 173) {
				player.sendMessage("Teleporting you to the lobby!");
				nonDonor.add(player.getName());
				Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
					public void run() {
						player.teleport(NONDONOR_LOC);
					}
					
				},1);
				if(nonDonor.size() >= 10){
					Random random = new Random();
					int gameType = random.nextInt(1);
					if(gameType == 0){
						BukkitTask start = new FourTeams(instance).runTask(instance);
					}else{
						BukkitTask start = new TwoTeams(instance).runTask(instance);
					}
				}
			}else if(event.getClickedBlock().getTypeId() == 70 && event.getClickedBlock().getRelative(BlockFace.DOWN).getTypeId() == 41){
				if(player.hasPermission("sky.donor") || player.hasPermission("sky.superdonor") || player.hasPermission("sky.ultradonor")){
				player.sendMessage(ChatColor.GOLD + "Teleporting you to the lobby!");
				donor.add(player.getName());
				Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
					public void run() {
						player.teleport(REGULARDONOR_LOC);
					}
					
				},1);
				if(donor.size() >= 10){
					
				}
				}else{
					player.sendMessage(ChatColor.GOLD + "Donate to recieve access to this warp.");
				}
			}else if (event.getClickedBlock().getTypeId() == 70
					&& event.getClickedBlock().getRelative(BlockFace.DOWN)
							.getTypeId() == 57) {
				if(player.hasPermission("sky.superdonor") || player.hasPermission("sky.ultradonor")){
				player.sendMessage(ChatColor.AQUA + "Teleporting you to the lobby!");
				Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
					public void run() {
						player.teleport(HIGHDONOR_LOC);
					}
					
				},1);
				highDonor.add(player.getName());
				if(highDonor.size() >= 10){
					
				}
				}else{
					player.sendMessage(ChatColor.AQUA + "Donate to recieve access to this warp!");
				}
			} else if (event.getClickedBlock().getTypeId() == 70
					&& event.getClickedBlock().getRelative(BlockFace.DOWN)
							.getTypeId() == 133) {
				if(player.hasPermission("sky.ultradonor")){
				player.sendMessage(ChatColor.GREEN  + "Teleporting you to the lobby!");
				highestDonor.add(player.getName());
				Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
					public void run() {
						player.teleport(HIGHESTDONOR_LOC);
					}
					
				},1);
				if(highestDonor.size() >= 10){
					
				}
				}else{
					player.sendMessage(ChatColor.GREEN + "Donate to recieve access to this warp!");
				}
			}else if(event.getClickedBlock().getTypeId() == 70
					&& event.getClickedBlock().getRelative(BlockFace.DOWN)
					.getTypeId() == 22) {
				player.sendMessage(ChatColor.DARK_PURPLE  + "Teleporting you to the information lobby!");
				Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
					public void run() {
						player.teleport(INFORMATION_LOC);
					}
					
				},1);
				

			}
		}
	}

	@EventHandler
	public void noPvP(EntityDamageByEntityEvent event) {
		Player damager = (Player) event.getDamager();
		Player hurt = (Player) event.getEntity();
		if (hurt instanceof Player) {
			if (damager instanceof Player) {
				if ((inLobby.contains(damager.getName()))
						&& (inLobby.contains(hurt.getName()))) {
					event.setCancelled(true);
				}

			}
		} else {
			return;
		}
	}

	@EventHandler
	public void noGrief(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (player.isOp()) {
			return;
		} else if ((inLobby.contains(player.getName()))) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void nooGrief(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (player.isOp()) {
			return;
		} else if ((inLobby.contains(player.getName()))) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void STAHPTHEMESSAGES(PlayerDeathEvent event) {
		event.setDeathMessage("");
	}

	@EventHandler
	public void soupFTW(PlayerInteractEvent pi) {
		Player player = pi.getPlayer();
		if ((pi.getAction().equals(Action.RIGHT_CLICK_AIR))
				&& player.getItemInHand().getType()
						.equals(Material.MUSHROOM_SOUP)
				|| (pi.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& (!player.getGameMode().equals(GameMode.CREATIVE)
						&& player.getItemInHand().getType()
								.equals(Material.MUSHROOM_SOUP) && player
						.getHealth() < 20)) {
			if (player.getHealth() == 20) {
				return;
			}
			if (player.getHealth() + 7 == 20) {
				player.setHealth(20);
				player.getItemInHand().setType(Material.BOWL);
			}
			if (player.getHealth() + 7 > 20) {
				player.setHealth(20);
				player.getItemInHand().setType(Material.BOWL);
			}
			if (player.getHealth() < 13) {
				player.setHealth(player.getHealth() + 7);
				player.getItemInHand().setType(Material.BOWL);
			}

		}
	}
	public EventListener(JavaPlugin instance){
		this.instance = instance;
	}

}
