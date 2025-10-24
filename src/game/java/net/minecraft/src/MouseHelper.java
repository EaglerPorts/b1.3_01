package net.minecraft.src;

import org.lwjgl.input.Mouse;

public class MouseHelper {
	public int deltaX;
	public int deltaY;

	public void func_774_a() {
		Mouse.setGrabbed(true);
		this.deltaX = 0;
		this.deltaY = 0;
	}

	public void func_773_b() {
		Mouse.setGrabbed(false);
	}

	public void mouseXYChange() {
		this.deltaX = Mouse.getDX();
		this.deltaY = Mouse.getDY();
	}
}
