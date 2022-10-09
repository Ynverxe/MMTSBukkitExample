package com.github.ynverxe.mmtsbukkitexample;

import com.github.ynverxe.mmts.bukkit.YamlSourceCreator;
import com.github.ynverxe.mmts.core.MMTSHandler;
import com.github.ynverxe.mmtsbukkitexample.command.MMTSBukkitExampleCommand;
import com.github.ynverxe.mmtsbukkitexample.mmts.BukkitMMTSModuleExample;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MMTSBukkitExamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        File pluginFolder = getDataFolder();

        if (!pluginFolder.exists()) {
            if (!pluginFolder.mkdirs()) {
                throw new RuntimeException("Unable to create folder");
            }
        }

        MMTSHandler mmtsHandler = MMTSHandler.create(
                new YamlSourceCreator(new File(pluginFolder, "messages"), "messages_<lang>.yml", this)
        );

        mmtsHandler.installModule(new BukkitMMTSModuleExample());

        CommandManager commandManager = new BukkitCommandManager("mmts");

        PartInjector partInjector = PartInjector.create();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder builder = AnnotatedCommandTreeBuilder.create(partInjector);

        commandManager.registerCommands(builder.fromClass(new MMTSBukkitExampleCommand(mmtsHandler)));
    }

    public static MMTSBukkitExamplePlugin getInstance() {
        return MMTSBukkitExamplePlugin.getPlugin(MMTSBukkitExamplePlugin.class);
    }
}