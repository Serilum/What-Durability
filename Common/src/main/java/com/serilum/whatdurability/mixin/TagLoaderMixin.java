package com.serilum.whatdurability.mixin;

import com.serilum.whatdurability.util.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagLoader;
import net.minecraft.tags.TagManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mixin(value = TagLoader.class, priority = 1001)
public class TagLoaderMixin {
	@Shadow @Final private String directory;

	@Inject(method = "build(Ljava/util/Map;)Ljava/util/Map;", at = @At(value = "RETURN"))
	public void build(Map<ResourceLocation, List<TagLoader.EntryWithSource>> builders, CallbackInfoReturnable<Map<ResourceLocation, Collection<Object>>> cir) {
		if (!directory.equals(TagManager.getTagDir(Registries.ITEM))) {
			return;
		}

		// Tinkers' Construct only damages tools in this tag, and only shows their durability then.
		ResourceLocation tinkersDurabilityTag = new ResourceLocation("tconstruct", "modifiable/durability");

		Map<ResourceLocation, Collection<Object>> itemTags = cir.getReturnValue();
		Collection<Object> taggedItems = itemTags.get(tinkersDurabilityTag);
		if (taggedItems == null) {
			return;
		}

		itemTags.put(tinkersDurabilityTag, Util.getTaggedItemsThatKeepDurability(taggedItems, itemTags));
	}
}
