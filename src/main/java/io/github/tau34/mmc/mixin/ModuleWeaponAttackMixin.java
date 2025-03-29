package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.module.ModifiedAttackDamage;
import io.github.tau34.mmc.module.ModifiedWAttackDamage;
import mekanism.api.gear.IModule;
import mekanism.api.gear.config.IModuleConfigItem;
import mekanism.api.gear.config.ModuleConfigItemCreator;
import mekanism.api.gear.config.ModuleEnumData;
import mekanism.api.radial.mode.NestedRadialMode;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.content.gear.mekasuit.ModuleLocomotiveBoostingUnit;
import mekanism.common.content.gear.mekatool.ModuleAttackAmplificationUnit;
import mekanism.common.content.gear.mekatool.ModuleExcavationEscalationUnit;
import meranha.mekaweapons.WeaponsLang;
import meranha.mekaweapons.items.ModuleWeaponAttackAmplificationUnit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(value = ModuleWeaponAttackAmplificationUnit.class, remap = false)
public abstract class ModuleWeaponAttackMixin {
    @Shadow protected abstract int getCurrentMaxDamage(ItemStack stack);

    @Unique
    private IModuleConfigItem<ModifiedWAttackDamage> mad;

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void modifyBoostConfig(IModule<ModuleLocomotiveBoostingUnit> module,
                                   ModuleConfigItemCreator configItemCreator,
                                   CallbackInfo ci) {
        this.mad = configItemCreator.createConfigItem("attack_damage", WeaponsLang.RADIAL_ATTACK_DAMAGE,
                new ModuleEnumData<>(ModifiedWAttackDamage.MAX01, module.getInstalledCount() + 2));
        ci.cancel();
    }

    @Inject(method = "addRadialModes", at = @At("HEAD"), cancellable = true)
    private void modifyRadialData(IModule<ModuleExcavationEscalationUnit> module, @NotNull ItemStack stack,
                                  Consumer<NestedRadialMode> adder, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "getCurrentUnit", at = @At("HEAD"), cancellable = true)
    private void modifyDamage(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(this.mad.get().ordinal());
    }

    @Inject(method = "addHUDStrings", at = @At("HEAD"), cancellable = true)
    private void modifyHUD(IModule<ModuleWeaponAttackAmplificationUnit> module, ItemStack stack, Player player,
                           Consumer<Component> hudStringAdder, CallbackInfo ci) {
        if (module.isEnabled()) {
            hudStringAdder.accept(MekanismLang.MODULE_DAMAGE.translateColored(EnumColor.DARK_GRAY,
                    EnumColor.INDIGO, this.getCurrentMaxDamage(stack)));
        }
        ci.cancel();
    }
}
