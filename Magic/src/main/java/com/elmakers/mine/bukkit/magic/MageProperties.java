package com.elmakers.mine.bukkit.magic;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import javax.annotation.Nullable;
import java.util.Set;

public class MageProperties extends CasterProperties {
    private final Mage mage;

    public MageProperties(Mage mage) {
        super(MagicPropertyType.MAGE, mage.getController());
        this.mage = mage;
    }

    @Override
    protected void rebuildEffectiveConfiguration(ConfigurationSection effectiveConfiguration) {
        // Not really doing anything here, this is the root of the tree.
    }

    @Override
    public void describe(CommandSender sender, @Nullable Set<String> ignoreProperties) {
        sender.sendMessage(ChatColor.AQUA + "Properties for Mage " + ChatColor.GREEN + mage.getName());

        Set<String> hideKeys = ignoreProperties;
        MageClass activeClass = mage.getActiveClass();
        if (activeClass != null) {
            hideKeys = activeClass.getEffectiveConfiguration().getKeys(false);
            if (ignoreProperties != null) {
                hideKeys.addAll(ignoreProperties);
            }
        }

        super.describe(sender, hideKeys);

        if (activeClass != null) {
            sender.sendMessage(ChatColor.AQUA + "Active Class: " + ChatColor.GREEN + activeClass.getTemplate().getKey());
            activeClass.describe(sender, ignoreProperties);
        }
    }
}
