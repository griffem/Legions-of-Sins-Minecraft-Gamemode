package com.griffem.legionofsins.mechanics.bosses.abilities;

import com.griffem.legionofsins.LOSMain;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiFunction;

public enum AbilityType {
    CULTOFTHEFEATHER((ps, m) -> new CultOfTheFeather(ps)),
    SEWERSWARM((ps, m) -> new SewerSwarm(ps, 10, m)),
    SMITE((ps, m) -> new Smite(ps)),
    WITHER((ps, m) -> new Wither(List.of(ps.get(LOSMain.getRandom().nextInt(ps.size()))))),
    ZOMBIESIEGE((ps, m) -> new ZombieSiege(List.of(ps.get(LOSMain.getRandom().nextInt(ps.size())))));

    private final BiFunction<List<Player>, LOSMain, Abilities> factory;

    AbilityType(BiFunction<List<Player>, LOSMain, Abilities> factory) {
        this.factory = factory;
    }

    public Abilities getValue(List<Player> ps, LOSMain main) {
        return factory.apply(ps, main);
    }
}
