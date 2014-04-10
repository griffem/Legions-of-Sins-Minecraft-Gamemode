package me.waternode.server.legion.of.sins;

import lombok.Getter;
import me.waternode.server.legion.of.sins.mechanics.MobHandler;
import me.waternode.server.legion.of.sins.mechanics.events.EventManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
import java.util.logging.Logger;

public class LOSMain extends JavaPlugin implements Listener {
	private static final Logger logger = Bukkit.getLogger();
	private me.waternode.server.legion.of.sins.generation.WorldListener WorldListener;

	@Getter
	private static Random random;

	public void onDisable() {
		PluginDescriptionFile pluginFile = this.getDescription();
		logger.info(pluginFile.getName() + " is now disabled.");
		Bukkit.getScheduler().cancelTasks(this);
	}

	public void onEnable() {
		random = new Random();
		EventManager events = new EventManager(this);
		events.runTaskTimer(this, 20L, 600L);

		WorldListener = new me.waternode.server.legion.of.sins.generation.WorldListener();
		MobHandler MS = new MobHandler();

		getServer().getPluginManager().registerEvents(WorldListener, this);
		getServer().getPluginManager().registerEvents(MS, this);
		logger.info("Listeners hooked!");

		PluginDescriptionFile pluginFile = this.getDescription();
		logger.info(pluginFile.getName() + " is written by " + pluginFile.getAuthors() + " is now enabled.");
		logger.info(pluginFile.getName() + " version " + pluginFile.getVersion() + " is now enabled.");

	}
}
