package com.creatifcubed.minecraft.mc_chatroom.models;

import scala.collection.mutable.{ Queue, ListBuffer };
import scala.util.control.Breaks._;

import java.io.InputStream;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class QueuedInputStream extends InputStream {
  val waiter = new Object();
  val bufferLock = new ReentrantReadWriteLock();
  val buffer = new Queue[Byte]();
  
  def write(x: Int): Unit = {
    this.bufferLock.writeLock().lock();
    try {
      this.buffer.enqueue(x.toByte);
      this.waiter.synchronized {
        this.waiter.notifyAll();
      }
    } finally {
      this.bufferLock.writeLock().unlock();
    }
  }
  
  override def read(): Int = {
    breakable {
      while (true) {
        this.bufferLock.readLock().lock();
        try {
          if (!this.buffer.isEmpty) {
            break;
          }
        } finally {
          this.bufferLock.readLock.unlock();
        }
        this.waiter.synchronized {
          this.waiter.wait();
        }
      }
    }
    this.bufferLock.writeLock().lock();
    try {
      return this.buffer.dequeue().toInt;
    } finally {
      this.bufferLock.writeLock().unlock();
    }
  }
}