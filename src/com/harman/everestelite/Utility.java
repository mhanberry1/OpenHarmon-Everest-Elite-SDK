// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Utility.java

package com.harman.everestelite;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Utility
{

    public Utility()
    {
    }

    public static boolean getBoolean(byte buffer[], int offset)
    {
        return getUnsignedInt(buffer, offset) != 0L;
    }

    public static float getFloat(byte buffer[], int offset)
    {
        return ByteBuffer.wrap(buffer, offset, 4).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    public static int getInt(byte buffer[], int offset)
    {
        return (int)getUnsignedInt(buffer, offset);
    }

    public static long getUnsignedInt(byte buffer[], int offset)
    {
        long result = (long)buffer[offset++] & 255L;
        result |= ((long)buffer[offset++] & 255L) << 8;
        result |= ((long)buffer[offset++] & 255L) << 16;
        result |= ((long)buffer[offset] & 255L) << 24;
        return result;
    }

    public static double getAccelValue(byte buffer[], int offset)
    {
        double result = ((double)(buffer[offset] & 0xff | buffer[offset + 1] << 8 | buffer[offset + 2] << 16 | buffer[offset + 3] << 24) / 16384D) * 9.8000000000000007D;
        return result;
    }

    public static double getGyroValue(byte buffer[], int offset)
    {
        double result = ((double)(buffer[offset] & 0xff | buffer[offset + 1] << 8 | buffer[offset + 2] << 16 | buffer[offset + 3] << 24) / 131D) * 0.017453300000000001D;
        return result;
    }

    public static double getMagnetoValue(byte buffer[], int offset)
    {
        double result = (double)(buffer[offset] & 0xff | buffer[offset + 1] << 8 | buffer[offset + 2] << 16 | buffer[offset + 3] << 24) * 0.14999999999999999D;
        return result;
    }

    public static void putBoolean(boolean value, byte buffer[], int offset)
    {
        putUnsignedInt(value ? 1L : 0L, buffer, offset);
    }

    public static void putFloat(float value, byte buffer[], int offset)
    {
        ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(value).get(buffer, offset, 4);
    }

    public static void putInt(int value, byte buffer[], int offset)
    {
        putUnsignedInt(value, buffer, offset);
    }

    public static void putUnsignedInt(long value, byte buffer[], int offset)
    {
        buffer[offset++] = (byte)(int)(value & 255L);
        buffer[offset++] = (byte)(int)(value >> 8 & 255L);
        buffer[offset++] = (byte)(int)(value >> 16 & 255L);
        buffer[offset] = (byte)(int)(value >> 24 & 255L);
    }
}
