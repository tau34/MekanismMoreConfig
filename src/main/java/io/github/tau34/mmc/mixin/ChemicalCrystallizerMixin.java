package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tile.machine.TileEntityChemicalCrystallizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TileEntityChemicalCrystallizer.class, remap = false)
public class ChemicalCrystallizerMixin {
    @ModifyConstant(method = "presetVariables", constant = @Constant(longValue = 10000L))
    private long modifyChemicalTankCapacity(long c) {
        return MMCConfig.INSTANCE.crystallizerInputCapacity.get();
    }
}
