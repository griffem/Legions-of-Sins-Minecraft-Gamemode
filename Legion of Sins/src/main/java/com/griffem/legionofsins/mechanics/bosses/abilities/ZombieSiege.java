package com.griffem.legionofsins.mechanics.bosses.abilities;

import com.griffem.legionofsins.InfinitePotionEffect;
import com.griffem.legionofsins.LOSMain;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class ZombieSiege extends Abilities {

    public ZombieSiege(List<Player> ps) {
        super(ps, false);
    }

    @Override
    public void playerCast(Player p) {
        if(!p.isSneaking()) {
            if (LOSMain.getRandom().nextInt(20) == 0) {
                Location l = p.getLocation();

                l.setX(p.getLocation().getX() + (LOSMain.getRandom().nextInt(11) - 5));
                l.setY(p.getLocation().getY());
                l.setZ(p.getLocation().getZ() + (LOSMain.getRandom().nextInt(11) - 5));


                if(LOSMain.getRandom().nextBoolean()) {
                    for(int i = 0; i < LOSMain.getRandom().nextInt(5)+2; i++) {
                        l.add(1, 0, 0);
                        LivingEntity a = (LivingEntity) p.getLocation().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                        a.addPotionEffect(new InfinitePotionEffect(PotionEffectType.WEAKNESS, 2));
                    }
                } else {
                    for(int i = 0; i < LOSMain.getRandom().nextInt(5)+2; i++) {
                        l.add(0, 0, -1);
                        LivingEntity a = (LivingEntity) p.getLocation().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                        a.addPotionEffect(new InfinitePotionEffect(PotionEffectType.WEAKNESS, 2));
                    }
                }

            }
        }
    }

    @Override
    public boolean cancelled() {
        return false;
    }
}
