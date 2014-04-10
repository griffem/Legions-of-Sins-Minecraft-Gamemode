package me.waternode.server.legion.of.sins.mechanics.events;

import me.waternode.server.legion.of.sins.mechanics.CatastrophicEvent;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class DustStorm extends CatastrophicEvent {

	public DustStorm(Location c, double r, double s, World w, Vector d) {
		super(c, r, s, w, d);
	}

	@Override
	public void OnPlayerNear(Player p) {
		World w = p.getWorld();
		Location pLoc = p.getLocation();
		int pX = pLoc.getBlockX();
		int pZ = pLoc.getBlockZ();
		if (w.getHighestBlockYAt(pX, pZ) - 5 > pLoc.getY()) return;
		p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 120, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 120, 1));
		p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 0);
		p.getWorld().playSound(p.getLocation(), Sound.FIZZ, 0.5f, 1f);
		p.getWorld().playEffect(p.getLocation().add(0, 0, 1), Effect.SMOKE, 0);
		p.getWorld().playEffect(p.getLocation().add(0, 0, -1), Effect.SMOKE, 0);
		p.getWorld().playEffect(p.getLocation().add(1, 0, 0), Effect.SMOKE, 0);
		p.getWorld().playEffect(p.getLocation().add(-1, 0, 0), Effect.SMOKE, 0);
	}
}
