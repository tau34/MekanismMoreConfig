package io.github.tau34.mmc.mixin;

import com.mojang.logging.LogUtils;
import io.github.tau34.mmc.MMCConfig;
import mekanism.api.gear.EnchantmentBasedModule;
import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.ModuleData;
import mekanism.api.providers.IItemProvider;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.util.NonNullSupplier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.UnaryOperator;

@Mixin(value = ModuleDeferredRegister.class, remap = false)
public class ModuleDeferredRegisterMixin {
    @Inject(method = "register*", at = @At("HEAD"), cancellable = true)
    private <M extends ICustomModule<M>> void modifyUnit(
            String name, NonNullSupplier<M> supplier,
            IItemProvider itemProvider,
            UnaryOperator<ModuleData.ModuleDataBuilder<M>> builderModifier,
            CallbackInfoReturnable<ModuleRegistryObject<M>> cir) {
        if (name.equals("energy_unit")) {
            builderModifier = builder -> builder.maxStackSize(MMCConfig.getEnergyUnitStacks()).rarity(Rarity.UNCOMMON).noDisable();
            cir.setReturnValue(rmd(name, builderModifier, supplier, itemProvider));
        }
        if (name.equals("locomotive_boosting_unit")) {
            builderModifier = builder -> builder.maxStackSize(MMCConfig.getLocomotiveUnitStacks()).rarity(Rarity.RARE).handlesModeChange();
            cir.setReturnValue(rmd(name, builderModifier, supplier, itemProvider));
        }
        if (name.equals("attack_amplification_unit")) {
            builderModifier = builder -> builder.maxStackSize(MMCConfig.getAttackUnitStacks()).rarity(Rarity.UNCOMMON).rendersHUD();
            cir.setReturnValue(rmd(name, builderModifier, supplier, itemProvider));
        }
        if (name.equals("excavation_escalation_unit")) {
            builderModifier = builder -> builder.maxStackSize(MMCConfig.getExcavationUnitStacks()).rarity(Rarity.UNCOMMON).rendersHUD();
            cir.setReturnValue(rmd(name, builderModifier, supplier, itemProvider));
        }
        if (name.equals("solar_recharging_unit")) {
            builderModifier = builder -> builder.maxStackSize(64).rarity(Rarity.RARE);
            cir.setReturnValue(rmd(name, builderModifier, supplier, itemProvider));
        }
    }

    @Inject(method = "registerEnchantBased", at = @At("HEAD"), cancellable = true)
    private void modifyEnchantUnit(String name, NonNullSupplier<Enchantment> enchantment,
                                   IItemProvider itemProvider,
                                   UnaryOperator<ModuleData.ModuleDataBuilder<?>> builderModifier,
                                   CallbackInfoReturnable<ModuleRegistryObject<?>> cir) {
        if (name.equals("fortune_unit")) {
            builderModifier = builder -> builder.maxStackSize(MMCConfig.getFortuneUnitStacks()).rarity(Rarity.RARE).exclusive(ModuleData.ExclusiveFlag.OVERRIDE_DROPS);
            cir.setReturnValue(reb(name, builderModifier, enchantment, itemProvider));
        }
    }

    private <M extends ICustomModule<M>> ModuleRegistryObject<M> rmd(String name, UnaryOperator<ModuleData.ModuleDataBuilder<M>> builder,
                                                                     NonNullSupplier<M> supplier, IItemProvider itemProvider) {
        return ((ModuleDeferredRegister) (Object) this)
                .register(name, (ModuleData.ModuleDataBuilder)builder.apply(ModuleData.ModuleDataBuilder.custom(supplier, itemProvider)));
    }

    private ModuleRegistryObject<?> reb(String name, UnaryOperator<ModuleData.ModuleDataBuilder<?>> builder,
                                        NonNullSupplier<Enchantment> enchantment, IItemProvider itemProvider) {
        return ((ModuleDeferredRegister) (Object) this).
                register(name, builder.apply(ModuleData.ModuleDataBuilder.custom(() -> new EnchantmentBasedModule() {
            @NotNull
            @Override
            public Enchantment getEnchantment() {
                return enchantment.get();
            }
        }, itemProvider)));
    }
}
