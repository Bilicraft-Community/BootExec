package com.bilicraft.bootexec;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public final class BootExec extends JavaPlugin {
    @Override
    public void onLoad() {
        saveDefaultConfig();
        List<String> commands = getConfig().getStringList("commands");
        Runtime runtime = Runtime.getRuntime();
        for (String command : commands) {
            try {
               printResults(runtime.exec(command));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        getLogger().info("Command executed.");
    }

    private void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            getLogger().info(line);
        }
    }
    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
