// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Log.java

package com.harman.everestelite;


public final class Log
{
    public static final class Level extends Enum
    {

        public static Level[] values()
        {
            return (Level[])$VALUES.clone();
        }

        public static Level valueOf(String name)
        {
            return (Level)Enum.valueOf(com/harman/everestelite/Log$Level, name);
        }

        public static final Level Debug;
        public static final Level Error;
        public static final Level Info;
        public static final Level Verbose;
        public static final Level Warning;
        private static final Level $VALUES[];

        static 
        {
            Debug = new Level("Debug", 0);
            Error = new Level("Error", 1);
            Info = new Level("Info", 2);
            Verbose = new Level("Verbose", 3);
            Warning = new Level("Warning", 4);
            $VALUES = (new Level[] {
                Debug, Error, Info, Verbose, Warning
            });
        }

        private Level(String s, int j)
        {
            super(s, j);
        }
    }

    public static interface Delegate
    {

        public abstract void log(Level level, String s, String s1);
    }


    private Log()
    {
    }

    public static void d(String message)
    {
        if(sLogDelegate != null)
            sLogDelegate.log(Level.Debug, "SmartDigitalHeadset", message);
    }

    public static void d(String tag, String message)
    {
        if(sLogDelegate != null)
            sLogDelegate.log(Level.Debug, tag, message);
    }

    public static void e(String message)
    {
        if(sLogDelegate != null)
            sLogDelegate.log(Level.Error, "SmartDigitalHeadset", message);
    }

    public static void e(String tag, String message)
    {
        if(sLogDelegate != null)
            sLogDelegate.log(Level.Error, tag, message);
    }

    public static void i(String message)
    {
        if(sLogDelegate != null)
            sLogDelegate.log(Level.Info, "SmartDigitalHeadset", message);
    }

    public static void v(String message)
    {
        if(sLogDelegate != null)
            sLogDelegate.log(Level.Verbose, "SmartDigitalHeadset", message);
    }

    public static void w(String message)
    {
        if(sLogDelegate != null)
            sLogDelegate.log(Level.Warning, "SmartDigitalHeadset", message);
    }

    public static Delegate sLogDelegate;
    private static final String kLogTag = "SmartDigitalHeadset";
}
