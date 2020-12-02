package me.thicccat.catstats.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.thicccat.catstats.Main;

public class CatStats implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private Main plugin;

	public CatStats(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("catstats").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (!(sender instanceof Player)) {
				
				return true;
			}
			Player p = (Player) sender;
			if (p.hasPermission("catx.catstats")) {
				p.sendMessage(ChatColor.AQUA + "Running " + ChatColor.DARK_AQUA + "CatStats v1.16-1 " + ChatColor.AQUA + "by " + ChatColor.DARK_AQUA + "ThiccCat.");	
				return true;
			} else {
				p.sendMessage(ChatColor.RED + "You don't have permission to do this.");
			}
		return false;
	}
}
