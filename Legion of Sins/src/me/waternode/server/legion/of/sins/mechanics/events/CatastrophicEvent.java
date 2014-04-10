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

	protected Location center;
	protected double range;
	protected double speed;
	protected int lifetime;
	protected Vector direction;
	protected LOSMain main;
	protected boolean D3Dist;
	protected Random rand;
	protected World world;

	public CatastrophicEvent(Location c, double r, double s, int l, LOSMain m, boolean D3, World w, Vector d) {
		rand = new Random();
		center = c;
		range = r;
		speed = s;
		lifetime = l;
		main = m;
		D3Dist = D3;
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
							OnPlayerNear(p);
						}

					}
				} else {
					if (Math.pow(playerLocation.getX() - center.getX(), 2) + Math.pow(playerLocation.getZ() - center.getZ(), 2) <= Math.pow(range, 2)) {
						if (p.getGameMode() != GameMode.CREATIVE) {
							OnPlayerNear(p);
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

	public void OnPlayerNear(Player p) {
	}
}
