package com.cjburkey.wuum.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModTabs {
    
    public static final CreativeTabs tabItems = new CreativeTabs("tab_items") {
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.APPLE);
        }
    };
    
}