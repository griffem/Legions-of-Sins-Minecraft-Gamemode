package com.griffem.legionofsins.mechanics.events;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Event that blinds and withers players caught out in the open during a dust storm.
 */
public class DustStorm extends CatastrophicEvent {

    private static final int LIFETIME = 100;
    private static final int WARNING_TICKS = 35;

    public DustStorm(Location c, double r, double s, World w, Vector d) {
        super(c, r, s, w, d, "A dust storm is coming, better take cover...", LIFETIME, WARNING_TICKS, false);
    }

    @Override
    public void onPlayerNear(Player p, ThreadLocalRandom random) {
        if (isSheltered(p)) return;

        p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 120, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 120, 1));

        Location loc = p.getLocation();
        World world = p.getWorld();
        world.spawnParticle(Particle.SMOKE_NORMAL, loc, 8, 0.25, 0.25, 0.25, 0.01);
        world.playSound(loc, Sound.BLOCK_FIRE_EXTINGUISH, 0.5f, 1f);
        spawnSmokeAround(loc, world);
    }

    /**
     * Checks whether the player is under cover and safe from the storm.
     */
    private boolean isSheltered(Player p) {
        World w = p.getWorld();
        Location loc = p.getLocation();
        int x = loc.getBlockX();
        int z = loc.getBlockZ();
        return w.getHighestBlockYAt(x, z) - 4 > loc.getY();
    }

    /**
     * Produces smoke particles in the four cardinal directions around a location.
     */
    private void spawnSmokeAround(Location loc, World world) {
        for (Vector v : new Vector[]{new Vector(0,0,1), new Vector(0,0,-1), new Vector(1,0,0), new Vector(-1,0,0)}) {
            Location target = loc.clone().add(v);
            world.spawnParticle(Particle.SMOKE_NORMAL, target, 8, 0.25, 0.25, 0.25, 0.01);
        }
    }
}
