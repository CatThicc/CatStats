package me.thicccat.catstats.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.thicccat.catstats.Main;

public class Blocksmined implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private Main plugin;

	public Blocksmined(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("blocksmined").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (!(sender instanceof Player)) {
				@SuppressWarnings("deprecation")
				OfflinePlayer other = sender.getServer().getOfflinePlayer(args[0]);
				if (other.hasPlayedBefore() == true) {
					if (args.length >= 2) {
						if (Material.getMaterial(args[1].toUpperCase()) != null) {
							Material block = Material.getMaterial(args[1].toUpperCase());
							String blockName = block.name().toLowerCase().replace("_", " ");
							switch(other.getStatistic(Statistic.MINE_BLOCK, block)) {
							case 0:
								sender.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has never mined " + ChatColor.DARK_AQUA + blockName);
								if (other.getStatistic(Statistic.PICKUP, block) <= 0) {
									sender.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has never picked up " + ChatColor.DARK_AQUA + blockName);
								} else {
									sender.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has picked up " + ChatColor.DARK_AQUA + other.getStatistic(Statistic.PICKUP, block) + " " + blockName);
								}
							break;
							default:
								sender.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has mined " + ChatColor.DARK_AQUA + other.getStatistic(Statistic.MINE_BLOCK, block) + " " +  blockName);
								if (other.getStatistic(Statistic.PICKUP, block) <= 0) {
									sender.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has never picked up " + ChatColor.DARK_AQUA + blockName);
								} else {
									sender.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has picked up " + ChatColor.DARK_AQUA + other.getStatistic(Statistic.PICKUP, block) + " " + blockName);
								}
							break;
							}
						} else {
							sender.sendMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Invalid block.");
						}
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Invalid block.");
					}
				} else {
					sender.sendMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Player not found.");
				}
				return true;
			}
			Player p = (Player) sender;
			if (p.hasPermission("catx.blocksmined")) {
				if (args.length == 0) {
					p.sendMessage(ChatColor.GRAY + "Usage: /blocksmined <player> <block_name>");
				} else {
					@SuppressWarnings("deprecation")
					OfflinePlayer other = p.getServer().getOfflinePlayer(args[0]);
					if (other.hasPlayedBefore() == true) {
						if (args.length >= 2) {
							if (Material.getMaterial(args[1].toUpperCase()) != null) {
								Material block = Material.getMaterial(args[1].toUpperCase());
								String blockName = block.name().toLowerCase().replace("_", " ");
								switch(other.getStatistic(Statistic.MINE_BLOCK, block)) {
								case 0:
									p.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has never mined " + ChatColor.DARK_AQUA + blockName);
									if (other.getStatistic(Statistic.PICKUP, block) <= 0) {
										p.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has never picked up " + ChatColor.DARK_AQUA + blockName);
									} else {
										p.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has picked up " + ChatColor.DARK_AQUA + other.getStatistic(Statistic.PICKUP, block) + " " + blockName);
									}
								break;
								default:
									p.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has mined " + ChatColor.DARK_AQUA + other.getStatistic(Statistic.MINE_BLOCK, block) + " " +  blockName);
									if (other.getStatistic(Statistic.PICKUP, block) <= 0) {
										p.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has never picked up " + ChatColor.DARK_AQUA + blockName);
									} else {
										p.sendMessage(ChatColor.DARK_AQUA + other.getName() + ChatColor.AQUA + " has picked up " + ChatColor.DARK_AQUA + other.getStatistic(Statistic.PICKUP, block) + " " + blockName);
									}
								break;
								}
							} else {
								p.sendMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Invalid block.");
							}
						} else {
							p.sendMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Invalid block.");
						}
					} else {
						p.sendMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Player not found.");
					}
				}
				return true;
			} else {
				p.sendMessage(ChatColor.RED + "You don't have permission to do this.");
			}
		return false;
	}
}
