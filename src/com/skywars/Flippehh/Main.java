package com.skywars.Flippehh;


import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable(){
		getServer().getLogger().info("SkyWars is enabled.");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new EventListener(), this);
	}
	public void onDisable(){
		getServer().getLogger().info("SkyWars is enabled.");
	}
}
