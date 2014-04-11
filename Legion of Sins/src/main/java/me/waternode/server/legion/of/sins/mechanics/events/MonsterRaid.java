package me.waternode.server.legion.of.sins.mechanics.events;

import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

public class MonsterRaid extends CatastrophicEvent {
	public MonsterRaid(Location c, double r, double s, World w, Vector d) {
		super(c, r, s, w, d);
	}

	@Override
	public void OnPlayerNear(Player p, Random random) {
		if (LOSMain.getRandom().nextInt(10) == 0) {
			Location l = new Location(this.world, this.range, this.range, this.range);

			l.setX(p.getLocation().getX() + (random.nextInt(21) - 10));
			l.setY(p.getLocation().getY());
			l.setZ(p.getLocation().getZ() + (random.nextInt(21) - 10));

			p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
		}
	}
}