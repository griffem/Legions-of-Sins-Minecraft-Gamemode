package com.griffem.legionofsins.mechanics.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class CatastrophicEvent extends BukkitRunnable {

    private static final int WARNING_BUFFER = 100;

    private final Location center;
    protected final double range;
    private final double speed;
    private final Vector direction;
    private final boolean use3DRange;
    protected final World world;
    private final String name;
    private int ticksLeft;
    private int warnTicks;

    protected CatastrophicEvent(Location c, double r, double s, World w, Vector d, String n,
                                int lifetime, int warningTicks, boolean use3DRange) {
        center = c;
        range = r;
        speed = s;
        world = w;
        direction = d;
        name = n;
        ticksLeft = lifetime;
        warnTicks = warningTicks;
        this.use3DRange = use3DRange;
    }

    @Override
    public void run() {
        if (handleWarning()) {
            return;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            Location playerLocation = p.getLocation();
            if (playerLocation.getWorld() != world) {
                continue;
            }
            if (isPlayerInRange(playerLocation) && shouldAffect(p)) {
                onPlayerNear(p, ThreadLocalRandom.current());
            }
        }

        if (--ticksLeft <= 0) {
            world.setStorm(false);
            cancel();
            return;
        }

        center.add(direction.getX() * speed, direction.getY() * speed, direction.getZ() * speed);
    }

    private boolean handleWarning() {
        if (warnTicks > 0 && warnTicks != 25) {
            warnTicks--;
            return true;
        }
        if (warnTicks == 25) {
            broadcastWarning();
            warnTicks--;
            return true;
        }
        return false;
    }

    private void broadcastWarning() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Location playerLocation = p.getLocation();
            if (playerLocation.getWorld() != world) continue;

            if (isPlayerInRange(playerLocation, range + WARNING_BUFFER) && shouldAffect(p)) {
                p.sendMessage(ChatColor.AQUA + name);
            }
        }
    }

    private boolean shouldAffect(Player p) {
        return p.getGameMode() != GameMode.CREATIVE && !p.hasPermission("los.events.bypass");
    }

    private boolean isPlayerInRange(Location playerLocation) {
        return isPlayerInRange(playerLocation, range);
    }

    private boolean isPlayerInRange(Location playerLocation, double testRange) {
        if (use3DRange) {
            return center.distance(playerLocation) <= testRange;
        }
        double dx = playerLocation.getX() - center.getX();
        double dz = playerLocation.getZ() - center.getZ();
        return dx * dx + dz * dz <= testRange * testRange;
    }

    protected void onPlayerNear(Player p, ThreadLocalRandom random) {
    }
}
