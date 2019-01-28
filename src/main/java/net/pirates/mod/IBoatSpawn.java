package net.pirates.mod;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public interface IBoatSpawn <T extends Entity>{
	
	T spawn(World world);

}
