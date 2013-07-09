package com.skywars.Flippehh;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		String player = event.getPlayer().getName();
		event.setJoinMessage(ChatColor.GRAY + player + " has connected.");
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		String player = event.getPlayer().getName();
		event.setQuitMessage(ChatColor.GRAY + player + " has disconnected.");
	}
}
