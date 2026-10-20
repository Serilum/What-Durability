package com.serilum.whatdurability.util;

import com.serilum.whatdurability.config.ConfigHandler;
import com.serilum.whatdurability.data.Variables;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class Util {
	public static boolean shouldRemoveDurability(ItemStack itemStack) {
		if (!Variables.isInstalledOnServer) {
			return false;
		}

		String itemsWithDurability = getItemsWithDurability();
		if (itemsWithDurability.isBlank()) {
			return true;
		}

		refreshItemsWithDurability(itemsWithDurability);

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

	public static List<Object> getTaggedItemsThatKeepDurability(List<Object> taggedItems, Map<Identifier, List<Object>> itemTags) {
		List<Object> itemsThatKeepDurability = new ArrayList<>();

		String itemsWithDurability = getItemsWithDurability();
		if (itemsWithDurability.isBlank()) {
			return itemsThatKeepDurability;
		}

		refreshItemsWithDurability(itemsWithDurability);

		for (Object taggedItem : taggedItems) {
			if (isListedWithDurability(taggedItem, itemTags)) {
				itemsThatKeepDurability.add(taggedItem);
			}
		}
		return itemsThatKeepDurability;
	}

	private static boolean isListedWithDurability(Object taggedItem, Map<Identifier, List<Object>> itemTags) {
		if (!(taggedItem instanceof Holder<?> itemHolder)) {
			return true;
		}

		Optional<? extends ResourceKey<?>> itemKey = itemHolder.unwrapKey();
		if (itemKey.isPresent() && Variables.itemIdsWithDurability.contains(itemKey.get().identifier())) {
			return true;
		}

		for (TagKey<Item> itemTag : Variables.itemTagsWithDurability) {
			List<Object> listedTagItems = itemTags.get(itemTag.location());
			if (listedTagItems != null && listedTagItems.contains(taggedItem)) {
				return true;
			}
		}
		return false;
	}

	private static void refreshItemsWithDurability(String itemsWithDurability) {
		if (!itemsWithDurability.equals(Variables.processedItemsWithDurability)) {
			processItemsWithDurability(itemsWithDurability);
		}
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
