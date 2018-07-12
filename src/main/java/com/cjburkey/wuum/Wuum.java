package com.cjburkey.wuum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cjburkey.wuum.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = ModInfo.name, modid = ModInfo.modid, version = ModInfo.version, clientSideOnly = false, serverSideOnly = false,
    acceptedMinecraftVersions = ModInfo.minecraftVersions, canBeDeactivated = false, modLanguage = "java")
public class Wuum {
    
    private static final Logger logger = LogManager.getLogger(ModInfo.modid);
    
    @Instance(owner = ModInfo.modid)
    public static Wuum instance;
    
    @SidedProxy(clientSide = ModInfo.clientProxyPath, serverSide = ModInfo.serverProxyPath)
    public static CommonProxy proxy;
    
    @EventHandler
    public void construct(FMLConstructionEvent e) {
        proxy.construct(e);
    }
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent e) {
        proxy.preinit(e);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent e) {
        proxy.postinit(e);
    }
    
    // -- LOGGING -- //
    
    public static void info(Object msg) {
        logger.info(sanitizeMessage(msg));
    }
    
    public static void info(Object msg, Object... data) {
        logger.info(sanitizeMessage(msg), data);
    }
    
    public static void warn(Object msg) {
        logger.warn(sanitizeMessage(msg));
    }
    
    public static void warn(Object msg, Object... data) {
        logger.warn(sanitizeMessage(msg), data);
    }
    
    public static void error(Object msg) {
        logger.error(sanitizeMessage(msg));
    }
    
    public static void error(Object msg, Object... data) {
        logger.error(sanitizeMessage(msg), data);
    }
    
    private static String sanitizeMessage(Object msg) {
        String m = (msg == null) ? "null" : msg.toString();
        return (m == null) ? "null" : m;
    }
    
}