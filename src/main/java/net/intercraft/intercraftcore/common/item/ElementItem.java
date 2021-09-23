package net.intercraft.intercraftcore.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.intercraft.intercraftcore.common.IRegistryName;
import net.intercraft.intercraftcore.common.ItemGroups;
import net.intercraft.intercraftcore.common.element.Element;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElementItem extends Item implements IRegistryName
{
    private static final FabricItemSettings settings = new FabricItemSettings().group(ItemGroups.RESOURCES);

    private final Element element;
    private final String suffix;

    public String getRegistryName()
    {
        return element.getName() + "_" + suffix;
    }

    /**
     * Generic auto-generated item from an element.
     * @param element Element to create item from.
     * @param suffix The shape of the element.
     */
    public ElementItem(Element element, String suffix)
    {
        super(settings);
        this.element = element;
        this.suffix = suffix;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
    {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new LiteralText(element.getSymbol()));
    }
}