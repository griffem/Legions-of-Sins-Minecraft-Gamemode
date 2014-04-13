package me.waternode.server.legion.of.sins.mechanics.bosses.abilities;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Emery
 * Date: 4/12/14
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class SewerSwarm extends Abilities implements Listener {
    // In Seconds
    private int duration;
    private World world;

    public SewerSwarm(ArrayList<Player> ps, int d) {
        super(ps, true);
        duration = d;
        world = ps.get(0).getWorld();

    }

    @Override
    public void PlayerCast(Player p) {
        if(!p.isSneaking()) {
            if (LOSMain.getRandom().nextBoolean()) {
                Location l = p.getLocation();

                l.setX(p.getLocation().getX() + (LOSMain.getRandom().nextInt(11) - 5));
                l.setY(p.getLocation().getY());
                l.setZ(p.getLocation().getZ() + (LOSMain.getRandom().nextInt(11) - 5));

                LivingEntity a = (LivingEntity) p.getLocation().getWorld().spawnEntity(l, EntityType.SILVERFISH);
            }
        }
    }

    @Override
    public boolean Cancelled() {
        duration--;
        if(duration == 0) {
            HandlerList.unregisterAll(this);
        }
        return duration == 0;
    }

    @EventHandler
    public void onPlayerDMG(EntityDamageByEntityEvent e) {
        if(e.getEntity().getWorld().getName().equalsIgnoreCase(world.getName())) {
            if(e.getEntity().getType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.SILVERFISH) {
                ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 1));
            }
        }
    }
}
