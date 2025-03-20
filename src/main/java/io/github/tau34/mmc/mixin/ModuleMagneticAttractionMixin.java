package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.module.ModifiedJumpBoost;
import io.github.tau34.mmc.module.ModifiedMagneticRange;
import io.github.tau34.mmc.module.ModifiedStepAssist;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.gear.IModule;
import mekanism.api.gear.config.IModuleConfigItem;
import mekanism.api.gear.config.ModuleConfigItemCreator;
import mekanism.api.gear.config.ModuleEnumData;
import mekanism.api.math.FloatingLong;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.gear.mekasuit.ModuleLocomotiveBoostingUnit;
import mekanism.common.content.gear.mekasuit.ModuleMagneticAttractionUnit;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = ModuleMagneticAttractionUnit.class, remap = false)
public abstract class ModuleMagneticAttractionMixin {
    @Shadow protected abstract void pullItem(Player player, ItemEntity item);

    @Unique
    private IModuleConfigItem<ModifiedMagneticRange> mmr;

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private void modifyInit(IModule<ModuleLocomotiveBoostingUnit> module,
                                   ModuleConfigItemCreator configItemCreator,
                                   CallbackInfo ci) {
        this.mmr = configItemCreator.createConfigItem("jump_boost", MekanismLang.MODULE_RANGE,
                new ModuleEnumData<>(ModifiedMagneticRange.MAX01, module.getInstalledCount() + 1));
        ci.cancel();
    }

    @Inject(method = "tickServer", at = @At("HEAD"), cancellable = true)
    private void modifyTickServer(IModule<ModuleMagneticAttractionUnit> module, Player player, CallbackInfo ci) {
        if (mmr.get() != ModifiedMagneticRange.OFF) {
            float size = 4 + mmr.get().getRange();
            FloatingLong usage = MekanismConfig.gear.mekaSuitEnergyUsageItemAttraction.get()
                    .multiply(mmr.get().getRange());
            boolean free = usage.isZero() || player.isCreative();
            IEnergyContainer energyContainer = free ? null : module.getEnergyContainer();
            if (free || (energyContainer != null && energyContainer.getEnergy().greaterOrEqual(usage))) {
                List<ItemEntity> items = player.level().getEntitiesOfClass(ItemEntity.class,
                        player.getBoundingBox().inflate(size, size, size), item -> !item.hasPickUpDelay());
                for (ItemEntity item : items) {
                    if (item.distanceTo(player) > 0.001) {
                        if (free) {
                            pullItem(player, item);
                        } else if (module.useEnergy(player, energyContainer, usage, true).isZero()) {
                            break;
                        } else {
                            pullItem(player, item);
                            if (energyContainer.getEnergy().smallerThan(usage)) {
                                break;
                            }
                        }

                    }
                }
            }
        }
        ci.cancel();
    }
}
