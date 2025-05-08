package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.IChemicalTank;
import mekanism.common.tile.machine.TileEntityChemicalWasher;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = TileEntityChemicalWasher.class, remap = false)
public class ChemicalWasherMixin {
    @Redirect(
            method = "getInitialSlurryTanks",
            at = @At(
                    value = "INVOKE",
                    target = "Lmekanism/api/chemical/ChemicalTankBuilder;output(JLmekanism/api/IContentsListener;)Lmekanism/api/chemical/IChemicalTank;"
            )
    )
    private IChemicalTank redirectGasOutput(ChemicalTankBuilder instance, long capacity, @Nullable IContentsListener listener) {
        return instance.output(MMCConfig.INSTANCE.washerOutputCapacity.get(), listener);
    }
}
