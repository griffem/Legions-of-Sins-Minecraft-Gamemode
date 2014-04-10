package me.waternode.server.legion.of.sins;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * <p/>
 * Latest Change:
 * <p/>
 *
 * @author George
 * @since 10/04/14
 */
public class InfinitePotionEffect extends PotionEffect {
	public InfinitePotionEffect(PotionEffectType type, int amplifier) {
		super(type, Integer.MAX_VALUE, amplifier, false);
	}
}
