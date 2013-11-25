package com.creatifcubed.minecraft.mc_chatroom.models;

import java.net.{ DatagramSocket };

class Chatroom(val port: Int) {
  
  
  def run(): Unit = {
    val socket: DatagramSocket = new DatagramSocket(port);
    val recievedData = new Array[Byte](Chatroom.DATAGRAM_PACKET_SIZE);
    while (true) {
      //
    }
  }
}

object Chatroom {
  val DATAGRAM_PACKET_SIZE: Int = 1024;
}