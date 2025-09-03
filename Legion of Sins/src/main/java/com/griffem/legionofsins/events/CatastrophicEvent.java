package com.griffem.legionofsins.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.griffem.legionofsins.LOSMain;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.logging.Logger;

/**
 * <p/>
 * Latest Change:
 * <p/>
 *
 * @author George
 * @since 11/04/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CatastrophicEvent extends Event implements Cancellable {
	private static final boolean debug = LOSMain.getDebug();
	private static final Logger log = LOSMain.getInstance().getLogger();

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	private final Integer livetime;
	private final Integer warningtime;

	@Override
	public HandlerList getHandlers() {
		if (debug) {
			log.info("getHandlers() was called in class com.griffem.legionofsins.events.CatastrophicEvent! It Normally Returns HandlerList!");
		}
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		if (debug) {
			log.info("isCancelled() was called in class com.griffem.legionofsins.events.CatastrophicEvent! It Normally Returns boolean!");
		}
		return cancelled;
	}

	@Override
	public void setCancelled(boolean b) {
		if (debug) {
			log.info("setCancelled() was called in class com.griffem.legionofsins.events.CatastrophicEvent! It Normally Returns void!");
		}
		this.cancelled = b;
	}
}
