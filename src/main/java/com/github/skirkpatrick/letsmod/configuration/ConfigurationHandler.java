package com.github.skirkpatrick.letsmod.configuration;

import com.github.skirkpatrick.letsmod.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {

    private static Configuration configuration;
    private static boolean exampleBool = false;

    public static void init(File configFile) {

        // Configuration should only be loaded from file once
        if (configuration == null) {
            configuration = new Configuration(configFile);

            // Load the configuration from file into memory
            configuration.load();

            // Set configuration properties
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {

        // Set configuration properties
        exampleBool = configuration.getBoolean("exampleBool",
                Configuration.CATEGORY_GENERAL,
                true, "This is an example of loading a boolean");
        // Alternative way (more verbose, a little redundant)
        //exampleBool = configuration.get("ExampleCategory", "exampleBool", true,
        //        "This is an example of loading a boolean.").getBoolean(true);

        // Save the configuration to disk if modified
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event) {

        // Only reload configuration if it was our mod that changed
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
