package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tile.machine.TileEntityRotaryCondensentrator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TileEntityRotaryCondensentrator.class, remap = false)
public class RotaryCondensentratorMixin {
    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 10000L))
    private long modifyChemicalTankCapacity(long c) {
        return MMCConfig.INSTANCE.rotaryGasCapacity.get();
    }

    @ModifyConstant(method = "getInitialFluidTanks", constant = @Constant(intValue = 10000))
    private int modifyFluidTankCapacity(int c) {
        return MMCConfig.INSTANCE.rotaryFluidCapacity.get();
    }
}
