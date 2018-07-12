package com.cjburkey.wuum.block;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.wuum.ModInfo;
import com.cjburkey.wuum.Wuum;
import com.cjburkey.wuum.item.ModItems;
import com.cjburkey.wuum.tab.ModTabs;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ModInfo.modid)
public class ModBlocks {
    
    private static void createBlocks() {
        
    }
    
    private static final List<Block> blocks = new ArrayList<>();
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        Wuum.info("Creating blocks");
        createBlocks();
        
        Wuum.info("Registering blocks");
        for (Block block : blocks) {
            e.getRegistry().register(block);
        }
    }
    
    private static Block createBlock(Block block, String unlocName) {
        block.setUnlocalizedName(unlocName);
        block.setRegistryName(ModInfo.modid, unlocName);
        block.setCreativeTab(ModTabs.tabItems);
        blocks.add(block);
        return block;
    }
    
    public static Block[] getBlocks() {
        return blocks.toArray(new Block[blocks.size()]);
    }
    
}