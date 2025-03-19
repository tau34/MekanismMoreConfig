package io.github.tau34.mmc.mixin;

import mekanism.api.gear.config.ModuleEnumData;
import mekanism.api.text.IHasTextComponent;
import mekanism.client.gui.element.custom.module.GuiModuleScreen;
import mekanism.common.content.gear.ModuleConfigItem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(targets = "mekanism.client.gui.element.custom.module.EnumToggle", remap = false)
public abstract class EnumToggleMixin<T extends Enum<T> & IHasTextComponent> {
    @Shadow protected abstract ModuleEnumData<T> getData();

    @Shadow @Final private ModuleConfigItem<T> data;

    @Shadow @Final private int optionDistance;

    @Shadow @Final private static float TEXT_SCALE;

    @Shadow @Final private static int BAR_START;

    @Inject(method = "renderForeground", at = @At("HEAD"), cancellable = true)
    private void modify(GuiGraphics guiGraphics, int mouseX, int mouseY, CallbackInfo ci) {
        List<T> options = getData().getEnums();
        int count = options.size();
        if (count > 6) {
            GuiModuleScreen parent = ((MiniElementMixin)this).getParent();
            int textColor = parent.screenTextColor();
            parent.drawTextWithScale(guiGraphics, data.getDescription(),
                    ((MiniElementMixin)this).invokeGetRelativeX() + 3,
                    ((MiniElementMixin)this).invokeGetRelativeY(), textColor, 0.8F);
            int min = 0;
            int max = count - 1;
            int q2 = (min + max) / 2;
            int q1 = (min + q2) / 2;
            int q3 = (max + q2) / 2;
            int[] indices = {min, q1, q2, q3, max};

            for (int index : indices) {
                int center = optionDistance * index;
                Component text = options.get(index).getTextComponent();

                int textWidth = parent.getStringWidth(text);
                float widthScaling = (textWidth / 2F) * TEXT_SCALE;
                float left = BAR_START + center - widthScaling;
                if (left < 0) {
                    left = 0;
                } else {
                    int mx = ((GuiModuleScreenMixin)parent).invokeGetScreenWidth() - 1;
                    int end = ((MiniElementMixin)this).getXPos() + (int) Math.ceil(left + textWidth * TEXT_SCALE);
                    if (end > mx) {
                        left -= end - mx;
                    }
                }
                parent.drawTextWithScale(guiGraphics, text, ((MiniElementMixin)this).invokeGetRelativeX() + left,
                        ((MiniElementMixin)this).invokeGetRelativeY() + 20, textColor, TEXT_SCALE);
            }
            ci.cancel();
        }
    }
}
