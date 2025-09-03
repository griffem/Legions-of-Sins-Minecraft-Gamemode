package com.griffem.legionofsins.chance;

import lombok.Data;
import com.griffem.legionofsins.LOSMain;

/**
 * <p/>
 * Latest Change:
 * <p/>
 *
 * @author George
 * @since 11/04/14
 */
@Data
public class Fraction {
	private final Integer numerator;
	private final Integer denominator;
	/**
	 * Returns true if the probability is achieved
	 * <p>
	 *     Example: getChance(new Fraction(1,5);
	 *              Has 1 in 5 chance of returning true
	 * </p>
	 * @param fraction ~ The probability fraction
	 * @return true if the probability is achieved
	 */
	public static Boolean getChance(Fraction fraction) {
		return LOSMain.getRandom().nextInt(fraction.getDenominator()) <= fraction.getNumerator()-1;
	}
}
