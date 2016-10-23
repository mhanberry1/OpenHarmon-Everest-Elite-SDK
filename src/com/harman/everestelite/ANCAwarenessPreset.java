// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ANCAwarenessPreset.java

package com.harman.everestelite;


public final class ANCAwarenessPreset extends Enum
{

    public static ANCAwarenessPreset[] values()
    {
        return (ANCAwarenessPreset[])$VALUES.clone();
    }

    public static ANCAwarenessPreset valueOf(String name)
    {
        return (ANCAwarenessPreset)Enum.valueOf(com/harman/everestelite/ANCAwarenessPreset, name);
    }

    private ANCAwarenessPreset(String s, int i, int value)
    {
        super(s, i);
        if(value < 0 || value > 3)
            value = -1;
        this.value = value;
    }

    public static ANCAwarenessPreset from(int value)
    {
        ANCAwarenessPreset var1[] = values();
        int var2 = var1.length;
        for(int var3 = 0; var3 < var2; var3++)
        {
            ANCAwarenessPreset v = var1[var3];
            if(value == v.value)
                return v;
        }

        throw new RuntimeException(String.format("invalid ANCAwarenessPreset value 0x%02x", new Object[] {
            Integer.valueOf(value)
        }));
    }

    public int value()
    {
        return value;
    }

    public static final ANCAwarenessPreset None;
    public static final ANCAwarenessPreset Low;
    public static final ANCAwarenessPreset Medium;
    public static final ANCAwarenessPreset High;
    public static final ANCAwarenessPreset First;
    public static final ANCAwarenessPreset Last;
    public static final ANCAwarenessPreset NumPresets;
    private final int value;
    private static final ANCAwarenessPreset $VALUES[];

    static 
    {
        None = new ANCAwarenessPreset("None", 0, 0);
        Low = new ANCAwarenessPreset("Low", 1, 1);
        Medium = new ANCAwarenessPreset("Medium", 2, 2);
        High = new ANCAwarenessPreset("High", 3, 3);
        First = new ANCAwarenessPreset("First", 4, 0);
        Last = new ANCAwarenessPreset("Last", 5, 3);
        NumPresets = new ANCAwarenessPreset("NumPresets", 6, 4);
        $VALUES = (new ANCAwarenessPreset[] {
            None, Low, Medium, High, First, Last, NumPresets
        });
    }
}
