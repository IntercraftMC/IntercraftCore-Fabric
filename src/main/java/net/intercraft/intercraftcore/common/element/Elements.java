package net.intercraft.intercraftcore.common.element;

import static net.intercraft.intercraftcore.common.element.Element.*;

/**
 * List of all elements and its properties.
 */

public class Elements
{
    /**
     * Groupings of forms
     */
    private static final short
            ORES = ORE_STONE | ORE_DEEPSLATE | RAW | RAW_BLOCK,// Ores and its produce
            NONSOLID = VIAL_GAS | VIAL_LIQUID,// Solid at room temperature
            UNSTABLE = GEAR | BLOCK | FRAME | PLATE,// Elements too unstable/unsuited to make components out of due to strength or reactivity
            OVERLAP_VANILLA = INGOT | NUGGET | BLOCK | ORES;// Vanilla counterparts

    /**
     * Metals
     */
    public static final Element
            ALUMINIUM = new ElementFactory("aluminium", "Al")
                    .excludeForms(NONSOLID)
                    .colorRefined(0xd8f0ff)
                    .colorUnrefined(0x996100)
                    .create(),
            COPPER = new ElementFactory("copper", "Cu")
                    .excludeForms(OVERLAP_VANILLA | NONSOLID)
                    .colorRefined(0xc1834b)
                    .colorUnrefined(0x5e9c59)
                    .create(),
            GOLD = new ElementFactory("gold", "Au")
                    .excludeForms(OVERLAP_VANILLA | NONSOLID)
                    .colorRefined(0xe7e74c)
                    .colorUnrefined(0xe7e74c)
                    .create(),
            IRIDIUM = new ElementFactory("iridium", "Ir")
                    .excludeForms(NONSOLID)
                    .colorRefined(0xffffff)
                    .colorUnrefined(0xf0ffd8)
                    .create(),
            IRON = new ElementFactory("iron", "Fe")
                    .excludeForms(OVERLAP_VANILLA | NONSOLID)
                    .colorRefined(0xe5dddd)
                    .create(),
            LEAD = new ElementFactory("lead", "Pb")
                    .excludeForms(NONSOLID)
                    .colorRefined(0x664c86)
                    .colorUnrefined(0xc1c2c5)
                    .create(),
            LITHIUM = new ElementFactory("lithium", "Li")
                    .excludeForms(UNSTABLE | NONSOLID)
                    .colorRefined(0xdfe3ee)
                    .colorUnrefined(0xc1c2cf)
                    .create(),
            MERCURY = new ElementFactory("mercury", "Hg")
                    .forms(NONSOLID)
                    .colorRefined(0xe8e9ee)
                    .colorUnrefined(0xdc122a)
                    .create(),
            NICKEL = new ElementFactory("nickel", "Ni")
                    .excludeForms(NONSOLID)
                    .colorRefined(0xF6E3A7)
                    .colorUnrefined(0x996100)
                    .create(),
            SILVER = new ElementFactory("silver", "Ag")
                    .excludeForms(NONSOLID)
                    .colorRefined(0xffffff)
                    .colorUnrefined(0xdaf7f8)
                    .create(),
            THORIUM = new ElementFactory("thorium", "Th")
                    .excludeForms(NONSOLID | GEAR)
                    .colorRefined(0x333)
                    .colorUnrefined(0x444818)
                    .create(),
            TIN = new ElementFactory("tin", "Sn")
                    .excludeForms(NONSOLID)
                    .colorRefined(0x9daaae)
                    .colorUnrefined(0xc7bdc4)
                    .create(),
            TITANIUM = new ElementFactory("titanium", "Ti")
                    .excludeForms(NONSOLID | ORES)
                    .colorRefined(0xE8E9EE)
                    .create(),
            TUNGSTEN = new ElementFactory("tungsten", "W")
                    .excludeForms(NONSOLID)
                    .colorRefined(0x1d2630)
                    .colorUnrefined(0x5e5454)
                    .create(),
            URANIUM = new ElementFactory("uranium", "U")
                    .excludeForms(NONSOLID | GEAR)
                    .colorRefined(0x077a07)
                    .colorUnrefined(0xaeb559)
                    .create(),
            ZINC = new ElementFactory("zinc", "Zn")
                    .excludeForms(NONSOLID | GEAR)
                    .colorRefined(0xaecfd8)
                    .colorUnrefined(0xaeb5ca)
                    .create();

    /**
     * Non-Metals
     */
    public static final Element
            CARBON = new ElementFactory("carbon", "C")
                    .excludeForms(ORES | VIAL_GAS | GEAR)
                    .colorRefined(0x000000)
                    .colorUnrefined(0x000000)
                    .create(),
            SILICON = new ElementFactory("silicon", "Si")
                    .excludeForms(VIAL_GAS | GEAR)
                    .colorRefined(0x000000)
                    .colorUnrefined(0x000000)
                    .create();

    /**
     * Alloys
     */
    public static final Element
            BRASS = new ElementFactory("brass", "CuZn")
                    .excludeForms(ORES | NONSOLID)
                    .colorRefined(0xc8a23c)
                    .composition("Cu","Zn")
                    .create(),
            BRONZE = new ElementFactory("bronze", "CuSn")
                    .excludeForms(ORES | NONSOLID)
                    .colorRefined(0xffa23c)
                    .composition("Cu","Sn")
                    .create(),
            ELECTRUM = new ElementFactory("electrum", "AgAu")
                    .excludeForms(ORES | NONSOLID)
                    .colorRefined(0xffe05c)
                    .composition("Ag","Au")
                    .create(),
            STEEL = new ElementFactory("steel", "FeC")
                    .excludeForms(ORES | NONSOLID)
                    .colorRefined(0x6b6d74)
                    .composition("Fe","C")
                    .create();
}