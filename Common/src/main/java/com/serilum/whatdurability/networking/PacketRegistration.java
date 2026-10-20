package com.serilum.whatdurability.networking;

import com.natamus.collective.implementations.networking.api.Network;
import com.serilum.whatdurability.networking.packets.ToClientConfirmModIsInstalledPacket;
import com.serilum.whatdurability.networking.packets.ToServerAskIfModIsInstalledPacket;

public class PacketRegistration {

	public void init() {
		initClientPackets();
		initServerPackets();
	}

	private void initClientPackets() {
		Network.registerPacket(ToClientConfirmModIsInstalledPacket.CHANNEL, ToClientConfirmModIsInstalledPacket.class, ToClientConfirmModIsInstalledPacket::encode, ToClientConfirmModIsInstalledPacket::decode, ToClientConfirmModIsInstalledPacket::handle);
	}

	private void initServerPackets() {
		Network.registerPacket(ToServerAskIfModIsInstalledPacket.CHANNEL, ToServerAskIfModIsInstalledPacket.class, ToServerAskIfModIsInstalledPacket::encode, ToServerAskIfModIsInstalledPacket::decode, ToServerAskIfModIsInstalledPacket::handle);
	}
}
