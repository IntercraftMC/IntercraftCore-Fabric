package net.intercraft.intercraftcore.common.element;

import static net.intercraft.intercraftcore.common.element.Element.*;

public class ElementFactory
{

    private int forms = INGOT | NUGGET | DUST | DUST_SMALL | PLATE | GEAR | ROD | RAW | VIAL_LIQUID | VIAL_GAS | BLOCK | FRAME | ORE_STONE | ORE_DEEPSLATE | RAW_BLOCK;;
    private final String name, symbol;
    private String[] composition;
    private int colorRefined, colorUnrefined;

    public ElementFactory(String name, String symbol)
    {
        this.name = name;
        this.symbol = symbol;
    }

    public Element create()
    {
        return new Element(name,symbol,composition,colorRefined,colorUnrefined,forms);
    }
    public ElementFactory composition(String...composition)
    {
        this.composition = composition;
        return this;
    }
    public ElementFactory forms(int forms)
    {
        this.forms = forms;
        return this;
    }
    public ElementFactory excludeForms(int excludeForms)
    {
        forms &= ~excludeForms;
        return this;
    }
    public ElementFactory colorRefined(int color)
    {
        colorRefined = color;
        return this;
    }
    public ElementFactory colorUnrefined(int color)
    {
        colorUnrefined = color;
        return this;
    }
}