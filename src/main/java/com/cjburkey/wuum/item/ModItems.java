package com.cjburkey.wuum.item;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.wuum.ModInfo;
import com.cjburkey.wuum.Wuum;
import com.cjburkey.wuum.block.ModBlocks;
import com.cjburkey.wuum.tab.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ModInfo.modid)
public class ModItems {
    
    public static ItemEntityCollector itemEntityCollector;
    
    private static void createItems() {
        itemEntityCollector = createItem(new ItemEntityCollector(), "item_entity_collector");
    }
    
    private static final List<Item> items = new ArrayList<>();
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        Wuum.info("Creating items");
        createItems();
        
        Wuum.info("Registering block items");
        for (Block block : ModBlocks.getBlocks()) {
            createItem(new ItemBlock(block), block.getRegistryName().getResourcePath());
        }
        
        Wuum.info("Registering items");
        for (Item item : items) {
            e.getRegistry().register(item);
        }
    }
    
    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent e) {
        Wuum.info("Registering renders");
        for (Item item : items) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
    
    private static <T extends Item> T createItem(T item, String unlocName) {
        item.setUnlocalizedName(unlocName);
        item.setRegistryName(ModInfo.modid, unlocName);
        item.setCreativeTab(ModTabs.tabItems);
        items.add(item);
        return item;
    }
    
}