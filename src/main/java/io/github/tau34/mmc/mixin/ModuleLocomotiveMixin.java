package io.github.tau34.mmc.mixin;

import com.mojang.logging.LogUtils;
import io.github.tau34.mmc.module.ModifiedSprintSpeed;
import mekanism.api.gear.IModule;
import mekanism.api.gear.config.IModuleConfigItem;
import mekanism.api.gear.config.ModuleConfigItemCreator;
import mekanism.api.gear.config.ModuleEnumData;
import mekanism.common.MekanismLang;
import mekanism.common.content.gear.mekasuit.ModuleLocomotiveBoostingUnit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ModuleLocomotiveBoostingUnit.class, remap = false)
public class ModuleLocomotiveMixin {
    @Unique
    private IModuleConfigItem<ModifiedSprintSpeed> mss;

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void modifyBoostConfig(IModule<ModuleLocomotiveBoostingUnit> module,
                                   ModuleConfigItemCreator configItemCreator,
                                   CallbackInfo ci) {
        this.mss = configItemCreator.createConfigItem("sprint_boost",
                MekanismLang.MODULE_SPRINT_BOOST, new ModuleEnumData<>(ModifiedSprintSpeed.M01,
                        module.getInstalledCount() + 1));
        ci.cancel();
    }

    @Inject(method = "getBoost", at = @At("HEAD"), cancellable = true)
    private void modifyBoost(CallbackInfoReturnable<Float> ci) {
        ci.setReturnValue(mss.get().getBoost());
    }

    @Inject(method = "changeMode", at = @At("HEAD"), cancellable = true)
    private void modifyChangeMode(IModule<ModuleLocomotiveBoostingUnit> module, Player player, ItemStack stack,
                                  int shift, boolean displayChangeMessage, CallbackInfo ci) {
        ModifiedSprintSpeed currentMode = mss.get();
        ModifiedSprintSpeed newMode = currentMode.adjust(shift,
                v -> v.ordinal() < module.getInstalledCount() + 1);
        if (currentMode != newMode) {
            mss.set(newMode);
            if (displayChangeMessage) {
                module.displayModeChange(player, MekanismLang.MODULE_SPRINT_BOOST.translate(), newMode);
            }
        }
    }
}
