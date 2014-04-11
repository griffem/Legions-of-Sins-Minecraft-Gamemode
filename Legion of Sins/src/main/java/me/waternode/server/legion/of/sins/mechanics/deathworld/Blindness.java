package me.waternode.server.legion.of.sins.mechanics.deathworld;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Ervin
 * on 4/11/2014
 */
public class Blindness implements Listener {

    //better way to tell if player is in a world?
    public void onPJE(PlayerJoinEvent event) {
        if(event.getPlayer().getWorld().getName().contains("deathworld")) {
            Player p = (Player) event.getPlayer();
            p.addPotionEffect(new InfinitePotionEffect(PotionEffectType.BLINDNESS, 1));
        }
    }
}
