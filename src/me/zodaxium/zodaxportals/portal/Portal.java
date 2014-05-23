package me.zodaxium.zodaxportals.portal;

import java.util.List;

import org.bukkit.World;
import org.bukkit.block.Block;

public class Portal{

	private String name, dest;
	private World world;
	private List<Block> blocks;
	
	public Portal(String name, World world, String dest, List<Block> blocks){
		this.name = name;
		this.world = world;
		this.dest = dest;
		this.blocks = blocks;
	}
	
	public String getName(){
		return name;
	}
	
	public World getWorld(){
		return world;
	}
	
	public String getDest(){
		return dest;
	}
	
	public List<Block> getArea(){
		return blocks;
	}
}
