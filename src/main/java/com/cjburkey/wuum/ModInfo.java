package com.cjburkey.wuum;

import com.cjburkey.wuum.proxy.ClientProxy;
import com.cjburkey.wuum.proxy.ServerProxy;

public class ModInfo {
    
    public static final String name = "Wuum";
    public static final String modid = "wuum";
    public static final String version = "0.0.1";
    public static final String minecraftVersions = "[1.12.2]";
    
    private static final String proxyPath = "com.cjburkey.wuum.proxy";
    public static final String clientProxyPath = proxyPath + ".ClientProxy";
    public static final String serverProxyPath = proxyPath + ".ServerProxy";
    
}