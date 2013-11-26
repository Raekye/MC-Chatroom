package com.creatifcubed.minecraft.mc_chatroom.models;

import javax.sound.sampled.{ TargetDataLine, AudioFormat, AudioSystem, DataLine };
import java.util.Arrays;

class AudioSource {
  final val microphone: TargetDataLine = AudioSystem.getTargetDataLine(AudioSource.FORMAT);
  final val buf: Array[Byte] = new Array[Byte](microphone.getBufferSize() / 4);
  try {
    this.microphone.open();
  }
  
  def start(): Unit = {
    this.microphone.start();
  }
  
  def stop(): Unit = {
    this.microphone.stop();
  }
  
  def read(): Array[Byte] = {
    val numBytes = this.microphone.read(this.buf, 0, this.buf.length);
    if (numBytes == -1) {
      return null;
    }
    return Arrays.copyOfRange(this.buf, 0, numBytes);
  }
}

object AudioSource {
  final val SAMPLE_RATE: Float = 8000;
  final val SAMPLE_SIZE_BITS: Int = 8;
  final val NUM_CHANNELS: Int = 1;
  final val FORMAT: AudioFormat = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE_BITS, NUM_CHANNELS, true, true);
}