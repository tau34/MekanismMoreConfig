package io.github.tau34.mmc.module;

import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;

public enum ModifiedJumpBoost implements IHasTextComponent {
    OFF(0),
    MAX01(0.5F),
    MAX02(1F),
    MAX03(1.5F),
    MAX04(2F),
    MAX05(2.5F),
    MAX06(3F),
    MAX07(3.5F),
    MAX08(4F),
    MAX09(4.5F),
    MAX10(5F),
    MAX11(5.5F),
    MAX12(6F),
    MAX13(6.5F),
    MAX14(7F),
    MAX15(7.5F),
    MAX16(8F),
    MAX17(8.5F),
    MAX18(9F),
    MAX19(9.5F),
    MAX20(10F),
    MAX21(10.5F),
    MAX22(11F),
    MAX23(11.5F),
    MAX24(12F),
    MAX25(12.5F),
    MAX26(13F),
    MAX27(13.5F),
    MAX28(14F),
    MAX29(14.5F),
    MAX30(15F),
    MAX31(15.5F),
    MAX32(16F),
    MAX33(16.5F),
    MAX34(17F),
    MAX35(17.5F),
    MAX36(18F),
    MAX37(18.5F),
    MAX38(19F),
    MAX39(19.5F),
    MAX40(20F),
    MAX41(20.5F),
    MAX42(21F),
    MAX43(21.5F),
    MAX44(22F),
    MAX45(22.5F),
    MAX46(23F),
    MAX47(23.5F),
    MAX48(24F),
    MAX49(24.5F),
    MAX50(25F),
    MAX51(25.5F),
    MAX52(26F),
    MAX53(26.5F),
    MAX54(27F),
    MAX55(27.5F),
    MAX56(28F),
    MAX57(28.5F),
    MAX58(29F),
    MAX59(29.5F),
    MAX60(30F),
    MAX61(30.5F),
    MAX62(31F),
    MAX63(31.5F),
    MAX64(32F);

    private final float boost;
    private final Component label;

    ModifiedJumpBoost(float boost) {
        this.boost = boost;
        this.label = TextComponentUtil.getString(Float.toString(boost));
    }

    public float getBoost() {
        return boost;
    }

    @Override
    public Component getTextComponent() {
        return label;
    }
}
