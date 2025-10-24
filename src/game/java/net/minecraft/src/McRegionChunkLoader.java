package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.lax1dude.eaglercraft.internal.vfs2.VFile2;

public class McRegionChunkLoader implements IChunkLoader {
	private final VFile2 field_22184_a;

	public McRegionChunkLoader(VFile2 var1) {
		this.field_22184_a = var1;
	}

	public Chunk loadChunk(World var1, int var2, int var3) throws IOException {
		DataInputStream var4 = RegionFileCache.func_22194_c(this.field_22184_a, var2, var3);
		if(var4 != null) {
			NBTTagCompound var5 = CompressedStreamTools.func_1141_a(var4);
			if(!var5.hasKey("Level")) {
				System.out.println("Chunk file at " + var2 + "," + var3 + " is missing level data, skipping");
				return null;
			} else if(!var5.getCompoundTag("Level").hasKey("Blocks")) {
				System.out.println("Chunk file at " + var2 + "," + var3 + " is missing block data, skipping");
				return null;
			} else {
				Chunk var6 = ChunkLoader.loadChunkIntoWorldFromCompound(var1, var5.getCompoundTag("Level"));
				if(!var6.isAtLocation(var2, var3)) {
					System.out.println("Chunk file at " + var2 + "," + var3 + " is in the wrong location; relocating. (Expected " + var2 + ", " + var3 + ", got " + var6.xPosition + ", " + var6.zPosition + ")");
					var5.setInteger("xPos", var2);
					var5.setInteger("zPos", var3);
					var6 = ChunkLoader.loadChunkIntoWorldFromCompound(var1, var5.getCompoundTag("Level"));
				}

				return var6;
			}
		} else {
			return null;
		}
	}

	public void saveChunk(World var1, Chunk var2) throws IOException {
		var1.checkSessionLock();

		try {
			DataOutputStream var3 = RegionFileCache.func_22190_d(this.field_22184_a, var2.xPosition, var2.zPosition);
			NBTTagCompound var4 = new NBTTagCompound();
			NBTTagCompound var5 = new NBTTagCompound();
			var4.setTag("Level", var5);
			ChunkLoader.storeChunkInCompound(var2, var1, var5);
			CompressedStreamTools.func_1139_a(var4, var3);
			var3.close();
			WorldInfo var6 = var1.func_22144_v();
			var6.func_22297_b(var6.func_22306_g() + (long)RegionFileCache.func_22191_b(this.field_22184_a, var2.xPosition, var2.zPosition));
		} catch (Exception var7) {
			var7.printStackTrace();
		}

	}

	public void saveExtraChunkData(World var1, Chunk var2) throws IOException {
	}

	public void func_814_a() {
	}

	public void saveExtraData() {
	}
}
