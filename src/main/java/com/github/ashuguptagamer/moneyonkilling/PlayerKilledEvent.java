package com.github.ashuguptagamer.moneyonkilling;

import me.realized.tokenmanager.api.TokenManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerKilledEvent implements Listener {

    private final Plugin plugin;

    public PlayerKilledEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {

        final Economy economy = MoneyOnKilling.economy;
        final TokenManager tokenManager = MoneyOnKilling.tokenManager;
        final Player entity = event.getEntity();
        final Player killer = entity.getKiller();
        final double balance = economy.getBalance(entity);

        if (killer != null) {

            economy.withdrawPlayer(entity, Math.min(balance, plugin.getConfig().getDouble("take-money-on-death", 20)));
            entity.sendMessage(plugin.getConfig().getString(ChatColor.translateAlternateColorCodes('&', "money-remove-message")));

            final OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(killer.getUniqueId());
            if (economy.hasAccount(offlinePlayer)) {

                long tokenAdded = plugin.getConfig().getLong("token-on-death", 10);
                double moneyAdded = plugin.getConfig().getDouble("money-on-death", 10);

                if (killer.hasPermission("mok.token-multiplier.level_5")) {
                    int multipliedToken = (int) (plugin.getConfig().getDouble("multipliers-token.level_5") * plugin.getConfig().getInt("token-on-death", 10));
                    tokenManager.addTokens(killer, multipliedToken);
                    tokenAdded = multipliedToken;
                } else if (killer.hasPermission("mok.token-multiplier.level_4")) {
                    int multipliedToken = (int) (plugin.getConfig().getDouble("multipliers-token.level_4") * plugin.getConfig().getInt("token-on-death", 10));
                    tokenManager.addTokens(killer, multipliedToken);
                    tokenAdded = multipliedToken;
                } else if (killer.hasPermission("mok.token-multiplier.level_3")) {
                    int multipliedToken = (int) (plugin.getConfig().getDouble("multipliers-token.level_3") * plugin.getConfig().getInt("token-on-death", 10));
                    tokenManager.addTokens(killer, multipliedToken);
                    tokenAdded = multipliedToken;
                } else if (killer.hasPermission("mok.token-multiplier.level_2")) {
                    int multipliedToken = (int) (plugin.getConfig().getDouble("multipliers-token.level_2") * plugin.getConfig().getInt("token-on-death", 10));
                    tokenManager.addTokens(killer, multipliedToken);
                    tokenAdded = multipliedToken;
                } else if (killer.hasPermission("mok.token-multiplier.level_1")) {
                    int multipliedToken = (int) (plugin.getConfig().getDouble("multipliers-token.level_1") * plugin.getConfig().getInt("token-on-death", 10));
                    tokenManager.addTokens(killer, multipliedToken);
                    tokenAdded = multipliedToken;
                }
                //
                if (killer.hasPermission("mok.money-multiplier.level_5")) {
                    double multipliedMoney = plugin.getConfig().getDouble("multipliers-money.level_5") * plugin.getConfig().getDouble("money-on-death", 10);

                    economy.depositPlayer(offlinePlayer, multipliedMoney);
                    moneyAdded = multipliedMoney;
                } else if (killer.hasPermission("mok.money-multiplier.level_4")) {
                    double multipliedMoney = plugin.getConfig().getDouble("multipliers-money.level_4") * plugin.getConfig().getDouble("money-on-death", 10);

                    economy.depositPlayer(offlinePlayer, multipliedMoney);
                    moneyAdded = multipliedMoney;
                } else if (killer.hasPermission("mok.money-multiplier.level_3")) {
                    double multipliedMoney = plugin.getConfig().getDouble("multipliers-money.level_3") * plugin.getConfig().getDouble("money-on-death", 10);

                    economy.depositPlayer(offlinePlayer, multipliedMoney);
                    moneyAdded = multipliedMoney;
                } else if (killer.hasPermission("mok.money-multiplier.level_2")) {
                    double multipliedMoney = plugin.getConfig().getDouble("multipliers-money.level_2") * plugin.getConfig().getDouble("money-on-death", 10);

                    economy.depositPlayer(offlinePlayer, multipliedMoney);
                    moneyAdded = multipliedMoney;
                } else if (killer.hasPermission("mok.money-multiplier.level_1")) {
                    double multipliedMoney = plugin.getConfig().getDouble("multipliers-money.level_1") * plugin.getConfig().getDouble("money-on-death", 10);

                    economy.depositPlayer(offlinePlayer, multipliedMoney);
                    moneyAdded = multipliedMoney;
                }
                killer.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("money-add-message").replace("%money%", String.valueOf(moneyAdded))));
                killer.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("token-add-message").replace("%token%", String.valueOf(tokenAdded))));
            } else {
                plugin.getLogger().info(ChatColor.RED + "Something went wrong");
            }
        }
    }
}