package com.creatifcubed.minecraft.mc_chatroom;

import cpw.mods.fml.client.registry.{ KeyBindingRegistry };

class CommonProxy {
  def registerRegistries(): Unit = {
    KeyBindingRegistry.registerKeyBinding(new KeyHandler());
  }
}