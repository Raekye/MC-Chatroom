package com.creatifcubed.minecraft.mc_chatroom.network;

import java.io.{ IOException, InputStream, ByteArrayOutputStream, DataOutputStream, ByteArrayInputStream, DataInputStream };
import java.lang.reflect.Field;
import net.minecraft.network.packet.Packet250CustomPayload;
import com.google.common.base.Charsets;
import com.google.common.io.{ ByteStreams, Closeables };
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class PacketFactory(val gson: Gson = new Gson()) {
  
  def serializeJson(channel: String, data: AnyRef): Packet250CustomPayload = {
    return new Packet250CustomPayload(channel, this.gson.toJson(data).getBytes(Charsets.UTF_8));
  }
  
  def deserializeJson[T](data: Array[Byte], clazz: Class[T]): T = {
    return this.gson.fromJson(new String(data, Charsets.UTF_8), clazz);
  }
  
  def serializePrimitives(channel: String, data: AnyRef): Packet250CustomPayload = {
    val fields: Array[Field] = data.getClass().getDeclaredFields().sortBy(x => x.getName());
    val buf: ByteArrayOutputStream = new ByteArrayOutputStream();
    val dos: DataOutputStream = new DataOutputStream(buf);
    try {
      for (each <- fields) {
        each.setAccessible(true);
        each.getType() match {
          case java.lang.Integer.TYPE => dos.writeInt(each.getInt(data))
          case java.lang.Short.TYPE => dos.writeShort(each.getShort(data));
          case java.lang.Character.TYPE => dos.writeChar(each.getChar(data));
          case java.lang.Byte.TYPE => dos.writeByte(each.getByte(data));
          case java.lang.Boolean.TYPE => dos.writeBoolean(each.getBoolean(data));
          case java.lang.Double.TYPE => dos.writeDouble(each.getDouble(data));
          case java.lang.Float.TYPE => dos.writeFloat(each.getFloat(data));
          case _ => null; // TODO: error
        }
      }
    } catch {
      case e: ReflectiveOperationException => throw new RuntimeException(e);
    } finally {
      try {
        buf.close();
      } catch {
        case _: IOException => null; // nothing
      }
      try {
        dos.close();
      } catch {
        case _: IOException => null; // nothing
      }
    }
    return new Packet250CustomPayload(channel, buf.toByteArray());
  }
  
  def deserializePrimitives[T](data: Array[Byte], clazz: Class[T]): T = {
    val obj: T = clazz.newInstance();
    val buf: ByteArrayInputStream = new ByteArrayInputStream(data);
    val dis: DataInputStream = new DataInputStream(buf);
    val fields: Array[Field] = clazz.getDeclaredFields().sortBy(x => x.getName());
    try {
      for (each <- fields) {
        each.setAccessible(true);
        each.getType() match {
          case java.lang.Integer.TYPE => each.setInt(obj, dis.readInt());
          case java.lang.Short.TYPE => each.setInt(obj, dis.readInt());
          case java.lang.Character.TYPE => each.setInt(obj, dis.readInt());
          case java.lang.Byte.TYPE => each.setInt(obj, dis.readInt());
          case java.lang.Boolean.TYPE => each.setInt(obj, dis.readInt());
          case java.lang.Double.TYPE => each.setInt(obj, dis.readInt());
          case java.lang.Float.TYPE => each.setInt(obj, dis.readInt());
          case _ => null; // TODO: error
        }
      }
    } catch {
      case e: ReflectiveOperationException => throw new RuntimeException(e);
    } finally {
      try {
        buf.close();
      } catch {
        case _: IOException => null; // nothing
      }
      try {
        dis.close();
      } catch {
        case _: IOException => null; // nothing
      }
    }
    return obj;
  }
}