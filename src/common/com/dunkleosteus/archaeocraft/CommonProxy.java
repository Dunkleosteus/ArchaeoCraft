package com.dunkleosteus.archaeocraft;

import java.util.Set;

import com.dunkleosteus.archaeocraft.Tools.ItemHatchet;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ArchaeoItem.initializeItems();
	}
	
	protected Item registerItem(Item i)
	{
		GameRegistry.registerItem(i, i.getUnlocalizedName().replace("item.", ""));
		return i;
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	public boolean isClientSide() {
		return false;
	}
}
