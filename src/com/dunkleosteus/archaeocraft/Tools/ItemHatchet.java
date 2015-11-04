package com.dunkleosteus.archaeocraft.Tools;

import java.util.Set;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemHatchet extends ItemTool {

	public ItemHatchet(float attackDamage, ToolMaterial material, Set effectiveBlocks) {
		super(attackDamage, material, effectiveBlocks);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	public float getDigSpeed(ItemStack item, IBlockState state)
	{
		return 0;
	}
}
