package me.waternode.server.legion.of.sins.generation;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.generation.deathworld.DeathWorldPopulator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
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
    public void onPortal(EntityPortalEnterEvent e) {
        if(e.getEntityType() == EntityType.PLAYER) {
            Player p = (Player) e.getEntity();
            if (e.getLocation().getWorld().getName().equalsIgnoreCase("spawn")) {
                Location l = new Location(Bukkit.getWorld("main"), LOSMain.getRandom().nextInt(1000), 260.0D, LOSMain.getRandom().nextInt(1000));

                while(true) {
                    if(l.getWorld().getHighestBlockAt(l).getType() != Material.COAL_BLOCK) {
                        break;
                    } else {
                        l = new Location(Bukkit.getWorld("main"), LOSMain.getRandom().nextInt(1000), 260.0D, LOSMain.getRandom().nextInt(1000));
                    }
                }
                p.teleport(l);
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 450, 5));
            } else if (e.getLocation().getWorld().getName().equalsIgnoreCase("main")) {
                if(LOSMain.getRandom().nextBoolean()) {
                    if(LOSMain.getRandom().nextBoolean()) {
                        p.teleport(new Location(Bukkit.getWorld("deathworld"), -10+LOSMain.getRandom().nextInt(21), 260.0D, 500+LOSMain.getRandom().nextInt(1000)));
                    } else {
                        p.teleport(new Location(Bukkit.getWorld("deathworld"), -10+LOSMain.getRandom().nextInt(21), 260.0D, -1*(500+LOSMain.getRandom().nextInt(1000))));
                    }
                } else {
                    if(LOSMain.getRandom().nextBoolean()) {
                        p.teleport(new Location(Bukkit.getWorld("deathworld"), -1*(500+LOSMain.getRandom().nextInt(1000)), 260.0D, -10+LOSMain.getRandom().nextInt(21)));
                    } else {
                        p.teleport(new Location(Bukkit.getWorld("deathworld"), 500+LOSMain.getRandom().nextInt(1000), 260.0D, -10+LOSMain.getRandom().nextInt(21)));
                    }
                }

                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 450, 5));
                p.addPotionEffect(new InfinitePotionEffect(PotionEffectType.BLINDNESS, 0));
            }
        }
    }
}
