package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.module.ModifiedAttackDamage;
import io.github.tau34.mmc.module.ModifiedJumpBoost;
import io.github.tau34.mmc.module.ModifiedStepAssist;
import mekanism.api.gear.IModule;
import mekanism.api.gear.config.IModuleConfigItem;
import mekanism.api.gear.config.ModuleConfigItemCreator;
import mekanism.api.gear.config.ModuleEnumData;
import mekanism.common.MekanismLang;
import mekanism.common.content.gear.mekasuit.ModuleHydraulicPropulsionUnit;
import mekanism.common.content.gear.mekasuit.ModuleLocomotiveBoostingUnit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ModuleHydraulicPropulsionUnit.class, remap = false)
public class ModulePropulsionMixin {
    @Unique
    private IModuleConfigItem<ModifiedJumpBoost> mjb;
    @Unique
    private IModuleConfigItem<ModifiedStepAssist> msa;

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void modifyBoostConfig(IModule<ModuleLocomotiveBoostingUnit> module,
                                   ModuleConfigItemCreator configItemCreator,
                                   CallbackInfo ci) {
        this.mjb = configItemCreator.createConfigItem("jump_boost", MekanismLang.MODULE_JUMP_BOOST,
                new ModuleEnumData<>(ModifiedJumpBoost.MAX01, module.getInstalledCount() + 1));
        this.msa = configItemCreator.createConfigItem("step_assist", MekanismLang.MODULE_STEP_ASSIST,
                new ModuleEnumData<>(ModifiedStepAssist.MAX01, module.getInstalledCount() + 1));
        ci.cancel();
    }

    @Inject(method = "getBoost", at = @At("HEAD"), cancellable = true)
    private void modifyBoost(CallbackInfoReturnable<Float> ci) {
        ci.setReturnValue(this.mjb.get().getBoost());
    }

    @Inject(method = "getStepHeight", at = @At("HEAD"), cancellable = true)
    private void modifyStepHeight(CallbackInfoReturnable<Float> ci) {
        ci.setReturnValue(msa.get().getHeight());
    }
}
