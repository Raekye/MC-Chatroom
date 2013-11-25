package com.creatifcubed.minecraft.mc_chatroom.network;

import java.util.Arrays;

case class AudioPacket(val channel: Int, data: Array[Byte]) {
  def toBytes(): Array[Byte] = {
    val bytes: Array[Byte] = new Array[Byte](4 + this.data.length);
    for (i <- 0 until 4) {
      bytes(i) = ((this.channel >> (8 * (4 - i + 1))) & 0xFF).toByte;
    }
    System.arraycopy(this.data, 0, bytes, 4, this.data.length)
    return bytes;
  }
}

object AudioPacket {
  def fromBytes(bytes: Array[Byte]): AudioPacket = {
    var channel: Int = 0;
    for (i <- 0 until 4) {
      channel = (channel << 8) + bytes(i);
    }
    return new AudioPacket(channel, Arrays.copyOfRange(bytes, 4, bytes.length - 4));
  }
}