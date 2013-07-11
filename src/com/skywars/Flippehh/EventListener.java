package com.skywars.Flippehh;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		event.setJoinMessage(ChatColor.GRAY + player.getName() + " has connected.");
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
}
