package com.serilum.whatdurability.config;

import com.natamus.collective.config.DuskConfig;
import com.serilum.whatdurability.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<>();

	@Entry(registry = "item") public static String itemsWithDurability = "";

	public static void initConfig() {
		configMetaData.put("itemsWithDurability", Arrays.asList(
			"Items that keep their durability, separated by a comma. Item tags work too, starting with a '#'. For example: 'minecraft:elytra,#minecraft:trimmable_armor'. When the mod is installed on a server, the list of the server is used."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}
