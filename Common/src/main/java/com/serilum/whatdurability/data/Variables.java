package com.serilum.whatdurability.data;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Variables {
	public static boolean askedServerIfIsInstalled = false;
	public static boolean isInstalledOnServer = true;
	public static String serverItemsWithDurability = null;

	public static String processedItemsWithDurability = "";
	public static Set<Identifier> itemIdsWithDurability = new HashSet<>();
	public static List<TagKey<Item>> itemTagsWithDurability = new ArrayList<>();
}
