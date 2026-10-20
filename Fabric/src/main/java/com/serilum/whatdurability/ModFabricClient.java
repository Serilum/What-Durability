package com.serilum.whatdurability;

import com.natamus.collective.check.ShouldLoadCheck;
import com.serilum.whatdurability.events.DurabilityClientEvents;
import com.serilum.whatdurability.util.Reference;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		ModCommon.registerPackets();

		ClientTickEvents.START_CLIENT_TICK.register((Minecraft client) -> {
			DurabilityClientEvents.onClientTick();
		});
	}
}
