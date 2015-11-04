package com.dunkleosteus.archaeocraft;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import java.util.List;
import java.util.Map;

import static net.minecraft.util.EnumChatFormatting.*;

public final class ArchaeoHarvestLevel {

	private ArchaeoHarvestLevel() {} // non-instantiable

	public static int _0_bone = 0;
	public static int _1_flint = 1;
	public static int _2_copper = 2;
	public static int _3_bronze = 3;
	public static int _4_iron = 4;
	public static int _5_steel = 5;
	public static int _6_diamond = 6;

	public static int max = 6;
/*
	public static void updateHarvestLevelNames()
	{
		Map<Integer, String> names = tconstruct.library.util.HarvestLevels.harvestLevelNames;

		if(vanilla)
		{
			names.put(0 , GRAY + StatCollector.translateToLocal("mininglevel.stone"));
			names.put(1 , DARK_RED + StatCollector.translateToLocal("mininglevel.iron"));
			names.put(2 , RED + StatCollector.translateToLocal("mininglevel.redstone"));
			names.put(3 , LIGHT_PURPLE + StatCollector.translateToLocal("mininglevel.obsidian"));
			names.put(4 , BLUE + StatCollector.translateToLocal("mininglevel.cobalt"));
			names.put(5 , DARK_PURPLE + StatCollector.translateToLocal("mininglevel.manyullyn"));
			names.put(6 , DARK_PURPLE + StatCollector.translateToLocal("mininglevel.manyullyn") + LIGHT_PURPLE + "+");
		}
		else {
			names.put(0, GRAY + StatCollector.translateToLocal("mininglevel.stone"));
			names.put(1, GOLD + StatCollector.translateToLocal("mininglevel.copper"));
			names.put(2, DARK_GRAY + StatCollector.translateToLocal("mininglevel.iron"));
			names.put(3, WHITE + StatCollector.translateToLocal("mininglevel.tin"));
			names.put(4, RED + StatCollector.translateToLocal("mininglevel.redstone"));
			names.put(5, LIGHT_PURPLE + StatCollector.translateToLocal("mininglevel.obsidian"));
			names.put(6, DARK_RED + StatCollector.translateToLocal("mininglevel.ardite"));
			names.put(7, DARK_AQUA + StatCollector.translateToLocal("mininglevel.cobalt"));
			names.put(8, DARK_PURPLE + StatCollector.translateToLocal("mininglevel.manyullyn"));
			names.put(9, DARK_PURPLE + StatCollector.translateToLocal("mininglevel.manyullyn") + LIGHT_PURPLE + "+");
		}
	}*/

	public static String getHarvestLevelName(int num)
	{
		return "unknown";
	}
}
