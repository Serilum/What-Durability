package com.serilum.whatdurability.events;

import com.natamus.collective.implementations.networking.api.Dispatcher;
import com.serilum.whatdurability.data.Variables;
import com.serilum.whatdurability.networking.packets.ToServerAskIfModIsInstalledPacket;
import net.minecraft.client.Minecraft;

public class DurabilityClientEvents {
	private static final Minecraft mc = Minecraft.getInstance();

	public static void onClientTick() {
		if (!Variables.askedServerIfIsInstalled) {
			if (mc.player == null || mc.getConnection() == null) {
				return;
			}

			Variables.askedServerIfIsInstalled = true;
			if (mc.hasSingleplayerServer()) {
				return;
			}

			Variables.isInstalledOnServer = false;

			try {
				Dispatcher.sendToServer(new ToServerAskIfModIsInstalledPacket());
			}
			catch (Exception ignored) { }
		}
		else if (mc.player == null) {
			Variables.askedServerIfIsInstalled = false;
			Variables.isInstalledOnServer = true;
			Variables.serverItemsWithDurability = null;
		}
	}
}
