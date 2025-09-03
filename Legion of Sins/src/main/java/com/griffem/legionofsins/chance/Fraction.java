package com.griffem.legionofsins.chance;

import com.griffem.legionofsins.LOSMain;

/**
 * Represents a simple probability fraction.
 */
public record Fraction(int numerator, int denominator) {

    public static final Fraction HALF = new Fraction(1, 2);
    public static final Fraction QUARTER = new Fraction(1, 4);

    /**
     * Rolls this fraction using the plugin's shared random instance.
     *
     * @return {@code true} when the roll succeeds
     */
    public boolean roll() {
        return LOSMain.getRandom().nextInt(denominator) < numerator;
    }
}
