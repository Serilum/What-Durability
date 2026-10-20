package com.serilum.whatdurability.networking.packets;

import com.natamus.collective.implementations.networking.api.Dispatcher;
import com.natamus.collective.implementations.networking.data.PacketContext;
import com.natamus.collective.implementations.networking.data.Side;
import com.serilum.whatdurability.config.ConfigHandler;
import com.serilum.whatdurability.util.Reference;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ToServerAskIfModIsInstalledPacket {
	public static final ResourceLocation CHANNEL = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "to_server_ask_if_mod_is_installed_packet");

	public ToServerAskIfModIsInstalledPacket() {
	}

	public static ToServerAskIfModIsInstalledPacket decode(FriendlyByteBuf buf) {
		return new ToServerAskIfModIsInstalledPacket();
	}

	public void encode(FriendlyByteBuf buf) {
	}

	public static void handle(PacketContext<ToServerAskIfModIsInstalledPacket> ctx) {
		if (ctx.side().equals(Side.SERVER)) {
			Player player = ctx.sender();

			Dispatcher.sendToClient(new ToClientConfirmModIsInstalledPacket(ConfigHandler.itemsWithDurability), (ServerPlayer)player);
		}
	}
}
