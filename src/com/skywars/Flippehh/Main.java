package com.skywars.Flippehh;


import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable(){
		loadEssentials();
		getServer().getLogger().info("SkyWars is enabled.");
		
	}
	
	void loadEssentials(){
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new EventListener(), this);
		//Just for quick copying later :] getCommand("foobar").setExecutor(new CommandHandler());
	}
	
	public void onDisable(){
		getServer().getLogger().info("SkyWars is enabled.");
	}
}
