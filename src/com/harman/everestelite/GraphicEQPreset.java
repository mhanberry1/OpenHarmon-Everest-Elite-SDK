// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GraphicEQPreset.java

package com.harman.everestelite;


public final class GraphicEQPreset extends Enum
{

    public static GraphicEQPreset[] values()
    {
        return (GraphicEQPreset[])$VALUES.clone();
    }

    public static GraphicEQPreset valueOf(String name)
    {
        return (GraphicEQPreset)Enum.valueOf(com/harman/everestelite/GraphicEQPreset, name);
    }

    private GraphicEQPreset(String s, int i, int value)
    {
        super(s, i);
        if(value >= 0 && value <= 4)
            this.value = value;
        else
            throw new IndexOutOfBoundsException();
    }

    public static GraphicEQPreset from(int value)
    {
        GraphicEQPreset var1[] = values();
        int var2 = var1.length;
        for(int var3 = 0; var3 < var2; var3++)
        {
            GraphicEQPreset v = var1[var3];
            if(value == v.value)
                return v;
        }

        throw new RuntimeException(String.format("invalid GraphicEQPreset value 0x%02x", new Object[] {
            Integer.valueOf(value)
        }));
    }

    public int value()
    {
        return value;
    }

    public static final GraphicEQPreset Off;
    public static final GraphicEQPreset Jazz;
    public static final GraphicEQPreset Vocal;
    public static final GraphicEQPreset Bass;
    public static final GraphicEQPreset User;
    public static final GraphicEQPreset First;
    public static final GraphicEQPreset Last;
    public static final GraphicEQPreset NumPresets;
    private final int value;
    private static final GraphicEQPreset $VALUES[];

    static 
    {
        Off = new GraphicEQPreset("Off", 0, 0);
        Jazz = new GraphicEQPreset("Jazz", 1, 1);
        Vocal = new GraphicEQPreset("Vocal", 2, 2);
        Bass = new GraphicEQPreset("Bass", 3, 3);
        User = new GraphicEQPreset("User", 4, 4);
        First = new GraphicEQPreset("First", 5, 1);
        Last = new GraphicEQPreset("Last", 6, 4);
        NumPresets = new GraphicEQPreset("NumPresets", 7, 4);
        $VALUES = (new GraphicEQPreset[] {
            Off, Jazz, Vocal, Bass, User, First, Last, NumPresets
        });
    }
}
