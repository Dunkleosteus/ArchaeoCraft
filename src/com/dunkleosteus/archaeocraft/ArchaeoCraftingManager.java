package com.dunkleosteus.archaeocraft;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ArchaeoCraftingManager {

	
	public static void registerRecipes()
	{
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.flintHatchet,1), new Object[]{"fs"," s", 'f', Items.flint, 's', Items.stick});
	}
}
