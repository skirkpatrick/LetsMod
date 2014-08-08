package com.github.skirkpatrick.letsmod.init;

import com.github.skirkpatrick.letsmod.item.ItemOctocat;
import com.github.skirkpatrick.letsmod.item.ItemLM;
import com.github.skirkpatrick.letsmod.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

    public static final ItemLM git = new ItemOctocat();

    public static void init() {
        // Register all items
        GameRegistry.registerItem(git, Names.Items.OCTOCAT);
    }
}
