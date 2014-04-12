package me.waternode.server.legion.of.sins.mechanics.deathworld;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Ervin
 * on 4/11/2014
 */
public class Blindness implements Listener {

    //better way to tell if player is in a world?
    @EventHandler
    public void onPJE(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(p.getWorld().getName().contains("deathworld")) {
            p.addPotionEffect(new InfinitePotionEffect(PotionEffectType.BLINDNESS, 1));
        } else if(p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
            p.removePotionEffect(PotionEffectType.BLINDNESS);
        }
    }

    @EventHandler
    public void onTP(PlayerTeleportEvent event) {
        Player p = event.getPlayer();
        if(event.getTo().getWorld().getName().contains("deathworld")) {
            p.addPotionEffect(new InfinitePotionEffect(PotionEffectType.BLINDNESS, 1));
        } else if(p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
            p.removePotionEffect(PotionEffectType.BLINDNESS);
        }
    }
}
