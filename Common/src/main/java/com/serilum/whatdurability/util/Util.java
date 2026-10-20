package com.serilum.whatdurability.util;

import com.serilum.whatdurability.config.ConfigHandler;
import com.serilum.whatdurability.data.Variables;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {
	public static boolean shouldRemoveDurability(ItemStack itemStack) {
		if (!Variables.isInstalledOnServer) {
			return false;
		}

		String itemsWithDurability = getItemsWithDurability();
		if (itemsWithDurability.isBlank()) {
			return true;
		}

		if (!itemsWithDurability.equals(Variables.processedItemsWithDurability)) {
			processItemsWithDurability(itemsWithDurability);
		}

		if (Variables.itemIdsWithDurability.contains(BuiltInRegistries.ITEM.getKey(itemStack.getItem()))) {
			return false;
		}

		for (TagKey<Item> itemTag : Variables.itemTagsWithDurability) {
			if (itemStack.is(itemTag)) {
				return false;
			}
		}

		return true;
	}

	public static String getItemsWithDurability() {
		if (Variables.serverItemsWithDurability != null) {
			return Variables.serverItemsWithDurability;
		}
		return ConfigHandler.itemsWithDurability;
	}

	private static void processItemsWithDurability(String itemsWithDurability) {
		Set<Identifier> itemIds = new HashSet<>();
		List<TagKey<Item>> itemTags = new ArrayList<>();

		for (String rawEntry : itemsWithDurability.split(",")) {
			String entry = rawEntry.strip();

			boolean isTag = entry.startsWith("#");
			if (isTag) {
				entry = entry.substring(1);
			}

			Identifier identifier = Identifier.tryParse(entry);
			if (identifier == null) {
				continue;
			}

			if (isTag) {
				itemTags.add(TagKey.create(Registries.ITEM, identifier));
			}
			else {
				itemIds.add(identifier);
			}
		}

		Variables.itemIdsWithDurability = itemIds;
		Variables.itemTagsWithDurability = itemTags;
		Variables.processedItemsWithDurability = itemsWithDurability;
	}
}
