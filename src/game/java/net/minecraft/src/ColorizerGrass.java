package net.minecraft.src;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.opengl.ImageData;

public class ColorizerGrass {
	private static final int[] grassBuffer = new int[65536];

	public static int getGrassColor(double var0, double var2) {
		var2 *= var0;
		int var4 = (int)((1.0D - var0) * 255.0D);
		int var5 = (int)((1.0D - var2) * 255.0D);
		return grassBuffer[var5 << 8 | var4];
	}

	static {
		try {
			ImageData var0 = ImageData.loadImageFile(EagRuntime.getResourceStream("/misc/grasscolor.png"));
			var0.getRGB(0, 0, 256, 256, grassBuffer, 0, 256);
		} catch (Exception var1) {
			var1.printStackTrace();
		}

	}
}
