package com.serilum.whatdurability.mixin;

import com.serilum.whatdurability.util.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(value = TagLoader.class, priority = 1001)
public class TagLoaderMixin {
	@Shadow @Final private String directory;

	@Inject(method = "build(Ljava/util/Map;)Ljava/util/Map;", at = @At(value = "RETURN"))
	public void build(Map<Identifier, List<TagLoader.EntryWithSource>> builders, CallbackInfoReturnable<Map<Identifier, List<Object>>> cir) {
		if (!directory.equals(Registries.tagsDirPath(Registries.ITEM))) {
			return;
		}

		// Tinkers' Construct only damages tools in this tag, and only shows their durability then.
		Identifier tinkersDurabilityTag = Identifier.fromNamespaceAndPath("tconstruct", "modifiable/durability");

		Map<Identifier, List<Object>> itemTags = cir.getReturnValue();
		List<Object> taggedItems = itemTags.get(tinkersDurabilityTag);
		if (taggedItems == null) {
			return;
		}

		itemTags.put(tinkersDurabilityTag, Util.getTaggedItemsThatKeepDurability(taggedItems, itemTags));
	}
}
