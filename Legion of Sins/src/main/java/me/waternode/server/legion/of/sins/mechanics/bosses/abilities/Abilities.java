package me.waternode.server.legion.of.sins.mechanics.bosses.abilities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ervin
 * on 4/12/2014
 */
public class Abilities extends BukkitRunnable {

    private ArrayList<Player> p = new ArrayList<Player>();
    private boolean constant;
    private int warning = 35;

    @Override
    public void run() {

        if(warning > 0 && warning != 25 ) {
            warning--;
            return;
        }

    }
}
