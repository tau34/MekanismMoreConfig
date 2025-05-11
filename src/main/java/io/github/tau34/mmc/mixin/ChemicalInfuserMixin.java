package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tile.machine.TileEntityChemicalInfuser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TileEntityChemicalInfuser.class, remap = false)
public class ChemicalInfuserMixin {
    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 10000L, ordinal = 0))
    private long modifyLeftTankCapacity(long c) {
        return MMCConfig.INSTANCE.chemicalInfuserLeftCapacity.get();
    }

    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 10000L, ordinal = 1))
    private long modifyRightTankCapacity(long c) {
        return MMCConfig.INSTANCE.chemicalInfuserRightCapacity.get();
    }

    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 10000L, ordinal = 2))
    private long modifyCenterTankCapacity(long c) {
        return MMCConfig.INSTANCE.chemicalInfuserOutputCapacity.get();
    }
}
