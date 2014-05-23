package me.zodaxium.zodaxportals;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Reference{
	public static String PREFIX = colorize("&9[&6Zodaxium&9] ");
	public static String PERM_ADMIN = colorize("zodaxium.admin");
	public static String PERM_DONATE = colorize("zodaxium.donator");
	public static String PERM_USER = colorize("zodaxium.user");
	public static String DENY_PERM = colorize(PREFIX + "&aPermission Denied");
	public static String DENY_USER = colorize(PREFIX + "&aPlayer is not online");
	public static String DENY_CONSOLE = colorize(PREFIX + "&aOnly players can use this command!");
	
	public static String colorize(String message){
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public static void nameItem(ItemStack item, String message, String type){
		ItemMeta im = item.getItemMeta();
		if(type.equals("NAME")){
			im.setDisplayName(colorize(message));
			item.setItemMeta(im);
		}else if(type.equals("LORE")){
			im.setLore(Arrays.asList(colorize(message)));
			item.setItemMeta(im);
		}
	}
}
