// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommonListner.java

package com.harman.everestelite;


public interface CommonListner
{

    public abstract void getProgrammableIndexButtonReply(int i);

    public abstract void getConfigModelNumberReply(String s);

    public abstract void getConfigProductNameReply(String s);

    public abstract void getAutoOffFeatureReply(boolean flag);

    public abstract void getEnableVoicePromptReply(boolean flag);

    public abstract void getFirmwareVersionReply(int i, int j, int k);

    public abstract void waitCommandReplyElapsedTime(int i);

    public abstract void headPhoneError(Exception exception);

    public abstract void setAutoOffFeatureReply(boolean flag);

    public abstract void setEnableVoicePromptReply(boolean flag);

    public abstract void getCustomButtonReply();

    public abstract void get9AxisRawDataReply(double d, double d1, double d2, double d3, double d4, double d5, double d6, 
            double d7, double d8);

    public abstract void get9AxisSensorStatusReply(boolean flag);

    public abstract void get9AxisPushFrequencyReply(int i);

    public abstract void set9AxisSensorStatusReply(boolean flag);

    public abstract void set9AxisPushFrequencyReply(boolean flag);
}
