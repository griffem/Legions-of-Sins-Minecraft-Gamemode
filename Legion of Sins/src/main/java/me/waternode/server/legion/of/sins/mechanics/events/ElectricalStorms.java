package me.waternode.server.legion.of.sins.mechanics.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

public class ElectricalStorms extends CatastrophicEvent {

	public ElectricalStorms(Location c, double r, double s, World w, Vector d) {
		super(c, r, s, w, d);
	}

	@Override
	public void OnPlayerNear(Player p, Random random) {
		// 4 times a second / 2 = ~2 times per second
		World w = p.getWorld();
		Location pLoc = p.getLocation();
		int pX = pLoc.getBlockX();
		int pZ = pLoc.getBlockZ();
		if (w.getHighestBlockYAt(pX, pZ) - 5 <= pLoc.getY()) {
			if (random.nextInt(4) == 0) {
				Location loc = new Location(world, range, range, range);

				loc.setX(p.getLocation().getX() + (random.nextInt(11) - 5));
				loc.setY(p.getLocation().getY());
				loc.setZ(p.getLocation().getZ() + (random.nextInt(11) - 5));

				p.getWorld().strikeLightning(loc);
			}
		}

	}
}
