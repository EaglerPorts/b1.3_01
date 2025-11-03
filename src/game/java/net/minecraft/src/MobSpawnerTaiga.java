package net.minecraft.src;

import java.util.Random;

public class MobSpawnerTaiga extends MobSpawnerBase {
	public WorldGenerator getRandomWorldGenForTrees(Random var1) {
		return (WorldGenerator)(var1.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2());
	}
}
