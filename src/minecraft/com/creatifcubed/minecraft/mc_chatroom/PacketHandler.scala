package com.creatifcubed.minecraft.mc_chatroom;

import _root_.cpw.mods.fml.common.network.{ IPacketHandler, Player, ITinyPacketHandler, PacketDispatcher };
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.{ Packet250CustomPayload, Packet131MapData, NetHandler };
import net.minecraft.entity.player.EntityPlayer;

class PacketHandler extends IPacketHandler {
  val handlers: Map[String, ChannelHandler] = Map(MCChatroom.MOD_NETWORKCHANNEL_A -> new ChannelAHandler());
  
  def onPacketData(manager: INetworkManager, packet: Packet250CustomPayload, player: Player): Unit = {
    this.handlers.get(packet.channel) match {
      case Some(handler) => handler.onPacketData(manager, packet, player.asInstanceOf[EntityPlayer]);
      case None => MCChatroom.log.info(String.format("Unknown channel {%s}", packet.channel));
    }
  }
}

trait ChannelHandler {
  def onPacketData(manager: INetworkManager, packet: Packet250CustomPayload, player: EntityPlayer): Unit;
}

class ChannelAHandler extends ChannelHandler {
  def onPacketData(manager: INetworkManager, packet: Packet250CustomPayload, player: EntityPlayer): Unit = {
    return;
  }
}