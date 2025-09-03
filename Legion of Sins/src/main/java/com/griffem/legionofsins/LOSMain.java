package com.griffem.legionofsins;

import lombok.Getter;
import com.griffem.legionofsins.generation.WorldListener;
import com.griffem.legionofsins.mechanics.EndGame.EndGameListener;
import com.griffem.legionofsins.mechanics.EndGame.EndGameMobHandler;
import com.griffem.legionofsins.mechanics.MobHandler;
import com.griffem.legionofsins.mechanics.bosses.BossManager;
import com.griffem.legionofsins.mechanics.deathworld.Blindness;
import com.griffem.legionofsins.mechanics.deathworld.DeathWorldMobHandler;
import com.griffem.legionofsins.mechanics.events.EventManager;
import com.griffem.legionofsins.mechanics.spawn.SpawnMobHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Main entry point for the Legion of Sins plugin.
 *
 * <p>The class is intentionally lightweight; most logic is delegated to
 * individual listeners and managers. Helper methods keep the lifecycle
 * callbacks concise and easy to follow.</p>
 */
public class LOSMain extends JavaPlugin implements Listener {

    @Getter
    private static LOSMain instance;

    @Getter
    private EventManager events;

    /** Shared boss manager instance used by both schedulers and listeners. */
    private BossManager bossManager;

    /**
     * Debug flag that can be toggled to enable verbose logging.
     */
    @Getter
    private static final boolean debug = false;

    /**
     * Provides a thread-local random instance for utility classes.
     *
     * @return thread local random
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pluginFile = this.getDescription();
        getLogger().info(pluginFile.getName() + " is now disabled.");
        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public void onEnable() {
        instance = this;
        events = new EventManager(this);

        bossManager = new BossManager(this);

        initSchedulers(bossManager);
        initListeners(bossManager);

        PluginDescriptionFile pluginFile = this.getDescription();
        getLogger().info(pluginFile.getName() + " is written by " + pluginFile.getAuthors() + " is now enabled.");
        getLogger().info(pluginFile.getName() + " version " + pluginFile.getVersion() + " is now enabled.");

    }

    /**
     * Sets up repeating tasks such as the event manager and boss manager.
     */
    private void initSchedulers(BossManager bosses) {
        events.runTaskTimer(this, 20L, 1000L);
        bosses.runTaskTimer(this, 20L, 500L);
    }

    /**
     * Instantiates and registers all plugin listeners.
     */
    private void initListeners(BossManager bosses) {
        WorldListener worldListener = new WorldListener();
        MobHandler mobHandler = new MobHandler();
        Blindness blind = new Blindness();
        EndGameListener egl = new EndGameListener();
        DeathWorldMobHandler dwMH = new DeathWorldMobHandler();
        SpawnMobHandler sMH = new SpawnMobHandler();
        EndGameMobHandler egmh = new EndGameMobHandler();

        registerListeners(worldListener, mobHandler, egl, bosses, blind, dwMH, sMH, egmh);
        getLogger().info("Listeners hooked!");
    }

    /**
     * Helper method to register any number of listeners with the plugin manager.
     *
     * @param listeners listeners to register
     */
    private void registerListeners(Listener... listeners) {
        var pm = getServer().getPluginManager();
        for (Listener listener : listeners) {
            pm.registerEvents(listener, this);
        }
    }
}
