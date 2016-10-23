// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Packet.java

package com.harman.everestelite;


// Referenced classes of package com.harman.everestelite:
//            ModuleId, Command

final class Packet
{
    public static final class FlashPartitionId extends Enum
    {

        public static FlashPartitionId[] values()
        {
            return (FlashPartitionId[])$VALUES.clone();
        }

        public static FlashPartitionId valueOf(String name)
        {
            return (FlashPartitionId)Enum.valueOf(com/harman/everestelite/Packet$FlashPartitionId, name);
        }

        public static FlashPartitionId from(int value)
        {
            FlashPartitionId var1[] = values();
            int var2 = var1.length;
            for(int var3 = 0; var3 < var2; var3++)
            {
                FlashPartitionId v = var1[var3];
                if(value == v.value)
                    return v;
            }

            throw new RuntimeException(String.format("invalid FlashPartitionId value 0x%02x", new Object[] {
                Integer.valueOf(value)
            }));
        }

        public int value()
        {
            return value;
        }

        public static final FlashPartitionId Bootloader;
        public static final FlashPartitionId BootData;
        public static final FlashPartitionId FactoryImage;
        public static final FlashPartitionId UpdateImage;
        public static final FlashPartitionId Resource;
        private final int value;
        private static final FlashPartitionId $VALUES[];

        static 
        {
            Bootloader = new FlashPartitionId("Bootloader", 0, 0);
            BootData = new FlashPartitionId("BootData", 1, 1);
            FactoryImage = new FlashPartitionId("FactoryImage", 2, 2);
            UpdateImage = new FlashPartitionId("UpdateImage", 3, 3);
            Resource = new FlashPartitionId("Resource", 4, 4);
            $VALUES = (new FlashPartitionId[] {
                Bootloader, BootData, FactoryImage, UpdateImage, Resource
            });
        }

        private FlashPartitionId(String s, int i, int value)
        {
            super(s, i);
            if(value >= 0 && value <= 255)
                this.value = (byte)value;
            else
                throw new IndexOutOfBoundsException();
        }
    }

    public static final class Opcode extends Enum
    {

        public static Opcode[] values()
        {
            return (Opcode[])$VALUES.clone();
        }

        public static Opcode valueOf(String name)
        {
            return (Opcode)Enum.valueOf(com/harman/everestelite/Packet$Opcode, name);
        }

        public static Opcode from(int value)
        {
            Opcode var1[] = values();
            int var2 = var1.length;
            for(int var3 = 0; var3 < var2; var3++)
            {
                Opcode v = var1[var3];
                if(value == v.value)
                    return v;
            }

            throw new RuntimeException(String.format("invalid Opcode value 0x%02x", new Object[] {
                Integer.valueOf(value)
            }));
        }

        public int value()
        {
            return value;
        }

        public static final Opcode None;
        public static final Opcode Read;
        public static final Opcode Write;
        public static final Opcode Set;
        public static final Opcode Get;
        public static final Opcode Request;
        public static final Opcode Mask;
        public static final Opcode BufferFlag;
        public static final Opcode HighPriorityFlag;
        private final int value;
        private static final Opcode $VALUES[];

        static 
        {
            None = new Opcode("None", 0, 0);
            Read = new Opcode("Read", 1, 1);
            Write = new Opcode("Write", 2, 2);
            Set = new Opcode("Set", 3, 3);
            Get = new Opcode("Get", 4, 4);
            Request = new Opcode("Request", 5, 5);
            Mask = new Opcode("Mask", 6, 63);
            BufferFlag = new Opcode("BufferFlag", 7, 128);
            HighPriorityFlag = new Opcode("HighPriorityFlag", 8, 64);
            $VALUES = (new Opcode[] {
                None, Read, Write, Set, Get, Request, Mask, BufferFlag, HighPriorityFlag
            });
        }

        private Opcode(String s, int i, int value)
        {
            super(s, i);
            if(value >= 0 && value <= 255)
                this.value = (byte)value;
            else
                throw new IndexOutOfBoundsException();
        }
    }

    public static final class Status extends Enum
    {

        public static Status[] values()
        {
            return (Status[])$VALUES.clone();
        }

        public static Status valueOf(String name)
        {
            return (Status)Enum.valueOf(com/harman/everestelite/Packet$Status, name);
        }

        public static Status from(int value)
        {
            Status var1[] = values();
            int var2 = var1.length;
            for(int var3 = 0; var3 < var2; var3++)
            {
                Status v = var1[var3];
                if(value == v.value)
                    return v;
            }

            throw new RuntimeException(String.format("invalid Status value 0x%02x", new Object[] {
                Integer.valueOf(value)
            }));
        }

        public int value()
        {
            return value;
        }

        public static final Status Ok;
        public static final Status Fail;
        public static final Status Pending;
        public static final Status NotFound;
        public static final Status NotSupported;
        public static final Status ExceededLimits;
        public static final Status NotReady;
        public static final Status CommFailure;
        public static final Status Complete;
        public static final Status Continue;
        public static final Status Mask;
        private final int value;
        private static final Status $VALUES[];

        static 
        {
            Ok = new Status("Ok", 0, 0);
            Fail = new Status("Fail", 1, 1);
            Pending = new Status("Pending", 2, 2);
            NotFound = new Status("NotFound", 3, 3);
            NotSupported = new Status("NotSupported", 4, 4);
            ExceededLimits = new Status("ExceededLimits", 5, 5);
            NotReady = new Status("NotReady", 6, 6);
            CommFailure = new Status("CommFailure", 7, 7);
            Complete = new Status("Complete", 8, 8);
            Continue = new Status("Continue", 9, 9);
            Mask = new Status("Mask", 10, 63);
            $VALUES = (new Status[] {
                Ok, Fail, Pending, NotFound, NotSupported, ExceededLimits, NotReady, CommFailure, Complete, Continue, 
                Mask
            });
        }

        private Status(String s, int i, int value)
        {
            super(s, i);
            if(value >= 0 && value <= 255)
                this.value = (byte)value;
            else
                throw new IndexOutOfBoundsException();
        }
    }

    public static final class Type extends Enum
    {

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        public static Type valueOf(String name)
        {
            return (Type)Enum.valueOf(com/harman/everestelite/Packet$Type, name);
        }

        public static Type from(int value)
        {
            Type var1[] = values();
            int var2 = var1.length;
            for(int var3 = 0; var3 < var2; var3++)
            {
                Type v = var1[var3];
                if(value == v.value)
                    return v;
            }

            throw new RuntimeException(String.format("invalid Type value 0x%02x", new Object[] {
                Integer.valueOf(value)
            }));
        }

        public int value()
        {
            return value;
        }

        public static final Type Result;
        public static final Type Nonblocking;
        public static final Type Blocking;
        public static final Type Mask;
        private final int value;
        private static final Type $VALUES[];

        static 
        {
            Result = new Type("Result", 0, 0);
            Nonblocking = new Type("Nonblocking", 1, 64);
            Blocking = new Type("Blocking", 2, 128);
            Mask = new Type("Mask", 3, 192);
            $VALUES = (new Type[] {
                Result, Nonblocking, Blocking, Mask
            });
        }

        private Type(String s, int i, int value)
        {
            super(s, i);
            if(value >= 0 && value <= 255)
                this.value = (byte)value;
            else
                throw new IndexOutOfBoundsException();
        }
    }


    Packet()
    {
    }

    public static Packet assemble(byte data[], int from, int to)
    {
        Packet packet = new Packet();
        packet.source = ModuleId.from(data[from]);
        from++;
        packet.destination = ModuleId.from(data[from]);
        from++;
        packet.opcode = Opcode.from(data[from] & Opcode.Mask.value());
        packet.hasBuffer = (data[from] & Opcode.BufferFlag.value()) != 0;
        packet.highPriority = (data[from] & Opcode.HighPriorityFlag.value()) != 0;
        from++;
        packet.status = Status.from(data[from] & Status.Mask.value());
        packet.type = Type.from(data[from] & Type.Mask.value());
        from++;
        packet.param0 = (long)data[from] & 255L;
        from++;
        packet.param0 |= ((long)data[from] & 255L) << 8;
        from++;
        packet.param0 |= ((long)data[from] & 255L) << 16;
        from++;
        packet.param0 |= ((long)data[from] & 255L) << 24;
        from++;
        packet.param1 = (long)data[from] & 255L;
        from++;
        packet.param1 |= ((long)data[from] & 255L) << 8;
        from++;
        packet.param1 |= ((long)data[from] & 255L) << 16;
        from++;
        packet.param1 |= ((long)data[from] & 255L) << 24;
        if(packet.hasBuffer)
        {
            int bufferLength = (int)(packet.param1 & 0xffffffffL);
            from++;
            int dataRemaining = to - from;
            if(dataRemaining < bufferLength)
            {
                bufferLength = dataRemaining;
                packet.param1 = dataRemaining;
            }
            packet.buffer = new byte[bufferLength];
            System.arraycopy(data, from, packet.buffer, 0, bufferLength);
        }
        return packet;
    }

    public int address()
    {
        return (int)(param0 & 0xffffffL);
    }

    public String bufferString()
    {
        String s = null;
        if(buffer != null)
        {
            int i = 0;
            for(int n = buffer.length; i < n && buffer[i] != 0; i++);
            s = new String(buffer, 0, i);
        }
        return s;
    }

    public static byte checksum(byte data[], int from, int to)
    {
        byte checksum = 0;
        for(; from < to; from++)
            checksum += data[from];

        return (byte)(-checksum);
    }

    public Command command()
    {
        return Command.from((int)((param0 & 0xff000000L) >> 24));
    }

    public boolean isPush()
    {
        return command().value() >= Command.PushFirst.value() && command().value() <= Command.PushLast.value();
    }

    public byte[] packetize()
    {
        byte payload[] = payload();
        int payloadLength = payload != null ? payload.length : 0;
        int dataLength = 6 + payloadLength;
        int packetLength = dataLength + 1;
        byte packet[] = new byte[packetLength];
        packet[0] = -1;
        packet[1] = 90;
        packet[2] = 0;
        packet[3] = 0;
        packet[4] = (byte)(packetLength >> 8 & 0xff);
        packet[5] = (byte)(packetLength & 0xff);
        System.arraycopy(payload, 0, packet, 6, payloadLength);
        packet[dataLength] = checksum(packet, 0, dataLength);
        return packet;
    }

    public long param0()
    {
        return param0 & 0xffffffffL;
    }

    public long param1()
    {
        return param1 & 0xffffffffL;
    }

    public byte[] payload()
    {
        int bufferLength = buffer != null ? buffer.length : 0;
        byte payload[] = new byte[12 + bufferLength];
        payload[0] = (byte)(source.value() & 0xff);
        payload[1] = (byte)(destination.value() & 0xff);
        payload[2] = (byte)(opcode.value() & 0xff);
        payload[2] = (byte)(payload[2] | (highPriority ? Opcode.HighPriorityFlag.value() : 0));
        payload[2] = (byte)(payload[2] | (hasBuffer ? Opcode.BufferFlag.value() : 0));
        payload[3] = (byte)((status != null ? status.value() : Status.Ok.value()) & 0xff);
        payload[3] = (byte)(payload[3] | (type != null ? type.value() : Type.Result.value()));
        payload[4] = (byte)(int)(param0 & 255L);
        payload[5] = (byte)(int)(param0 >> 8 & 255L);
        payload[6] = (byte)(int)(param0 >> 16 & 255L);
        payload[7] = (byte)(int)(param0 >> 24 & 255L);
        if(hasBuffer && buffer == null)
            buffer = new byte[0];
        long param;
        if(buffer != null)
        {
            param = buffer.length;
            System.arraycopy(buffer, 0, payload, 12, bufferLength);
        } else
        {
            param = param1;
        }
        payload[8] = (byte)(int)(param & 255L);
        payload[9] = (byte)(int)(param >> 8 & 255L);
        payload[10] = (byte)(int)(param >> 16 & 255L);
        payload[11] = (byte)(int)(param >> 24 & 255L);
        return payload;
    }

    public static Packet request(ModuleId source, Opcode opcode, Command command, int timeoutMs, int address, byte buffer[])
    {
        Packet packet = new Packet();
        if(buffer == null)
            buffer = new byte[256];
        else
        if(buffer.length > 256)
            throw new IllegalArgumentException("buffer cannot exceed 256 bytes");
        packet.source = source;
        packet.destination = ModuleId.Application;
        packet.opcode = opcode;
        packet.hasBuffer = true;
        packet.buffer = buffer;
        packet.timeoutMs = timeoutMs;
        packet.setAddress(address);
        packet.setCommand(command);
        return packet;
    }

    public static Packet readRequest(ModuleId source, Command command, int timeoutMs, int address, byte buffer[])
    {
        return request(source, Opcode.Read, command, timeoutMs, address, buffer);
    }

    public static Packet writeRequest(ModuleId source, Command command, int timeoutMs, int address, byte buffer[])
    {
        return request(source, Opcode.Write, command, timeoutMs, address, buffer);
    }

    public void setAddress(int address)
    {
        if(address >= 0 && address <= 0xffffff)
            param0 |= address;
        else
            throw new IndexOutOfBoundsException();
    }

    public void setCommand(Command command)
    {
        param0 |= (command.value() & 0xff) << 24;
    }

    public void setFlashPartitionId(FlashPartitionId flashPartitionId)
    {
        param0 |= (flashPartitionId.value() & 0xff) << 24;
    }

    public void setParam0(long value)
    {
        if(value >= 0L && value <= 0xffffffffL)
            param0 = value;
        else
            throw new IndexOutOfBoundsException();
    }

    public void setParam1(long value)
    {
        if(value >= 0L && value <= 0xffffffffL)
            param1 = value;
        else
            throw new IndexOutOfBoundsException();
    }

    public String toString()
    {
        String bufferValue = bufferString();
        return String.format("src: %s, dst: %s, opcode: %s, buffer?: %b, priority?: %b, status: %s, type: %s, command: %s, address: 0x%06X, buffer: %s", new Object[] {
            source, destination, opcode, Boolean.valueOf(hasBuffer), Boolean.valueOf(highPriority), status, type, command(), Integer.valueOf(address()), bufferValue != null ? (new StringBuilder()).append("\"").append(bufferValue).append("\"").toString() : "null"
        });
    }

    public static boolean verifyChecksum(byte packet[], int from, int to)
    {
        to--;
        return checksum(packet, from, to) == packet[to];
    }

    public byte buffer[];
    public ModuleId destination;
    public boolean hasBuffer;
    public boolean highPriority;
    public Opcode opcode;
    public ModuleId source;
    public Status status;
    public int timeoutMs;
    public Type type;
    public static final int kChecksumSize = 1;
    public static final int kPacketHeaderSize = 12;
    public static final int kTransportHeaderSize = 6;
    public static final int kTransportSize = 7;
    public static final int kMinimumPacketSize = 19;
    public static final int kMaximumBufferSize = 256;
    private long param0;
    private long param1;
}
