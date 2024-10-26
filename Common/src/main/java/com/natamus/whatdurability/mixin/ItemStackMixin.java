package com.natamus.whatdurability.mixin;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemStack.class, priority = 1001)
public class ItemStackMixin {
	@Inject(method = "isDamaged()Z", at = @At(value = "HEAD"), cancellable = true)
	public void isDamaged(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}

	@Inject(method = "getDamageValue()I", at = @At(value = "HEAD"), cancellable = true)
	public void getDamageValue(CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(0);
	}

	@Inject(method = "setDamageValue(I)V", at = @At(value = "HEAD"))
	public void setDamageValue(int damage, CallbackInfo ci) {
		ItemStack itemStack = (ItemStack)(Object)this;
		itemStack.set(DataComponents.DAMAGE, 0);
	}
}
