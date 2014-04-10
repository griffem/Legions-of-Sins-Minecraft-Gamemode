package me.waternode.server.legion.of.sins.mechanics.events;

import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class CatastrophicEvent extends BukkitRunnable {

	private final Location center;
	protected final double range;
	private final double speed;
	private int lifetime;
	private final Vector direction;
	private final boolean D3Dist;
	protected final World world;

	protected CatastrophicEvent(Location c, double r, double s, World w, Vector d) {
		center = c;
		range = r;
		speed = s;
		lifetime = 100;
		D3Dist = false;
		world = w;
		direction = d;
	}

	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Location playerLocation = p.getLocation();
			if (playerLocation.getWorld() == world) {
				if (D3Dist) {
					if (center.distance(playerLocation) <= range) {
						if (p.getGameMode() != GameMode.CREATIVE) {
							OnPlayerNear(p, LOSMain.getRandom());
						}

					}
				} else {
					if (Math.pow(playerLocation.getX() - center.getX(), 2) + Math.pow(playerLocation.getZ() - center.getZ(), 2) <= Math.pow(range, 2)) {
						if (p.getGameMode() != GameMode.CREATIVE) {
							OnPlayerNear(p, LOSMain.getRandom());
						}
					}
				}

			}
		}
		lifetime--;
		if (lifetime <= 0) {
			cancel();
		}
		center.add(direction.getX() * speed, direction.getY() * speed, direction.getZ() * speed);
	}

	protected void OnPlayerNear(Player p, Random random) {
	}
}
