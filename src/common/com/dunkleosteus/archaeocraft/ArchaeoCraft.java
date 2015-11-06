package com.dunkleosteus.archaeocraft;

import com.dunkleosteus.archaeocraft.Tools.ItemHatchet;
import com.dunkleosteus.archaeocraft.CommonProxy;
import com.dunkleosteus.archaeocraft.ArchaeoItem;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;

import net.minecraftforge.fml.common.SidedProxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ArchaeoCraft.MODID, name = ArchaeoCraft.MODNAME, version = ArchaeoCraft.MODVER)
public class ArchaeoCraft {
	public static final String MODID = "archaeocraft";
	public static final String MODNAME = "ArchaeoCraft";
	public static final String MODVER = "A0.0.1";

	@SidedProxy(clientSide = "com.dunkleosteus.archaeocraft.ClientProxy", serverSide = "com.dunkleosteus.archaeocraft.CommonProxy")
	public static CommonProxy proxy;

	@Instance(value = ArchaeoCraft.MODID)
	public static ArchaeoCraft instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		ArchaeoHarvestFix.modifyArchaeoHarvestLevel();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ArchaeoCraftingManager.registerRecipes();
	}
}
