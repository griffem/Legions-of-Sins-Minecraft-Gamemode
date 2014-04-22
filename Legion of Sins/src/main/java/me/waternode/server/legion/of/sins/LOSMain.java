package me.waternode.server.legion.of.sins;

import lombok.Getter;
import me.waternode.server.legion.of.sins.generation.WorldListener;
import me.waternode.server.legion.of.sins.mechanics.EndGame.EndGameListener;
import me.waternode.server.legion.of.sins.mechanics.EndGame.EndGameMobHandler;
import me.waternode.server.legion.of.sins.mechanics.MobHandler;
import me.waternode.server.legion.of.sins.mechanics.bosses.BossManager;
import me.waternode.server.legion.of.sins.mechanics.deathworld.Blindness;
import me.waternode.server.legion.of.sins.mechanics.deathworld.DeathWorldMobHandler;
import me.waternode.server.legion.of.sins.mechanics.events.EventManager;
import me.waternode.server.legion.of.sins.mechanics.spawn.SpawnMobHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class LOSMain extends JavaPlugin implements Listener {

	@Getter
	private static LOSMain instance;

    @Getter
    private EventManager events;

	private WorldListener worldListener;

	@Getter
	private static final Boolean debug = false;

	@Getter
	private static Random random;

	public void onDisable() {
		PluginDescriptionFile pluginFile = this.getDescription();
		getLogger().info(pluginFile.getName() + " is now disabled.");
		Bukkit.getScheduler().cancelTasks(this);
	}

	public void onEnable() {
		instance = this;
		random = new Random();
		events = new EventManager(this);
        BossManager bosses = new BossManager(this);
		events.runTaskTimer(this, 20L, 1000L);
        bosses.runTaskTimer(this, 20L, 500L);

		worldListener = new WorldListener();
		MobHandler MH = new MobHandler();
        Blindness blind = new Blindness();
        EndGameListener egl = new EndGameListener();
        DeathWorldMobHandler dwMH = new DeathWorldMobHandler();
        SpawnMobHandler sMH = new SpawnMobHandler();
        EndGameMobHandler egmh = new EndGameMobHandler();

		getServer().getPluginManager().registerEvents(worldListener, this);
		getServer().getPluginManager().registerEvents(MH, this);
        getServer().getPluginManager().registerEvents(egl, this);
        getServer().getPluginManager().registerEvents(bosses, this);
        getServer().getPluginManager().registerEvents(blind, this);
        getServer().getPluginManager().registerEvents(dwMH, this);
        getServer().getPluginManager().registerEvents(sMH, this);
        getServer().getPluginManager().registerEvents(egmh, this);
		getLogger().info("Listeners hooked!");

		PluginDescriptionFile pluginFile = this.getDescription();
		getLogger().info(pluginFile.getName() + " is written by " + pluginFile.getAuthors() + " is now enabled.");
		getLogger().info(pluginFile.getName() + " version " + pluginFile.getVersion() + " is now enabled.");

	}
}
