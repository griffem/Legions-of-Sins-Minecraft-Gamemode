package me.waternode.server.legion.of.sins.mechanics.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;
import org.bukkit.entity.Entity;

public class MeteorShowers extends CatastrophicEvent {

	public MeteorShowers(Location c, double r, double s, World w, Vector d) {
		super(c, r, s, w, d, "Run, the meteors arrive.");
	}

	@Override
	public void OnPlayerNear(Player p, Random random) {
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
