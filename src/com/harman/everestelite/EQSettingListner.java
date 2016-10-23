// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EQSettingListner.java

package com.harman.everestelite;


public interface EQSettingListner
{

    public abstract void getCurrentEQPresetReply(String s, int i);

    public abstract void getEQSettingParamReply(int i, int j, long al[]);

    public abstract void getEQMinMaxParam(int i, int j, int k, int l);
}
