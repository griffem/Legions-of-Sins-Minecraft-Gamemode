package me.waternode.server.legion.of.sins.generation;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import me.waternode.server.legion.of.sins.generation.deathworld.DeathWorldPopulator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class WorldListener implements Listener {

	@EventHandler
	public void onInit(WorldInitEvent e) {
        if(e.getWorld().getName().equalsIgnoreCase("main")) {
            e.getWorld().getPopulators().add(new MainPopulator());
        } else if(e.getWorld().getName().equalsIgnoreCase("deathworld")) {
            e.getWorld().getPopulators().add(new DeathWorldPopulator());
        }

	}

	@EventHandler
	public void onPortal(PlayerPortalEvent e) {
		Random r = new Random();
        Location locBelow = e.getPlayer().getLocation().subtract(0, 1, 0);

		if (e.getFrom().getWorld().getName().equalsIgnoreCase("spawn")) {
            if(locBelow.getBlock().getType() == Material.COAL_BLOCK) {
                e.getPlayer().teleport(new Location(Bukkit.getWorld("main"), r.nextInt(1000), 260.0D, r.nextInt(1000)));
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 150, 5));
            } else {
                e.getPlayer().teleport(new Location(Bukkit.getWorld("main"), r.nextInt(1000), 260.0D, r.nextInt(1000)));
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 150, 5));
            }

		} else if (e.getFrom().getWorld().getName().equalsIgnoreCase("main")) {
			e.getPlayer().teleport(new Location(Bukkit.getWorld("deathworld"), r.nextInt(1000), 260.0D, r.nextInt(1000)));
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 150, 5));
            e.getPlayer().addPotionEffect(new InfinitePotionEffect(PotionEffectType.BLINDNESS, 1));
		}

		e.setCancelled(true);
	}
}
