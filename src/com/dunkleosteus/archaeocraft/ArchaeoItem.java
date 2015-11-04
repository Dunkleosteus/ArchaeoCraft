package com.dunkleosteus.archaeocraft;

import java.util.Set;

import com.dunkleosteus.archaeocraft.Tools.ItemArchaeoPickaxe;
import com.dunkleosteus.archaeocraft.Tools.ItemHatchet;
import com.dunkleosteus.archaeocraft.Tools.ItemKnife;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;

public class ArchaeoItem extends Item {
	//flint
	public static Item flintHatchet;
	public static Item flintKnife;
	//bone
	public static Item bonePick;
	//copper
	public static Item copperIngot;
	public static Item copperHatchet;
	public static Item copperShovel;
	public static Item copperSword;
	public static Item copperPickaxe;
	public static Item copperHoe;
	//bronze
	public static Item bronzeIngot;
	public static Item bronzeSword;
	
	
	static Set axeSet = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder});
	
	public static void initializeItems()
	{
		
		
		flintHatchet = ArchaeoCraft.proxy.registerItem(new ItemHatchet(2f, ToolMaterial.WOOD, axeSet).setUnlocalizedName("flintHatchet"));
		copperHatchet = ArchaeoCraft.proxy.registerItem(new ItemHatchet(2f, ToolMaterial.STONE, axeSet).setUnlocalizedName("copperHatchet"));
		copperSword = ArchaeoCraft.proxy.registerItem(new ItemSword(ToolMaterial.STONE).setUnlocalizedName("copperSword"));
		copperIngot = ArchaeoCraft.proxy.registerItem(new Item().setUnlocalizedName("copperIngot"));
		copperHoe = ArchaeoCraft.proxy.registerItem(new ItemHoe(ToolMaterial.STONE).setUnlocalizedName("copperHoe"));
		copperPickaxe = ArchaeoCraft.proxy.registerItem(new ItemArchaeoPickaxe(Item.ToolMaterial.STONE,false).setUnlocalizedName("copperPickaxe"));
		copperShovel = ArchaeoCraft.proxy.registerItem(new ItemSpade(Item.ToolMaterial.STONE).setUnlocalizedName("copperShovel"));
		flintKnife = ArchaeoCraft.proxy.registerItem(new ItemKnife(Item.ToolMaterial.WOOD).setUnlocalizedName("flintKnife"));
		bonePick = ArchaeoCraft.proxy.registerItem(new ItemArchaeoPickaxe(Item.ToolMaterial.WOOD,true).setUnlocalizedName("bonePick"));
		bronzeIngot = ArchaeoCraft.proxy.registerItem(new Item().setUnlocalizedName("bronzeIngot"));
		bronzeSword = ArchaeoCraft.proxy.registerItem(new ItemSword(Item.ToolMaterial.IRON).setUnlocalizedName("bronzeSword"));
	}
	
	
}
