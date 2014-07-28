package com.github.skirkpatrick.letsmod;

import com.github.skirkpatrick.letsmod.proxy.Proxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "LetsMod", name = "Let's Mod", version = "1.7.2-0.1")
public class LetsMod {

    @Mod.Instance("LetsMod")
    public static LetsMod instance;

    @SidedProxy(clientSide = "com.github.skirkpatrick.letsmod.proxy.ClientProxy",
                serverSide = "com.github.skirkpatrick.letsmod.proxy.ServerProxy")
    public static Proxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
