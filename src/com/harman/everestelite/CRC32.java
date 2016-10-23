// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRC32.java

package com.harman.everestelite;


final class CRC32
{

    CRC32()
    {
    }

    public static long calculate(long seed, byte buffer[], int from, int to)
    {
        for(; from < to; from++)
        {
            seed ^= buffer[from] & 0xff;
            seed = seed >> 4 ^ kCRC32Table[(int)(seed & 15L)];
            seed = seed >> 4 ^ kCRC32Table[(int)(seed & 15L)];
        }

        return seed;
    }

    public static long calculate(long seed, byte buffer[])
    {
        return calculate(seed, buffer, 0, buffer.length);
    }

    private static long phase(long value)
    {
        return (value & 1L) != 1L ? value >> 1 : value >> 1 ^ 0xedb88320L;
    }

    private static long compute(long value)
    {
        return phase(phase(phase(phase(value))));
    }

    private static final long kCRC32Polynomial = 0xedb88320L;
    private static final long kCRC32Table[] = {
        compute(0L), compute(1L), compute(2L), compute(3L), compute(4L), compute(5L), compute(6L), compute(7L), compute(8L), compute(9L), 
        compute(10L), compute(11L), compute(12L), compute(13L), compute(14L), compute(15L)
    };

}
