package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tile.machine.TileEntityFluidicPlenisher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TileEntityFluidicPlenisher.class, remap = false)
public class FluidicPlenisherMixin {
    @ModifyConstant(method = "getInitialFluidTanks", constant = @Constant(intValue = 10000))
    private int modifyFluidTankCapacity(int c) {
        return MMCConfig.INSTANCE.plenisherInputCapacity.get();
    }
}
