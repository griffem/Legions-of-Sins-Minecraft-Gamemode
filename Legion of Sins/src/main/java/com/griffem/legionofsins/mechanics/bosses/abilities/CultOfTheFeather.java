package com.griffem.legionofsins.mechanics.bosses.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class CultOfTheFeather extends Abilities {

    public CultOfTheFeather(List<Player> ps) {super(ps, false);}

    @Override
    public void playerCast(Player p) {
        p.getLocation().getBlock().setType(Material.FIRE);
        p.getLocation().add(1, 0, 0).getBlock().setType(Material.FIRE);
        p.getLocation().add(-1, 0, 0).getBlock().setType(Material.FIRE);
        p.getLocation().add(0, 0, 1).getBlock().setType(Material.FIRE);
        p.getLocation().add(0, 0, -1).getBlock().setType(Material.FIRE);
    }

    @Override
    public boolean cancelled() {
        return false;
    }
}
