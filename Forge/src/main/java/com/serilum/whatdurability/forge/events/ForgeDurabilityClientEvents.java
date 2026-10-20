package com.serilum.whatdurability.forge.events;

import com.serilum.whatdurability.events.DurabilityClientEvents;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeDurabilityClientEvents {
	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent e) {
		if (!e.phase.equals(TickEvent.Phase.START)) {
			return;
		}

		DurabilityClientEvents.onClientTick();
	}
}
