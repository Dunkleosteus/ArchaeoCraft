package com.dunkleosteus.archaeocraft.Tools;

import java.util.Set;

import com.dunkleosteus.archaeocraft.ArchaeoHarvestLevel;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArchaeoPickaxe extends ItemTool {

	private boolean canTill;
    private static final Set EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.activator_rail, Blocks.coal_ore, Blocks.cobblestone, Blocks.detector_rail, Blocks.diamond_block, Blocks.diamond_ore, Blocks.double_stone_slab, Blocks.golden_rail, Blocks.gold_block, Blocks.gold_ore, Blocks.ice, Blocks.iron_block, Blocks.iron_ore, Blocks.lapis_block, Blocks.lapis_ore, Blocks.lit_redstone_ore, Blocks.mossy_cobblestone, Blocks.netherrack, Blocks.packed_ice, Blocks.rail, Blocks.redstone_ore, Blocks.sandstone, Blocks.red_sandstone, Blocks.stone, Blocks.stone_slab});


	public ItemArchaeoPickaxe(ToolMaterial material, boolean canTill) {
		super(2, material, EFFECTIVE_ON);
		this.canTill = canTill;
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass)
	{
		return super.getHarvestLevel(stack, toolClass);
	}

	@Override
	public boolean canHarvestBlock(Block blockIn, ItemStack stack)
	{
		return this.getHarvestLevel(stack, "pickaxe") >= blockIn.getHarvestLevel(null);
	}

	@Override
	public boolean canHarvestBlock(Block blockIn)
	{
		return canHarvestBlock(blockIn,new ItemStack(this,1));
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 *  
	 * @param pos The block being right-clicked
	 * @param side The side being right-clicked
	 */
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!playerIn.canPlayerEdit(pos.offset(side), side, stack) || !canTill)
		{
			return false;
		}
		else
		{
			int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, playerIn, worldIn, pos);
			if (hook != 0) return hook > 0;

			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (side != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
			{
				if (block == Blocks.grass)
				{
					return this.useHoe(stack, playerIn, worldIn, pos, Blocks.farmland.getDefaultState());
				}

				if (block == Blocks.dirt)
				{
					switch (ItemArchaeoPickaxe.SwitchDirtType.TYPE_LOOKUP[((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT)).ordinal()])
					{
					case 1:
						return this.useHoe(stack, playerIn, worldIn, pos, Blocks.farmland.getDefaultState());
					case 2:
						return this.useHoe(stack, playerIn, worldIn, pos, Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
					}
				}
			}

			return false;
		}
	}

	protected boolean useHoe(ItemStack stack, EntityPlayer player, World worldIn, BlockPos target, IBlockState newState)
	{
		worldIn.playSoundEffect((double)((float)target.getX() + 0.5F), (double)((float)target.getY() + 0.5F), (double)((float)target.getZ() + 0.5F), newState.getBlock().stepSound.getStepSound(), (newState.getBlock().stepSound.getVolume() + 1.0F) / 2.0F, newState.getBlock().stepSound.getFrequency() * 0.8F);

		if (worldIn.isRemote)
		{
			return true;
		}
		else
		{
			worldIn.setBlockState(target, newState);
			stack.damageItem(1, player);
			return true;
		}
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public float getDigSpeed(ItemStack item, IBlockState state)
	{
		Block block = state.getBlock();
		if (block.getHarvestLevel(state) <= getHarvestLevel(item,"pickaxe"))
		{
			return super.getDigSpeed(item, state);
		}
		return 1.0f;
	}

	/**
	 * Returns the name of the material this tool is made from as it is declared in EnumToolMaterial (meaning diamond
	 * would return "EMERALD")
	 */
	public String getMaterialName()
	{
		return this.toolMaterial.toString();
	}

	static final class SwitchDirtType
	{
		static final int[] TYPE_LOOKUP = new int[BlockDirt.DirtType.values().length];
		private static final String __OBFID = "CL_00002179";

		static
		{
			try
			{
				TYPE_LOOKUP[BlockDirt.DirtType.DIRT.ordinal()] = 1;
			}
			catch (NoSuchFieldError var2)
			{
				;
			}

			try
			{
				TYPE_LOOKUP[BlockDirt.DirtType.COARSE_DIRT.ordinal()] = 2;
			}
			catch (NoSuchFieldError var1)
			{
				;
			}
		}
	}

}
