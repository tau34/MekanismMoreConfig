package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.api.heat.HeatAPI;
import mekanism.api.heat.IHeatCapacitor;
import mekanism.common.capabilities.heat.ITileHeatHandler;
import mekanism.common.util.EnumUtils;
import mekanism.common.util.WorldUtils;
import mekanism.generators.common.content.fusion.FusionReactorMultiblockData;
import mekanism.generators.common.tile.fusion.TileEntityFusionReactorPort;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(value = FusionReactorMultiblockData.class, remap = false)
public class FusionDataMixin {
    @Shadow @Final private Set<ITileHeatHandler> heatHandlers;

    @Shadow public IHeatCapacitor heatCapacitor;

    @Inject(method = "simulate", at = @At("RETURN"), cancellable = true)
    private void modifyHeat(CallbackInfoReturnable<HeatAPI.HeatTransfer> cir) {
        if (MMCConfig.INSTANCE.isFusionHeatedByLava.get()) {
            HeatAPI.HeatTransfer ht = cir.getReturnValue();
            double at = ht.adjacentTransfer() + heat();
            cir.setReturnValue(new HeatAPI.HeatTransfer(at, ht.environmentTransfer()));
        }
    }

    private double heat() {
        double r = 0.0F;
        for (ITileHeatHandler handler : heatHandlers) {
            if (handler instanceof TileEntityFusionReactorPort port) {
                for (Direction d : EnumUtils.DIRECTIONS) {
                    FluidState state = WorldUtils.getFluidState(port.getLevel(), port.getBlockPos().relative(d)).get();
                    if (state.is(FluidTags.LAVA)) {
                        r += Math.max(0, 1300.0D - heatCapacitor.getHeat()) / 10.0D;
                        heatCapacitor.handleHeat(r);
                    }
                }
            }
        }
        return r;
    }
}
