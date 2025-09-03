package com.griffem.legionofsins.mechanics.bosses;

import com.griffem.legionofsins.LOSMain;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;

/**
 * Created by Ervin
 * on 4/13/2014
 */
public class FinalBoss extends Bosses {
    public FinalBoss(LivingEntity b, LivingEntity boss, ArrayList<Ability> abs, LOSMain p) {
        super(b, // the bat
                boss, // The boss mob
                abs, // The list of abiltiies
                3, // The cooldown between abiliity casting
                30, // the range
                p, // The main class
                true,
                500); // Floating is true
    }
}
