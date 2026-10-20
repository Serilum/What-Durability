package com.serilum.whatdurability.mixin;

import com.serilum.whatdurability.util.Util;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemStack.class, priority = 1001)
public class ItemStackMixin {
	@Inject(method = "isDamaged()Z", at = @At(value = "HEAD"), cancellable = true)
	public void isDamaged(CallbackInfoReturnable<Boolean> cir) {
		if (Util.shouldRemoveDurability((ItemStack)(Object)this)) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "getDamageValue()I", at = @At(value = "HEAD"), cancellable = true)
	public void getDamageValue(CallbackInfoReturnable<Integer> cir) {
		if (Util.shouldRemoveDurability((ItemStack)(Object)this)) {
			cir.setReturnValue(0);
		}
	}

	@ModifyVariable(method = "setDamageValue(I)V", at = @At(value = "HEAD"), argsOnly = true)
	public int setDamageValue(int damage) {
		if (Util.shouldRemoveDurability((ItemStack)(Object)this)) {
			return 0;
		}
		return damage;
	}
}
