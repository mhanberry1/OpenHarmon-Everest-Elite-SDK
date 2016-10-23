// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LightX.java

package com.harman.everestelite;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeoutException;

// Referenced classes of package com.harman.everestelite:
//            ModuleId, Socket, Command, Packet, 
//            Log, Utility, GraphicEQPreset, ANCAwarenessPreset, 
//            AudioEQPreset, CRC32, Debug

public final class LightX
{
    public static final class FirmwareRegion extends Enum
    {

        public static FirmwareRegion[] values()
        {
            return (FirmwareRegion[])$VALUES.clone();
        }

        public static FirmwareRegion valueOf(String name)
        {
            return (FirmwareRegion)Enum.valueOf(com/harman/everestelite/LightX$FirmwareRegion, name);
        }

        public static final FirmwareRegion Application;
        public static final FirmwareRegion Resource;
        private static final FirmwareRegion $VALUES[];

        static 
        {
            Application = new FirmwareRegion("Application", 0);
            Resource = new FirmwareRegion("Resource", 1);
            $VALUES = (new FirmwareRegion[] {
                Application, Resource
            });
        }

        private FirmwareRegion(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class FirmwareWriteOperation extends Enum
    {

        public static FirmwareWriteOperation[] values()
        {
            return (FirmwareWriteOperation[])$VALUES.clone();
        }

        public static FirmwareWriteOperation valueOf(String name)
        {
            return (FirmwareWriteOperation)Enum.valueOf(com/harman/everestelite/LightX$FirmwareWriteOperation, name);
        }

        public static final FirmwareWriteOperation Erase;
        public static final FirmwareWriteOperation Write;
        public static final FirmwareWriteOperation Verify;
        public static final FirmwareWriteOperation Checksum;
        public static final FirmwareWriteOperation Complete;
        private static final FirmwareWriteOperation $VALUES[];

        static 
        {
            Erase = new FirmwareWriteOperation("Erase", 0);
            Write = new FirmwareWriteOperation("Write", 1);
            Verify = new FirmwareWriteOperation("Verify", 2);
            Checksum = new FirmwareWriteOperation("Checksum", 3);
            Complete = new FirmwareWriteOperation("Complete", 4);
            $VALUES = (new FirmwareWriteOperation[] {
                Erase, Write, Verify, Checksum, Complete
            });
        }

        private FirmwareWriteOperation(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface Delegate
    {

        public abstract void lightXAppReadResult(LightX lightx, Command command, boolean flag, byte abyte0[]);

        public abstract void lightXAppReceivedPush(LightX lightx, Command command, byte abyte0[]);

        public abstract void lightXAppWriteResult(LightX lightx, Command command, boolean flag);

        public abstract boolean lightXAwaitingReply(LightX lightx, Command command, int i);

        public abstract void lightXError(LightX lightx, Exception exception);

        public abstract boolean lightXFirmwareReadStatus(LightX lightx, FirmwareRegion firmwareregion, int i, byte abyte0[], Exception exception);

        public abstract boolean lightXFirmwareWriteStatus(LightX lightx, FirmwareRegion firmwareregion, FirmwareWriteOperation firmwarewriteoperation, double d, Exception exception);

        public abstract void lightXIsInBootloader(LightX lightx, boolean flag);

        public abstract void lightXReadConfigResult(LightX lightx, Command command, boolean flag, String s);
    }

    private class ReadThread extends Thread
    {

        public void close()
        {
            mDone = true;
            try
            {
                mInputStream.close();
            }
            catch(IOException ioexception) { }
        }

        public void run()
        {
            byte buffer[] = new byte[4096];
            int count;
label0:
            do
                do
                {
                    int dataLength;
                    byte data[];
                    int consumed;
                    do
                    {
                        do
                        {
                            do
                            {
                                if(mDone)
                                    break label0;
                                if((count = mInputStream.read(buffer)) <= 0)
                                    continue label0;
                                mBuffer.write(buffer, 0, count);
                            } while((dataLength = mBuffer.size()) < 19);
                            data = mBuffer.toByteArray();
                        } while((consumed = consume(data)) <= 0);
                        mBuffer = new ByteArrayOutputStream();
                    } while(consumed >= dataLength);
                    mBuffer.write(data, consumed, dataLength - consumed);
                } while(true);
            while(count >= 0);
            close();
            break MISSING_BLOCK_LABEL_206;
            Exception var10;
            var10;
            Log.e((new StringBuilder()).append("LightX exception on read thread: ").append(var10.getLocalizedMessage()).append(", backtrace:\n").append(Debug.stackTrace(var10)).toString());
            firmwareUpdateEnd(var10, true);
            mDelegate.lightXError(LightX.this, var10);
            close();
            break MISSING_BLOCK_LABEL_206;
            Exception exception;
            exception;
            close();
            throw exception;
        }

        private ByteArrayOutputStream mBuffer;
        private boolean mDone;
        private final InputStream mInputStream;
        final LightX this$0;

        public ReadThread(InputStream inputStream)
        {
            this$0 = LightX.this;
            super();
            mBuffer = new ByteArrayOutputStream();
            mInputStream = inputStream;
        }
    }

    private class WriteThread extends Thread
    {

        public void close()
        {
            synchronized(this)
            {
                mDone = true;
                notify();
            }
            synchronized(mSocketLock)
            {
                mSocketLock.notify();
            }
        }

        public synchronized void write(Packet packet)
            throws IOException
        {
            packet.timeoutMs = packet.timeoutMs < 0 ? Math.min(packet.timeoutMs, -2000) : Math.max(packet.timeoutMs, 2000);
            mPacketQueue.add(packet);
            notify();
        }

        public void run()
        {
_L17:
            boolean kMaxTimesToSend = true;
_L3:
            Iterator var6 = this;
            JVM INSTR monitorenter ;
            if(mDone)
            {
                try
                {
                    mOutputStream.close();
                }
                catch(Exception exception1) { }
                return;
            }
            if(!mPacketQueue.isEmpty()) goto _L2; else goto _L1
_L1:
            try
            {
                wait();
            }
            catch(InterruptedException interruptedexception) { }
            var6;
            JVM INSTR monitorexit ;
              goto _L3
_L2:
            List packetQueue;
            packetQueue = mPacketQueue;
            mPacketQueue = new ArrayList();
            var6;
            JVM INSTR monitorexit ;
              goto _L4
            Exception exception2;
            exception2;
            var6;
            JVM INSTR monitorexit ;
            throw exception2;
_L4:
            var6 = packetQueue.iterator();
_L7:
            Packet packet;
            if(!var6.hasNext())
                continue; /* Loop/switch isn't completed */
            packet = (Packet)var6.next();
            if(mDone)
            {
                try
                {
                    mOutputStream.close();
                }
                catch(Exception exception3) { }
                return;
            }
            byte e[];
            int totalElapsedMs;
            int sendCount;
            synchronized(mSocketLock)
            {
                mRequest = packet;
            }
            e = packet.packetize();
            totalElapsedMs = 0;
            sendCount = 0;
_L14:
            if(mDone)
                break MISSING_BLOCK_LABEL_509;
            if(sendCount >= 5) goto _L6; else goto _L5
_L5:
            if(sendCount > 0)
                Log.w((new StringBuilder()).append("timeout waiting for reply, retransmitting ").append(packet.command()).append(" packet (retransmit #").append(sendCount).append(")").toString());
            mOutputStream.write(e);
            if(LightX.sEnablePacketDumps)
                Log.d((new StringBuilder()).append("sent ").append(packet.opcode).append(" ").append(packet.command()).append(" (").append(e.length).append(" bytes to socket):\n").append(Debug.hexify(e)).toString());
            sendCount++;
_L6:
            Object obj1 = mSocketLock;
            JVM INSTR monitorenter ;
            if(mRequest != null) goto _L8; else goto _L7
_L8:
            try
            {
                mSocketLock.wait(Math.abs(packet.timeoutMs));
                break MISSING_BLOCK_LABEL_371;
            }
            catch(InterruptedException interruptedexception1) { }
            if(mRequest != null) goto _L10; else goto _L9
_L9:
            obj1;
            JVM INSTR monitorexit ;
              goto _L7
_L10:
            if(packet.timeoutMs >= 0) goto _L12; else goto _L11
_L11:
            mRequest = null;
            obj1;
            JVM INSTR monitorexit ;
              goto _L7
_L12:
            if(mDelegate.lightXAwaitingReply(LightX.this, packet.command(), totalElapsedMs))
                throw new TimeoutException((new StringBuilder()).append("no reply received for ").append(packet.command()).append("command in ").append(totalElapsedMs).append("ms").toString());
            obj1;
            JVM INSTR monitorexit ;
              goto _L13
            Exception exception6;
            exception6;
            obj1;
            JVM INSTR monitorexit ;
            throw exception6;
_L13:
            totalElapsedMs += packet.timeoutMs;
              goto _L14
            try
            {
                mOutputStream.close();
            }
            catch(Exception exception5) { }
            return;
            Exception var29;
            var29;
            Log.e((new StringBuilder()).append("LightX exception on write thread: ").append(var29.getLocalizedMessage()).append(", ").append(var29.getStackTrace()).toString());
            firmwareUpdateEnd(var29, true);
            mDelegate.lightXError(LightX.this, var29);
            try
            {
                mOutputStream.close();
            }
            catch(Exception exception) { }
            break MISSING_BLOCK_LABEL_617;
            Exception exception7;
            exception7;
            try
            {
                mOutputStream.close();
            }
            catch(Exception exception8) { }
            throw exception7;
            return;
            if(true) goto _L7; else goto _L15
_L15:
            if(true) goto _L17; else goto _L16
_L16:
        }

        private boolean mDone;
        private final OutputStream mOutputStream;
        private List mPacketQueue;
        final LightX this$0;

        public WriteThread(OutputStream outputStream)
        {
            this$0 = LightX.this;
            super();
            mPacketQueue = new ArrayList();
            mOutputStream = outputStream;
        }
    }


    public LightX(ModuleId sourceModuleId, Delegate delegate, Socket socket)
        throws IOException
    {
        if(delegate == null)
            throw new IllegalArgumentException("delegate cannot be null");
        if(socket == null)
        {
            throw new IllegalArgumentException("socket cannot be null");
        } else
        {
            mDelegate = delegate;
            mSocket = socket;
            mSocketLock = new Object();
            mSourceModuleId = sourceModuleId;
            mReadThread = new ReadThread(socket.getInputStream());
            mWriteThread = new WriteThread(socket.getOutputStream());
            mWriteThread.start();
            mReadThread.start();
            return;
        }
    }

    public Socket getSocket()
    {
        return mSocket;
    }

    public void close()
    {
        mReadThread.close();
        mWriteThread.close();
        try
        {
            mSocket.close();
        }
        catch(IOException ioexception) { }
    }

    private int consume(byte data[])
    {
        int consumed = 0;
        int length = 0;
        int start = 0;
        int state = 0;
        int i = 0;
        for(int n = data.length; i < n; i++)
label0:
            switch(state)
            {
            default:
                break;

            case 0: // '\0'
                do
                {
                    if(i >= n)
                        break label0;
                    if(data[i] == -1)
                    {
                        start = i;
                        state = 1;
                        break label0;
                    }
                    i++;
                } while(true);

            case 1: // '\001'
                if(data[i] == 90)
                {
                    state = 2;
                } else
                {
                    state = 0;
                    i--;
                }
                break;

            case 2: // '\002'
            case 3: // '\003'
                if(data[i] == 0)
                {
                    state++;
                } else
                {
                    state = 0;
                    i--;
                }
                break;

            case 4: // '\004'
                length = (data[i] & 0xff) << 8;
                state = 5;
                break;

            case 5: // '\005'
                length |= data[i] & 0xff;
                if(start + length > n)
                {
                    consumed = start;
                    return consumed;
                }
                consumed = start + length;
                if(Packet.verifyChecksum(data, start, consumed))
                    processPacket(data, i + 1, consumed - 1);
                else
                    Log.w("received packet failed checksum verification, ignoring");
                i = consumed;
                state = 0;
                break;
            }

        return consumed;
    }

    public void enterApplication()
    {
        writeBootJumpToApplication();
    }

    public void enterBootloader()
    {
        readBootAuthChallenge();
    }

    private void read(Command command)
    {
        read(command, 2000, 0, (byte[])null);
    }

    private void read(Command command, int timeoutMs)
    {
        read(command, timeoutMs, 0, (byte[])null);
    }

    private void read(Command command, int timeoutMs, int address, byte buffer[])
    {
        write(Packet.readRequest(mSourceModuleId, command, timeoutMs, address, buffer));
    }

    private void readRegion(Command command, int offset, int size)
    {
        int regionSize;
        switch(command.value())
        {
        case 1: // '\001'
            regionSize = 0x80000;
            break;

        case 2: // '\002'
            regionSize = 0x140000;
            break;

        default:
            throw new IllegalArgumentException("invalid command");
        }
        if(size < 0)
            throw new IndexOutOfBoundsException("size cannot be negative");
        if(offset < 0)
            throw new IndexOutOfBoundsException("offset cannot be negative");
        if(size > 256)
            throw new IndexOutOfBoundsException("request size exceeds 256 bytes");
        if(offset + size > regionSize)
        {
            throw new IndexOutOfBoundsException("request exceeds data region");
        } else
        {
            read(command, 2000, offset, new byte[size]);
            return;
        }
    }

    public void readApp(Command command)
    {
        readApp(command, 2000);
    }

    public void readApp(Command command, int timeoutMs)
    {
        if(command.value() >= Command.AppFirst.value() && command.value() <= Command.AppLast.value())
            read(command, timeoutMs);
        else
            throw new IndexOutOfBoundsException("command out of range");
    }

    public void readApp(Command command, byte buffer[], int timeoutMs)
    {
        if(command.value() >= Command.AppFirst.value() && command.value() <= Command.AppLast.value())
            read(command, timeoutMs, 0, buffer);
        else
            throw new IndexOutOfBoundsException("command out of range");
    }

    public void readAppWithUInt32Argument(Command command, long argument, int timeoutMs)
    {
        byte buffer[] = new byte[4];
        Utility.putUnsignedInt(argument, buffer, 0);
        readApp(command, buffer, timeoutMs);
    }

    public void readAppANCAwarenessPreset()
    {
        readApp(Command.AppANCAwarenessPreset);
    }

    public void readAppANCEnable()
    {
        readApp(Command.AppANCEnable);
    }

    public void readAppAudioEQPreset()
    {
        readApp(Command.AppAudioEQPreset);
    }

    public void readAppOnEarDetectionWithAutoOff()
    {
        readApp(Command.AppOnEarDetectionWithAutoOff);
    }

    public void readAppAwarenessRawLeft()
    {
        readApp(Command.AppAwarenessRawLeft);
    }

    public void readAppAwarenessRawRight()
    {
        readApp(Command.AppAwarenessRawRight);
    }

    public void readAppAwarenessRawSteps()
    {
        readApp(Command.AppAwarenessRawSteps);
    }

    public void readAppBatteryLevel()
    {
        readApp(Command.AppBatteryLevel);
    }

    public void readAppFirmwareVersion()
    {
        readApp(Command.AppFirmwareVersion);
    }

    public void readAppGraphicEQBand(GraphicEQPreset preset, int band)
    {
        if(band >= 0 && band < 10)
        {
            byte buffer[] = new byte[12];
            Utility.putUnsignedInt(preset.value(), buffer, 0);
            Utility.putUnsignedInt(band, buffer, 4);
            readApp(Command.AppGraphicEQBand, buffer, 2000);
        } else
        {
            throw new IllegalArgumentException("band out of range");
        }
    }

    public void readAppGraphicEQBandFreq()
    {
        byte buffer[] = new byte[44];
        Utility.putUnsignedInt(10L, buffer, 0);
        readApp(Command.AppGraphicEQBandFreq, buffer, 2000);
    }

    public void readAppGraphicEQCurrentPreset()
    {
        readApp(Command.AppGraphicEQCurrentPreset);
    }

    public void readAppGraphicEQLimits()
    {
        readApp(Command.AppGraphicEQLimits);
    }

    public void readAppGraphicEQPresetBandSettings(GraphicEQPreset preset)
    {
        byte buffer[] = new byte[48];
        Utility.putUnsignedInt(preset.value(), buffer, 0);
        Utility.putUnsignedInt(10L, buffer, 4);
        readApp(Command.AppGraphicEQPresetBandSettings, buffer, 2000);
    }

    public void readAppIsHeadsetOn()
    {
        readApp(Command.AppIsHeadsetOn);
    }

    public void readAppSensorStatus()
    {
        readApp(Command.AppSensorStatus);
    }

    public void readAppSmartButtonFeatureIndex()
    {
        readApp(Command.AppSmartButtonFeatureIndex);
    }

    public void readAppPushCustomEvent()
    {
        readApp(Command.AppPushCustomEvent);
    }

    public void read9AxisSensorStatus()
    {
        readApp(Command.App9AxisSensorStatus);
    }

    public void readApp9AxisPushFrequency()
    {
        readApp(Command.App9AxisPushFrequency);
    }

    public void read9AxisRawData()
    {
        readApp(Command.App9AxisData);
    }

    public void readAppTapDebounce()
    {
        readApp(Command.AppTapDebounce);
    }

    public void readAppTapIdleDebounce()
    {
        readApp(Command.AppTapIdleDebounce);
    }

    public void readAppTapPulseWindow()
    {
        readApp(Command.AppTapPulseWindow);
    }

    public void readAppTapThresholdHigh()
    {
        readApp(Command.AppTapThresholdHigh);
    }

    public void readAppTapThresholdLow()
    {
        readApp(Command.AppTapThresholdLow);
    }

    public void readAppTapTimeout()
    {
        readApp(Command.AppTapTimeout);
    }

    public void readAppVoicePromptEnable()
    {
        readApp(Command.AppVoicePromptEnable);
    }

    private void readBootAuthChallenge()
    {
        read(Command.BootAuthChallenge);
    }

    private void readBootAuthStatus()
    {
        read(Command.BootAuthStatus);
    }

    public void readBootImageType()
    {
        read(Command.BootImageType);
    }

    public void readConfigBTDisplayName()
    {
        read(Command.ConfigBTDisplayName);
    }

    public void readConfigFirmwareVersion()
    {
        read(Command.ConfigFirmwareVersion);
    }

    public void readConfigHardwareVersion()
    {
        read(Command.ConfigHardwareVersion);
    }

    public void readConfigManufacturerName()
    {
        read(Command.ConfigManufacturerName);
    }

    public void readConfigModelNumber()
    {
        read(Command.ConfigModelNumber);
    }

    public void readConfigProductName()
    {
        read(Command.ConfigProductName);
    }

    public void readConfigSDHMBootVersion()
    {
        read(Command.ConfigSDHMBootVersion);
    }

    public void readConfigSDHMHardwareVersion()
    {
        read(Command.ConfigSDHMHardwareVersion);
    }

    public void readConfigSDHMManufacturerName()
    {
        read(Command.ConfigSDHMManufacturerName);
    }

    public void readConfigSDHMModelNumber()
    {
        read(Command.ConfigSDHMModelNumber);
    }

    public void readConfigSDHMProductName()
    {
        read(Command.ConfigSDHMProductName);
    }

    public void readConfigSDHMSerialNumber()
    {
        read(Command.ConfigSDHMSerialNumber);
    }

    public void readConfigSerialNumber()
    {
        read(Command.ConfigSerialNumber);
    }

    public void readFirmware(FirmwareRegion region, int from, int to)
    {
        firmwareOperationPrepare(region, (byte[])null, from, to);
    }

    private void write(Packet packet)
    {
        try
        {
            mWriteThread.write(packet);
        }
        catch(IOException var3)
        {
            mDelegate.lightXError(this, var3);
        }
    }

    private void write(Command command)
    {
        write(command, (byte[])null, 0, 2000);
    }

    private void write(Command command, byte buffer[])
    {
        write(command, buffer, 0, 2000);
    }

    private void write(Command command, int timeoutMs)
    {
        write(command, (byte[])null, 0, timeoutMs);
    }

    private void write(Command command, byte buffer[], int address, int timeoutMs)
    {
        write(Packet.writeRequest(mSourceModuleId, command, timeoutMs, address, buffer));
    }

    private void writeRegion(Command command, int offset, byte buffer[])
    {
        int regionSize;
        switch(command.ordinal())
        {
        case 3: // '\003'
            regionSize = 0x80000;
            break;

        case 4: // '\004'
            regionSize = 0x140000;
            break;

        default:
            throw new IllegalArgumentException("invalid command");
        }
        int size;
        if(buffer != null && (size = buffer.length) != 0)
        {
            if(offset < 0)
                throw new IndexOutOfBoundsException("offset cannot be negative");
            if(size > 256)
                throw new IndexOutOfBoundsException("request size exceeds 256 bytes");
            if(offset + size > regionSize)
                throw new IndexOutOfBoundsException("request exceeds data region");
            write(command, buffer, offset, 2000);
        } else
        {
            throw new IllegalArgumentException("buffer cannot be empty");
        }
    }

    private void writeWithUInt32Argument(Command command, long uint32Arg)
    {
        writeWithUInt32Arguments(command, new long[] {
            uint32Arg
        }, 2000);
    }

    private void writeWithUInt32Arguments(Command command, long arguments[], int timeoutMs)
    {
        boolean kSizeofUInt32 = true;
        int n = arguments.length;
        byte buffer[] = new byte[4 * n];
        for(int i = 0; i < n; i++)
            Utility.putUnsignedInt(arguments[i], buffer, 4 * i);

        write(command, buffer, 0, timeoutMs);
    }

    public void writeApp(Command command, byte buffer[])
    {
        writeApp(command, buffer, 2000);
    }

    public void writeApp(Command command, byte buffer[], int timeoutMs)
    {
        if(command.value() >= Command.AppFirst.value() && command.value() <= Command.AppLast.value())
        {
            if(buffer != null && buffer.length > 256)
                throw new IndexOutOfBoundsException("buffer size must not exceed 256 bytes");
            write(command, buffer, 0, timeoutMs);
        } else
        {
            throw new IndexOutOfBoundsException("command out of range");
        }
    }

    public void writeAppWithBooleanArgument(Command command, boolean argument, int timeoutMs)
    {
        writeAppWithUInt32Arguments(command, new long[] {
            argument ? 1L : 0L
        }, timeoutMs);
    }

    public void writeAppWithBooleanArgument(Command command, boolean argument)
    {
        writeAppWithBooleanArgument(command, argument, 2000);
    }

    public void writeAppWithFloatArgument(Command command, float argument, int timeoutMs)
    {
        byte buffer[] = new byte[4];
        Utility.putFloat(argument, buffer, 0);
        writeApp(command, buffer, timeoutMs);
    }

    public void writeAppWithUInt32Argument(Command command, long argument)
    {
        writeAppWithUInt32Arguments(command, new long[] {
            argument
        }, 2000);
    }

    public void writeAppWithUInt32Arguments(Command command, long arguments[])
    {
        writeAppWithUInt32Arguments(command, arguments, 2000);
    }

    public void writeAppWithUInt32Arguments(Command command, long arguments[], int timeoutMs)
    {
        if(command.value() >= Command.AppFirst.value() && command.value() <= Command.AppLast.value())
            writeWithUInt32Arguments(command, arguments, timeoutMs);
        else
            throw new IndexOutOfBoundsException("command out of range");
    }

    public void writeAppANCAwarenessPreset(ANCAwarenessPreset preset)
    {
        writeAppWithUInt32Argument(Command.AppANCAwarenessPreset, preset.value());
    }

    public void writeAppANCEnable(boolean enable)
    {
        writeAppWithBooleanArgument(Command.AppANCEnable, enable);
    }

    public void writeAppANCLevel(float level)
    {
        if(level >= 0.0F && level <= 1.0F)
            writeAppWithFloatArgument(Command.AppANCLevel, level, 2000);
        else
            throw new IllegalArgumentException("level must be between 0 and 1");
    }

    public void writeAppAudioEQPreset(AudioEQPreset preset)
    {
        writeAppWithUInt32Argument(Command.AppAudioEQPreset, preset.value());
    }

    public void writeAppOnEarDetectionWithAutoOff(boolean enable)
    {
        writeAppWithBooleanArgument(Command.AppOnEarDetectionWithAutoOff, enable);
    }

    public void writeAppGraphicEQBand(GraphicEQPreset preset, int band, int value)
    {
        if(band >= 0 && band < 10)
        {
            if(value >= -10 && value <= 10)
            {
                byte buffer[] = new byte[12];
                Utility.putUnsignedInt(preset.value(), buffer, 0);
                Utility.putUnsignedInt(band, buffer, 4);
                Utility.putInt(value, buffer, 8);
                writeApp(Command.AppGraphicEQBand, buffer, 2000);
            } else
            {
                throw new IllegalArgumentException("value out of range");
            }
        } else
        {
            throw new IllegalArgumentException("band out of range");
        }
    }

    public void writeAppGraphicEQCurrentPreset(GraphicEQPreset preset)
    {
        writeAppWithUInt32Argument(Command.AppGraphicEQCurrentPreset, preset.value());
    }

    public void writeAppGraphicEQDefaultPreset(GraphicEQPreset preset)
    {
        writeAppWithUInt32Argument(Command.AppGraphicEQDefaultPreset, preset.value());
    }

    public void writeAppGraphicEQFactoryResetPreset(GraphicEQPreset preset)
    {
        writeAppWithUInt32Argument(Command.AppGraphicEQFactoryResetPreset, preset.value());
    }

    public void writeAppGraphicEQPersistPreset(GraphicEQPreset preset)
    {
        writeAppWithUInt32Argument(Command.AppGraphicEQPersistPreset, preset.value());
    }

    public void writeAppGraphicEQPresetBandSettings(GraphicEQPreset preset, int bands[])
    {
        if(bands.length != 10)
            throw new IllegalArgumentException("invalid band count (!= 10)");
        byte buffer[] = new byte[48];
        Utility.putUnsignedInt(preset.value(), buffer, 0);
        Utility.putUnsignedInt(10L, buffer, 4);
        int i = 0;
        for(int offset = 8; i < 10; offset += 4)
        {
            if(bands[i] < -10 || bands[i] > 10)
                throw new IllegalArgumentException("value out of range");
            Utility.putInt(bands[i], buffer, offset);
            i++;
        }

        writeApp(Command.AppGraphicEQPresetBandSettings, buffer, 2000);
    }

    public void writeAppSmartButtonFeatureIndex(int idx)
    {
        writeAppWithUInt32Argument(Command.AppSmartButtonFeatureIndex, idx);
    }

    public void writeApp9AxisSensorStatus(boolean enable)
    {
        writeAppWithBooleanArgument(Command.App9AxisSensorStatus, enable);
    }

    public void writeApp9AxisPushFrequency(int frequency)
    {
        if(frequency < 200)
            frequency = 200;
        writeAppWithUInt32Argument(Command.App9AxisPushFrequency, frequency);
    }

    public void writeAppTapCommit()
    {
        writeApp(Command.AppTapCommit, (byte[])null);
    }

    public void writeAppTapDebounce(long uint32Value)
    {
        writeAppWithUInt32Argument(Command.AppTapDebounce, uint32Value);
    }

    public void writeAppTapIdleDebounce(long uint32Value)
    {
        writeAppWithUInt32Argument(Command.AppTapIdleDebounce, uint32Value);
    }

    public void writeAppTapPulseWindow(long uint32Value)
    {
        writeAppWithUInt32Argument(Command.AppTapPulseWindow, uint32Value);
    }

    public void writeAppTapThresholdHigh(long uint32Value)
    {
        writeAppWithUInt32Argument(Command.AppTapThresholdHigh, uint32Value);
    }

    public void writeAppTapThresholdLow(long uint32Value)
    {
        writeAppWithUInt32Argument(Command.AppTapThresholdLow, uint32Value);
    }

    public void writeAppTapTimeout(long uint32Value)
    {
        writeAppWithUInt32Argument(Command.AppTapTimeout, uint32Value);
    }

    public void writeAppVoicePromptEnable(boolean enable)
    {
        writeAppWithBooleanArgument(Command.AppVoicePromptEnable, enable);
    }

    private void writeAppShutdown()
    {
        write(Command.AppShutdown);
    }

    private void writeBootCommitImage(int sectorCount)
    {
        writeWithUInt32Arguments(Command.BootCommitImage, new long[] {
            (long)sectorCount
        }, 8000);
    }

    private void writeBootJumpToApplication()
    {
        write(Command.BootJumpToApplication, -8000);
    }

    private void jumpToApplicationAndSendPowerOn()
    {
        write(Command.BootJumpToApplication, -100);
        byte buffer[] = new byte[4];
        Utility.putUnsignedInt(0x80008L, buffer, 4);
        Packet packet = Packet.request(mSourceModuleId, Packet.Opcode.Set, Command.AppIsHeadsetOn, 1000, 0, buffer);
        write(packet);
    }

    private void writeBootJumpToBootloader()
    {
        write(Command.BootJumpToBootloader, -8000);
    }

    private void writeBootVerifyCRC(int sectorCount, long crc32, FirmwareRegion firmwareRegion)
    {
        static class _cls1
        {

            static final int $SwitchMap$com$harman$everestelite$LightX$FirmwareRegion[];
            static final int $SwitchMap$com$harman$everestelite$Command[] = new int[Command.values().length];

            static 
            {
                $SwitchMap$com$harman$everestelite$LightX$FirmwareRegion = new int[FirmwareRegion.values().length];
                try
                {
                    $SwitchMap$com$harman$everestelite$LightX$FirmwareRegion[FirmwareRegion.Application.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$LightX$FirmwareRegion[FirmwareRegion.Resource.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
            }
        }

        long args[];
        switch(_cls1..SwitchMap.com.harman.everestelite.LightX.FirmwareRegion[firmwareRegion.ordinal()])
        {
        case 1: // '\001'
            args = (new long[] {
                (long)sectorCount, crc32
            });
            break;

        case 2: // '\002'
            long address = 0xc0000L;
            args = (new long[] {
                (long)sectorCount, crc32, address
            });
            break;

        default:
            throw new IllegalArgumentException((new StringBuilder()).append("unsupported firmware region ").append(firmwareRegion).toString());
        }
        writeWithUInt32Arguments(Command.BootVerifyCRC, args, 8000);
    }

    public void writeFirmware(FirmwareRegion region, byte data[])
    {
        firmwareOperationPrepare(region, data, 0, 0);
    }

    private void firmwareCommitImage(boolean success)
    {
        Exception exception = null;
        try
        {
            if(!success)
                throw new IOException("image failed checksum verification");
            int sectors = ((mFirmwareData.length + 4096) - 1) / 4096;
            writeBootCommitImage(sectors);
        }
        catch(Exception var5)
        {
            exception = var5;
        }
        if(exception != null)
            firmwareUpdateEnd(exception, true);
    }

    private void firmwareEraseNextBlock()
    {
        boolean complete = false;
        Exception exception = null;
        try
        {
            mFirmwareBlockCurrent++;
            double progress = (double)mFirmwareBlockCurrent / (double)mFirmwareBlockLast;
            if(!(complete = mDelegate.lightXFirmwareWriteStatus(this, mFirmwareRegion, FirmwareWriteOperation.Erase, progress, (Exception)null)))
                if(mFirmwareBlockCurrent < mFirmwareBlockLast)
                {
                    writeWithUInt32Argument(mFirmwareEraseCommand, mFirmwareBlockCurrent);
                } else
                {
                    mFirmwareBlockCurrent = -1;
                    mFirmwareBlockLast = ((mFirmwareData.length + 256) - 1) / 256;
                    firmwareWriteNextBlock();
                }
        }
        catch(Exception var6)
        {
            complete = true;
            exception = var6;
        }
        if(complete)
            firmwareUpdateEnd(exception, true);
    }

    private void firmwareOperationPrepare(FirmwareRegion region, byte data[], int from, int to)
    {
        firmwareUpdateBegin();
        try
        {
            int regionSize;
            switch(_cls1..SwitchMap.com.harman.everestelite.LightX.FirmwareRegion[region.ordinal()])
            {
            case 1: // '\001'
                mFirmwareEraseCommand = Command.BootEraseImageSector;
                mFirmwareReadCommand = Command.BootReadImage;
                mFirmwareWriteCommand = Command.BootWriteImage;
                regionSize = 0x80000;
                break;

            case 2: // '\002'
                mFirmwareEraseCommand = Command.BootEraseRsrc1Sector;
                mFirmwareReadCommand = Command.BootReadRsrc1;
                mFirmwareWriteCommand = Command.BootWriteRsrc1;
                regionSize = 0x140000;
                break;

            default:
                throw new IllegalArgumentException("unsupported region");
            }
            mFirmwareRegion = region;
            if(data != null)
            {
                if(data.length > regionSize)
                    throw new IndexOutOfBoundsException("request exceeds data region");
                mFirmwareBlockCurrent = -1;
                mFirmwareBlockLast = ((regionSize + 4096) - 1) / 4096;
                mFirmwareReadFrom = -1;
                mFirmwareReadTo = -1;
                int dataBlocks = ((data.length + 4096) - 1) / 4096;
                int dataSize = dataBlocks * 4096;
                mFirmwareData = new byte[dataSize];
                System.arraycopy(data, 0, mFirmwareData, 0, data.length);
                firmwareEraseNextBlock();
            } else
            {
                mFirmwareBlockCurrent = from / 256 - 1;
                mFirmwareBlockLast = ((to + 256) - 1) / 256;
                mFirmwareData = null;
                mFirmwareReadFrom = from;
                mFirmwareReadTo = to;
                firmwareReadNextBlock((byte[])null);
            }
        }
        catch(Exception var9)
        {
            firmwareUpdateEnd((Exception)null, false);
            throw var9;
        }
    }

    private void firmwareReadNextBlock(byte currentBlockContent[])
    {
        boolean complete = false;
        Exception exception = null;
        try
        {
            if(currentBlockContent != null)
            {
                int offset = mFirmwareBlockCurrent * 256;
                int to;
                if(offset + currentBlockContent.length > mFirmwareReadTo)
                    to = mFirmwareReadTo - offset;
                else
                    to = currentBlockContent.length;
                int from;
                if(offset < mFirmwareReadFrom)
                {
                    from = mFirmwareReadFrom - offset;
                    offset = mFirmwareReadFrom;
                } else
                {
                    from = 0;
                }
                if(to - from < currentBlockContent.length)
                    currentBlockContent = Arrays.copyOfRange(currentBlockContent, from, to);
                complete = mDelegate.lightXFirmwareReadStatus(this, mFirmwareRegion, offset, currentBlockContent, (Exception)null);
            }
            if(!complete)
                complete = ++mFirmwareBlockCurrent >= mFirmwareBlockLast;
            if(!complete)
            {
                int offset = mFirmwareBlockCurrent * 256;
                int size = Math.min(mFirmwareReadTo - offset, 256);
                readRegion(mFirmwareReadCommand, offset, size);
            }
        }
        catch(Exception var9)
        {
            complete = true;
            exception = var9;
        }
        if(complete)
            firmwareUpdateEnd(exception, true);
    }

    private synchronized void firmwareUpdateBegin()
    {
        if(mFirmwareIsUpdating)
        {
            throw new ConcurrentModificationException("a firmware update is already in progress");
        } else
        {
            mFirmwareIsUpdating = true;
            return;
        }
    }

    private synchronized void firmwareUpdateEnd(Exception exception, boolean notifyDelegate)
    {
        if(mFirmwareIsUpdating)
        {
            if(notifyDelegate)
                if(mFirmwareData != null)
                    mDelegate.lightXFirmwareWriteStatus(this, mFirmwareRegion, FirmwareWriteOperation.Complete, exception != null ? 0.0D : 1.0D, exception);
                else
                if(exception != null)
                    mDelegate.lightXFirmwareReadStatus(this, mFirmwareRegion, mFirmwareBlockCurrent * 256, (byte[])null, exception);
            mFirmwareData = null;
            mFirmwareIsUpdating = false;
        }
    }

    private void firmwareWriteCRC()
    {
        Exception exception = null;
        long kCRC32Seed = 0xffffffffL;
        try
        {
            long crc32 = CRC32.calculate(0xffffffffL, mFirmwareData);
            int sectors = ((mFirmwareData.length + 4096) - 1) / 4096;
            writeBootVerifyCRC(sectors, crc32, mFirmwareRegion);
        }
        catch(Exception var8)
        {
            exception = var8;
        }
        if(exception != null)
            firmwareUpdateEnd(exception, true);
    }

    private void firmwareWriteNextBlock()
    {
        boolean complete = false;
        Exception exception = null;
        try
        {
            mFirmwareBlockCurrent++;
            Log.d((new StringBuilder()).append("firmwareWriteNextBlock, mFirmwareBlockCurrent ").append(mFirmwareBlockCurrent).toString());
            double progress = (double)mFirmwareBlockCurrent / (double)mFirmwareBlockLast;
            if(!(complete = mDelegate.lightXFirmwareWriteStatus(this, mFirmwareRegion, FirmwareWriteOperation.Write, progress, (Exception)null)))
                if(mFirmwareBlockCurrent < mFirmwareBlockLast)
                {
                    int offset = mFirmwareBlockCurrent * 256;
                    int size = Math.min(mFirmwareData.length - offset, 256);
                    byte buffer[] = new byte[size];
                    System.arraycopy(mFirmwareData, offset, buffer, 0, size);
                    writeRegion(mFirmwareWriteCommand, offset, buffer);
                } else
                {
                    firmwareWriteCRC();
                }
        }
        catch(Exception var9)
        {
            complete = true;
            exception = var9;
        }
        if(complete)
            firmwareUpdateEnd(exception, true);
    }

    private void processPacket(byte data[], int from, int to)
    {
        Packet packet;
        Command command;
label0:
        {
            packet = Packet.assemble(data, from, to);
            command = packet.command();
            if(sEnablePacketDumps)
                Log.d((new StringBuilder()).append("received ").append(packet.opcode).append(" ").append(command).append(" (").append((to - from) + 7).append(" bytes from socket):\n").append(Debug.hexify(data, 0, from - 6, to + 1)).toString());
            if(packet.source != mSourceModuleId && packet.source != ModuleId.Application)
            {
                Log.w((new StringBuilder()).append("Unexpected packet source ").append(packet.source).append(" (should be ").append(mSourceModuleId).append(" or ").append(ModuleId.Application).append("), ignoring").toString());
                break MISSING_BLOCK_LABEL_1181;
            }
            if(packet.isPush())
                break MISSING_BLOCK_LABEL_1164;
            Object var7 = mSocketLock;
            synchronized(mSocketLock)
            {
                if(mRequest != null && packet.param0() == mRequest.param0())
                    break label0;
                Log.w("received packet is not a push command and was received outside of request loop, ignoring");
            }
            return;
        }
        mRequest = null;
        mSocketLock.notify();
        obj;
        JVM INSTR monitorexit ;
          goto _L1
        exception;
        throw exception;
_L1:
        int value = command.value();
        switch(packet.status.ordinal())
        {
        case 0: // '\0'
label1:
            switch(packet.opcode.ordinal())
            {
            default:
                break;

            case 1: // '\001'
                if(value >= Command.ConfigFirst.value() && value <= Command.ConfigLast.value())
                {
                    mDelegate.lightXReadConfigResult(this, command, true, packet.bufferString());
                    break;
                }
                if(value >= Command.BootFirst.value() && value <= Command.BootLast.value())
                {
                    switch(command.ordinal())
                    {
                    case 1: // '\001'
                    case 2: // '\002'
                    case 9: // '\t'
                        firmwareReadNextBlock(packet.buffer);
                        return;

                    case 3: // '\003'
                    case 4: // '\004'
                    case 5: // '\005'
                    default:
                        return;

                    case 6: // '\006'
                        write(Command.BootAuthResponse, calculateBootAuthChallengeResponse(packet.buffer));
                        return;

                    case 7: // '\007'
                        mIsAuthenticated = packet.buffer[0] == 1;
                        if(mIsAuthenticated && !mIsInBootloader)
                            writeAppShutdown();
                        return;

                    case 8: // '\b'
                        mIsInBootloader = packet.buffer[0] == 0;
                        mDelegate.lightXIsInBootloader(this, mIsInBootloader);
                        break;
                    }
                    break;
                }
                if(value < Command.AppFirst.value() || value > Command.AppLast.value())
                    break;
                switch(_cls1..SwitchMap.com.harman.everestelite.Command[command.ordinal()])
                {
                default:
                    mDelegate.lightXAppReadResult(this, command, true, packet.buffer);
                    break;
                }
                break;

            case 2: // '\002'
                if(value >= Command.AppFirst.value() && value <= Command.AppLast.value())
                {
                    mDelegate.lightXAppWriteResult(this, command, true);
                    break;
                }
                switch(command.ordinal())
                {
                case 6: // '\006'
                case 7: // '\007'
                case 8: // '\b'
                case 9: // '\t'
                default:
                    break;

                case 3: // '\003'
                case 4: // '\004'
                case 16: // '\020'
                    firmwareWriteNextBlock();
                    break label1;

                case 5: // '\005'
                    firmwareUpdateEnd((Exception)null, true);
                    break label1;

                case 10: // '\n'
                    writeBootJumpToBootloader();
                    break label1;

                case 11: // '\013'
                    readBootAuthStatus();
                    break label1;

                case 12: // '\f'
                    readBootImageType();
                    break label1;

                case 13: // '\r'
                case 14: // '\016'
                case 15: // '\017'
                    firmwareEraseNextBlock();
                    break label1;

                case 17: // '\021'
                    switch(mFirmwareRegion.ordinal())
                    {
                    case 1: // '\001'
                        firmwareCommitImage(true);
                        break;

                    case 2: // '\002'
                        firmwareUpdateEnd((Exception)null, true);
                        break;
                    }
                    break;
                }
                break;
            }
            return;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            Log.e((new StringBuilder()).append("receive ").append(command).append(" ").append(packet.status).toString());
            switch(packet.opcode.ordinal())
            {
            default:
                break;

            case 1: // '\001'
                if(value >= Command.ConfigFirst.value() && value <= Command.ConfigLast.value())
                {
                    mDelegate.lightXReadConfigResult(this, command, false, (String)null);
                    break;
                }
                if(value < Command.AppFirst.value() || value > Command.AppLast.value())
                    break;
                switch(_cls1..SwitchMap.com.harman.everestelite.Command[command.ordinal()])
                {
                default:
                    mDelegate.lightXAppReadResult(this, command, false, (byte[])null);
                    break;
                }
                break;

            case 2: // '\002'
                if(value >= Command.BootFirst.value() && value <= Command.BootLast.value())
                {
                    switch(command.ordinal())
                    {
                    case 5: // '\005'
                        firmwareUpdateEnd(new IOException("boot commit image failed, cannot jump to application"), true);
                        break;
                    }
                    break;
                }
                if(value >= Command.AppFirst.value() && value <= Command.AppLast.value())
                    mDelegate.lightXAppWriteResult(this, command, false);
                break;
            }
            return;

        case 8: // '\b'
            Log.d((new StringBuilder()).append("receive ").append(command).append(" ").append(packet.status).toString());
            break;
        }
        break MISSING_BLOCK_LABEL_1181;
        mDelegate.lightXAppReceivedPush(this, command, packet.buffer);
    }

    private byte[] calculateBootAuthChallengeResponse(byte challenge[])
    {
        byte result[] = new byte[16];
        if(challenge.length >= result.length)
        {
            for(int i = 0; i < 16; i++)
                result[i] = (byte)(challenge[i] ^ 0x5d);

        }
        return result;
    }

    private final Delegate mDelegate;
    private int mFirmwareBlockCurrent;
    private int mFirmwareBlockLast;
    private byte mFirmwareData[];
    private Command mFirmwareEraseCommand;
    private boolean mFirmwareIsUpdating;
    private Command mFirmwareReadCommand;
    private int mFirmwareReadFrom;
    private int mFirmwareReadTo;
    private FirmwareRegion mFirmwareRegion;
    private Command mFirmwareWriteCommand;
    private boolean mIsAuthenticated;
    private boolean mIsInBootloader;
    private final ReadThread mReadThread;
    private Packet mRequest;
    private final Socket mSocket;
    private final Object mSocketLock;
    private final ModuleId mSourceModuleId;
    private final WriteThread mWriteThread;
    private static final int kMaximumRegionRequestSize = 256;
    private static final int kResource1Size = 0x40000;
    private static final int kResource2Size = 0x100000;
    private static final int kSectorSize = 4096;
    private static final int kEraseBlockSize = 4096;
    private static final int kReadBlockSize = 256;
    private static final int kWriteBlockSize = 256;
    private static final int kTimeoutMsDefault = 2000;
    private static final int kGraphicEQNumBands = 10;
    private static final int kGraphicEQSettingMax = 10;
    private static final int kGraphicEQSettingMin = -10;
    private static final int kSizeofUInt32 = 4;
    public static boolean sEnablePacketDumps = true;
    public static final int kApplicationSize = 0x80000;
    public static final int kResourceSize = 0x140000;







}
