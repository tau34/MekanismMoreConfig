package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tile.machine.TileEntityAntiprotonicNucleosynthesizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TileEntityAntiprotonicNucleosynthesizer.class, remap = false)
public class AntiprotonicNucleosynthesizerMixin {
    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 10000L))
    private long modifyChemicalTankCapacity(long c) {
        return MMCConfig.INSTANCE.nucleosynthesizerInputCapacity.get();
    }
}
