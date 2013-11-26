package com.creatifcubed.minecraft.mc_chatroom

object Utils {
  def cleanly[A, B](resource: => A)(cleanup: A => Unit)(code: A => B): B = {
    try {
      return code(resource);
    } finally {
      cleanup(resource);
    }
  }
}