package com.griffem.legionofsins.mechanics.events;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;

/**
 * Event that poisons players exposed to the sky while rain falls.
 */
public class AcidRain extends CatastrophicEvent {

    public AcidRain(Location c, double r, double s, World w, Vector d) {
        super(c, r, s, w, d, "The rain will burn your skin, better find a leaf or two to cover it...");
    }

    @Override
    public void onPlayerNear(Player p, Random random) {
        if (isExposedToSky(p)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 1));
        }
        World w = p.getWorld();
        w.playSound(p.getLocation(), Sound.WEATHER_RAIN, 0.5f, 1f);
        w.setStorm(true);
    }

    /**
     * Determines if a player is outdoors and should be affected by acid rain.
     */
    private boolean isExposedToSky(Player p) {
        World w = p.getWorld();
        Location loc = p.getLocation();
        int x = loc.getBlockX();
        int z = loc.getBlockZ();
        return w.getHighestBlockYAt(x, z) - 5 <= loc.getY();
    }
}
