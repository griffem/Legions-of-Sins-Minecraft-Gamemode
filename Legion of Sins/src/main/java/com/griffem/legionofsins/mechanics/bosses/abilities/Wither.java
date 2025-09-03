package com.griffem.legionofsins.mechanics.bosses.abilities;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

/**
 * Created by Ervin
 * on 4/12/2014
 */
public class Wither extends Abilities {

    public Wither(List<Player> ps) {super(ps, false);}

    @Override
    public void playerCast(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 120, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 120, 0));
    }

    @Override
    public boolean cancelled() {
        return false;
    }
}
