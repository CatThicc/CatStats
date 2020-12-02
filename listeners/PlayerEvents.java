package me.thicccat.catstats.listeners;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

// This is for craftbukkit support

import net.minecraft.server.v1_16_R2.ChatMessageType;
import net.minecraft.server.v1_16_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R2.PacketPlayOutChat;
import net.minecraft.server.v1_16_R2.PlayerConnection;

public class PlayerEvents implements Listener {

	Plugin pl = null;

	public PlayerEvents(Plugin plugin) {
		this.pl = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		if (p.isOp() == true || p.hasPermission("catstats.update")) {
			String line;
			PluginDescriptionFile pdf = pl.getDescription();
			String version = pdf.getVersion();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					new URL("https://raw.githubusercontent.com/CatThicc/CatStats/main/currentVersion.txt").openStream(),
					Charset.forName("UTF-8")))) {
				while ((line = br.readLine()) != null) {
					String[] words = line.split(" ");
					if (!(words[0].equalsIgnoreCase(version))) {
						String jsonText = "{\"text\":\"§bCatStats Version §3{0} §bis now available.\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://github.com/CatThicc/CatStats/releases/latest\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":{\"text\":\"GitHub\",\"bold\":true,\"color\":\"dark_aqua\"}}}"
								.replace("{0}", words[0]);
						PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
						PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(jsonText),
								ChatMessageType.CHAT, p.getUniqueId());
						connection.sendPacket(packet);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
