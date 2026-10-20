package com.serilum.whatdurability;

import com.serilum.whatdurability.config.ConfigHandler;
import com.serilum.whatdurability.networking.PacketRegistration;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();

		registerPackets();

		load();
	}

	private static void load() {

	}

	public static void registerPackets() {
		new PacketRegistration().init();
	}
}