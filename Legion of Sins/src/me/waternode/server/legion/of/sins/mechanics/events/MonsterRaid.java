package me.waternode.server.legion.of.sins.mechanics.events;

import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.mechanics.CatastrophicEvent;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class MonsterRaid extends CatastrophicEvent {
	public MonsterRaid(Location c, double r, double s, int l, LOSMain m, boolean D3, World w, Vector d) {
		super(c, r, s, l, m, D3, w, d);
	}

	public void OnPlayerNear(Player p) {
		if (this.rand.nextInt(4) == 0) {
			Location l = new Location(this.world, this.range, this.range, this.range);

			l.setX(p.getLocation().getX() + (this.rand.nextInt(11) - 5));
			l.setY(p.getLocation().getY());
			l.setZ(p.getLocation().getZ() + (this.rand.nextInt(11) - 5));

			p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
		}
	}
}