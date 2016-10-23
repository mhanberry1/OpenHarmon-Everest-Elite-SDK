// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Debug.java

package com.harman.everestelite;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class Debug
{

    public Debug()
    {
    }

    public static String hexify(byte bytes[], int baseAddress, int from, int to)
    {
        StringBuilder s = new StringBuilder();
        int i = from;
        int j;
        for(int n = to; i < n; i += j)
        {
            int o;
            if((o = n - i) > 16)
                o = 16;
            s.append(String.format("%06X:", new Object[] {
                Integer.valueOf(baseAddress + i)
            }));
            for(j = 0; j < o; j++)
                s.append(String.format(" %02X", new Object[] {
                    Byte.valueOf(bytes[i + j])
                }));

            s.append("\n");
        }

        return s.toString();
    }

    public static String hexify(byte bytes[], int from, int to)
    {
        return hexify(bytes, 0, from, to);
    }

    public static String hexify(byte bytes[], int baseAddress)
    {
        return hexify(bytes, baseAddress, 0, bytes.length);
    }

    public static String hexify(byte bytes[])
    {
        return hexify(bytes, 0, 0, bytes.length);
    }

    public static String stackTrace(Throwable t)
    {
        StringWriter writer = new StringWriter();
        t.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }
}
