package me.waternode.server.legion.of.sins.mechanics.bosses;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Emery
 * Date: 4/12/14
 * Time: 9:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Witch extends Bosses {
    public Witch(Bat b, LivingEntity boss, ArrayList<Ability> abs, LOSMain p) {
        super(b, // the bat
                boss, // The boss mob
                abs, // The list of abiltiies
                5, // The cooldown between abiliity casting
                30, // the range
                p, // The main class
                true,
                50); // Floating is true
    }
}
