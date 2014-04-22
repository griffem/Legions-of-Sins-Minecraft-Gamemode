package me.waternode.server.legion.of.sins.mechanics.events;

import me.waternode.server.legion.of.sins.InfinitePotionEffect;
import me.waternode.server.legion.of.sins.LOSMain;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;

public class MonsterRaid extends CatastrophicEvent {
	public MonsterRaid(Location c, double r, double s, World w, Vector d) {
		super(c, r, s, w, d, "Brace the sneak, the zombie army sieges.");
	}

	@Override
	public void OnPlayerNear(Player p, Random random) {
        if(!p.isSneaking()) {
            if (LOSMain.getRandom().nextInt(10) == 0) {
                Location l = new Location(this.world, this.range, this.range, this.range);

                l.setX(p.getLocation().getX() + (random.nextInt(15) - 7));
                l.setY(p.getLocation().getY());
                l.setZ(p.getLocation().getZ() + (random.nextInt(15) - 7));

                //Making mob spawn to the side? Add potion effects like weakness or slowness?
                LivingEntity a = (LivingEntity) p.getLocation().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                a.addPotionEffect(new InfinitePotionEffect(PotionEffectType.WEAKNESS, 2));
            }
        }
	}
}