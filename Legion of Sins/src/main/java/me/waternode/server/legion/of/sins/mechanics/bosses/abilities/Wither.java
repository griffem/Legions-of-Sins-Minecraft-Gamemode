package me.waternode.server.legion.of.sins.mechanics.bosses.abilities;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

/**
 * Created by Ervin
 * on 4/12/2014
 */
public class Wither extends Abilities {

    public Wither(ArrayList<Player> ps) {super(ps, false);}

    @Override
    public void PlayerCast(Player p) {
        p.add(new InfinitePotionEffect(PotionEffectType.WITHER, 1));
        p.add(new InfinitePotionEffect(PotionEffectType.SLOW, 0));
        p.add(new InfinitePotionEffect(PotionEffectType.WEAKNESS, 0));
    }

    @Override
    public boolean Cancelled() {
        return false;
    }
}
