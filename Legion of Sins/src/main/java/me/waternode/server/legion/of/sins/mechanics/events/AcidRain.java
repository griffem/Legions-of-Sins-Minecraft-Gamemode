package me.waternode.server.legion.of.sins.mechanics.events;

import me.waternode.server.legion.of.sins.LOSMain;
import me.waternode.server.legion.of.sins.mechanics.CatastrophicEvent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class AcidRain extends CatastrophicEvent {

	public AcidRain(Location c, double r, double s, int l, LOSMain m, boolean D3, World w, Vector d) {
		super(c, r, s, l, m, D3, w, d);
	}

	@Override
	public void OnPlayerNear(Player p) {
		World w = p.getWorld();
		Location pLoc = p.getLocation();
		Integer pX = (Integer) pLoc.getBlockX();
		Integer pZ = (Integer) pLoc.getBlockZ();
		if (w.getHighestBlockYAt(pX, pZ) - 5 <= pLoc.getY())
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 1));
		p.getWorld().playSound(p.getLocation(), Sound.AMBIENCE_RAIN, 0.5f, 1f);
	}
}
