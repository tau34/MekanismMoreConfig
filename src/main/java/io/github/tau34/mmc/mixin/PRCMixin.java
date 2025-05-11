package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tile.machine.TileEntityPressurizedReactionChamber;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TileEntityPressurizedReactionChamber.class, remap = false)
public class PRCMixin {
    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 10000L, ordinal = 0))
    private long modifyInputTankCapacity(long c) {
        return MMCConfig.INSTANCE.prcGasInputCapacity.get();
    }

    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 10000L, ordinal = 1))
    private long modifyOutputTankCapacity(long c) {
        return MMCConfig.INSTANCE.prcGasOutputCapacity.get();
    }

    @ModifyConstant(method = "getInitialFluidTanks", constant = @Constant(intValue = 10000))
    private int modifyFluidTankCapacity(int c) {
        return MMCConfig.INSTANCE.prcFluidCapacity.get();
    }
}