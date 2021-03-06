package com.dunkleosteus.archaeocraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.dunkleosteus.archaeocraft.ArchaeoHarvestLevel;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;
import java.lang.reflect.Field;
public final class ArchaeoHarvestFix
{
	 	private ArchaeoHarvestFix() {}
	    public static void modifyArchaeoHarvestLevel() {
	      

	        modifyOredictBlocks();
	        modifyVanillaBlocks();

	        modifyTools();
	    }

	    private static void modifyVanillaBlocks()
	    {
	        // ensure that the forgehooks are in place
	        new ForgeHooks(); // this ensures that the static initializer of ForgeHooks is called already. Otherwise it overwrites our Harvestlevel changes.
	        // see ForgeHooks.initTools()
	        
	        Blocks.stone.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._0_bone, Blocks.stone.getStateFromMeta(BlockStone.EnumType.STONE.getMetadata()));
	        Blocks.stone.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._2_copper, Blocks.stone.getStateFromMeta(BlockStone.EnumType.ANDESITE.getMetadata()));
	        Blocks.stone.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._2_copper, Blocks.stone.getStateFromMeta(BlockStone.EnumType.ANDESITE_SMOOTH.getMetadata()));
	        Blocks.stone.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._2_copper, Blocks.stone.getStateFromMeta(BlockStone.EnumType.GRANITE.getMetadata()));
	        Blocks.stone.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._2_copper, Blocks.stone.getStateFromMeta(BlockStone.EnumType.GRANITE_SMOOTH.getMetadata()));
	        Blocks.stone.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._2_copper, Blocks.stone.getStateFromMeta(BlockStone.EnumType.DIORITE.getMetadata()));
	        Blocks.stone.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._2_copper, Blocks.stone.getStateFromMeta(BlockStone.EnumType.DIORITE_SMOOTH.getMetadata()));
	        
	        Blocks.iron_ore.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._3_bronze);
	        Blocks.iron_block.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._3_bronze);
	        Blocks.iron_bars.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._3_bronze);
	        Blocks.lapis_ore.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._3_bronze);
	        Blocks.lapis_block.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._3_bronze);

	        Blocks.gold_ore.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._4_iron);
	        Blocks.gold_block.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._4_iron);
	        Blocks.redstone_ore.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._4_iron);
	        Blocks.lit_redstone_ore.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._4_iron);

	        Blocks.diamond_ore.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._4_iron); // yes, diamond requires diamond level. good thing there's bronze/steel ;)
	        Blocks.diamond_block.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._4_iron);
	        Blocks.emerald_ore.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._4_iron);
	        Blocks.emerald_block.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._4_iron);

	        Blocks.obsidian.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._6_diamond);

	        Blocks.enchanting_table.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._6_diamond);

	    }

	    private static void modifyOredictBlocks()
	    {
	        //String[][][] lists = new String[][][] {oreDictLevels, oreDictLevelsMetallurgyFantasy, oreDictLevelsMetallurgyNether, oreDictLevelsMetallurgyEnd};
	        //for(String[][] odll : lists)
	            for (int i = 0; i < allOreDicLevels.length; ++i)
	                for (String materialName : allOreDicLevels[i]) {
	                    modifyOredictBlock(materialName, i);
	                }
	    }

	    public static void modifyOredictBlock(String orePostfix, int hlvl)
	    {
	        for(String prefix : oreDictPrefixes)
	            for (ItemStack oreStack : OreDictionary.getOres(prefix + orePostfix))
	                modifyBlock(oreStack, hlvl);
	    }

	    public static void modifyBlock(ItemStack stack, int harvestLevel)
	    {
	        Block block = Block.getBlockFromItem(stack.getItem());
	        
	        int meta = stack.getItemDamage();
	        //IBlockState = stack
	        Integer[] metas;
	        if(meta == OreDictionary.WILDCARD_VALUE)
	            metas = new Integer[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	        else
	            metas = new Integer[] {meta};

	        for(int m : metas) {
	            try {
	            	//sorry
	                    block.setHarvestLevel("pickaxe", harvestLevel);

	            } catch(Exception e)
	            {
	                // exception can occur if stuff does weird things metadatas
	            }
	        }
	    }

	    private static void modifyTools()
	    {
	        ItemStack tmp = new ItemStack(Items.stick); // we need one as argument, it's never actually accessed...
	        // search for all items that have pickaxe harvestability
	        for(Object o : Item.itemRegistry)
	        {
	            Item item = (Item) o;
	            // cycle through all toolclasses. usually this'll either be pickaxe, shovel or axe. But mods could add items with multiple.
	            for(String toolClass : item.getToolClasses(tmp)) {
	                // adapt harvest levels
	                int old = item.getHarvestLevel(tmp, toolClass);
	                // wood/gold tool unchanged
	                if (old <= 0)
	                    continue;

	                int hlvl = getUpdatedHarvestLevel(old);

	                updateToolHarvestLevel(item, toolClass, hlvl);
	            }

	        }
			ArchaeoItem.copperHatchet.setHarvestLevel("hatchet", ArchaeoHarvestLevel._2_copper);
			ArchaeoItem.flintHatchet.setHarvestLevel("hatchet",  ArchaeoHarvestLevel._1_flint);
			ArchaeoItem.bonePick.setHarvestLevel("pickaxe", ArchaeoHarvestLevel._0_bone);
			ArchaeoItem.copperPickaxe.setHarvestLevel("pickaxe",ArchaeoHarvestLevel._2_copper);
			ArchaeoItem.bronzeChisel.setHarvestLevel("pickaxe",ArchaeoHarvestLevel._3_bronze);
	    }

	    public static int getUpdatedHarvestLevel(int old)
	    {
	        switch (old) {
	            // stone tool: nerfed to wood level
	            case 1:
	                return ArchaeoHarvestLevel._3_bronze;
	            // iron tool
	            case 2:
	                return ArchaeoHarvestLevel._4_iron;
	            // diamond tool
	            case 3:
	                return ArchaeoHarvestLevel._6_diamond;
	            // default... we just increase it?
	            default:
	                return old + 2;
	        }
	    }

	    public static void updateToolHarvestLevel(Item item, String toolClass, int hlvl)
	    {
	        item.setHarvestLevel(toolClass, hlvl);
	        // meh. special fix for CofH tools
	        Class clazz = item.getClass();
	        while(clazz != Object.class)
	        {
	            if(clazz.getSimpleName().equals("ItemToolAdv"))
	            {
	                try {
	                    Field hlvlField = clazz.getDeclaredField("harvestLevel");
	                    hlvlField.setAccessible(true);
	                    hlvlField.set(item, hlvl);
	                } catch (NoSuchFieldException e) {
	                    // errorrr
	                   
	                } catch (IllegalAccessException e) {
	                    
	                }
	                break;
	            }
	            clazz = clazz.getSuperclass();
	        }

	        // check if the setting was successful
	        //if(item.getHarvestLevel(new ItemStack(item), toolClass) != hlvl)
	        //    Log.error("Could not set harvestlevel of " + item.getUnlocalizedName() + ". Contact the Mod Author to properly support Item.setHarvestLevel().");
	    }

	    // todo: expose this to config. But I'm too lazy for such a minor thing. Just call me to add another string...
	    public static final String[] oreDictPrefixes = {
	            "ore", "denseore", "oreNether", "denseoreNether", "block", "stone", "brick", "orePoor"
	    };

	    // ArchaeoHarvestLevel
	    public static final String[][] oreDictLevels = {
	            // 0: Stone
	            {},
	            // 1: Flint
	            {"Copper", "Coal", "Tetrahedrite", "Aluminum", "Aluminium", "NaturalAluminum", "AluminumBrass", "Shard", "Bauxite", "Zinc"},
	            // 2: Copper
	            {"Iron", "Pyrite", "Silver", "Lapis"},
	            // 3: Iron
	            {"Tin", "Cassiterite", "Gold", "Lead", "Redstone", "Steel", "Galena", "Nickel", "Invar", "Electrum", "Sphalerite", "Osmium"},
	            // 4: Bronze
	            {"Diamond", "Emerald", "Ruby", "Sapphire", "Amethyst", "Cinnabar", "GreenSapphire", "BlackGranite", "RedGranite", "Manganese"},
	            // 5: Redstone/Diamond
	            {"Obsidian", "Tungstate", "Sodalite", "Quartz", "CertusQuartz", "SkyStone"},
	            // 6: Obsidian/Alumite
	            {"Ardite", "Uranium", "Olivine", "Sheldonite", "Platinum", "Yellorite"},
	            // 7: Ardite
	            {"Cobalt", "Iridium", "Cooperite", "Titanium"},
	            // 8: Cobalt
	            {"Manyullyn"},
	            // 9: Manyullyn (empty)
	            {}
	    };

	    public static final String[][] oreDictLevelsMetallurgyFantasy = {
	            // 0: Stone
	            {"Prometheum", "DeepIron"},
	            // 1: Flint
	            {"Infuscolium"},
	            // 2: Copper
	            {"Oureclase"},
	            // 3: Iron
	            {"AstralSilver"},
	            // 4: Bronze
	            {"Carmot"},
	            // 5: Redstone/Diamond
	            {"Mithril"},
	            // 6: Obsidian/Alumite
	            {"Rubracium"},
	            // 7: Ardite
	            {"Orichalcum"},
	            // 8: Cobalt
	            {"Adamantine"},
	            // 9: Manyullyn
	            {"Atlarus"}
	    };

	    public static final String[][] oreDictLevelsMetallurgyNether = {
	            // 0: Stone
	            {},
	            // 1: Flint
	            {},
	            // 2: Copper
	            {"Lemurite", "Ignatius"},
	            // 3: Iron
	            {"ShadowIron"},
	            // 4: Bronze
	            {"Midasium", "Vyroxeres"},
	            // 5: Redstone/Diamond
	            {"Ceruclase"},
	            // 6: Obsidian/Alumite
	            {"Alduorite"},
	            // 7: Ardite
	            {"Kalendrite"},
	            // 8: Cobalt
	            {"Vulcanite"},
	            // 9: Manyullyn
	            {"Sanguinite"}
	    };

	    public static final String[][] oreDictLevelsMetallurgyEnd = {
	            // 0: Stone
	            {},
	            // 1: Flint
	            {},
	            // 2: Copper
	            {},
	            // 3: Iron
	            {},
	            // 4: Bronze
	            {},
	            // 5: Redstone/Diamond
	            {"Eximite"},
	            // 6: Obsidian/Alumite
	            {"Meutoite"},
	            // 7: Ardite
	            {},
	            // 8: Cobalt
	            {},
	            // 9: Manyullyn
	            {}
	    };

	    public static String[][] allOreDicLevels;
	    static {
	        String[][][] lists = new String[][][] {oreDictLevels, oreDictLevelsMetallurgyFantasy, oreDictLevelsMetallurgyNether, oreDictLevelsMetallurgyEnd};
	        allOreDicLevels = new String[oreDictLevels.length][];
	        for(int i = 0; i < 10; i++)
	        {
	            int size = 0;
	            for (String[][] list : lists) size += list.length;

	            allOreDicLevels[i] = new String[size];
	            int j = 0;
	            for (String[][] list : lists)
	                for(String entry : list[i])
	                    allOreDicLevels[i][j++] = entry;
	        }
	    }

}
