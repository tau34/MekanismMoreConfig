package io.github.tau34.mmc.module;

import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;

public enum ModifiedFarmingRadius implements IHasTextComponent {
    OFF(0),
    MAX01(1),
    MAX02(3),
    MAX03(5),
    MAX04(7),
    MAX05(9),
    MAX06(11),
    MAX07(13),
    MAX08(15),
    MAX09(17),
    MAX10(19),
    MAX11(21),
    MAX12(23),
    MAX13(25),
    MAX14(27),
    MAX15(29),
    MAX16(31),
    MAX17(33),
    MAX18(35),
    MAX19(37),
    MAX20(39),
    MAX21(41),
    MAX22(43),
    MAX23(45),
    MAX24(47),
    MAX25(49),
    MAX26(51),
    MAX27(53),
    MAX28(55),
    MAX29(57),
    MAX30(59),
    MAX31(61),
    MAX32(63),
    MAX33(65),
    MAX34(67),
    MAX35(69),
    MAX36(71),
    MAX37(73),
    MAX38(75),
    MAX39(77),
    MAX40(79),
    MAX41(81),
    MAX42(83),
    MAX43(85),
    MAX44(87),
    MAX45(89),
    MAX46(91),
    MAX47(93),
    MAX48(95),
    MAX49(97),
    MAX50(99),
    MAX51(101),
    MAX52(103),
    MAX53(105),
    MAX54(107),
    MAX55(109),
    MAX56(111),
    MAX57(113),
    MAX58(115),
    MAX59(117),
    MAX60(119),
    MAX61(121),
    MAX62(123),
    MAX63(125),
    MAX64(127);

    private final int radius;
    private final Component label;

    ModifiedFarmingRadius(int damage) {
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
