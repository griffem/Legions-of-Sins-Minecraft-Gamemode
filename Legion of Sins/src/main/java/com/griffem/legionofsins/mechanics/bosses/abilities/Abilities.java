package com.griffem.legionofsins.mechanics.bosses.abilities;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

/**
 * Created by Ervin
 * on 4/12/2014
 */
public class Abilities extends BukkitRunnable {

    private final List<Player> players;
    private final boolean constant;

    protected Abilities(List<Player> ps, boolean c) {
        this.players = List.copyOf(ps);
        this.constant = c;
    }

    @Override
    public void run() {
        if (cancelled()) {
            cancel();
            return;
        }
        for (Player p : players) {
            playerCast(p);
        }
        if (!constant) {
            cancel();
        }
    }

    protected void playerCast(Player p) {

    }

    protected boolean cancelled() {
        return false;
    }
}
