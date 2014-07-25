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
        $ gradlew setupDecompWorkspace
5.  Setup IDE workspace
        $ # For Eclipse
        $ gradlew eclipse
        $ # For IntelliJ IDEA
        $ gradlew idea
6.  Open project in IDE

    For Eclipse, select the `eclipse/` folder as the workspace.

    For IntelliJ IDEA, Open Project and select the project root directory.


### Issues I had with IntelliJ IDEA

#### Message about importing Gradle project

##### tl;dr: Ignore it and opt to not display it again

Upon opening the project in IDEA, I got a warning suggesting I import the Gradle project. I chose to do it the first time, and it starting configuring something for Maven which took forever. After starting over, I chose to ignore the message and opt to not have it display again, which works just fine.

#### No JDK setup for project

##### tl;dr: Press `CTRL+SHIFT+ALT+S`, select Project tab, add new JDK

Apparently IntelliJ IDEA doesn't automatically detect JDK installs, so you have to set it up yourself. This is done in the Project Structure window, accessible from either File -> Project Structure, or pressing `CTRL+SHIFT+ALT+S`. Select the Project tab on the left, then use `New...` under the Project SDK section to select the desired JDK installation.

#### Missing Gradle assets (FileNotFound -> ~/.gradle/caches/minecraft/assets/.../\*\*\*.json)

##### tl;dr: Keep running `gradlew setupDecompWorkspace` until it goes away

I'm still not 100% sure why these assets don't always get downloaded in the first place, but running `gradlew setupDecompWorkspace` again fixed it for me. It'll skip everything that was done correctly the first time, so it doesn't take very long. Of the two environments on which I setup a workspace, only one of them ran into this issue.


Basic Gradle Configuration ([Episode 2](https://www.youtube.com/watch?v=9-gIMtN027c))
--------------------------

The following fields should be modified per project:

-   **version** : Mod version. Convention is to prefix it with the intended Minecraft version, i.e. `1.7.2-1.0`.
-   **group** : Java package name. Naming convention is the same as the Java package naming convention, i.e. `com.github.skirkpatrick.letsmod`.
-   **archivesBaseName** : Name of the mod. Can include spaces.

The following fields may be modified depending on your needs:

-   **minecraft.version** : Minecraft Forge version to use.
-   **minecraft.assetDir** : Directory in which to store base game and forge assets.
