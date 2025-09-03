package com.griffem.legionofsins.mechanics.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.entity.Entity;

public class MeteorShowers extends CatastrophicEvent {

        private static final int LIFETIME = 100;
        private static final int WARNING_TICKS = 35;

        public MeteorShowers(Location c, double r, double s, World w, Vector d) {
                super(c, r, s, w, d, "Run, the meteors arrive.", LIFETIME, WARNING_TICKS, false);
        }

        @Override
        public void onPlayerNear(Player p, ThreadLocalRandom random) {
		// 4 times a second / 4 = ~1 time per second
		if (random.nextInt(6) != 0) return;
        if (p.getWorld().getHighestBlockYAt(p.getLocation()) - 5 >= p.getLocation().getY()) return;
		Location l = new Location(world, range, range, range);

		l.setX(p.getLocation().getX() + (random.nextInt(11) - 5));
		l.setY(250);
		l.setZ(p.getLocation().getZ() + (random.nextInt(11) - 5));

		Entity a = p.getWorld().spawnEntity(l, EntityType.PRIMED_TNT);
        a.setVelocity(new Vector(0, -4, 0));
	}
}
