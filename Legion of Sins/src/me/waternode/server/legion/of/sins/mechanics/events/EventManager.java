package me.waternode.server.legion.of.sins.mechanics.events;

import java.util.ArrayList;
import java.util.Random;
import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class EventManager extends BukkitRunnable
{
  LOSMain main;

  public EventManager(LOSMain p)
  {
    this.main = p;
  }

  public void run()
  {
    Random r = new Random();
    ArrayList<Player> ps = new ArrayList<Player>();
    for (Player p : this.main.getServer().getOnlinePlayers()) {
      if (p.getWorld().getName().contains("main")) {
        ps.add(p);
      }
    }
    if (ps.size() > 0) {
      Player p = (Player)ps.get(r.nextInt(ps.size()));
      int i = r.nextInt(5);
      if (i == 0)
        new AcidRain(p.getLocation(), 
          r.nextInt(101) + 50, r.nextInt(3) + 1, 100, 
          this.main, false, p.getWorld(), 
          new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
      else if (i == 1)
        new DustStorm(p.getLocation(), 
          r.nextInt(101) + 50, r.nextInt(3) + 1, 100, 
          this.main, false, p.getWorld(), 
          new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
      else if (i == 2)
        new ElectricalStorms(p.getLocation(), 
          r.nextInt(101) + 50, r.nextInt(3) + 1, 100, 
          this.main, false, p.getWorld(), 
          new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
      else if (i == 3)
        new MeteorShowers(p.getLocation(), 
          r.nextInt(101) + 50, r.nextInt(3) + 1, 100, 
          this.main, false, p.getWorld(), 
          new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
      else if (i == 4)
        new MonsterRaid(p.getLocation(), 
          r.nextInt(101) + 50, r.nextInt(3) + 1, 100, 
          this.main, false, p.getWorld(), 
          new Vector(r.nextInt(5) - 2, 0, r.nextInt(5) - 2)).runTaskTimer(this.main, 20L, 5L);
    }
  }
}
