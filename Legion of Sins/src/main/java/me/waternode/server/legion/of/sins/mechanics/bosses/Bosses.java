package me.waternode.server.legion.of.sins.mechanics.bosses;

import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Emery
 * Date: 4/12/14
 * Time: 9:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bosses extends BukkitRunnable {
    private ArrayList<Ability> abilities;
    private Bat bat;
    private LivingEntity boss;
    private int AbilityUse;
    private int AbilityUseCD;

    protected Bosses(Bat b, LivingEntity bo, ArrayList<Ability> abs, int au) {
        bat = b;
        boss = bo;
        abilities = abs;
        AbilityUse = au;
    }

    @Override
    public void run() {
        UseAbility();
        Movement();
    }

    private void UseAbility() {
         if(AbilityUseCD == 0) {
             if(AbilityUsage()) {
                 AbilityUseCD = AbilityUse;
             }
         } else {
             AbilityUseCD--;
         }
    }

    protected boolean AbilityUsage() {
        return false;
    }

    protected void Movement()
    {

    }
}
