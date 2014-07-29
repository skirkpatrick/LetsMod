package com.github.skirkpatrick.letsmod.configuration;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {

    public static void init(File configFile) {

        Configuration configuration = new Configuration(configFile);

        try {
            // Load the configuration file
            configuration.load();

            // Read the configuration properties
            boolean exampleBool = configuration.get("ExampleCategory", "exampleBool", true,
                "This is an example of loading a boolean.").getBoolean(true);
        } catch (Exception e) {
            // This was Pahimar's suggestion. It seems like the only thing that
            // can be thrown are RuntimeExceptions from invalid syntax in the file.

            // Log Exception

        } finally {
            // Save the configuration file
            configuration.save();
        }
    }
}
