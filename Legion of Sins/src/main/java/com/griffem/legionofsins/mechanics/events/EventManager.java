package com.griffem.legionofsins.mechanics.events;

import com.griffem.legionofsins.LOSMain;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;

public class EventManager extends BukkitRunnable {

    private final LOSMain main;
    private final Set<Player> exceptions = new HashSet<>();
    private final List<BiFunction<Player, ThreadLocalRandom, CatastrophicEvent>> events;

    public EventManager(LOSMain p) {
        this.main = p;
        this.events = List.of(
            (player, rand) -> new AcidRain(player.getLocation(), rand.nextInt(101) + 50, rand.nextInt(3) + 1,
                player.getWorld(), new Vector(rand.nextInt(5) - 2, 0, rand.nextInt(5) - 2)),
            (player, rand) -> new DustStorm(player.getLocation(), rand.nextInt(101) + 50, rand.nextInt(3) + 1,
                player.getWorld(), new Vector(rand.nextInt(5) - 2, 0, rand.nextInt(5) - 2)),
            (player, rand) -> new ElectricalStorms(player.getLocation(), rand.nextInt(101) + 50, rand.nextInt(3) + 1,
                player.getWorld(), new Vector(rand.nextInt(5) - 2, 0, rand.nextInt(5) - 2)),
            (player, rand) -> new MeteorShowers(player.getLocation(), rand.nextInt(101) + 50, rand.nextInt(3) + 1,
                player.getWorld(), new Vector(rand.nextInt(5) - 2, 0, rand.nextInt(5) - 2)),
            (player, rand) -> new MonsterRaid(player.getLocation(), rand.nextInt(101) + 50, rand.nextInt(3) + 1,
                player.getWorld(), new Vector(rand.nextInt(5) - 2, 0, rand.nextInt(5) - 2))
        );
    }

    public void run() {
        ThreadLocalRandom r = LOSMain.getRandom();

        List<Player> ps = new ArrayList<>();
        for (Player player : main.getServer().getOnlinePlayers()) {
            if (player.getWorld().getName().toLowerCase().contains("main") && !exceptions.contains(player)) {
                ps.add(player);
            }
            exceptions.remove(player);
        }
        exceptions.removeIf(p -> !p.isOnline());

        if (ps.isEmpty()) return;

        Player p = ps.get(r.nextInt(ps.size()));

        events.get(r.nextInt(events.size())).apply(p, r).runTaskTimer(this.main, 20L, 5L);
    }

    public void addException(Player p) {
        exceptions.add(p);
    }
}
