package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.generators.common.content.fusion.FusionReactorMultiblockData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = FusionReactorMultiblockData.class, remap = false)
public class FusionReactorDataMixin {
    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 1.0E8))
    private double modifyTick(double constant) {
        return MMCConfig.INSTANCE.fusionIgnitionTemp.get();
    }

    @ModifyConstant(method = "burnFuel", constant = @Constant(doubleValue = 1.0E8))
    private double modifyBurnFuel(double constant) {
        return MMCConfig.INSTANCE.fusionIgnitionTemp.get();
    }

    @ModifyConstant(method = "getIgnitionTemperature", constant = @Constant(doubleValue = 1.0E8))
    private double modifyIgnitionTemp(double constant) {
        return MMCConfig.INSTANCE.fusionIgnitionTemp.get();
    }
}
