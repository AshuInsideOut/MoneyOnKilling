package com.github.ashuguptagamer.moneyonkilling;

import me.realized.tokenmanager.api.TokenManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoneyOnKilling extends JavaPlugin {

    public static Economy economy;
    public static TokenManager tokenManager;

    @Override
    public void onEnable() {
        setupEconomy();
        saveDefaultConfig();
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerKilledEvent(this), this);
    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        tokenManager = (TokenManager) Bukkit.getPluginManager().getPlugin("TokenManager");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
