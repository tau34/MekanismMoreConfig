package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tier.FactoryTier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = FactoryTier.class, remap = false)
public class FactoryTierMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private static int modifyProcess(int value) {
        return switch (value) {
            case 3 -> MMCConfig.getBasicProcesses();
            case 5 -> MMCConfig.getAdvancedProcesses();
            case 7 -> MMCConfig.getEliteProcesses();
            case 9 -> MMCConfig.getUltimateProcesses();
            default -> value;
        };
    }
}
