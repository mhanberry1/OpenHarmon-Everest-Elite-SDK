// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ANCCtrlListner.java

package com.harman.everestelite;


// Referenced classes of package com.harman.everestelite:
//            ANCAwarenessPreset

public interface ANCCtrlListner
{

    public abstract void getANCSwitchStateReply(boolean flag);

    public abstract void getANCAwarenessPresetReply(ANCAwarenessPreset ancawarenesspreset);

    public abstract void getLeftANCValueReply(long l);

    public abstract void getRightANCValueReply(long l);

    public abstract void getBatteryLevelReply(long l);
}
