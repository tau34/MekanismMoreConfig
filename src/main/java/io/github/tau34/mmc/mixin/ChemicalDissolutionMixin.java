package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tile.machine.TileEntityChemicalDissolutionChamber;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TileEntityChemicalDissolutionChamber.class, remap = false)
public class ChemicalDissolutionMixin {
    @ModifyConstant(method = "getInitialGasTanks", constant = @Constant(longValue = 10000L))
    private long modifyGasTankCapacity(long c) {
        return MMCConfig.INSTANCE.dissolutionInputCapacity.get();
    }

    @ModifyConstant(method = "presetVariables", constant = @Constant(longValue = 10000L))
    private long modifyChemicalTankCapacity(long c) {
        return MMCConfig.INSTANCE.dissolutionOutputCapacity.get();
    }
}
