package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tile.machine.TileEntityElectrolyticSeparator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TileEntityElectrolyticSeparator.class, remap = false)
public class ElectrolyticSeparatorMixin {
    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 2400L, ordinal = 0))
    private long modifyLeftTankCapacity(long c) {
        return MMCConfig.INSTANCE.separatorLeftCapacity.get();
    }

    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 2400L, ordinal = 1))
    private long modifyRightTankCapacity(long c) {
        return MMCConfig.INSTANCE.separatorRightCapacity.get();
    }

    @ModifyConstant(method = "getInitialFluidTanks", constant = @Constant(intValue = 24000))
    private int modifyFluidTankCapacity(int c) {
        return MMCConfig.INSTANCE.separatorInputCapacity.get();
    }
}
