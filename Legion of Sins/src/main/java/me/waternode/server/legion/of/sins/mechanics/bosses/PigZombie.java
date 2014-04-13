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
public class PigZombie extends Bosses {
    public PigZombie(Bat b, LivingEntity boss, ArrayList<Ability> abs, LOSMain p) {
        super(b, // the bat
                boss, // The boss mob
                abs, // The list of abiltiies
                5, // The cooldown between abiliity casting
                30, // the range
                p, // The main class
                false,
                75); // Floating is true
    }
}
