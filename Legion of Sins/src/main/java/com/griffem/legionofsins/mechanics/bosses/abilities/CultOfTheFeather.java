package com.griffem.legionofsins.mechanics.bosses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Emery
 * Date: 4/12/14
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class CultOfTheFeather extends Abilities {

    public CultOfTheFeather(ArrayList<Player> ps) {super(ps, false);}

    @Override
    public void PlayerCast(Player p) {
        p.getLocation().getBlock().setType(Material.FIRE);
        p.getLocation().add(1, 0, 0).getBlock().setType(Material.FIRE);
        p.getLocation().add(-1, 0, 0).getBlock().setType(Material.FIRE);
        p.getLocation().add(0, 0, 1).getBlock().setType(Material.FIRE);
        p.getLocation().add(0, 0, -1).getBlock().setType(Material.FIRE);
    }

    @Override
    public boolean Cancelled() {
        return false;
    }
}
