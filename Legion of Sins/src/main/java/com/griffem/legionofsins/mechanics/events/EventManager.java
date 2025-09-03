package com.griffem.legionofsins.mechanics.events;

import com.griffem.legionofsins.LOSMain;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager extends BukkitRunnable {
	private final LOSMain main;
    public ArrayList<Player> exceptions = new ArrayList<Player>();

	public EventManager(LOSMain p) {
		this.main = p;
	}

	public void run() {
		Random r = new Random();
		// Use lowest interface possible
        ArrayList<Player> ps = new ArrayList<Player>();
		for (Player p : main.getServer().getOnlinePlayers()) {
            if (p.getWorld().getName().toLowerCase().contains("main") && !exceptions.contains(p)) ps.add(p);
            if (exceptions.contains(p)) exceptions.remove(p);
        }

		if (ps.size() <= 0) return;

		Player p = ps.get(r.nextInt(ps.size()));
		int i = r.nextInt(8);

        switch (i) {
            case 0:
                new AcidRain(p.getLocation(),
                                r.nextInt(101) + 50, r.nextInt(3) + 1,
                                p.getWorld(),
                                new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
                break;
            case 1:
                new DustStorm(p.getLocation(),
                        r.nextInt(101) + 50, r.nextInt(3) + 1,
                        p.getWorld(),
                        new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
                        break;
            case 2:
                new ElectricalStorms(p.getLocation(),
                       r.nextInt(101) + 50, r.nextInt(3) + 1,
                       p.getWorld(),
                       new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
                   break;
            case 3:
                new MeteorShowers(p.getLocation(),
                       r.nextInt(101) + 50, r.nextInt(3) + 1,
                       p.getWorld(),
                       new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
                   break;
            case 4:
                new MonsterRaid(p.getLocation(),
                        r.nextInt(101) + 50, r.nextInt(3) + 1,
                        p.getWorld(),
                        new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
                break;
        }
	}
}
