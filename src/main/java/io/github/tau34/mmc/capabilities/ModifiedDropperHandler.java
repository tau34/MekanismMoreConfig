package io.github.tau34.mmc.capabilities;

import io.github.tau34.mmc.MMCConfig;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.chemical.ChemicalTankBuilder;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.fluid.IMekanismFluidHandler;
import mekanism.common.capabilities.DynamicHandler;
import mekanism.common.capabilities.chemical.dynamic.DynamicChemicalHandler;
import mekanism.common.capabilities.chemical.variable.RateLimitChemicalTank;
import mekanism.common.capabilities.fluid.item.RateLimitFluidHandler;
import mekanism.common.capabilities.merged.MergedTank;
import mekanism.common.capabilities.merged.MergedTankContentsHandler;
import mekanism.common.capabilities.resolver.BasicCapabilityResolver;
import mekanism.common.capabilities.resolver.ICapabilityResolver;
import mekanism.common.util.ItemDataUtils;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@NothingNullByDefault
public class ModifiedDropperHandler extends MergedTankContentsHandler<MergedTank> implements IMekanismFluidHandler, IFluidHandlerItem {
    protected final List<IExtendedFluidTank> fluidTanks;

    public static ModifiedDropperHandler create() {
        return new ModifiedDropperHandler();
    }

    private ModifiedDropperHandler() {
        this.mergedTank = MergedTank.create(new RateLimitFluidHandler.RateLimitFluidTank(MMCConfig.INSTANCE.dropperFluidRate::get, MMCConfig.INSTANCE.dropperFluidCapacity::get, this),
                new RateLimitChemicalTank.RateLimitGasTank(() -> MMCConfig.INSTANCE.dropperGasRate.get(), () -> MMCConfig.INSTANCE.dropperGasCapacity.get(), ChemicalTankBuilder.GAS.alwaysTrueBi, ChemicalTankBuilder.GAS.alwaysTrueBi, ChemicalTankBuilder.GAS.alwaysTrue, null, this.gasHandler = new DynamicChemicalHandler.DynamicGasHandler((side) -> this.gasTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE, DynamicHandler.InteractPredicate.ALWAYS_TRUE, () -> this.onContentsChanged("GasTanks", this.gasTanks))),
                new RateLimitChemicalTank.RateLimitInfusionTank(() -> MMCConfig.INSTANCE.dropperInfusionRate.get(), () -> MMCConfig.INSTANCE.dropperInfusionCapacity.get(), ChemicalTankBuilder.INFUSION.alwaysTrueBi, ChemicalTankBuilder.INFUSION.alwaysTrueBi, ChemicalTankBuilder.INFUSION.alwaysTrue, this.infusionHandler = new DynamicChemicalHandler.DynamicInfusionHandler((side) -> this.infusionTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE, DynamicHandler.InteractPredicate.ALWAYS_TRUE, () -> this.onContentsChanged("InfusionTanks", this.infusionTanks))),
                new RateLimitChemicalTank.RateLimitPigmentTank(() -> MMCConfig.INSTANCE.dropperPigmentRate.get(), () -> MMCConfig.INSTANCE.dropperPigmentCapacity.get(), ChemicalTankBuilder.PIGMENT.alwaysTrueBi, ChemicalTankBuilder.PIGMENT.alwaysTrueBi, ChemicalTankBuilder.PIGMENT.alwaysTrue, this.pigmentHandler = new DynamicChemicalHandler.DynamicPigmentHandler((side) -> this.pigmentTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE, DynamicHandler.InteractPredicate.ALWAYS_TRUE, () -> this.onContentsChanged("PigmentTanks", this.pigmentTanks))),
                new RateLimitChemicalTank.RateLimitSlurryTank(() -> MMCConfig.INSTANCE.dropperSlurryRate.get(), () -> MMCConfig.INSTANCE.dropperSlurryCapacity.get(), ChemicalTankBuilder.SLURRY.alwaysTrueBi, ChemicalTankBuilder.SLURRY.alwaysTrueBi, ChemicalTankBuilder.SLURRY.alwaysTrue, this.slurryHandler = new DynamicChemicalHandler.DynamicSlurryHandler((side) -> this.slurryTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE, DynamicHandler.InteractPredicate.ALWAYS_TRUE, () -> this.onContentsChanged("SlurryTanks", this.slurryTanks))));
        this.fluidTanks = Collections.singletonList((this.mergedTank).getFluidTank());
    }

    protected void load() {
        super.load();
        ItemDataUtils.readContainers(this.getStack(), "FluidTanks", this.getFluidTanks((Direction)null));
    }

    public @NotNull List<IExtendedFluidTank> getFluidTanks(@Nullable Direction side) {
        return this.fluidTanks;
    }

    public void onContentsChanged() {
        this.onContentsChanged("FluidTanks", this.fluidTanks);
    }

    public @NotNull ItemStack getContainer() {
        return this.getStack();
    }

    protected void gatherCapabilityResolvers(Consumer<ICapabilityResolver> consumer) {
        super.gatherCapabilityResolvers(consumer);
        consumer.accept(BasicCapabilityResolver.constant(ForgeCapabilities.FLUID_HANDLER_ITEM, this));
    }
}