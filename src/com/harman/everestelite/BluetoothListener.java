// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BluetoothListener.java

package com.harman.everestelite;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

// Referenced classes of package com.harman.everestelite:
//            Bluetooth

public interface BluetoothListener
{

    public abstract void bluetoothAdapterChangedState(Bluetooth bluetooth, int i, int j);

    public abstract void bluetoothDeviceBondStateChanged(Bluetooth bluetooth, BluetoothDevice bluetoothdevice, int i, int j);

    public abstract void bluetoothDeviceConnected(Bluetooth bluetooth, BluetoothDevice bluetoothdevice, BluetoothSocket bluetoothsocket);

    public abstract void bluetoothDeviceDisconnected(Bluetooth bluetooth, BluetoothDevice bluetoothdevice);

    public abstract void bluetoothDeviceDiscovered(Bluetooth bluetooth, BluetoothDevice bluetoothdevice);

    public abstract void bluetoothDeviceFailedToConnect(Bluetooth bluetooth, BluetoothDevice bluetoothdevice, Exception exception);
}
