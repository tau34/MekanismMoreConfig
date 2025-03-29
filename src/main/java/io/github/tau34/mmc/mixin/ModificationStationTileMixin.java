package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.common.tile.TileEntityModificationStation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileEntityModificationStation.class, remap = false)
public class ModificationStationTileMixin {
    @Shadow public int ticksRequired;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void modifyTicks(BlockPos pos, BlockState state, CallbackInfo ci) {
        this.ticksRequired = MMCConfig.INSTANCE.modificationSpeed.get();
    }
}
