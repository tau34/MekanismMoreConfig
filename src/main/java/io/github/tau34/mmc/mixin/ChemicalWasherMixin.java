package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.IChemicalTank;
import mekanism.common.tile.machine.TileEntityChemicalWasher;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = TileEntityChemicalWasher.class, remap = false)
public class ChemicalWasherMixin {
    @ModifyConstant(method = "getInitialSlurryTanks", constant = @Constant(longValue = 10000L, ordinal = 0))
    private long modifyInputTankCapacity(long c) {
        return MMCConfig.INSTANCE.washerInputCapacity.get();
    }

    @ModifyConstant(method = "getInitialSlurryTanks", constant = @Constant(longValue = 10000L, ordinal = 1))
    private long modifyOutputTankCapacity(long c) {
        return MMCConfig.INSTANCE.washerOutputCapacity.get();
    }

    @ModifyConstant(method = "getInitialFluidTanks", constant = @Constant(intValue = 10000))
    private int modifyFluidTankCapacity(int c) {
        return MMCConfig.INSTANCE.washerFluidCapacity.get();
    }
}
