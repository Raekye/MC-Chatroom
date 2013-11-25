package com.creatifcubed.minecraft.mc_chatroom;

import _root_.cpw.mods.fml.client.registry.KeyBindingRegistry.{ KeyHandler => AbstractKeyHandler };
import _root_.cpw.mods.fml.common.TickType;
import net.minecraft.client.settings.KeyBinding;
import java.util.EnumSet;
import org.lwjgl.input.Keyboard;
import net.minecraft.src.ModLoader;

class KeyHandler extends AbstractKeyHandler(Array(
    KeyHandler.KEYBINDING_TOGGLE_MICROPHONE, KeyHandler.KEYBINDING_TOGGLE_CHANNEL)
    , KeyHandler.KEYBINDINGS_REPEATINGS) {
  override def getLabel(): String = {
    return MCChatroom.MOD_ID + "-keys";
  }
  override def keyDown(types: EnumSet[TickType], kb: KeyBinding, tickEnd: Boolean, isRepeat: Boolean): Unit = {
    if (tickEnd) {
      return;
    }
    if (kb.keyCode == KeyHandler.KEYBINDING_TOGGLE_MICROPHONE.keyCode) {
      
    } else if (kb.keyCode == KeyHandler.KEYBINDING_TOGGLE_CHANNEL.keyCode) {
      
    }
  }
  
  override def keyUp(types: EnumSet[TickType], kb: KeyBinding, tickEnd: Boolean): Unit = {
    if (tickEnd) {
      return;
    }
  }
  
  override def ticks(): EnumSet[TickType] = {
    EnumSet.of(TickType.CLIENT);
  }
}

object KeyHandler {
  final val KEYBINDING_TOGGLE_MICROPHONE = new KeyBinding(MCChatroom.MOD_NAME + " - Toggle mute microphone mute", Keyboard.KEY_M);
  final val KEYBINDING_TOGGLE_CHANNEL = new KeyBinding(MCChatroom.MOD_NAME + " - Toggle channel mute", Keyboard.KEY_N);
  final val KEYBINDINGS_REPEATINGS: Array[Boolean] = Array(false, false);
}