package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.module.ModifiedAttackDamage;
import mekanism.api.gear.IModule;
import mekanism.api.gear.config.IModuleConfigItem;
import mekanism.api.gear.config.ModuleConfigItemCreator;
import mekanism.api.gear.config.ModuleEnumData;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.content.gear.mekasuit.ModuleLocomotiveBoostingUnit;
import mekanism.common.content.gear.mekatool.ModuleAttackAmplificationUnit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(value = ModuleAttackAmplificationUnit.class, remap = false)
public class ModuleAttackMixin {
    @Unique
    private IModuleConfigItem<ModifiedAttackDamage> mad;
    @Unique
    private int installed;

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void modifyBoostConfig(IModule<ModuleLocomotiveBoostingUnit> module,
                                   ModuleConfigItemCreator configItemCreator,
                                   CallbackInfo ci) {
        installed = module.getInstalledCount();
        this.mad = configItemCreator.createConfigItem("attack_damage", MekanismLang.MODULE_BONUS_ATTACK_DAMAGE,
                new ModuleEnumData<>(ModifiedAttackDamage.MED, installed + 2));
        ci.cancel();
    }

    @Inject(method = "getDamage", at = @At("HEAD"), cancellable = true)
    private void modifyDamage(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(this.mad.get().getDamage());
    }

    @Inject(method = "addHUDStrings", at = @At("HEAD"), cancellable = true)
    private void modifyHUD(IModule<ModuleAttackAmplificationUnit> module,
                           Player player, Consumer<Component> hudStringAdder, CallbackInfo ci) {
        if (module.isEnabled()) {
            hudStringAdder.accept(MekanismLang.MODULE_DAMAGE.translateColored(EnumColor.DARK_GRAY,
                    EnumColor.INDIGO, mad.get().getDamage()));
        }
        ci.cancel();
    }
}
