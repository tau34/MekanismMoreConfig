package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.module.ModifiedBlastRadius;
import mekanism.api.gear.IModule;
import mekanism.api.gear.config.IModuleConfigItem;
import mekanism.api.gear.config.ModuleConfigItemCreator;
import mekanism.api.gear.config.ModuleEnumData;
import mekanism.api.radial.mode.NestedRadialMode;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.content.gear.mekatool.ModuleAttackAmplificationUnit;
import mekanism.common.content.gear.mekatool.ModuleBlastingUnit;
import mekanism.common.content.gear.mekatool.ModuleExcavationEscalationUnit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(value = ModuleBlastingUnit.class, remap = false)
public class ModuleBlastingMixin {
    @Unique
    private IModuleConfigItem<ModifiedBlastRadius> mbr;

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void modifyInit(IModule<ModuleExcavationEscalationUnit> module,
                            ModuleConfigItemCreator configItemCreator, CallbackInfo ci) {
        mbr = configItemCreator.createConfigItem("excavation_mode", MekanismLang.MODULE_BLAST_RADIUS,
                new ModuleEnumData<>(ModifiedBlastRadius.MAX01, module.getInstalledCount() + 1));
        ci.cancel();
    }

    @Inject(method = "addRadialModes", at = @At("HEAD"), cancellable = true)
    private void modifyRadialData(IModule<ModuleExcavationEscalationUnit> module, @NotNull ItemStack stack,
                                  Consumer<NestedRadialMode> adder, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "getBlastRadius", at = @At("HEAD"), cancellable = true)
    private void modifyMode(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(mbr.get().getRadius());
    }

    @Inject(method = "addHUDStrings", at = @At("HEAD"), cancellable = true)
    private void modifyHUD(IModule<ModuleAttackAmplificationUnit> module,
                           Player player, Consumer<Component> hudStringAdder, CallbackInfo ci) {
        if (module.isEnabled()) {
            hudStringAdder.accept(MekanismLang.MODULE_BLASTING_ENABLED.translateColored(EnumColor.DARK_GRAY,
                    EnumColor.INDIGO, mbr.get().getRadius()));
        }
        ci.cancel();
    }
}
