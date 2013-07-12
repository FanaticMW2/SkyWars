package com.skywars.Flippehh.Utilities;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class EventListener implements Listener {
	Location SPAWN_LOC = new Location(Bukkit.getWorld("world"), -1447.5, 103, -1897.5);
	//Not implemented in world yet.
	//Location COAL_LOC = new Location(Bukkit.getWorld("world"), -1447.5, 103, -1897.5);
	//Location REDSTONE_LOC = new Location(Bukkit.getWorld("world"), -1447.5, 103, -1897.5);
	//Location IRON_LOC = new Location(Bukkit.getWorld("world"), -1447.5, 103, -1897.5);
	//Location GOLD_LOC = new Location(Bukkit.getWorld("world"), -1447.5, 103, -1897.5);
	//Location DIAMOND_LOC = new Location(Bukkit.getWorld("world"), -1447.5, 103, -1897.5);
	//Location EMERALD_LOC = new Location(Bukkit.getWorld("world"), -1447.5, 103, -1897.5);
	public static HashSet<String> playerNotInLobby = new HashSet<String>();
	public static HashSet<String> twoTeams = new HashSet<String>();
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.teleport(SPAWN_LOC);
		if(player.hasPlayedBefore()){
			event.setJoinMessage(ChatColor.GRAY + player.getName() + " has connected.");
		}else{
			Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.LIGHT_PURPLE + " has just joined for the first time!");
			event.setJoinMessage(ChatColor.GRAY + player.getName() + " has connected.");
		}
		
	}
	@EventHandler
	public void tabColors(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(player.hasPermission("sky.owner")){
			player.setPlayerListName(ChatColor.DARK_RED + player.getName());
		}else if(player.hasPermission("sky.admin")){
			player.setPlayerListName(ChatColor.RED + player.getName());
		}else if(player.hasPermission("sky.moderator")){
			player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
		}
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		event.setQuitMessage(ChatColor.GRAY + player.getName() + " has disconnected.");
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKicked(PlayerKickEvent event){
		Player player = event.getPlayer();
		Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GRAY + " was kicked for: " + event.getReason());
	}
	@EventHandler
	public void inVoid(EntityDamageEvent event){
		Player player = (Player) event.getEntity();
		if(event.getCause() == DamageCause.VOID){
			player.teleport(SPAWN_LOC);
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onServerPing(ServerListPingEvent event){
		event.setMotd(ChatColor.GOLD.toString() + ChatColor.BOLD + "Welcome to SkyWars, we hope you enjoy your stay!");
	}
	@EventHandler
	//Type ID's 173:CoalBlock  152:RedStoneBlock 42:IronBlock 41:GoldBlock 57:DiamondBlock 133:EmeraldBlock
	public void joinGame(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(event.getAction().equals(Action.PHYSICAL)){
			if(event.getClickedBlock().getTypeId() == 70 && event.getClickedBlock().getRelative(BlockFace.DOWN).getTypeId() == 173){
				playerNotInLobby.add(player.getName());
				player.teleport(SPAWN_LOC);
			}else if(event.getClickedBlock().getTypeId() == 70 && event.getClickedBlock().getRelative(BlockFace.DOWN).getTypeId() == 152){
				playerNotInLobby.add(player.getName());
				player.teleport(SPAWN_LOC);
			}else if(event.getClickedBlock().getTypeId() == 70 && event.getClickedBlock().getRelative(BlockFace.DOWN).getTypeId() == 42){
				playerNotInLobby.add(player.getName());
				player.teleport(SPAWN_LOC);
			}else if(event.getClickedBlock().getTypeId() == 70 && event.getClickedBlock().getRelative(BlockFace.DOWN).getTypeId() == 41){
				playerNotInLobby.add(player.getName());
				player.teleport(SPAWN_LOC);
			}else if(event.getClickedBlock().getTypeId() == 70 && event.getClickedBlock().getRelative(BlockFace.DOWN).getTypeId() == 57){
				playerNotInLobby.add(player.getName());
				player.teleport(SPAWN_LOC);
			}else if(event.getClickedBlock().getTypeId() == 70 && event.getClickedBlock().getRelative(BlockFace.DOWN).getTypeId() == 133){
				playerNotInLobby.add(player.getName());
				player.teleport(SPAWN_LOC);
			}
		}
	}
}
