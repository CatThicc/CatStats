package me.thicccat.catstats;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.thicccat.catstats.commands.Blocksmined;
import me.thicccat.catstats.commands.CatStats;
import me.thicccat.catstats.listeners.PlayerEvents;

public class Main extends JavaPlugin {
	
	public static Logger log = Bukkit.getLogger();
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		PlayerEvents pl = new PlayerEvents(this);
		pm.registerEvents(pl, this);
		new CatStats(this);
		new Blocksmined(this);
		String line;
		ConsoleCommandSender console = this.getServer().getConsoleSender();
		PluginDescriptionFile pdf = this.getDescription();
		String version = pdf.getVersion();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/CatThicc/CatStats/main/currentVersion.txt").openStream(), Charset.forName("UTF-8")))) {
		    while ((line = br.readLine()) != null) {
		    	String[] words = line.split(" ");
		    	if (!(words[0].equalsIgnoreCase(version))) {
		    		console.sendMessage(ChatColor.AQUA + "CatStats Version " + ChatColor.DARK_AQUA + words[0] + ChatColor.AQUA + " is now available.");
		    		console.sendMessage(ChatColor.AQUA + "CatStats: " + ChatColor.DARK_AQUA + "https://github.com/CatThicc/CatStats/releases/latest");
		    	}
		    }
		    } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	public void onDisable() {
	}
}

