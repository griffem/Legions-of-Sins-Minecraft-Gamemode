package me.waternode.server.legion.of.sins.mechanics.events;

import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class EventManager extends BukkitRunnable {
	LOSMain main;

	public EventManager(LOSMain p) {
		this.main = p;
	}

	public void run() {
		Random r = new Random();
		ArrayList<Player> ps = new ArrayList<Player>();
		for (Player p : this.main.getServer().getOnlinePlayers()) {
			if (p.getWorld().getName().contains("main")) {
				ps.add(p);
			}
		}
		if (ps.size() <= 0) return;
		Player p = (Player) ps.get(r.nextInt(ps.size()));
		int i = r.nextInt(5);
		switch (i) {
			case 0:
				new AcidRain(p.getLocation(),
						r.nextInt(101) + 50, r.nextInt(3) + 1, 100,
						this.main, false, p.getWorld(),
						new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
				break;
			case 1:
				new DustStorm(p.getLocation(),
						r.nextInt(101) + 50, r.nextInt(3) + 1, 100,
						this.main, false, p.getWorld(),
						new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
				break;
			case 2:
				new ElectricalStorms(p.getLocation(),
						r.nextInt(101) + 50, r.nextInt(3) + 1, 100,
						this.main, false, p.getWorld(),
						new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
				break;
			case 3:
				new MeteorShowers(p.getLocation(),
						r.nextInt(101) + 50, r.nextInt(3) + 1, 100,
						this.main, false, p.getWorld(),
						new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
				break;
			case 4:
				new MonsterRaid(p.getLocation(),
						r.nextInt(101) + 50, r.nextInt(3) + 1, 100,
						this.main, false, p.getWorld(),
						new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
				break;
			default:
				break;

		}
	}
}
