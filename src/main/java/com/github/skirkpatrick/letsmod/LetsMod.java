package com.github.skirkpatrick.letsmod;

import com.github.skirkpatrick.letsmod.proxy.Proxy;
import com.github.skirkpatrick.letsmod.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class LetsMod {

    @Mod.Instance(Reference.MOD_ID)
    public static LetsMod instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY,
                serverSide = Reference.SERVER_PROXY)
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
