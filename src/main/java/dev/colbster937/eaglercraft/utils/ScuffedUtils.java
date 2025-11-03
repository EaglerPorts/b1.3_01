package dev.colbster937.eaglercraft.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.lax1dude.eaglercraft.EagRuntime;
import net.lax1dude.eaglercraft.HString;
import net.lax1dude.eaglercraft.internal.vfs2.VFile2;

public class ScuffedUtils {
  public static File getFileFromVFile(VFile2 vfile) throws IOException {
    File file = new File(vfile.getName());
    FileOutputStream fos = new FileOutputStream(file);
    fos.write(vfile.getAllBytes());
    fos.close();
    return file;
  }

  public static byte[] getBytes(Number n) {
    if (n instanceof Byte)
      return new byte[] { n.byteValue() };
    else if (n instanceof Short)
      return EagRuntime.allocateByteBuffer(2).putShort(n.shortValue()).array();
    else if (n instanceof Integer)
      return EagRuntime.allocateByteBuffer(4).putInt(n.intValue()).array();
    else if (n instanceof Long)
      return EagRuntime.allocateByteBuffer(8).putLong(n.longValue()).array();
    return null;
  }

  public static String getFormattedTime(long ticks) {
    long t = (((ticks % 24000) + 24000) % 24000 + 6000) % 24000;
    long h = t / 1000;
    long m = (t % 1000) * 60 / 1000;
    return I18n.format("command.getTime", ticks / 24000,
        HString.format("%02d:%02d", h, m));
  }
  
  public static String getEnabledDisabled(boolean value) {
    return value ? I18n.format("enabled") : I18n.format("disabled");
  }

  public static boolean isCtrlKeyDown() {
    return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
  }
}
