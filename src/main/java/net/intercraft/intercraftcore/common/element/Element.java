package net.intercraft.intercraftcore.common.element;

import net.minecraft.block.Material;

public class Element
{
    public static final short
            INGOT         = 1,
            NUGGET        = 1<<1,
            DUST          = 1<<2,
            DUST_SMALL    = 1<<3,
            PLATE         = 1<<4,
            GEAR          = 1<<5,
            ROD           = 1<<6,
            RAW           = 1<<7,
            VIAL_LIQUID   = 1<<8,
            VIAL_GAS      = 1<<9,

            BLOCK         = 1<<10,
            FRAME         = 1<<11,
            ORE_STONE     = 1<<12,
            ORE_DEEPSLATE = 1<<13,
            RAW_BLOCK     = 1<<14;


    public final int forms;

    private final String name, symbol;
    private final String[] composition;
    private final int colorRefined, colorUnrefined;



    public Element(String name, String symbol, String[] composition, int colorRefined, int colorUnrefined, int forms) {
        this.name = name;
        this.symbol = symbol;
        this.composition = composition;
        this.colorRefined = colorRefined;
        this.colorUnrefined = colorUnrefined;
        //forms &= ~excludeForms;
        this.forms = forms;
    }

    /**
     * Instantiate a element and automatically exclude forms from melting- and boiling point (k).
     */
    /*public Element(float meltingPoint, float boilingPoint)
    {

    }*/



    /**
     * Name of the element.
     */
    public String getName()
    {
        return name;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public String[] getComposition()
    {
        return composition;
    }

    public int getColorRefined()
    {
        return colorRefined;
    }

    public int getColorUnrefined()
    {
        return colorUnrefined;
    }

    public String toStringComposition()
    {
        String t = "";
        for (String i : composition) {
            t += i;
        }
        return t;
    }

    /**
     * Material type. Override to change.
     */
    public Material getMaterial()
    {
        return Material.METAL;
    }
    /**
     * Strength of the block. Override to change.
     */
    public float getStrength()
    {
        return 4.0f;
    }
}
