package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tile.machine.TileEntitySolarNeutronActivator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TileEntitySolarNeutronActivator.class, remap = false)
public class NeutronActivatorMixin {
    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 10000L, ordinal = 0))
    private long modifyInputTankCapacity(long c) {
        return MMCConfig.INSTANCE.activatorInputCapacity.get();
    }

    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 10000L, ordinal = 1))
    private long modifyOutputTankCapacity(long c) {
        return MMCConfig.INSTANCE.activatorOutputCapacity.get();
    }
}
