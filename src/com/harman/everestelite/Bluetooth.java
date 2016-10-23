// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bluetooth.java

package com.harman.everestelite;

import android.app.Activity;
import android.bluetooth.*;
import android.content.*;
import android.os.ParcelUuid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package com.harman.everestelite:
//            BluetoothListener, Log

public final class Bluetooth
    implements android.bluetooth.BluetoothProfile.ServiceListener
{
    private class ConcreteBroadcastReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            String var3 = intent.getAction();
            byte var4 = -1;
            switch(var3.hashCode())
            {
            case -1780914469: 
                if(var3.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED"))
                    var4 = 2;
                break;

            case -1530327060: 
                if(var3.equals("android.bluetooth.adapter.action.STATE_CHANGED"))
                    var4 = 4;
                break;

            case -377527494: 
                if(var3.equals("android.bluetooth.device.action.UUID"))
                    var4 = 9;
                break;

            case -301431627: 
                if(var3.equals("android.bluetooth.device.action.ACL_CONNECTED"))
                    var4 = 5;
                break;

            case 6759640: 
                if(var3.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED"))
                    var4 = 3;
                break;

            case 545516589: 
                if(var3.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED"))
                    var4 = 1;
                break;

            case 1167529923: 
                if(var3.equals("android.bluetooth.device.action.FOUND"))
                    var4 = 8;
                break;

            case 1244161670: 
                if(var3.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED"))
                    var4 = 0;
                break;

            case 1821585647: 
                if(var3.equals("android.bluetooth.device.action.ACL_DISCONNECTED"))
                    var4 = 6;
                break;

            case 2116862345: 
                if(var3.equals("android.bluetooth.device.action.BOND_STATE_CHANGED"))
                    var4 = 7;
                break;

            default:
                if(var3.equals("android.bluetooth.device.action.PAIRING_REQUEST"))
                {
                    Log.d((new StringBuilder()).append("------paring req hashcode").append(var3.hashCode()).toString());
                    var4 = 10;
                }
                break;
            }
            switch(var4)
            {
            default:
                break;

            case 0: // '\0'
            {
                Log.d("BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED received");
                int e1 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                BluetoothDevice parcel1 = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int uuid1 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                Log.d((new StringBuilder()).append(parcel1.getName()).append(" A2DP State is ").append(profileState(e1)).append(", was ").append(profileState(uuid1)).toString());
                switch(e1)
                {
                case 0: // '\0'
                    notifyDelegate(DelegateCallbackType.ActionACLDisconnected, new Object[] {
                        parcel1
                    });
                    return;

                case 2: // '\002'
                    notifyDelegate(DelegateCallbackType.ActionACLConnected, new Object[] {
                        parcel1
                    });
                    return;
                }
                return;
            }

            case 1: // '\001'
            {
                Log.d("BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED received");
                int e1 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                BluetoothDevice parcel1 = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int uuid1 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                Log.d((new StringBuilder()).append(parcel1.getName()).append(" HEADSET State is ").append(profileState(e1)).append(", was ").append(profileState(uuid1)).toString());
                switch(e1)
                {
                case 0: // '\0'
                    notifyDelegate(DelegateCallbackType.ActionACLDisconnected, new Object[] {
                        parcel1
                    });
                    return;

                case 2: // '\002'
                    notifyDelegate(DelegateCallbackType.ActionACLConnected, new Object[] {
                        parcel1
                    });
                    return;
                }
                return;
            }

            case 2: // '\002'
            {
                Log.d("BluetoothAdapter.ACTION_DISCOVERY_FINISHED received");
                break;
            }

            case 3: // '\003'
            {
                Log.d("BluetoothAdapter.ACTION_DISCOVERY_STARTED received");
                break;
            }

            case 4: // '\004'
            {
                int e1 = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                int parcel2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
                Log.d((new StringBuilder()).append("BluetoothAdapter.ACTION_STATE_CHANGED received, new state is ").append(e1).append(", previous state is ").append(parcel2).toString());
                notifyDelegate(DelegateCallbackType.ActionAdapterStateChanged, new Object[] {
                    Integer.valueOf(e1), Integer.valueOf(parcel2)
                });
                break;
            }

            case 5: // '\005'
            {
                Log.d("BluetoothDevice.ACTION_ACL_CONNECTED received");
                BluetoothDevice e = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                notifyDelegate(DelegateCallbackType.ActionACLConnected, new Object[] {
                    e
                });
                break;
            }

            case 6: // '\006'
            {
                Log.d("BluetoothDevice.ACTION_ACL_DISCONNECTED received");
                BluetoothDevice e = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                notifyDelegate(DelegateCallbackType.ActionACLDisconnected, new Object[] {
                    e
                });
                break;
            }

            case 7: // '\007'
            {
                Log.d("BluetoothDevice.ACTION_BOND_STATE_CHANGED received");
                int e1 = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
                BluetoothDevice parcel1 = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int uuid1 = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", -1);
                notifyDelegate(DelegateCallbackType.ActionBondStateChanged, new Object[] {
                    parcel1, Integer.valueOf(e1), Integer.valueOf(uuid1)
                });
                break;
            }

            case 8: // '\b'
            {
                BluetoothDevice e = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                notifyDelegate(DelegateCallbackType.ActionFound, new Object[] {
                    e
                });
                break;
            }

            case 9: // '\t'
            {
                Log.d("BluetoothDevice.ACTION_UUID received");
                try
                {
                    BluetoothDevice e = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    ParcelUuid parcel = (ParcelUuid)intent.getParcelableExtra("android.bluetooth.device.extra.UUID");
                    UUID uuid = parcel != null ? parcel.getUuid() : null;
                    if(uuid == null)
                        break;
                    Log.d((new StringBuilder()).append("ACTION_UUID for ").append(deviceName(e)).append(": ").append(uuid.toString()).toString());
                    if(uuid.toString().toUpperCase().equals(Bluetooth.kBluetoothServiceSPP))
                        notifyDelegate(DelegateCallbackType.ActionACLConnected, new Object[] {
                            e
                        });
                }
                catch(Exception var8)
                {
                    Log.e("Failed to parse UUID in ACTION_UUID");
                }
                break;
            }

            case 10: // '\n'
            {
                BluetoothDevice device = (BluetoothDevice)intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                notifyDelegate(DelegateCallbackType.ActionParingReq, new Object[] {
                    device
                });
                break;
            }
            }
        }

        final Bluetooth this$0;

        private ConcreteBroadcastReceiver()
        {
            this$0 = Bluetooth.this;
            super();
        }

    }

    private class ConnectThread extends Thread
    {

        public void run()
        {
            BluetoothSocket socket = null;
            Bluetooth e = Bluetooth.this;
            synchronized(Bluetooth.this)
            {
label0:
                {
                    Iterator var6 = mDeviceMap.entrySet().iterator();
                    HashMap map;
                    boolean connected1;
                    do
                    {
                        java.util.Map.Entry entry;
                        do
                        {
                            if(!var6.hasNext())
                                break label0;
                            entry = (java.util.Map.Entry)var6.next();
                        } while(!mDevice.equals(entry.getKey()));
                        map = (HashMap)entry.getValue();
                        if(!map.containsKey("shouldConnect"))
                            break label0;
                        if(mHeadsetProxy == null)
                            break;
                        connected1 = mHeadsetProxy.getConnectedDevices().contains(mDevice);
                    } while(!connected1);
                    Log.d((new StringBuilder()).append(mDeviceName).append(" Connect thread - ready to open connection, performing checks").toString());
                    if(mDevice.getBondState() != 12)
                    {
                        Log.d((new StringBuilder()).append(mDeviceName).append(" Connect thread - device is not BONDED, initiating pairing").toString());
                        pair(mDevice);
                    } else
                    if(!deviceSupportsProfile(mDevice, Bluetooth.kBluetoothServiceSPP))
                    {
                        Log.e((new StringBuilder()).append(mDeviceName).append(" Connect thread - device does not support serial port profile, launching SDP").toString());
                        mDevice.fetchUuidsWithSdp();
                    } else
                    {
                        if(mA2DPProxy != null)
                        {
                            boolean a2dpConnected = mA2DPProxy.getConnectedDevices().contains(mDevice);
                            Log.d((new StringBuilder()).append(mDeviceName).append(" Connect thread - device ").append(a2dpConnected ? "is" : "is not").append(" in A2DP connected list").toString());
                            if(!a2dpConnected && mA2DPProxy.getConnectionState(mDevice) != 2)
                                try
                                {
                                    mA2DPProxy.getClass().getMethod("connect", new Class[] {
                                        android/bluetooth/BluetoothDevice
                                    }).invoke(mA2DPProxy, new Object[] {
                                        mDevice
                                    });
                                }
                                catch(IllegalAccessException ex)
                                {
                                    ex.printStackTrace();
                                }
                                catch(InvocationTargetException ex)
                                {
                                    ex.printStackTrace();
                                }
                                catch(NoSuchMethodException ex)
                                {
                                    ex.printStackTrace();
                                }
                        } else
                        {
                            Log.w("A2DP proxy is null, cannot query A2DP connection state");
                            boolean a2dpConnected = false;
                        }
                        if(mHeadsetProxy != null)
                        {
                            boolean headsetConnected = mHeadsetProxy.getConnectedDevices().contains(mDevice);
                            Log.d((new StringBuilder()).append(mDeviceName).append(" Connect thread - device ").append(headsetConnected ? "is" : "is not").append(" in Headset connected list").toString());
                            if(!headsetConnected && mHeadsetProxy.getConnectionState(mDevice) != 2)
                                try
                                {
                                    mHeadsetProxy.getClass().getMethod("connect", new Class[] {
                                        android/bluetooth/BluetoothDevice
                                    }).invoke(mHeadsetProxy, new Object[] {
                                        mDevice
                                    });
                                }
                                catch(IllegalAccessException ex)
                                {
                                    ex.printStackTrace();
                                }
                                catch(InvocationTargetException ex)
                                {
                                    ex.printStackTrace();
                                }
                                catch(NoSuchMethodException ex)
                                {
                                    ex.printStackTrace();
                                }
                        } else
                        {
                            Log.w("Headset proxy is null, cannot query Headset connection state");
                            boolean headsetConnected = false;
                        }
                        Log.d((new StringBuilder()).append(mDeviceName).append(" Connect thread - all checks passed, opening connection").toString());
                        if((socket = (BluetoothSocket)map.get("socket")) == null)
                        {
                            Log.d((new StringBuilder()).append(mDeviceName).append(" Connect thread - creating RFCOMM socket to SPP profile").toString());
                            socket = createRfcommSocketToSPP(mDevice);
                            Log.d((new StringBuilder()).append(mDeviceName).append(" Connect thread - created RFCOMM socket to SPP profile").toString());
                            map.put("socket", socket);
                        }
                        if(!socket.isConnected())
                        {
                            Log.d((new StringBuilder()).append(mDeviceName).append(" Connect thread - calling socket.connect()").toString());
                            socket.connect();
                        }
                        Log.d((new StringBuilder()).append(mDeviceName).append(" Connect thread - socket is connected").toString());
                        notifyDelegate(DelegateCallbackType.Connected, new Object[] {
                            mDevice, socket
                        });
                        socket = null;
                    }
                }
            }
            if(socket != null)
                try
                {
                    Log.e((new StringBuilder()).append(mDeviceName).append(" Connect thread - closing socket ").append(socket).toString());
                    socket.close();
                }
                catch(Exception exception) { }
            break MISSING_BLOCK_LABEL_1046;
            Exception var19;
            var19;
            Log.e((new StringBuilder()).append(mDeviceName).append(" Connect thread - failed to connect: ").append(var19.getLocalizedMessage()).toString());
            notifyDelegate(DelegateCallbackType.FailedToConnect, new Object[] {
                mDevice, var19
            });
            if(socket != null)
                try
                {
                    Log.e((new StringBuilder()).append(mDeviceName).append(" Connect thread - closing socket ").append(socket).toString());
                    socket.close();
                }
                catch(Exception exception1) { }
            break MISSING_BLOCK_LABEL_1046;
            Exception exception3;
            exception3;
            if(socket != null)
                try
                {
                    Log.e((new StringBuilder()).append(mDeviceName).append(" Connect thread - closing socket ").append(socket).toString());
                    socket.close();
                }
                catch(Exception exception4) { }
            throw exception3;
            Log.d((new StringBuilder()).append(mDeviceName).append(" Connect thread - exiting").toString());
            return;
        }

        private final BluetoothDevice mDevice;
        private final String mDeviceName;
        final Bluetooth this$0;

        public ConnectThread(BluetoothDevice device)
        {
            this$0 = Bluetooth.this;
            super();
            mDevice = device;
            mDeviceName = deviceName(device);
            cancelDiscovery();
        }
    }

    private static final class DelegateCallbackType extends Enum
    {

        public static DelegateCallbackType[] values()
        {
            return (DelegateCallbackType[])$VALUES.clone();
        }

        public static DelegateCallbackType valueOf(String name)
        {
            return (DelegateCallbackType)Enum.valueOf(com/harman/everestelite/Bluetooth$DelegateCallbackType, name);
        }

        public static final DelegateCallbackType ActionACLConnected;
        public static final DelegateCallbackType ActionACLDisconnected;
        public static final DelegateCallbackType ActionAdapterStateChanged;
        public static final DelegateCallbackType ActionBondStateChanged;
        public static final DelegateCallbackType ActionFound;
        public static final DelegateCallbackType Connected;
        public static final DelegateCallbackType Disconnected;
        public static final DelegateCallbackType Discovered;
        public static final DelegateCallbackType FailedToConnect;
        public static final DelegateCallbackType ActionParingReq;
        private static final DelegateCallbackType $VALUES[];

        static 
        {
            ActionACLConnected = new DelegateCallbackType("ActionACLConnected", 0);
            ActionACLDisconnected = new DelegateCallbackType("ActionACLDisconnected", 1);
            ActionAdapterStateChanged = new DelegateCallbackType("ActionAdapterStateChanged", 2);
            ActionBondStateChanged = new DelegateCallbackType("ActionBondStateChanged", 3);
            ActionFound = new DelegateCallbackType("ActionFound", 4);
            Connected = new DelegateCallbackType("Connected", 5);
            Disconnected = new DelegateCallbackType("Disconnected", 6);
            Discovered = new DelegateCallbackType("Discovered", 7);
            FailedToConnect = new DelegateCallbackType("FailedToConnect", 8);
            ActionParingReq = new DelegateCallbackType("ActionParingReq", 9);
            $VALUES = (new DelegateCallbackType[] {
                ActionACLConnected, ActionACLDisconnected, ActionAdapterStateChanged, ActionBondStateChanged, ActionFound, Connected, Disconnected, Discovered, FailedToConnect, ActionParingReq
            });
        }

        private DelegateCallbackType(String s, int i)
        {
            super(s, i);
        }
    }

    private static final class NotificationThreadState extends Enum
    {

        public static NotificationThreadState[] values()
        {
            return (NotificationThreadState[])$VALUES.clone();
        }

        public static NotificationThreadState valueOf(String name)
        {
            return (NotificationThreadState)Enum.valueOf(com/harman/everestelite/Bluetooth$NotificationThreadState, name);
        }

        public static final NotificationThreadState Running;
        public static final NotificationThreadState ShuttingDown;
        public static final NotificationThreadState Done;
        private static final NotificationThreadState $VALUES[];

        static 
        {
            Running = new NotificationThreadState("Running", 0);
            ShuttingDown = new NotificationThreadState("ShuttingDown", 1);
            Done = new NotificationThreadState("Done", 2);
            $VALUES = (new NotificationThreadState[] {
                Running, ShuttingDown, Done
            });
        }

        private NotificationThreadState(String s, int i)
        {
            super(s, i);
        }
    }

    private class NotificationThread extends Thread
    {

        public synchronized void add(Map map)
        {
            mCalls.add(map);
            notify();
        }

        public synchronized void close()
        {
            mState = NotificationThreadState.ShuttingDown;
            notify();
            while(mState != NotificationThreadState.Done) 
                try
                {
                    wait();
                }
                catch(InterruptedException interruptedexception) { }
        }

        public void run()
        {
            ArrayList tmp = null;
_L5:
label0:
            {
                if(tmp == null)
                    tmp = new ArrayList();
                synchronized(this)
                {
                    if(mState != NotificationThreadState.ShuttingDown)
                        break label0;
                    mState = NotificationThreadState.Done;
                    notify();
                }
                return;
            }
            if(mCalls.isEmpty()) goto _L2; else goto _L1
_L1:
            ArrayList calls;
            calls = mCalls;
            mCalls = tmp;
            tmp = null;
            notificationthread;
            JVM INSTR monitorexit ;
              goto _L3
_L2:
            try
            {
                wait();
            }
            catch(InterruptedException interruptedexception) { }
            notificationthread;
            JVM INSTR monitorexit ;
            continue;
            exception1;
            throw exception1;
_L3:
            Iterator var8 = calls.iterator();
            do
            {
                if(!var8.hasNext())
                    continue;
                Map map = (Map)var8.next();
                static class _cls1
                {

                    static final int $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[];

                    static 
                    {
                        $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType = new int[DelegateCallbackType.values().length];
                        try
                        {
                            $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[DelegateCallbackType.ActionAdapterStateChanged.ordinal()] = 1;
                        }
                        catch(NoSuchFieldError nosuchfielderror) { }
                        try
                        {
                            $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[DelegateCallbackType.ActionBondStateChanged.ordinal()] = 2;
                        }
                        catch(NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[DelegateCallbackType.Connected.ordinal()] = 3;
                        }
                        catch(NoSuchFieldError nosuchfielderror2) { }
                        try
                        {
                            $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[DelegateCallbackType.ActionACLConnected.ordinal()] = 4;
                        }
                        catch(NoSuchFieldError nosuchfielderror3) { }
                        try
                        {
                            $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[DelegateCallbackType.ActionACLDisconnected.ordinal()] = 5;
                        }
                        catch(NoSuchFieldError nosuchfielderror4) { }
                        try
                        {
                            $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[DelegateCallbackType.ActionFound.ordinal()] = 6;
                        }
                        catch(NoSuchFieldError nosuchfielderror5) { }
                        try
                        {
                            $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[DelegateCallbackType.Disconnected.ordinal()] = 7;
                        }
                        catch(NoSuchFieldError nosuchfielderror6) { }
                        try
                        {
                            $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[DelegateCallbackType.Discovered.ordinal()] = 8;
                        }
                        catch(NoSuchFieldError nosuchfielderror7) { }
                        try
                        {
                            $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[DelegateCallbackType.ActionParingReq.ordinal()] = 9;
                        }
                        catch(NoSuchFieldError nosuchfielderror8) { }
                        try
                        {
                            $SwitchMap$com$harman$everestelite$Bluetooth$DelegateCallbackType[DelegateCallbackType.FailedToConnect.ordinal()] = 10;
                        }
                        catch(NoSuchFieldError nosuchfielderror9) { }
                    }
                }

                switch(_cls1..SwitchMap.com.harman.everestelite.Bluetooth.DelegateCallbackType[((DelegateCallbackType)map.get("type")).ordinal()])
                {
                case 1: // '\001'
                {
                    int state = ((Integer)map.get("state")).intValue();
                    int previousState = ((Integer)map.get("previousState")).intValue();
                    handleActionAdapterStateChanged(state, previousState);
                    break;
                }

                case 2: // '\002'
                {
                    BluetoothDevice device = (BluetoothDevice)map.get("device");
                    int state = ((Integer)map.get("state")).intValue();
                    int previousState = ((Integer)map.get("previousState")).intValue();
                    handleActionBondStateChanged(device, state, previousState);
                    break;
                }

                case 3: // '\003'
                {
                    BluetoothDevice device = (BluetoothDevice)map.get("device");
                    BluetoothSocket socket = (BluetoothSocket)map.get("socket");
                    mListener.bluetoothDeviceConnected(Bluetooth.this, device, socket);
                    break;
                }

                case 4: // '\004'
                {
                    BluetoothDevice device = (BluetoothDevice)map.get("device");
                    createConnectThread(device);
                    break;
                }

                case 5: // '\005'
                {
                    BluetoothDevice device = (BluetoothDevice)map.get("device");
                    handleActionACLDisconnected(device);
                    break;
                }

                case 6: // '\006'
                {
                    BluetoothDevice device = (BluetoothDevice)map.get("device");
                    handleActionFound(device);
                    break;
                }

                case 7: // '\007'
                {
                    BluetoothDevice device = (BluetoothDevice)map.get("device");
                    mListener.bluetoothDeviceDisconnected(Bluetooth.this, device);
                    break;
                }

                case 8: // '\b'
                {
                    BluetoothDevice device = (BluetoothDevice)map.get("device");
                    mListener.bluetoothDeviceDiscovered(Bluetooth.this, device);
                    break;
                }

                case 10: // '\n'
                {
                    BluetoothDevice device = (BluetoothDevice)map.get("device");
                    Exception exception = (Exception)map.get("exception");
                    mListener.bluetoothDeviceFailedToConnect(Bluetooth.this, device, exception);
                    break;
                }

                case 9: // '\t'
                {
                    BluetoothDevice device = (BluetoothDevice)map.get("device");
                    device.setPairingConfirmation(true);
                    break;
                }
                }
            } while(true);
              goto _L4
            if(true) goto _L5; else goto _L4
_L4:
        }

        private ArrayList mCalls;
        private NotificationThreadState mState;
        final Bluetooth this$0;

        private NotificationThread()
        {
            this$0 = Bluetooth.this;
            super();
            mCalls = new ArrayList();
            mState = NotificationThreadState.Running;
        }

    }


    public Bluetooth(BluetoothListener listener, Activity activity, boolean secureRfcomm)
        throws IOException
    {
        mA2DPProxy = null;
        mHeadsetProxy = null;
        if(listener == null)
            throw new IllegalArgumentException("delegate cannot be null");
        if(activity == null)
            throw new IllegalArgumentException("activity cannot be null");
        if((mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()) == null)
        {
            throw new IOException("No bluetooth adapter detected ");
        } else
        {
            mActivity = activity;
            mBroadcastReceiver = new ConcreteBroadcastReceiver(null);
            mListener = listener;
            mDeviceMap = new HashMap();
            mSecureRfcomm = secureRfcomm;
            mBluetoothAdapter.getProfileProxy(activity, this, 2);
            mBluetoothAdapter.getProfileProxy(activity, this, 1);
            return;
        }
    }

    private void bluetoothAdapterEnabled()
    {
        discoverBluetoothDevices();
    }

    public static String bondStateDescription(int bondState)
    {
        String result;
        switch(bondState)
        {
        case 10: // '\n'
            result = "BOND_NONE";
            break;

        case 11: // '\013'
            result = "BOND_BONDING";
            break;

        case 12: // '\f'
            result = "BOND_BONDED";
            break;

        default:
            result = "UNKNOWN";
            break;
        }
        return result;
    }

    public void cancelDiscovery()
    {
        mBluetoothAdapter.cancelDiscovery();
    }

    public void close()
    {
        ArrayList devices = new ArrayList();
        mActivity.unregisterReceiver(mBroadcastReceiver);
        NotificationThread notificationThread;
        synchronized(this)
        {
            notificationThread = mNotificationThread;
            mNotificationThread = null;
        }
        notificationThread.close();
        synchronized(this)
        {
            java.util.Map.Entry entry;
            for(Iterator device = mDeviceMap.entrySet().iterator(); device.hasNext(); devices.add(entry.getKey()))
                entry = (java.util.Map.Entry)device.next();

        }
        BluetoothDevice device1;
        for(Iterator var3 = devices.iterator(); var3.hasNext(); disconnect(device1))
            device1 = (BluetoothDevice)var3.next();

    }

    private BluetoothSocket createRfcommSocketToSPP(BluetoothDevice device)
        throws IOException
    {
        BluetoothSocket socket;
        if(mSecureRfcomm)
            socket = device.createRfcommSocketToServiceRecord(kBluetoothServiceSPP);
        else
            socket = device.createInsecureRfcommSocketToServiceRecord(kBluetoothServiceSPP);
        return socket;
    }

    public void connect(BluetoothDevice device)
        throws IOException
    {
        String name = deviceName(device);
        Log.d((new StringBuilder()).append("Bluetooth.connect() called for ").append(name).toString());
        cancelDiscovery();
        synchronized(this)
        {
            Iterator var5 = mDeviceMap.entrySet().iterator();
            do
            {
                if(!var5.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)var5.next();
                if(!((BluetoothDevice)entry.getKey()).equals(device))
                    continue;
                Log.w((new StringBuilder()).append("Adding ").append(name).append(" to logical-connect list").toString());
                HashMap map = (HashMap)entry.getValue();
                map.put("shouldConnect", Boolean.valueOf(true));
                break;
            } while(true);
        }
        createConnectThread(device);
    }

    private void createConnectThread(BluetoothDevice device)
    {
        if(device == null)
        {
            synchronized(this)
            {
                Iterator name1 = mDeviceMap.entrySet().iterator();
                do
                {
                    if(!name1.hasNext())
                        break;
                    java.util.Map.Entry socket1 = (java.util.Map.Entry)name1.next();
                    HashMap map = (HashMap)socket1.getValue();
                    if(map.containsKey("shouldConnect"))
                        createConnectThread((BluetoothDevice)socket1.getKey());
                } while(true);
            }
        } else
        {
            registerDevice(device);
            boolean create = false;
            String name = deviceName(device);
            synchronized(this)
            {
                Iterator var7 = mDeviceMap.entrySet().iterator();
                do
                {
                    if(!var7.hasNext())
                        break;
                    java.util.Map.Entry entry = (java.util.Map.Entry)var7.next();
                    if(!((BluetoothDevice)entry.getKey()).equals(device))
                        continue;
                    HashMap map = (HashMap)entry.getValue();
                    BluetoothSocket socket;
                    if(map.containsKey("shouldConnect") && ((socket = (BluetoothSocket)map.get("socket")) == null || !socket.isConnected()))
                        create = true;
                    break;
                } while(true);
            }
            if(create)
            {
                Log.d((new StringBuilder()).append("Creating connect thread for ").append(name).toString());
                (new ConnectThread(device)).start();
            }
        }
    }

    public synchronized void disconnect(BluetoothDevice device)
    {
        boolean notify = false;
        BluetoothSocket socket = null;
        Log.d("Bluetooth.disconnect() called.");
        Iterator e = mDeviceMap.entrySet().iterator();
        do
        {
            if(!e.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)e.next();
            if(!((BluetoothDevice)entry.getKey()).equals(device))
                continue;
            Log.w((new StringBuilder()).append("Removing ").append(deviceName(device)).append(" from logical-connect list").toString());
            HashMap map = (HashMap)entry.getValue();
            socket = (BluetoothSocket)map.get("socket");
            map.remove("socket");
            map.remove("shouldConnect");
            notify = true;
            Log.d((new StringBuilder()).append("Bluetooth.disconnect() socket ").append(socket).append(" found for ").append(deviceName(device)).toString());
            break;
        } while(true);
        if(notify)
            notifyDelegate(DelegateCallbackType.Disconnected, new Object[] {
                device
            });
        if(socket != null)
            try
            {
                Log.d("Bluetooth.disconnect() closing socket.");
                socket.close();
            }
            catch(IOException ioexception) { }
    }

    public String deviceName(BluetoothDevice device)
    {
        String name = device.getName();
        if(name == null)
            name = device.getAddress();
        return name;
    }

    public boolean deviceSupportsProfile(BluetoothDevice device, UUID bluetoothProfileUUID)
    {
        ParcelUuid var4[];
        int var5;
        int var6;
        ParcelUuid e[] = device.getUuids();
        if(e == null)
            break MISSING_BLOCK_LABEL_90;
        var4 = e;
        var5 = e.length;
        var6 = 0;
_L1:
        ParcelUuid parcel;
        if(var6 >= var5)
            break MISSING_BLOCK_LABEL_90;
        parcel = var4[var6];
        if(parcel.getUuid().equals(bluetoothProfileUUID))
            return true;
        var6++;
          goto _L1
        Exception var8;
        var8;
        Log.e((new StringBuilder()).append(deviceName(device)).append(".getUuids() failed: ").append(var8.getLocalizedMessage()).toString());
        return false;
    }

    public void discoverBluetoothDevices()
    {
        mBluetoothAdapter.startDiscovery();
        BluetoothDevice device;
        for(Iterator var1 = mBluetoothAdapter.getBondedDevices().iterator(); var1.hasNext(); notifyDelegate(DelegateCallbackType.ActionBondStateChanged, new Object[] {
    device, Integer.valueOf(12), Integer.valueOf(10)
}))
            device = (BluetoothDevice)var1.next();

    }

    public boolean isDiscovering()
    {
        return mBluetoothAdapter.isDiscovering();
    }

    private void logDeviceUUIDs(BluetoothDevice device)
    {
        String name = deviceName(device);
        try
        {
            ParcelUuid uuids[] = device.getUuids();
            int n;
            if((n = uuids.length) == 0)
            {
                Log.d((new StringBuilder()).append(name).append(".getUuids() returned 0 uuids").toString());
            } else
            {
                int i = 0;
                do
                    Log.d((new StringBuilder()).append(name).append(".getUuids()[ ").append(i).append(" ]: ").append(uuids[i].getUuid().toString()).toString());
                while(++i < n);
            }
        }
        catch(Exception var7)
        {
            Log.e((new StringBuilder()).append(name).append(".getUuids() failed: ").append(var7.getLocalizedMessage()).toString());
        }
    }

    public void onServiceConnected(int profile, BluetoothProfile proxy)
    {
        synchronized(this)
        {
            switch(profile)
            {
            case 1: // '\001'
                mHeadsetProxy = (BluetoothHeadset)proxy;
                Log.d("Bluetooth Headset proxy acquired");
                break;

            case 2: // '\002'
                mA2DPProxy = (BluetoothA2dp)proxy;
                Log.d("Bluetooth A2DP proxy acquired");
                break;
            }
        }
    }

    public void onServiceDisconnected(int profile)
    {
        synchronized(this)
        {
            switch(profile)
            {
            case 1: // '\001'
                mHeadsetProxy = null;
                Log.d("Bluetooth Headset proxy released");
                break;

            case 2: // '\002'
                mA2DPProxy = null;
                Log.d("Bluetooth A2DP proxy released");
                break;
            }
        }
    }

    private void registerDevice(BluetoothDevice device)
    {
        boolean notify = false;
        synchronized(this)
        {
            if(!mDeviceMap.containsKey(device))
            {
                mDeviceMap.put(device, new HashMap());
                notify = true;
            }
        }
        if(notify)
            notifyDelegate(DelegateCallbackType.Discovered, new Object[] {
                device
            });
    }

    public synchronized void start()
    {
        if(mNotificationThread == null)
        {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
            filter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            filter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
            filter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
            filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            filter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            filter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            filter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            filter.addAction("android.bluetooth.device.action.FOUND");
            filter.addAction("android.bluetooth.device.action.UUID");
            filter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
            mActivity.registerReceiver(mBroadcastReceiver, filter);
            mNotificationThread = new NotificationThread(null);
            mNotificationThread.start();
        }
        if(!mBluetoothAdapter.isEnabled())
        {
            Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
            mActivity.startActivityForResult(intent, 8191);
        } else
        {
            bluetoothAdapterEnabled();
        }
    }

    public boolean pair(BluetoothDevice device)
    {
        boolean result;
        if(result = device.createBond())
            Log.d((new StringBuilder()).append("pairing with Bluetooth device \"").append(deviceName(device)).append("\" initiated").toString());
        else
            Log.e((new StringBuilder()).append("pairing with Bluetooth device \"").append(deviceName(device)).append("\" failed to start").toString());
        return result;
    }

    protected boolean unpair(BluetoothDevice device)
    {
        return ((Boolean)android/bluetooth/BluetoothDevice.getMethod("removeBond", new Class[0]).invoke(device, new Object[0])).booleanValue();
        Exception var3;
        var3;
        return false;
    }

    protected void handleActionACLDisconnected(BluetoothDevice device)
    {
        String name = device.getName();
        boolean notify = false;
        registerDevice(device);
        Log.d((new StringBuilder()).append(name).append(" received ACL disconnect event").toString());
        synchronized(this)
        {
            Iterator var9 = mDeviceMap.entrySet().iterator();
            do
            {
                if(!var9.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)var9.next();
                if(!((BluetoothDevice)entry.getKey()).equals(device))
                    continue;
                HashMap map = (HashMap)entry.getValue();
                if(map.containsKey("shouldConnect"))
                {
                    boolean a2dpConnected;
                    if(mA2DPProxy != null)
                    {
                        a2dpConnected = mA2DPProxy.getConnectedDevices().contains(device);
                        Log.d((new StringBuilder()).append(name).append(" A2DP ").append(a2dpConnected ? "is" : "is not").append(" connected").toString());
                    } else
                    {
                        Log.w("A2DP proxy is null, cannot query A2DP connection state");
                        a2dpConnected = false;
                    }
                    boolean headsetConnected;
                    if(mHeadsetProxy != null)
                    {
                        headsetConnected = mHeadsetProxy.getConnectedDevices().contains(device);
                        Log.d((new StringBuilder()).append(name).append(" Headset ").append(headsetConnected ? "is" : "is not").append(" connected").toString());
                    } else
                    {
                        Log.w("Headset proxy is null, cannot query Headset connection state");
                        headsetConnected = false;
                    }
                    if(!a2dpConnected && !headsetConnected)
                    {
                        notify = true;
                        BluetoothSocket socket;
                        if((socket = (BluetoothSocket)map.get("socket")) != null && socket.isConnected())
                        {
                            Log.w((new StringBuilder()).append(name).append(" is not connected to either A2DP or Headset profiles, closing socket").toString());
                            try
                            {
                                socket.close();
                            }
                            catch(Exception exception) { }
                        }
                    }
                }
                break;
            } while(true);
        }
        if(notify)
        {
            Log.d((new StringBuilder()).append(name).append(" sending device disconnected notification").toString());
            mListener.bluetoothDeviceDisconnected(this, device);
        }
    }

    protected void handleActionAdapterStateChanged(int currentState, int previousState)
    {
        if(currentState == 12)
            bluetoothAdapterEnabled();
        mListener.bluetoothAdapterChangedState(this, currentState, previousState);
    }

    protected void handleActionBondStateChanged(BluetoothDevice device, int currentState, int previousState)
    {
        registerDevice(device);
        Log.d((new StringBuilder()).append(deviceName(device)).append(" bond state changed: ").append(bondStateDescription(previousState)).append(" -> ").append(bondStateDescription(currentState)).toString());
        switch(currentState)
        {
        default:
            break;

        case 11: // '\013'
            if(previousState == 12)
                Log.w("Bond state transitioned from BOND_BONDED to BOND_BONDING, this may mean the peripheral device forgot about the Android device (was its firmware flashed?  It may be necessary to unpair the headset from Android's Bluetooth settings");
            break;

        case 12: // '\f'
            logDeviceUUIDs(device);
            try
            {
                connect(device);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            break;
        }
        mListener.bluetoothDeviceBondStateChanged(this, device, currentState, previousState);
    }

    protected void handleActionFound(BluetoothDevice device)
    {
        Log.d((new StringBuilder()).append("Discovered bluetooth device \"").append(device.getName()).append("\" at ").append(device.getAddress()).toString());
        registerDevice(device);
    }

    protected String profileState(int state)
    {
        switch(state)
        {
        case 0: // '\0'
            return "Disconnected";

        case 1: // '\001'
            return "Connecting";

        case 2: // '\002'
            return "Connected";

        case 3: // '\003'
            return "Disconnecting";
        }
        return (new StringBuilder()).append("Unknown state ").append(state).toString();
    }

    private transient void notifyDelegate(DelegateCallbackType type, Object arguments[])
    {
        HashMap map = new HashMap();
        map.put("type", type);
        switch(_cls1..SwitchMap.com.harman.everestelite.Bluetooth.DelegateCallbackType[type.ordinal()])
        {
        case 1: // '\001'
            map.put("state", arguments[0]);
            map.put("previousState", arguments[1]);
            break;

        case 2: // '\002'
            map.put("device", arguments[0]);
            map.put("state", arguments[1]);
            map.put("previousState", arguments[2]);
            break;

        case 3: // '\003'
            map.put("device", arguments[0]);
            map.put("socket", arguments[1]);
            break;

        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
            map.put("device", arguments[0]);
            break;

        case 10: // '\n'
            map.put("device", arguments[0]);
            map.put("exception", arguments[1]);
            break;
        }
        synchronized(this)
        {
            if(mNotificationThread != null)
                mNotificationThread.add(map);
        }
    }

    private BluetoothA2dp mA2DPProxy;
    private final Activity mActivity;
    private final BluetoothAdapter mBluetoothAdapter;
    private final ConcreteBroadcastReceiver mBroadcastReceiver;
    private final BluetoothListener mListener;
    private final HashMap mDeviceMap;
    private BluetoothHeadset mHeadsetProxy;
    private NotificationThread mNotificationThread;
    private final boolean mSecureRfcomm;
    public static final UUID kBluetoothServiceSPP = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static final int REQUEST_ENABLE_BT = 8191;
    private static final String keyAction = "action";
    private static final String keyDevice = "device";
    private static final String keyException = "exception";
    private static final String keyPreviousState = "previousState";
    private static final String keyShouldConnect = "shouldConnect";
    private static final String keySocket = "socket";
    private static final String keyState = "state";
    private static final String keyType = "type";








}
