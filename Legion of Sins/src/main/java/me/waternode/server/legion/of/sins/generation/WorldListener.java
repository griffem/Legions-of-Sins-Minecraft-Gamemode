package me.waternode.server.legion.of.sins.generation;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.generation.deathworld.DeathWorldPopulator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
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
        if (e.getFrom().getWorld().getName().equalsIgnoreCase("spawn")) {
            Location l = new Location(Bukkit.getWorld("main"), LOSMain.getRandom().nextInt(1000), 260.0D, LOSMain.getRandom().nextInt(1000));

            while(true) {
                if(l.getWorld().getHighestBlockAt(l).getType() != Material.COAL_BLOCK) {
                    break;
                } else {
                    l = new Location(Bukkit.getWorld("main"), LOSMain.getRandom().nextInt(1000), 260.0D, LOSMain.getRandom().nextInt(1000));
                }
            }
            e.getPlayer().teleport(l);
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 450, 5));
        } else if (e.getFrom().getWorld().getName().equalsIgnoreCase("main")) {
            e.getPlayer().teleport(new Location(Bukkit.getWorld("deathworld"), LOSMain.getRandom().nextInt(1000), 260.0D, LOSMain.getRandom().nextInt(1000)));
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 450, 5));
            e.getPlayer().addPotionEffect(new InfinitePotionEffect(PotionEffectType.BLINDNESS, 0));
        }

        e.setCancelled(true);
    }
}
