package net.minecraft.src;

import org.lwjgl.input.Keyboard;

public class GuiMultiplayer extends GuiScreen {
	private GuiScreen parentScreen;
	private GuiDisableButton field_22111_h;

	public GuiMultiplayer(GuiScreen var1) {
		this.parentScreen = var1;
	}

	public void updateScreen() {
		this.field_22111_h.func_22070_b();
	}

	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, var1.translateKey("multiplayer.connect")));
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, var1.translateKey("gui.cancel")));
		String var2 = this.mc.gameSettings.lastServer.replaceAll("_", ":");
		((GuiButton)this.controlList.get(0)).enabled = var2.length() > 0;
		this.field_22111_h = new GuiDisableButton(this.fontRenderer, this.width / 2 - 100, this.height / 4 - 10 + 50 + 18, 200, 20, var2);
		this.field_22111_h.field_22082_a = true;
		this.field_22111_h.func_22066_a(32);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id == 1) {
				this.mc.displayGuiScreen(this.parentScreen);
			} else if(var1.id == 0) {
				String var2 = this.field_22111_h.func_22071_a();
				this.mc.gameSettings.lastServer = var2.replaceAll(":", "_");
				this.mc.gameSettings.saveOptions();
				this.mc.displayGuiScreen(new GuiConnecting(this.mc, var2));
			}

		}
	}

	protected void keyTyped(char var1, int var2) {
		this.field_22111_h.func_22072_a(var1, var2);
		if(var1 == 13) {
			this.actionPerformed((GuiButton)this.controlList.get(0));
		}

		((GuiButton)this.controlList.get(0)).enabled = this.field_22111_h.func_22071_a().length() > 0;
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		super.mouseClicked(var1, var2, var3);
		this.field_22111_h.func_22069_a(var1, var2, var3);
	}

	public void drawScreen(int var1, int var2, float var3) {
		StringTranslate var4 = StringTranslate.getInstance();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, var4.translateKey("multiplayer.title"), this.width / 2, this.height / 4 - 60 + 20, 16777215);
		this.drawString(this.fontRenderer, var4.translateKey("multiplayer.info1"), this.width / 2 - 140, this.height / 4 - 60 + 60 + 0, 10526880);
		this.drawString(this.fontRenderer, var4.translateKey("multiplayer.info2"), this.width / 2 - 140, this.height / 4 - 60 + 60 + 9, 10526880);
		this.drawString(this.fontRenderer, var4.translateKey("multiplayer.ipinfo"), this.width / 2 - 140, this.height / 4 - 60 + 60 + 36, 10526880);
		this.field_22111_h.func_22067_c();
		super.drawScreen(var1, var2, var3);
	}
}
