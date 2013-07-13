package com.skywars.Flippehh.Utilities;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class EventListener implements Listener {

	Location SPAWN_LOC = new Location(Bukkit.getWorld("world"), -1448.5, 34,
			-1898.5);
	// Not implemented in world yet.
	// Location COAL_LOC = new Location(Bukkit.getWorld("world"), -1447.5, 103,
	// -1897.5);
	// Location REDSTONE_LOC = new Location(Bukkit.getWorld("world"), -1447.5,
	// 103, -1897.5);
	// Location IRON_LOC = new Location(Bukkit.getWorld("world"), -1447.5, 103,
	// -1897.5);
	// Location GOLD_LOC = new Location(Bukkit.getWorld("world"), -1447.5, 103,
	// -1897.5);
	// Location DIAMOND_LOC = new Location(Bukkit.getWorld("world"), -1447.5,
	// 103, -1897.5);
	// Location EMERALD_LOC = new Location(Bukkit.getWorld("world"), -1447.5,
	// 103, -1897.5);

	public static HashSet<String> playerNotInSpawn = new HashSet<String>();
	public static HashSet<String> twoTeams = new HashSet<String>();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.teleport(SPAWN_LOC);
		if (player.hasPlayedBefore()) {
			event.setJoinMessage(ChatColor.GRAY + player.getName()
					+ " has connected.");
		} else {
			Bukkit.broadcastMessage(ChatColor.GOLD + player.getName()
					+ ChatColor.LIGHT_PURPLE
					+ " has just joined for the first time!");
			event.setJoinMessage(ChatColor.GRAY + player.getName()
					+ " has connected.");
		}

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
		if (playerNotInSpawn.contains(player.getName())) {
			playerNotInSpawn.remove(player.getName());
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
		Player player = (Player) event.getEntity();
		if (event.getCause() == DamageCause.VOID
				&& !(playerNotInSpawn.contains(player.getName()))) {
			player.teleport(SPAWN_LOC);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void noFall(EntityDamageEvent event) {
		Player player = (Player) event.getEntity();
		if (event.getCause() == DamageCause.FALL
				&& !(playerNotInSpawn.contains(player.getName()))) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onServerPing(ServerListPingEvent event) {
		event.setMotd(ChatColor.GOLD.toString() + ChatColor.BOLD
				+ "Welcome to SkyWars, enjoy your stay!");
	}

	@EventHandler
	// Type ID's 173:CoalBlock 152:RedStoneBlock 42:IronBlock 41:GoldBlock
	// 57:DiamondBlock 133:EmeraldBlock
	public void joinGame(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.PHYSICAL)) {
			if (event.getClickedBlock().getTypeId() == 70
					&& event.getClickedBlock().getRelative(BlockFace.DOWN)
							.getTypeId() == 173) {
				playerNotInSpawn.add(player.getName());
				player.teleport(SPAWN_LOC);
			} else if (event.getClickedBlock().getTypeId() == 70
					&& event.getClickedBlock().getRelative(BlockFace.DOWN)
							.getTypeId() == 152) {
				playerNotInSpawn.add(player.getName());
				player.teleport(SPAWN_LOC);
			} else if (event.getClickedBlock().getTypeId() == 70
					&& event.getClickedBlock().getRelative(BlockFace.DOWN)
							.getTypeId() == 42) {
				playerNotInSpawn.add(player.getName());
				player.teleport(SPAWN_LOC);
			} else if (event.getClickedBlock().getTypeId() == 70
					&& event.getClickedBlock().getRelative(BlockFace.DOWN)
							.getTypeId() == 41) {
				playerNotInSpawn.add(player.getName());
				player.teleport(SPAWN_LOC);
			} else if (event.getClickedBlock().getTypeId() == 70
					&& event.getClickedBlock().getRelative(BlockFace.DOWN)
							.getTypeId() == 57) {
				playerNotInSpawn.add(player.getName());
				player.teleport(SPAWN_LOC);
			} else if (event.getClickedBlock().getTypeId() == 70
					&& event.getClickedBlock().getRelative(BlockFace.DOWN)
							.getTypeId() == 133) {
				playerNotInSpawn.add(player.getName());
				player.teleport(SPAWN_LOC);
			}
		}
	}

	@EventHandler
	public void noPvP(EntityDamageByEntityEvent event) {
		Player damager = (Player) event.getDamager();
		Player hurt = (Player) event.getEntity();
		if (hurt instanceof Player) {
			if (damager instanceof Player) {
				if (!(playerNotInSpawn.contains(damager.getName()))
						&& !(playerNotInSpawn.contains(hurt.getName()))) {
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
		} else if (!(playerNotInSpawn.contains(player.getName()))) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void nooGrief(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (player.isOp()) {
			return;
		} else if (!(playerNotInSpawn.contains(player.getName()))) {
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

}
