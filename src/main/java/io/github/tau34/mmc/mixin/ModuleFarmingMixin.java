package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.module.ModifiedFarmingRadius;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.gear.IModule;
import mekanism.api.gear.config.IModuleConfigItem;
import mekanism.api.gear.config.ModuleConfigItemCreator;
import mekanism.api.gear.config.ModuleEnumData;
import mekanism.common.MekanismLang;
import mekanism.common.content.gear.mekatool.ModuleFarmingUnit;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.StorageUtils;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.util.Lazy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ModuleFarmingUnit.class, remap = false)
public abstract class ModuleFarmingMixin {
    @Shadow protected abstract InteractionResult useAxeAOE(UseOnContext context, Lazy<BlockState> lazyClickedState, IEnergyContainer energyContainer, int diameter, ToolAction action, SoundEvent sound, int particle);

    @Shadow protected abstract InteractionResult flattenAOE(UseOnContext context, Lazy<BlockState> lazyClickedState, IEnergyContainer energyContainer, int diameter);

    @Shadow protected abstract InteractionResult dowseCampfire(UseOnContext context, Lazy<BlockState> lazyClickedState, IEnergyContainer energyContainer);

    @Shadow protected abstract InteractionResult tillAOE(UseOnContext context, Lazy<BlockState> lazyClickedState, IEnergyContainer energyContainer, int diameter);

    @Unique
    private IModuleConfigItem<ModifiedFarmingRadius> mfr;

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void modifyInit(IModule<ModuleFarmingUnit> module, ModuleConfigItemCreator configItemCreator,
                            CallbackInfo ci) {
        mfr = configItemCreator.createConfigItem("farming_radius", MekanismLang.MODULE_FARMING_RADIUS,
                new ModuleEnumData<>(ModifiedFarmingRadius.MAX01, module.getInstalledCount() + 1));
        ci.cancel();
    }

    @Inject(method = "onItemUse", at = @At("HEAD"), cancellable = true)
    private void modifyDiameter(IModule<ModuleFarmingUnit> module,
                                UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Player player = context.getPlayer();
        if (player == null || player.isShiftKeyDown()) {
            cir.setReturnValue(InteractionResult.PASS);
        }
        int diameter = mfr.get().getRadius();
        if (diameter == 0) {
            cir.setReturnValue(InteractionResult.PASS);
        }
        ItemStack stack = context.getItemInHand();
        IEnergyContainer energyContainer = StorageUtils.getEnergyContainer(stack, 0);
        if (energyContainer == null) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
        Lazy<BlockState> lazyClickedState = Lazy.of(() -> context.getLevel().getBlockState(context.getClickedPos()));
        cir.setReturnValue(MekanismUtils.performActions(
                useAxeAOE(context, lazyClickedState, energyContainer, diameter, ToolActions.AXE_STRIP, SoundEvents.AXE_STRIP, -1),
                () -> useAxeAOE(context, lazyClickedState, energyContainer, diameter, ToolActions.AXE_SCRAPE, SoundEvents.AXE_SCRAPE, LevelEvent.PARTICLES_SCRAPE),
                () -> useAxeAOE(context, lazyClickedState, energyContainer, diameter, ToolActions.AXE_WAX_OFF, SoundEvents.AXE_WAX_OFF, LevelEvent.PARTICLES_WAX_OFF),
                //Then as a shovel
                () -> flattenAOE(context, lazyClickedState, energyContainer, diameter),
                () -> dowseCampfire(context, lazyClickedState, energyContainer),
                //Finally, as a hoe
                () -> tillAOE(context, lazyClickedState, energyContainer, diameter)
        ));
    }
}
