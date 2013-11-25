package com.creatifcubed.minecraft.mc_chatroom.network;

import net.minecraft.entity.player.EntityPlayerMP;
import _root_.cpw.mods.fml.common.network.{ Player, PacketDispatcher };

import net.minecraft.command.{ CommandBase, ICommandSender };

import com.creatifcubed.minecraft.mc_chatroom.MCChatroom;

class MCChatroomCommand extends CommandBase {
  override def getCommandName(): String = {
    return "siangels";
  }
  
  override def getCommandUsage(icommandsender: ICommandSender): String = {
    return "siangels <action> <mod id> <arguments>";
  }
  
  override def processCommand(icommandsender: ICommandSender, args: Array[String]): Unit = {
    icommandsender match {
      case player: EntityPlayerMP => {
        var x = 10;
        MCChatroom.log.info("World is remote %b".format(player.worldObj.isRemote));
      };
      case _ => throw new ClassCastException();
    }
  }
}