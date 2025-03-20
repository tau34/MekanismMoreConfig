package io.github.tau34.mmc.module;

import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;

public enum ModifiedBlastRadius implements IHasTextComponent {
    OFF(0),
    MAX01(1),
    MAX02(2),
    MAX03(3),
    MAX04(4),
    MAX05(5),
    MAX06(6),
    MAX07(7),
    MAX08(8),
    MAX09(9),
    MAX10(10),
    MAX11(11),
    MAX12(12),
    MAX13(13),
    MAX14(14),
    MAX15(15),
    MAX16(16),
    MAX17(17),
    MAX18(18),
    MAX19(19),
    MAX20(20),
    MAX21(21),
    MAX22(22),
    MAX23(23),
    MAX24(24),
    MAX25(25),
    MAX26(26),
    MAX27(27),
    MAX28(28),
    MAX29(29),
    MAX30(30),
    MAX31(31),
    MAX32(32),
    MAX33(33),
    MAX34(34),
    MAX35(35),
    MAX36(36),
    MAX37(37),
    MAX38(38),
    MAX39(39),
    MAX40(40),
    MAX41(41),
    MAX42(42),
    MAX43(43),
    MAX44(44),
    MAX45(45),
    MAX46(46),
    MAX47(47),
    MAX48(48),
    MAX49(49),
    MAX50(50),
    MAX51(51),
    MAX52(52),
    MAX53(53),
    MAX54(54),
    MAX55(55),
    MAX56(56),
    MAX57(57),
    MAX58(58),
    MAX59(59),
    MAX60(60),
    MAX61(61),
    MAX62(62),
    MAX63(63),
    MAX64(64);

    private final int radius;
    private final Component label;

    ModifiedBlastRadius(int damage) {
        this.radius = damage;
        this.label = TextComponentUtil.getString(Integer.toString(damage));
    }

    @Override
    public Component getTextComponent() {
        return label;
    }

    public int getRadius() {
        return radius;
    }
}
