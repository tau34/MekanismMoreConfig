package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.chemical.infuse.IInfusionTank;
import mekanism.api.chemical.pigment.IPigmentTank;
import mekanism.common.tile.machine.TileEntityChemicalDissolutionChamber;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = TileEntityChemicalDissolutionChamber.class, remap = false)
public class ChemicalDissolutionMixin {
    @Redirect(
            method = "presetVariables",
            at = @At(
                    value = "INVOKE",
                    target = "Lmekanism/api/chemical/ChemicalTankBuilder;output(JLmekanism/api/IContentsListener;)Lmekanism/api/chemical/IChemicalTank;"
            )
    )
    private IChemicalTank redirectGasOutput(ChemicalTankBuilder instance, long capacity, @Nullable IContentsListener listener) {
        return instance.output(MMCConfig.INSTANCE.dissolutionOutputCapacity.get(), listener);
    }
}
