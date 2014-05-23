package me.zodaxium.zodaxportals.portal;

import java.util.List;

import org.bukkit.block.Block;

public class Portal{

	private String name, dest;
	private List<Block> blocks;
	
	public Portal(String name, String dest, List<Block> blocks){
		this.name = name;
		this.dest = dest;
		this.blocks = blocks;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDest(){
		return dest;
	}
	
	public List<Block> getArea(){
		return blocks;
	}
}
