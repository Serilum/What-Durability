package com.serilum.whatdurability.networking.packets;

import com.natamus.collective.implementations.networking.data.PacketContext;
import com.natamus.collective.implementations.networking.data.Side;
import com.serilum.whatdurability.data.Variables;
import com.serilum.whatdurability.util.Reference;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;

public class ToClientConfirmModIsInstalledPacket {
	public static final Identifier CHANNEL = Identifier.fromNamespaceAndPath(Reference.MOD_ID, "to_client_confirm_mod_is_installed_packet");

	private final String itemsWithDurability;

	public ToClientConfirmModIsInstalledPacket(String itemsWithDurabilityIn) {
		this.itemsWithDurability = itemsWithDurabilityIn;
	}

	public static ToClientConfirmModIsInstalledPacket decode(FriendlyByteBuf buf) {
		String itemsWithDurabilityIn = buf.readUtf();

		return new ToClientConfirmModIsInstalledPacket(itemsWithDurabilityIn);
	}

	public void encode(FriendlyByteBuf buf) {
		buf.writeUtf(itemsWithDurability);
	}

	public static void handle(PacketContext<ToClientConfirmModIsInstalledPacket> ctx) {
		if (ctx.side().equals(Side.CLIENT)) {
			Variables.serverItemsWithDurability = ctx.message().itemsWithDurability;
			Variables.isInstalledOnServer = true;
		}
	}
}
