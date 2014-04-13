package me.waternode.server.legion.of.sins.mechanics.bosses;

import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;

/**
 * Created by Ervin
 * on 4/13/2014
 */
public class Pig extends Bosses {
    public Pig(Bat b, ArrayList<Ability> abs, LOSMain p) {
        super(b, // the bat
                (LivingEntity) b.getWorld().spawnEntity(b.getLocation(), EntityType.PIG), // The boss mob
                abs, // The list of abiltiies
                5, // The cooldown between abiliity casting
                30, // the range
                p, // The main class
                true,
                100); // Floating is true
    }
}
