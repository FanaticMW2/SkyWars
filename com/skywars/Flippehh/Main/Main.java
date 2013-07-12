package com.skywars.Flippehh.Main;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.skywars.Flippehh.Utilities.EventListener;
import com.skywars.Flippehh.Utilities.Methods;

public class Main extends JavaPlugin implements Listener {


	public final static Logger logger = Logger.getLogger("Minecraft");

	public static Main plugin;

	public Methods m = new Methods();

	public void onEnable(){
		loadEssentials();
		getServer().getLogger().info("SkyWars is enabled.");

	}

	void loadEssentials(){
		saveConfig();
		loadConfigurationFile(getConfig());
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new EventListener(), this);
		//Just for quick copying later :] getCommand("foobar").setExecutor(new CommandHandler());
	}

	private void loadConfigurationFile(FileConfiguration config) {
		try {
			m.sendLoggerMessage("Setting up Config...");
			if (!new File(getDataFolder(), "RESET.FILE").exists()) {
				m.sendLoggerMessage("Reset File Not Found! Resetting Default Configuration Values!");

				//config.set("SkyWars.Something", null);

				new File(getDataFolder(), "RESET.FILE").createNewFile();

			}
			m.sendLoggerMessage("Configuration Loaded!");
			saveConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDisable(){
		getServer().getLogger().info("SkyWars is enabled.");
	}
}
