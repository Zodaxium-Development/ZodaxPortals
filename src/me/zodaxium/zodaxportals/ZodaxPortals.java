package me.zodaxium.zodaxportals;

import me.zodaxium.zodaxportals.commands.CommandManager;
import me.zodaxium.zodaxportals.listeners.Listenermove;
import me.zodaxium.zodaxportals.portal.PortalManager;

import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class ZodaxPortals extends JavaPlugin{

	private static ZodaxPortals instance;
	public WorldEditPlugin we;
	
	public void onEnable(){
		instance = this;
		
		we = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");
		
		if(we == null)
			setEnabled(false);
		
		saveDefaultConfig();
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		PortalManager.getInstance().loadPortals();
		
		new CommandManager(this, "portal");
		new Listenermove(this);
	}
	
	public static ZodaxPortals getInstance(){
		return instance;
	}
}
