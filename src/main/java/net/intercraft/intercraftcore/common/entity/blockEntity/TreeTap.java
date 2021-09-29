package net.intercraft.intercraftcore.common.entity.blockEntity;

import net.intercraft.intercraftcore.common.BlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TreeTap extends BlockEntity
{
    public static final short MAX_VOLUME = 255;

    private byte amount = -128;// -128 <-> 127
    public boolean canFill = false;


    public TreeTap(BlockPos pos, BlockState state)
    {
        super(BlockEntityTypes.TREETAP,pos,state);
    }

    public void tick(World world, BlockPos pos, BlockState state)
    {

    }

    public int getAmount()
    {
        return 128 + amount;// Signed -> unsigned
    }

    public void setAmount(int amount)
    {
        this.amount = (byte) Math.max(Math.min(amount -128,127),-128);// Full range of unsigned byte
    }

    public void fill(int n)
    {
        if (n > 0) {
            // TODO Spawn drop particle.
            setAmount(this.amount + Math.min(n, MAX_VOLUME - getAmount()));
        }
    }

    public void drain(int n)
    {
        if (n > 0) {
            setAmount(this.amount - Math.min(n, getAmount()));
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt)
    {
        super.writeNbt(nbt);
        nbt.putByte("amount",amount);
        nbt.putBoolean("can_fill",canFill);
        return nbt;
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        amount = nbt.getByte("amount");
        canFill = nbt.getBoolean("can_fill");
    }
}