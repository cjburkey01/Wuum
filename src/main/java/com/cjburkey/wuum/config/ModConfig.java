package com.cjburkey.wuum.config;

import com.cjburkey.wuum.ModInfo;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ModInfo.modid)
@LangKey("wuum.config.title")
public class ModConfig {
    
    public static General general = new General();
    
    public static class General {
        
        // Examples:
        /*
        
        @Comment("Just a simple value")
        @RangeInt(min = 5, max = 100)
        public int value;
        
        public General(int valueDef) {
            value = valueDef;
        }
        
         */
        
    }
    
    @EventBusSubscriber(modid = ModInfo.modid)
    private static class EventHandler {
        
        @SubscribeEvent
        public void onConfigChanged(OnConfigChangedEvent e) {
            if (e.getModID().equals(ModInfo.modid)) {
                ConfigManager.sync(ModInfo.modid, Type.INSTANCE);
            }
        }
        
    }
    
}