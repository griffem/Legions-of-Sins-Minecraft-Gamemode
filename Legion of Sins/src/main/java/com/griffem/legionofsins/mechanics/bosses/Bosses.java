package com.griffem.legionofsins.mechanics.bosses;

import com.griffem.legionofsins.InfinitePotionEffect;
import com.griffem.legionofsins.LOSMain;
import org.bukkit.Location;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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
public class Bosses extends BukkitRunnable implements Listener {
    private ArrayList<Ability> abilities;
    private LivingEntity bat;
    private LivingEntity boss;
    private int AbilityUse;
    private int AbilityUseCD = 0;
    private int range;
    private LOSMain main;
    private boolean floating;


    protected Bosses(LivingEntity b, LivingEntity bo, ArrayList<Ability> abs, int au, int r, LOSMain p, boolean fl, int health) {
        bat = b;
        boss = bo;
        abilities = abs;
        AbilityUse = au*20;
        range = r;
        main = p;
        floating = fl;
        bat.setPassenger(boss);
        boss.setMaxHealth(health);
        boss.setHealth(health);
        bat.addPotionEffect(new InfinitePotionEffect(PotionEffectType.INVISIBILITY, 0));
        bat.addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, 3));
        bat.addPotionEffect(new InfinitePotionEffect(PotionEffectType.WEAKNESS, 6));
        bat.addPotionEffect(new InfinitePotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5));
        for(Ability ab : abilities) {
            ab.runTaskTimer(main, 0L, 20L);
        }
    }

    @Override
    public void run() {
        UseAbility();
        Movement();
        if(boss.isDead()) {
            for(Ability ab : abilities) {
                ab.cancel();
            }
            HandlerList.unregisterAll(this);
            this.cancel();
            bat.setHealth(0);
        }
    }

    private void UseAbility() {
         if(AbilityUseCD == 0) {
             ArrayList<Ability> abs = Abilities();
             if(abs.size() > 0) {
                 abs.get(LOSMain.getRandom().nextInt(abs.size())).Cast();
                 AbilityUseCD += AbilityUse;
             }
         } else {
             AbilityUseCD--;
         }
    }

    private ArrayList<Ability> Abilities() {
        ArrayList<Ability> abs = new ArrayList<Ability>();
        for(int i = 0; i < abilities.size(); i++) {
            if(abilities.get(i).GetState()) {
                abs.add(abilities.get(i));
            }
        }
        return abs;
    }

    private void Movement()
    {
        ArrayList<Player> ps = new ArrayList<Player>();
        for (Player p : main.getServer().getOnlinePlayers())
            if (p.getWorld().getName().equalsIgnoreCase(boss.getWorld().getName())) ps.add(p);
        if(ps.size() > 0) {
            Player nearest = ps.get(0);

            for(Player p : ps) {
                if(Math.pow(bat.getLocation().getX() - p.getLocation().getX(), 2.0D) + Math.pow(bat.getLocation().getZ() - p.getLocation().getZ(), 2.0D) > Math.pow(bat.getLocation().getX() - nearest.getLocation().getX(), 2.0D) + Math.pow(bat.getLocation().getZ() - nearest.getLocation().getZ(), 2.0D)) {
                    nearest = p;
                }
            }
            MovementAINearest(nearest);
        } else {
            for(Ability ab : abilities) {
                ab.cancel();
            }
            HandlerList.unregisterAll(this);
            this.cancel();
            bat.setHealth(0);
            boss.setHealth(0);
        }
    }

    @EventHandler
     public void onDMG(EntityDamageEvent e) {
        if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
            if(e.getEntity().equals(boss)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDMG(EntityDeathEvent e) {
        if(e.getEntity().equals(boss)) {
            for(Ability ab : abilities) {
                ab.cancel();
            }
            HandlerList.unregisterAll(this);
            this.cancel();
            bat.setHealth(0);
        }
    }

    private void MovementAINearest(Player p) {
        Location l = bat.getLocation();
        if(p.getLocation().getX() < bat.getLocation().getX()) {
            l.add(-0.5, 0, 0);
        } else {
            l.add(0.5, 0, 0);
        }

        if(p.getLocation().getZ() < bat.getLocation().getZ()) {
            l.add(0, 0, -0.5);
        } else {
            l.add(0, 0, 0.5);
        }

        if(floating) {
            if(p.getLocation().getY()+3 < bat.getLocation().getY()) {
                l.add(0, -0.5, 0);
            } else {
                l.add(0, 0.5, 0);
            }
        } else {
            if(p.getLocation().getY() < bat.getLocation().getY()) {
                l.add(0, -0.5, 0);
            } else {
                l.add(0, 0.5, 0);
            }
        }
        bat.teleport(l);
    }
}
