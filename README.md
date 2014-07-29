LetsMod
=======

Learning Minecraft modding with Pahimar - http://www.pahimar.com/tutorials/lets-mod/

Notes
=====

I'll be keeping notes for reference as I work through the series.


I've opted to use IntelliJ IDEA for this project.


Initial Setup ([Episode 1](https://www.youtube.com/watch?v=e6v5egIkThk))
-------------

1.  Create folder for project (duh)
2.  [Download Forge](files.minecraftforge.net) for desired version
3.  Exract the following files/folders into project directory:
    - eclipse/
    - gradle/
    - build.gradle
    - gradlew
    - gradlew.bat
4.  Setup workspace with decompiled Minecraft

    ```
    $ gradlew setupDecompWorkspace
    ```

5.  Setup IDE workspace

    ```
    $ # For Eclipse
    $ gradlew eclipse
    $ # For IntelliJ IDEA
    $ gradlew idea
    ```

6.  Open project in IDE

    For Eclipse, select the `eclipse/` folder as the workspace.

    For IntelliJ IDEA, Open Project and select the project root directory.


### Issues I had with IntelliJ IDEA

#### Message about importing Gradle project

##### tl;dr: Ignore it and opt to not display it again

Upon opening the project in IDEA, I got a warning suggesting I import the Gradle project. I chose to do it the first time, and it starting configuring something for Maven which took forever. After starting over, I chose to ignore the message and opt to not have it display again, which works just fine.

---

#### No JDK setup for project

##### tl;dr: Press `CTRL+SHIFT+ALT+S`, select Project tab, add new JDK

Apparently IntelliJ IDEA doesn't automatically detect JDK installs, so you have to set it up yourself. This is done in the Project Structure window, accessible from either File -> Project Structure, or pressing `CTRL+SHIFT+ALT+S`. Select the Project tab on the left, then use `New...` under the Project SDK section to select the desired JDK installation.

---

#### Missing Gradle assets (FileNotFound -> ~/.gradle/caches/minecraft/assets/.../\*\*\*.json)

##### tl;dr: Keep running `gradlew setupDecompWorkspace` until it goes away

I'm still not 100% sure why these assets don't always get downloaded in the first place, but running `gradlew setupDecompWorkspace` again fixed it for me. It'll skip everything that was done correctly the first time, so it doesn't take very long. Of the two environments on which I setup a workspace, only one of them ran into this issue.


Basic Gradle Configuration ([Episode 2](https://www.youtube.com/watch?v=9-gIMtN027c))
--------------------------

The following fields should be modified per project:

-   `version` : Mod version. Convention is to prefix it with the intended Minecraft version, i.e. `1.7.2-1.0`.
-   `group` : Java package name. Naming convention is the same as the Java package naming convention, i.e. `com.github.skirkpatrick.letsmod`.
-   `archivesBaseName` : Mod identifier. Can contain spaces, but should it?

The following fields may be modified depending on your needs:

-   `minecraft.version` : Minecraft Forge version to use.
-   `minecraft.assetDir` : Directory in which to store base game and forge assets.


Source Folder Structure ([Episode 3](https://www.youtube.com/watch?v=lqb4WJEEIGI) and [Episode 4](https://www.youtube.com/watch?v=KchpGhnH5wg))
-----------------------

The Java convention should be used for the project source structure. Optional folders are marked with an asterisk (`*`). All other folders are required to be the way Minecraft and Forge expect.

    <Project Root>
    `---src
        `---main
            +---java
            |   `---<Package)
            `---resources
                `---assets
                    `---(modid)
                        +---lang
                        +---models*
                        +---sounds
                        `---textures
                            +---blocks
                            +---gui*
                            `---items

The `src/main/java` and `src/main/resources` folders should be marked as source folders.

The `(Package)` folders should be a directory hierarchy based on your package name. For example, `src/main/java/com/github/skirkpatrick/letsmod/`.

The `(modid)` folder should be named the same as the Mod Id/archivesBaseName, but all lowercase, i.e. `src/main/resources/assets/letsmod/`.

The optional (`*`) folders are the convention used by Pahimar. These will not be automatically loaded by Forge, and must be configured to do so.


@Mod Class ([Episode 3](https://www.youtube.com/watch?v=lqb4WJEEIGI))
----------

### @Mod Annotation

The `@Mod` annotation marks the main class to be loaded by the Forge Mod Loader (FML).

Important `@Mod` attributes to set:
-   `modid` : Unique identifier for mod. Should be the same as the `archivesBaseName` in build.gradle.
-   `name` : Name of the mod. Probably the same as the ModId but with spaces.
-   `version` : Mod version. Should be the same as the `version` in build.gradle.

### FML Phases

All FML event classes can be found in the external library package `cpw.mods.fml.common.event`.

FML performs each phase across all mods at a time, so all mods will run the pre-initialization phase before any mod continues to the initialization phase.

#### Pre-initialization (`FMLPreInitializationEvent`)

-   Load configurations
-   Initialize network handling
-   Setup key bindings
-   Initialize items
-   Initialize blocks

#### Initialization (`FMLInitializationEvent`)

-   Setup GUI handler
-   Setup tile entities
-   Setup rendering
-   Register crafting recipes
-   Setup other general event handlers
-   Start registering recipes

#### Post-initialization (`FMLPostInitializationEvent`)

Phase to wrap things up after other mods have loaded.

### @Mod.EventHandler

The `@Mod.EventHandler` subscribes a method to the event handler.

Method argument determines the event type (phase). Argument should be an `FMLEvent` type from the `cpw.mods.fml.common.event` package.

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ...
    }

### Mod Instance

The `@Mod.Instance` annotation marks a static data member of your mod type as a safe, reliable instance that can be used. ModId should be given as the default argument.

    @Mod.Instance("LetsMod")
    public static LetsMod instance;


mcmod.info ([Episode 4](https://www.youtube.com/watch?v=KchpGhnH5wg))
--------------------------

The `mcmod.info` is a JSON formatted file that contains information about the mod to be displayed in the Mods menu accessible from the game's main menu. It should be placed in the `src/main/resources` directory.

Pahimar's suggestion: Just copy the mdmod.info from another mod and modify it as necessary to ensure correct formatting.

The fields are as follows:

-   `modid` : The Mod Id
-   `name` : Name of the mod. Same as specified in the `@Mod` attributes.
-   `description` : A short description of the mod.
-   `version` : Mod version. Should be the same as specified in `build.gradle`, but without the Minecraft version prepended, i.e. `0.1`.
-   `mcversion` : Minecraft version the mod is designed for. This would be the prepended bit in the `build.gradle` version.
-   `url` : URL to get more information about the mod, likely the mod homepage, if one exists.
-   `updateUrl` : This field does nothing!
-   `authorList` : List of mod authors.
-   `credits` : Credits due, if any.
-   `logoFile` : Path (?) to a PNG file to be used as the logo on the mod info screen. Maximum dimensions are 256x256.
-   `screenshots` : This field does nothing!
-   `parent` : Declares the mod as a child of another mod.
-   `dependencies` : List of mods on which the mod is dependent.


Sided Proxy ([Episode 5](https://www.youtube.com/watch?v=oVq5kslZJc4))
-----------

Proxy classes simplify writing mods to work on both the Minecraft client and the dedicated server client. A base proxy class can be used to define code common to both clients,  while client- and server-specific subclasses contain code for unique operations. Forge refers to this concept as a 'sided proxy'.

The SidedProxy annotation is used to inject a static instance of the appropriate Proxy implementation at runtime (such as a member in your base mod class). The clientSide attribute specifies the client proxy and the serverSide attribute specifies the server proxy.

This post gives a better explanation of proxies than Pahimar does: [http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html](http://greyminecraftcoder.blogspot.com/2013/11/how-forge-starts-up-your-code.html).


Reference Classes ([Episode 5](https://www.youtube.com/watch?v=oVq5kslZJc4))
-----------------

Reference classes provide a centralized place to store constants that may be referenced in multiple places across the code base. This provides a couple of advantages:

-   If a value needs to be modified or updated, it only has to be done in one place
-   Errors are less likely to be introduced from typos
-   Keeps code [DRY](http://en.wikipedia.org/wiki/Don't_repeat_yourself)
-   Constants are all kept in a predictable location

These classes are written the same as for any Java project: a class with `public static final` data members.


Utility Classes ([Episode 5](https://www.youtube.com/watch?v=oVq5kslZJc4))
---------------

As with any Java project (or any coding project, for that matter), utility classes can come in handy for defining `static` methods for perfoming common operations. This can including things such as performing bulk operations (such as on `List`s) or performing tasks that don't fit as instance methods elsewhere.
