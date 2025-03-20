package io.github.tau34.mmc.module;

import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;

public enum ModifiedMagneticRange implements IHasTextComponent {
    OFF(0),
    MAX01(1F),
    MAX02(2F),
    MAX03(3F),
    MAX04(4F),
    MAX05(5F),
    MAX06(6F),
    MAX07(7F),
    MAX08(8F),
    MAX09(9F),
    MAX10(10F),
    MAX11(11F),
    MAX12(12F),
    MAX13(13F),
    MAX14(14F),
    MAX15(15F),
    MAX16(16F),
    MAX17(17F),
    MAX18(18F),
    MAX19(19F),
    MAX20(20F),
    MAX21(21F),
    MAX22(22F),
    MAX23(23F),
    MAX24(24F),
    MAX25(25F),
    MAX26(26F),
    MAX27(27F),
    MAX28(28F),
    MAX29(29F),
    MAX30(30F),
    MAX31(31F),
    MAX32(32F),
    MAX33(33F),
    MAX34(34F),
    MAX35(35F),
    MAX36(36F),
    MAX37(37F),
    MAX38(38F),
    MAX39(39F),
    MAX40(40F),
    MAX41(41F),
    MAX42(42F),
    MAX43(43F),
    MAX44(44F),
    MAX45(45F),
    MAX46(46F),
    MAX47(47F),
    MAX48(48F),
    MAX49(49F),
    MAX50(50F),
    MAX51(51F),
    MAX52(52F),
    MAX53(53F),
    MAX54(54F),
    MAX55(55F),
    MAX56(56F),
    MAX57(57F),
    MAX58(58F),
    MAX59(59F),
    MAX60(60F),
    MAX61(61F),
    MAX62(62F),
    MAX63(63F),
    MAX64(64F);

    private final float range;
    private final Component label;

    ModifiedMagneticRange(float boost) {
        this.range = boost;
        this.label = TextComponentUtil.getString(Float.toString(boost));
    }

    public float getRange() {
        return range;
    }

    @Override
    public Component getTextComponent() {
        return label;
    }
}
