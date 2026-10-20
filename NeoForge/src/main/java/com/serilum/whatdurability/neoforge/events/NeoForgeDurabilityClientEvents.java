package com.serilum.whatdurability.neoforge.events;

import com.serilum.whatdurability.events.DurabilityClientEvents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

public class NeoForgeDurabilityClientEvents {
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent.Pre e) {
		DurabilityClientEvents.onClientTick();
	}
}
