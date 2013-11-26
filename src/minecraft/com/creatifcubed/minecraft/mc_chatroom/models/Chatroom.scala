package com.creatifcubed.minecraft.mc_chatroom.models;

import scala.util.control.Breaks._;
import java.net.{ DatagramSocket, DatagramPacket, SocketTimeoutException };
import com.creatifcubed.minecraft.mc_chatroom.network.AudioPacket;
import com.creatifcubed.minecraft.mc_chatroom.MCChatroom;

class Chatroom(val port: Int) extends Runnable {

  override def run(): Unit = {
    MCChatroom.log.info("Chatroom starting.");
    val receivedData = new Array[Byte](Chatroom.DATAGRAM_PACKET_SIZE);
    val serverSocket: DatagramSocket = new DatagramSocket(port);
    try {
      breakable {
        while (true) {
          val receivedPacket = new DatagramPacket(receivedData, receivedData.length);
          try {
            serverSocket.receive(receivedPacket);
            new Thread(new Runnable() {
              override def run(): Unit = {
                val data = receivedPacket.getData();
                val audioPacket = AudioPacket.fromBytes(data);
                MCChatroom.log.info("Recieved: " + new String(audioPacket.data, "utf-8"));
              }
            }).start();
          } catch {
            case ex: SocketTimeoutException => {
              if (Thread.currentThread().isInterrupted()) {
                break;
              }
            }
          }
        }
      }
    } finally {
      serverSocket.close();
      MCChatroom.log.info("Chatroom closed.");
    }
  }
}

object Chatroom {
  val DATAGRAM_PACKET_SIZE: Int = 1024;
}