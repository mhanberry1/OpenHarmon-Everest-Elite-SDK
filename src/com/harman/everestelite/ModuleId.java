// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModuleId.java

package com.harman.everestelite;


public final class ModuleId extends Enum
{

    public static ModuleId[] values()
    {
        return (ModuleId[])$VALUES.clone();
    }

    public static ModuleId valueOf(String name)
    {
        return (ModuleId)Enum.valueOf(com/harman/everestelite/ModuleId, name);
    }

    private ModuleId(String s, int i, int value)
    {
        super(s, i);
        if(value >= 0 && value <= 255)
            this.value = value;
        else
            throw new IndexOutOfBoundsException();
    }

    public static ModuleId from(int value)
    {
        ModuleId var1[] = values();
        int var2 = var1.length;
        for(int var3 = 0; var3 < var2; var3++)
        {
            ModuleId v = var1[var3];
            if(value == v.value)
                return v;
        }

        throw new RuntimeException(String.format("invalid ModuleId value 0x%02x", new Object[] {
            Integer.valueOf(value)
        }));
    }

    public int value()
    {
        return value;
    }

    public static final ModuleId None;
    public static final ModuleId Register;
    public static final ModuleId DSP;
    public static final ModuleId Memory;
    public static final ModuleId SPIM;
    public static final ModuleId File;
    public static final ModuleId Flash;
    public static final ModuleId TWIM;
    public static final ModuleId USB;
    public static final ModuleId JTAG;
    public static final ModuleId Log;
    public static final ModuleId UART;
    public static final ModuleId SPIS;
    public static final ModuleId TWIS;
    public static final ModuleId Audio;
    public static final ModuleId Bluetooth;
    public static final ModuleId ANC;
    public static final ModuleId DVM;
    public static final ModuleId NVM;
    public static final ModuleId HostMessageHandler;
    public static final ModuleId Application;
    public static final ModuleId Localhost;
    private final int value;
    private static final ModuleId $VALUES[];

    static 
    {
        None = new ModuleId("None", 0, 0);
        Register = new ModuleId("Register", 1, 2);
        DSP = new ModuleId("DSP", 2, 3);
        Memory = new ModuleId("Memory", 3, 1);
        SPIM = new ModuleId("SPIM", 4, 4);
        File = new ModuleId("File", 5, 5);
        Flash = new ModuleId("Flash", 6, 6);
        TWIM = new ModuleId("TWIM", 7, 7);
        USB = new ModuleId("USB", 8, 8);
        JTAG = new ModuleId("JTAG", 9, 9);
        Log = new ModuleId("Log", 10, 10);
        UART = new ModuleId("UART", 11, 11);
        SPIS = new ModuleId("SPIS", 12, 12);
        TWIS = new ModuleId("TWIS", 13, 13);
        Audio = new ModuleId("Audio", 14, 14);
        Bluetooth = new ModuleId("Bluetooth", 15, 15);
        ANC = new ModuleId("ANC", 16, 16);
        DVM = new ModuleId("DVM", 17, 17);
        NVM = new ModuleId("NVM", 18, 18);
        HostMessageHandler = new ModuleId("HostMessageHandler", 19, 20);
        Application = new ModuleId("Application", 20, 64);
        Localhost = new ModuleId("Localhost", 21, 66);
        $VALUES = (new ModuleId[] {
            None, Register, DSP, Memory, SPIM, File, Flash, TWIM, USB, JTAG, 
            Log, UART, SPIS, TWIS, Audio, Bluetooth, ANC, DVM, NVM, HostMessageHandler, 
            Application, Localhost
        });
    }
}
