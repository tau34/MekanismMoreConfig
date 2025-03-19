package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.capabilities.ModifiedDropperHandler;
import mekanism.common.capabilities.ItemCapabilityWrapper;
import mekanism.common.capabilities.merged.GaugeDropperContentsHandler;
import mekanism.common.item.ItemGaugeDropper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = ItemGaugeDropper.class, remap = false)
public class ItemGaugeDropperMixin {
    @Inject(method = "gatherCapabilities", at = @At("TAIL"))
    private void injectGatherCapabilities(List<ItemCapabilityWrapper.ItemCapability> capabilities, ItemStack stack, CompoundTag nbt, CallbackInfo info) {
        capabilities.removeIf(cap -> cap instanceof GaugeDropperContentsHandler);
        capabilities.add(ModifiedDropperHandler.create());
    }
}
