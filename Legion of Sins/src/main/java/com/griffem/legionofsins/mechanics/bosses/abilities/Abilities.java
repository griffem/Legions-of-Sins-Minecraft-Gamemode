package com.griffem.legionofsins.mechanics.bosses.abilities;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by Ervin
 * on 4/12/2014
 */
public class Abilities extends BukkitRunnable {

    private ArrayList<Player> players;
    private boolean constant;

    protected Abilities(ArrayList<Player> ps, boolean c) {
        players = ps;
        constant = c;
    }

    @Override
    public void run() {
        if(Cancelled()) {
            this.cancel();
        }
        for(Player p : players) {
            PlayerCast(p);
        }
        if(!constant) {
            this.cancel();
        }
    }

    protected void PlayerCast(Player p) {

    }

    protected boolean Cancelled() {
        return true;
    }
}
