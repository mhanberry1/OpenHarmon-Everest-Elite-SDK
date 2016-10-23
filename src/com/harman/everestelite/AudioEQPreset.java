// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AudioEQPreset.java

package com.harman.everestelite;


public final class AudioEQPreset extends Enum
{

    public static AudioEQPreset[] values()
    {
        return (AudioEQPreset[])$VALUES.clone();
    }

    public static AudioEQPreset valueOf(String name)
    {
        return (AudioEQPreset)Enum.valueOf(com/harman/everestelite/AudioEQPreset, name);
    }

    private AudioEQPreset(String s, int i, int value)
    {
        super(s, i);
        if(value >= 0 && value <= 3)
            this.value = value;
        else
            throw new IndexOutOfBoundsException();
    }

    public static AudioEQPreset from(int value)
    {
        AudioEQPreset var1[] = values();
        int var2 = var1.length;
        for(int var3 = 0; var3 < var2; var3++)
        {
            AudioEQPreset v = var1[var3];
            if(value == v.value)
                return v;
        }

        throw new RuntimeException(String.format("invalid AudioEQPreset value 0x%02x", new Object[] {
            Integer.valueOf(value)
        }));
    }

    public int value()
    {
        return value;
    }

    public static final AudioEQPreset Music;
    public static final AudioEQPreset Gaming;
    public static final AudioEQPreset Movie;
    public static final AudioEQPreset Conference;
    public static final AudioEQPreset First;
    public static final AudioEQPreset Last;
    public static final AudioEQPreset NumPresets;
    private final int value;
    private static final AudioEQPreset $VALUES[];

    static 
    {
        Music = new AudioEQPreset("Music", 0, 0);
        Gaming = new AudioEQPreset("Gaming", 1, 1);
        Movie = new AudioEQPreset("Movie", 2, 2);
        Conference = new AudioEQPreset("Conference", 3, 3);
        First = new AudioEQPreset("First", 4, 0);
        Last = new AudioEQPreset("Last", 5, 3);
        NumPresets = new AudioEQPreset("NumPresets", 6, 4);
        $VALUES = (new AudioEQPreset[] {
            Music, Gaming, Movie, Conference, First, Last, NumPresets
        });
    }
}
