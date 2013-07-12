package com.skywars.Flippehh.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Methods {

	public final Logger logger = Logger.getLogger("Minecraft");

	public static String name = "Sky Wars";
	public static String consoleName = "[Sky Wars]";

	ChatColor bracketColour = ChatColor.GRAY;
	ChatColor pluginNameColour = ChatColor.GOLD;
	ChatColor goodMsgColour = ChatColor.GREEN;
	ChatColor badMsgColour = ChatColor.RED;

	List<String> kitnames2 = new ArrayList<String>();
	public void createInv(String name, int slots, Player owner) {
		Inventory inv = Bukkit.createInventory(owner, slots, name);
		owner.openInventory(inv);
	}

	public void broadcastMessage(String msg) {
		Bukkit.broadcastMessage(bracketColour + "[" + pluginNameColour + name + bracketColour + "] " + goodMsgColour + msg);
	}

	public void sendPlayerMessage(Player p, String msg) {
		p.sendMessage(bracketColour + "[" + pluginNameColour + name + bracketColour + "] " + goodMsgColour + msg);
	}

	public void sendPlayerWarningMessage(Player p, String msg) {
		p.sendMessage(bracketColour + "[" + pluginNameColour + name + bracketColour + "] " + badMsgColour + msg);
	}

	public void sendConsoleMessage(String msg) {
		System.out.println(consoleName + " " + msg);
	}

	public void sendLoggerMessage(String msg) {
		logger.info(consoleName + " " + msg);
	}
}
