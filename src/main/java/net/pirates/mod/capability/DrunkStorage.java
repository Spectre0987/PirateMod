package net.pirates.mod.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.pirates.mod.Pirate;

public class DrunkStorage implements IStorage {

	@CapabilityInject(IDrunk.class)
	public static final Capability<CapabilityDrunk> DRUNK = null;
	
	@Override
	public NBTBase writeNBT(Capability capability, Object instance, EnumFacing side) {
		return ((CapabilityDrunk)instance).writeNBT();
	}

	@Override
	public void readNBT(Capability capability, Object instance, EnumFacing side, NBTBase nbt) {
		((CapabilityDrunk)instance).readNBT((NBTTagCompound)nbt);
	}

	public static class DrunkProvider implements ICapabilityProvider{

		CapabilityDrunk cap;
		
		public DrunkProvider(CapabilityDrunk drunk) {
			this.cap = drunk;
		}
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return capability == DRUNK;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			return capability == DRUNK ? (T)cap : null;
		}
		
	}
	
	@EventBusSubscriber(modid = Pirate.MODID)
	public static class DrunkEvents{
		
		@SubscribeEvent
		public static void tickDrunk(PlayerTickEvent event) {
			CapabilityDrunk drunk = event.player.getCapability(DRUNK, null);
			if(drunk != null) {
				drunk.tickDrunk(event.player);
				if(!event.player.world.isRemote && drunk.isDirty()) {
					drunk.sync(event.player);
				}
			}
		}
		
		@SubscribeEvent
		public static void attachDrunk(AttachCapabilitiesEvent<Entity> event) {
			if(event.getObject() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)event.getObject();
				if(!player.hasCapability(DRUNK, null)) {
					event.addCapability(new ResourceLocation(Pirate.MODID, "drunk"), new DrunkProvider(new CapabilityDrunk()));
				}
			}
		}
	}
}
