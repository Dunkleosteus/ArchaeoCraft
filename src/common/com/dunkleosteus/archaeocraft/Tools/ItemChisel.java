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
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemChisel extends ItemTool {

	private static final Set EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.sandstone, Blocks.red_sandstone, Blocks.stone});
	private static final Set BRONZE_SILK = Sets.newHashSet(new Block[] {Blocks.coal_ore, Blocks.ice, Blocks.iron_ore, Blocks.lapis_ore, Blocks.stone});
	private static final Set DIAMOND_SILK = Sets.newHashSet(new Block[] {Blocks.coal_ore, Blocks.ice, Blocks.iron_ore, Blocks.lapis_ore, Blocks.stone, Blocks.diamond_ore, Blocks.gold_ore, Blocks.lit_redstone_ore, Blocks.redstone_ore});
	private final ChiselType type;
	private int chiselCounter;
	private BlockPos chiselPos;
	public static enum ChiselType
	{
		BRONZE(),
		DIAMOND();

		private ChiselType()
		{
		}
	}

	public ItemChisel(ToolMaterial material, ChiselType type) {
		super(2, material, EFFECTIVE_ON);
		this.type = type;
		this.efficiencyOnProperMaterial*=0.5f;
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass)
	{
		return super.getHarvestLevel(stack, toolClass);
	}

	@Override
	public boolean canHarvestBlock(Block blockIn, ItemStack stack)
	{
		return this.getHarvestLevel(stack, "pickaxe") >= blockIn.getHarvestLevel(blockIn.getStateFromMeta(0));
	}

	@Override
	public boolean canHarvestBlock(Block blockIn)
	{
		return canHarvestBlock(blockIn,new ItemStack(this,1)) && this.EFFECTIVE_ON.contains(blockIn);
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 *  
	 * @param pos The block being right-clicked
	 * @param side The side being right-clicked
	 */
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!playerIn.canPlayerEdit(pos, side, stack))
		{
			return false;
		}
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();
		if(((this.type == ChiselType.BRONZE && this.BRONZE_SILK.contains(block)) || (this.type == ChiselType.DIAMOND && this.DIAMOND_SILK.contains(block))) && !worldIn.isRemote)
		{
			if(pos.equals(chiselPos))
			{
				chiselCounter++;
			}
			else
			{
				chiselPos = pos;
				chiselCounter = 1;
			}
			if(chiselCounter == 5)
			{
				block.spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(block),1,block.getDamageValue(worldIn, pos)));
				worldIn.destroyBlock(pos, false);
				if(!playerIn.capabilities.isCreativeMode)
				{
					stack.damageItem(1, playerIn);
				}
			}
			return true;
		}
		if((this.type == ChiselType.BRONZE && this.BRONZE_SILK.contains(block)) || (this.type == ChiselType.DIAMOND && this.DIAMOND_SILK.contains(block)))
		{
			worldIn.playSoundEffect((double)((float)playerIn.posX + 0.5F), (double)((float)playerIn.posY + 0.5F), (double)((float)playerIn.posZ + 0.5F), block.soundTypeStone.getStepSound(), (block.soundTypeStone.getVolume() + 1.0F) / 2.0F, block.soundTypeStone.getFrequency() * 0.8F);
			return true;
		}
		return false;
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
