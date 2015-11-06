package com.dunkleosteus.archaeocraft;

import net.minecraft.client.Minecraft;
import com.dunkleosteus.archaeocraft.CommonProxy;
import com.dunkleosteus.archaeocraft.ArchaeoCraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		// remember to Setup a faster way to do this pls
		/*ModelLoader.setCustomModelResourceLocation(ArchaeoItem.flintHatchet, 0,
				new ModelResourceLocation(
						ArchaeoCraft.MODID + ":" + ArchaeoItem.flintHatchet.getUnlocalizedName().replace("item.", ""),
						"inventory"));*/
	}

	protected Item registerItem(Item i)
	{
		i = super.registerItem(i);
		ModelLoader.setCustomModelResourceLocation(i, 0,
				new ModelResourceLocation(
						ArchaeoCraft.MODID + ":" + i.getUnlocalizedName().replace("item.", ""),
						"inventory"));
		return i;
	}
	
	public boolean isClientSide() {
		return true;
	}
}
