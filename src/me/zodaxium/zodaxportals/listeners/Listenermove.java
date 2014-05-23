package me.zodaxium.zodaxportals.listeners;

import me.zodaxium.zodaxportals.ZodaxPortals;
import me.zodaxium.zodaxportals.portal.Portal;
import me.zodaxium.zodaxportals.portal.PortalManager;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Listenermove implements Listener{

	ZodaxPortals plugin;
	
	public Listenermove(ZodaxPortals plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler(ignoreCancelled=true)
	public void onPlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(e.getTo().getBlock().equals(e.getFrom().getBlock())) return;		
		Portal portal = PortalManager.getInstance().inPortal(e.getTo());
		if(portal == null) return;
		e.setCancelled(true);
		Location l = e.getFrom();
		l.setYaw(l.getYaw() + 180);
		p.teleport(e.getFrom());
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(portal.getDest());
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}
}
