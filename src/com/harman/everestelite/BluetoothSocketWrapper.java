// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BluetoothSocketWrapper.java

package com.harman.everestelite;

import android.bluetooth.BluetoothSocket;
import java.io.*;

// Referenced classes of package com.harman.everestelite:
//            Socket

public final class BluetoothSocketWrapper
    implements Socket
{

    public BluetoothSocketWrapper(BluetoothSocket socket)
    {
        if((mSocket = socket) == null)
            throw new IllegalArgumentException("socket cannot be null");
        else
            return;
    }

    public void close()
        throws IOException
    {
        mSocket.close();
    }

    public InputStream getInputStream()
        throws IOException
    {
        return mSocket.getInputStream();
    }

    public OutputStream getOutputStream()
        throws IOException
    {
        return mSocket.getOutputStream();
    }

    BluetoothSocket mSocket;
}
