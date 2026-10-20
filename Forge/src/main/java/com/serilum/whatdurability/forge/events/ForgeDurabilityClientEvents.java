package com.serilum.whatdurability.forge.events;

import com.serilum.whatdurability.events.DurabilityClientEvents;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

public class ForgeDurabilityClientEvents {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeDurabilityClientEvents.class);

		TickEvent.ClientTickEvent.Pre.BUS.addListener(ForgeDurabilityClientEvents::onClientTick);
	}

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent.Pre e) {
		DurabilityClientEvents.onClientTick();
	}
}
