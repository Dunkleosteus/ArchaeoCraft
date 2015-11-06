package com.dunkleosteus.archaeocraft;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ArchaeoCraftingManager {


	public static void registerRecipes()
	{
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.flintHatchet,1), new Object[]{"fs"," s", 'f', Items.flint, 's', Items.stick});
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.copperHatchet,1), new Object[]{"cs"," s", 'c', ArchaeoItem.copperIngot, 's', Items.stick});
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.flintHatchet,1), new Object[]{"sf","s ", 'f', Items.flint, 's', Items.stick});
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.copperHatchet,1), new Object[]{"sc","s ", 'c', ArchaeoItem.copperIngot, 's', Items.stick});
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.copperPickaxe,1), new Object[]{"ccc"," s ", " s ", 'c', ArchaeoItem.copperIngot, 's', Items.stick});
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.copperHoe,1), new Object[]{"cc"," s", " s", 'c', ArchaeoItem.copperIngot, 's', Items.stick});
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.copperHoe,1), new Object[]{"cc","s ", "s ", 'c', ArchaeoItem.copperIngot, 's', Items.stick});
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.copperSword,1), new Object[]{"c","c", "s", 'c', ArchaeoItem.copperIngot, 's', Items.stick});
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.copperShovel,1), new Object[]{"c","s", "s", 'c', ArchaeoItem.copperIngot, 's', Items.stick});
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.bronzeSword,1), new Object[]{"b","b", "s", 'b', ArchaeoItem.bronzeIngot, 's', Items.stick});
		CraftingManager.getInstance().addRecipe(new ItemStack(ArchaeoItem.flintKnife,1), new Object[]{"f", "s", 'f', Items.flint, 's', Items.stick});
	}
}
