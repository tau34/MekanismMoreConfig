package io.github.tau34.mmc.mixin;

import io.github.tau34.mmc.MMCConfig;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.ChemicalType;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.merged.BoxedChemicalStack;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryStack;
import mekanism.api.recipes.ChemicalCrystallizerRecipe;
import mekanism.api.recipes.ChemicalDissolutionRecipe;
import mekanism.api.recipes.FluidSlurryToSlurryRecipe;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.ingredients.creator.IItemStackIngredientCreator;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.client.MekanismClient;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.impl.*;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Mixin(value = MekanismRecipeType.class, remap = false)
public class MekanismRecipeTypeMixin<R extends MekanismRecipe, IC extends IInputRecipeCache> {
    @Shadow private List<R> cachedRecipes;

    @Inject(method = "getRecipes", at = @At("HEAD"), cancellable = true)
    private void modifyRecipes(@Nullable Level world, CallbackInfoReturnable<List<R>> cir) {
        if (world == null) {
            if (FMLEnvironment.dist.isClient()) {
                world = MekanismClient.tryGetClientWorld();
            } else {
                world = ServerLifecycleHooks.getCurrentServer().overworld();
            }

            if (world == null) {
                cir.setReturnValue(Collections.emptyList());
            }
        }

        if (this.cachedRecipes.isEmpty()) {
            RecipeManager recipeManager = world.getRecipeManager();
            List<R> recipes = recipeManager.getAllRecipesFor((MekanismRecipeType<R, IC>)(Object)this);
            if ((Object)this == MekanismRecipeType.SMELTING.get()) {
                recipes = new ArrayList<>(recipes);

                for(SmeltingRecipe smeltingRecipe : recipeManager.getAllRecipesFor(RecipeType.SMELTING)) {
                    ItemStack recipeOutput = smeltingRecipe.getResultItem(world.registryAccess());
                    if (!smeltingRecipe.isSpecial() && !smeltingRecipe.isIncomplete() && !recipeOutput.isEmpty()) {
                        NonNullList<Ingredient> ingredients = smeltingRecipe.getIngredients();
                        if (!ingredients.isEmpty()) {
                            IItemStackIngredientCreator ingredientCreator = IngredientCreatorAccess.item();
                            ItemStackIngredient input = ingredientCreator.from(ingredients.stream().map(ingredientCreator::from));
                            recipes.add((R) new SmeltingIRecipe(smeltingRecipe.getId(), input, recipeOutput));
                        }
                    }
                }
            }

            this.cachedRecipes = recipes.stream().filter((recipe) -> !recipe.isIncomplete()).map(this::replaceRecipe).toList();
        }

        cir.setReturnValue(this.cachedRecipes);
    }

    private R replaceRecipe(R r) {
        if (r instanceof ChemicalDissolutionRecipe cdr) {
            ChemicalStack<?> stack = cdr.getOutput(ItemStack.EMPTY, GasStack.EMPTY).getChemicalStack();
            if (stack.getType() instanceof Slurry s) {
                SlurryStack output = new SlurryStack(s, stack.getAmount() / 5 * MMCConfig.INSTANCE.quintupling1stMult.get());
                return (R) new ChemicalDissolutionIRecipe(cdr.getId(), cdr.getItemInput(), cdr.getGasInput(), output);
            } else {
                return r;
            }
        } else if (r instanceof FluidSlurryToSlurryRecipe cwr) {
            SlurryStack stack = cwr.getOutput(FluidStack.EMPTY, SlurryStack.EMPTY);
            SlurryStack output = new SlurryStack(stack.getType(), MMCConfig.INSTANCE.quintupling2ndMult.get());
            return (R) new FluidSlurryToSlurryIRecipe(cwr.getId(), cwr.getFluidInput(), cwr.getChemicalInput(), output);
        } else if (r instanceof ChemicalCrystallizerRecipe ccr) {
            if (ccr.getInput() instanceof ChemicalStackIngredient.SlurryStackIngredient) {
                ItemStack stack = ccr.getOutput(BoxedChemicalStack.EMPTY);
                ItemStack output = new ItemStack(stack.getItem(), MMCConfig.INSTANCE.quintupling3rdMult.get());
                return (R) new ChemicalCrystallizerIRecipe(ccr.getId(), ccr.getInput(), output);
            } else {
                return r;
            }
        } else {
            return r;
        }
    }
}
