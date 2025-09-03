package com.griffem.legionofsins.mechanics.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class ElectricalStorms extends CatastrophicEvent {

        private static final int LIFETIME = 100;
        private static final int WARNING_TICKS = 35;

        public ElectricalStorms(Location c, double r, double s, World w, Vector d) {
                super(c, r, s, w, d, "The lightning goes boom, I would take cover.", LIFETIME, WARNING_TICKS, false);
        }

        @Override
        public void onPlayerNear(Player p, ThreadLocalRandom random) {
		// 4 times a second / 2 = ~2 times per second
		World w = p.getWorld();
		Location pLoc = p.getLocation();
		int pX = pLoc.getBlockX();
		int pZ = pLoc.getBlockZ();
		if (w.getHighestBlockYAt(pX, pZ) - 1 <= pLoc.getY()) {
			if (random.nextInt(4) == 0) {
				Location loc = new Location(world, range, range, range);

				loc.setX(p.getLocation().getX() + (random.nextInt(15) - 7));
				loc.setY(p.getLocation().getY());
				loc.setZ(p.getLocation().getZ() + (random.nextInt(15) - 7));

				p.getWorld().strikeLightning(loc);
			}
		}

	}
}
