package com.skywars.Flippehh.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skywars.Flippehh.Utilities.EventListener;

public class CommandHandler implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Location SPAWN_LOC = new Location(Bukkit.getWorld("world"), -1448.5,
				34, -1898.5);
		if (cmd.getName().equalsIgnoreCase("leave")) {
			Player player = (Player) sender;
			if (!(EventListener.inLobby.contains(player.getName()))) {
				player.teleport(SPAWN_LOC);
				EventListener.inLobby.add(player.getName());
			} else {
				player.sendMessage("You are currently not in a game.");
			}
		}
		if (cmd.getName().equalsIgnoreCase("g")) {
			Player player = (Player) sender;
			if (player.isOp()) {
				if (player.getGameMode() == GameMode.SURVIVAL) {
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage("Swaping gamemodes!");
				} else if (player.getGameMode() == GameMode.CREATIVE) {
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage("Swaping gamemodes!");
				}
			}
		}
		return true;
	}

}
