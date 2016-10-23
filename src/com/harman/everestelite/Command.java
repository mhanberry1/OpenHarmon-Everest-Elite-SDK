// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Command.java

package com.harman.everestelite;


public final class Command extends Enum
{

    public static Command[] values()
    {
        return (Command[])$VALUES.clone();
    }

    public static Command valueOf(String name)
    {
        return (Command)Enum.valueOf(com/harman/everestelite/Command, name);
    }

    private Command(String s, int i, int value)
    {
        super(s, i);
        if(value >= 0 && value <= 255)
            this.value = value;
        else
            throw new IndexOutOfBoundsException();
    }

    public static Command from(int value)
    {
        Command var1[] = values();
        int var2 = var1.length;
        for(int var3 = 0; var3 < var2; var3++)
        {
            Command v = var1[var3];
            if(value == v.value)
                return v;
        }

        throw new RuntimeException(String.format("invalid Command value 0x%02x", new Object[] {
            Integer.valueOf(value)
        }));
    }

    public int value()
    {
        return value;
    }

    public static final Command Reserved;
    public static final Command ConfigProductName;
    public static final Command ConfigManufacturerName;
    public static final Command ConfigModelNumber;
    public static final Command ConfigSerialNumber;
    public static final Command ConfigHardwareVersion;
    public static final Command ConfigFirmwareVersion;
    public static final Command ConfigBTDisplayName;
    public static final Command ConfigSDHMProductName;
    public static final Command ConfigSDHMManufacturerName;
    public static final Command ConfigSDHMModelNumber;
    public static final Command ConfigSDHMSerialNumber;
    public static final Command ConfigSDHMBootVersion;
    public static final Command ConfigSDHMHardwareVersion;
    public static final Command BootImageType;
    public static final Command BootAuthStatus;
    public static final Command BootAuthChallenge;
    public static final Command BootAuthResponse;
    public static final Command BootJumpToBootloader;
    public static final Command BootEraseImageSector;
    public static final Command BootWriteImage;
    public static final Command BootReadImage;
    public static final Command BootCommitImage;
    public static final Command BootJumpToApplication;
    public static final Command BootVerifyCRC;
    public static final Command BootEraseRsrc1Sector;
    public static final Command BootWriteRsrc1;
    public static final Command BootReadRsrc1;
    public static final Command BootEraseRsrc2Sector;
    public static final Command BootWriteRsrc2;
    public static final Command BootReadRsrc2;
    public static final Command AppShutdown;
    public static final Command App_0x80;
    public static final Command AppAudioEQPreset;
    public static final Command AppMicEQ;
    public static final Command AppSysState;
    public static final Command AppIsHeadsetOn;
    public static final Command AppANCEnable;
    public static final Command AppANCAwarenessPreset;
    public static final Command AppFirmwareVersion;
    public static final Command AppANCLevel;
    public static final Command AppAwarenessRawLeft;
    public static final Command AppAwarenessRawRight;
    public static final Command AppAwarenessRawSteps;
    public static final Command AppSpatialPreset;
    public static final Command AppGraphicEQSetting;
    public static final Command AppGraphicEQLimits;
    public static final Command AppGraphicEQDefaultPreset;
    public static final Command AppGraphicEQCurrentPreset;
    public static final Command AppGraphicEQFactoryPresetReset;
    public static final Command AppGraphicEQPresetBandSettings;
    public static final Command AppGraphicEQBand;
    public static final Command AppGraphicEQPersistPreset;
    public static final Command AppGraphicEQBandFreq;
    public static final Command AppGraphicEQFactoryResetPreset;
    public static final Command App_0x99;
    public static final Command App_0x9A;
    public static final Command App_0x9B;
    public static final Command App_0x9C;
    public static final Command App_0x9D;
    public static final Command App_0x9E;
    public static final Command App_0x9F;
    public static final Command AppSensorStatus;
    public static final Command AppTapThresholdHigh;
    public static final Command AppTapThresholdLow;
    public static final Command AppTapPulseWindow;
    public static final Command AppTapTimeout;
    public static final Command AppTapDebounce;
    public static final Command AppTapIdleDebounce;
    public static final Command AppTapCommit;
    public static final Command App9AxisSensorStatus;
    public static final Command App9AxisPushFrequency;
    public static final Command App9AxisData;
    public static final Command App_0xAB;
    public static final Command App_0xAC;
    public static final Command App_0xAD;
    public static final Command App_0xAE;
    public static final Command App_0xAF;
    public static final Command AppBatteryLevel;
    public static final Command App_0xB1;
    public static final Command App_0xB2;
    public static final Command App_0xB3;
    public static final Command App_0xB4;
    public static final Command AppSmartButtonFeatureIndex;
    public static final Command AppOnEarDetectionWithAutoOff;
    public static final Command AppVoicePromptEnable;
    public static final Command App_0xB8;
    public static final Command App_0xB9;
    public static final Command App_0xBA;
    public static final Command App_0xBB;
    public static final Command App_0xBC;
    public static final Command App_0xBD;
    public static final Command App_0xBE;
    public static final Command App_0xBF;
    public static final Command AppPushHeartrateSensorData;
    public static final Command AppPushANCEnable;
    public static final Command AppPushANCAwarenessPreset;
    public static final Command AppPush9AxisRawData;
    public static final Command AppPushTapDetect;
    public static final Command AppPushCustomEvent;
    public static final Command App_0xCA;
    public static final Command App_0xCB;
    public static final Command App_0xCC;
    public static final Command App_0xCD;
    public static final Command App_0xCE;
    public static final Command App_0xCF;
    public static final Command App_0xD0;
    public static final Command App_0xD1;
    public static final Command App_0xD2;
    public static final Command App_0xD3;
    public static final Command App_0xD4;
    public static final Command App_0xD5;
    public static final Command App_0xD6;
    public static final Command App_0xD7;
    public static final Command App_0xD8;
    public static final Command App_0xD9;
    public static final Command App_0xDA;
    public static final Command App_0xDB;
    public static final Command App_0xDC;
    public static final Command App_0xDD;
    public static final Command App_0xDE;
    public static final Command App_0xDF;
    public static final Command App_0xE0;
    public static final Command App_0xE1;
    public static final Command App_0xE2;
    public static final Command App_0xE3;
    public static final Command App_0xE4;
    public static final Command App_0xE5;
    public static final Command App_0xE6;
    public static final Command App_0xE7;
    public static final Command App_0xE8;
    public static final Command App_0xE9;
    public static final Command App_0xEA;
    public static final Command App_0xEB;
    public static final Command App_0xEC;
    public static final Command App_0xED;
    public static final Command App_0xEE;
    public static final Command App_0xEF;
    public static final Command App_0xF0;
    public static final Command App_0xF1;
    public static final Command App_0xF2;
    public static final Command App_0xF3;
    public static final Command App_0xF4;
    public static final Command App_0xF5;
    public static final Command App_0xF6;
    public static final Command App_0xF7;
    public static final Command App_0xF8;
    public static final Command App_0xF9;
    public static final Command App_0xFA;
    public static final Command App_0xFB;
    public static final Command App_0xFC;
    public static final Command App_0xFD;
    public static final Command App_0xFE;
    public static final Command App_0xFF;
    public static final Command ConfigFirst;
    public static final Command ConfigLast;
    public static final Command BootFirst;
    public static final Command BootLast;
    public static final Command AppFirst;
    public static final Command AppLast;
    public static final Command PushFirst;
    public static final Command PushLast;
    public static final Command LastEntry;
    private final int value;
    private static final Command $VALUES[];

    static 
    {
        Reserved = new Command("Reserved", 0, 0);
        ConfigProductName = new Command("ConfigProductName", 1, 1);
        ConfigManufacturerName = new Command("ConfigManufacturerName", 2, 2);
        ConfigModelNumber = new Command("ConfigModelNumber", 3, 3);
        ConfigSerialNumber = new Command("ConfigSerialNumber", 4, 4);
        ConfigHardwareVersion = new Command("ConfigHardwareVersion", 5, 5);
        ConfigFirmwareVersion = new Command("ConfigFirmwareVersion", 6, 6);
        ConfigBTDisplayName = new Command("ConfigBTDisplayName", 7, 17);
        ConfigSDHMProductName = new Command("ConfigSDHMProductName", 8, 26);
        ConfigSDHMManufacturerName = new Command("ConfigSDHMManufacturerName", 9, 27);
        ConfigSDHMModelNumber = new Command("ConfigSDHMModelNumber", 10, 28);
        ConfigSDHMSerialNumber = new Command("ConfigSDHMSerialNumber", 11, 29);
        ConfigSDHMBootVersion = new Command("ConfigSDHMBootVersion", 12, 30);
        ConfigSDHMHardwareVersion = new Command("ConfigSDHMHardwareVersion", 13, 31);
        BootImageType = new Command("BootImageType", 14, 32);
        BootAuthStatus = new Command("BootAuthStatus", 15, 34);
        BootAuthChallenge = new Command("BootAuthChallenge", 16, 35);
        BootAuthResponse = new Command("BootAuthResponse", 17, 36);
        BootJumpToBootloader = new Command("BootJumpToBootloader", 18, 37);
        BootEraseImageSector = new Command("BootEraseImageSector", 19, 38);
        BootWriteImage = new Command("BootWriteImage", 20, 39);
        BootReadImage = new Command("BootReadImage", 21, 40);
        BootCommitImage = new Command("BootCommitImage", 22, 41);
        BootJumpToApplication = new Command("BootJumpToApplication", 23, 42);
        BootVerifyCRC = new Command("BootVerifyCRC", 24, 44);
        BootEraseRsrc1Sector = new Command("BootEraseRsrc1Sector", 25, 45);
        BootWriteRsrc1 = new Command("BootWriteRsrc1", 26, 46);
        BootReadRsrc1 = new Command("BootReadRsrc1", 27, 47);
        BootEraseRsrc2Sector = new Command("BootEraseRsrc2Sector", 28, 48);
        BootWriteRsrc2 = new Command("BootWriteRsrc2", 29, 49);
        BootReadRsrc2 = new Command("BootReadRsrc2", 30, 50);
        AppShutdown = new Command("AppShutdown", 31, 127);
        App_0x80 = new Command("App_0x80", 32, 128);
        AppAudioEQPreset = new Command("AppAudioEQPreset", 33, 129);
        AppMicEQ = new Command("AppMicEQ", 34, 130);
        AppSysState = new Command("AppSysState", 35, 131);
        AppIsHeadsetOn = new Command("AppIsHeadsetOn", 36, 132);
        AppANCEnable = new Command("AppANCEnable", 37, 133);
        AppANCAwarenessPreset = new Command("AppANCAwarenessPreset", 38, 134);
        AppFirmwareVersion = new Command("AppFirmwareVersion", 39, 135);
        AppANCLevel = new Command("AppANCLevel", 40, 136);
        AppAwarenessRawLeft = new Command("AppAwarenessRawLeft", 41, 137);
        AppAwarenessRawRight = new Command("AppAwarenessRawRight", 42, 138);
        AppAwarenessRawSteps = new Command("AppAwarenessRawSteps", 43, 139);
        AppSpatialPreset = new Command("AppSpatialPreset", 44, 140);
        AppGraphicEQSetting = new Command("AppGraphicEQSetting", 45, 142);
        AppGraphicEQLimits = new Command("AppGraphicEQLimits", 46, 144);
        AppGraphicEQDefaultPreset = new Command("AppGraphicEQDefaultPreset", 47, 145);
        AppGraphicEQCurrentPreset = new Command("AppGraphicEQCurrentPreset", 48, 146);
        AppGraphicEQFactoryPresetReset = new Command("AppGraphicEQFactoryPresetReset", 49, 147);
        AppGraphicEQPresetBandSettings = new Command("AppGraphicEQPresetBandSettings", 50, 148);
        AppGraphicEQBand = new Command("AppGraphicEQBand", 51, 149);
        AppGraphicEQPersistPreset = new Command("AppGraphicEQPersistPreset", 52, 150);
        AppGraphicEQBandFreq = new Command("AppGraphicEQBandFreq", 53, 151);
        AppGraphicEQFactoryResetPreset = new Command("AppGraphicEQFactoryResetPreset", 54, 152);
        App_0x99 = new Command("App_0x99", 55, 153);
        App_0x9A = new Command("App_0x9A", 56, 154);
        App_0x9B = new Command("App_0x9B", 57, 155);
        App_0x9C = new Command("App_0x9C", 58, 156);
        App_0x9D = new Command("App_0x9D", 59, 157);
        App_0x9E = new Command("App_0x9E", 60, 158);
        App_0x9F = new Command("App_0x9F", 61, 159);
        AppSensorStatus = new Command("AppSensorStatus", 62, 160);
        AppTapThresholdHigh = new Command("AppTapThresholdHigh", 63, 161);
        AppTapThresholdLow = new Command("AppTapThresholdLow", 64, 162);
        AppTapPulseWindow = new Command("AppTapPulseWindow", 65, 163);
        AppTapTimeout = new Command("AppTapTimeout", 66, 164);
        AppTapDebounce = new Command("AppTapDebounce", 67, 165);
        AppTapIdleDebounce = new Command("AppTapIdleDebounce", 68, 166);
        AppTapCommit = new Command("AppTapCommit", 69, 167);
        App9AxisSensorStatus = new Command("App9AxisSensorStatus", 70, 168);
        App9AxisPushFrequency = new Command("App9AxisPushFrequency", 71, 169);
        App9AxisData = new Command("App9AxisData", 72, 170);
        App_0xAB = new Command("App_0xAB", 73, 171);
        App_0xAC = new Command("App_0xAC", 74, 172);
        App_0xAD = new Command("App_0xAD", 75, 173);
        App_0xAE = new Command("App_0xAE", 76, 174);
        App_0xAF = new Command("App_0xAF", 77, 175);
        AppBatteryLevel = new Command("AppBatteryLevel", 78, 176);
        App_0xB1 = new Command("App_0xB1", 79, 177);
        App_0xB2 = new Command("App_0xB2", 80, 178);
        App_0xB3 = new Command("App_0xB3", 81, 179);
        App_0xB4 = new Command("App_0xB4", 82, 180);
        AppSmartButtonFeatureIndex = new Command("AppSmartButtonFeatureIndex", 83, 181);
        AppOnEarDetectionWithAutoOff = new Command("AppOnEarDetectionWithAutoOff", 84, 182);
        AppVoicePromptEnable = new Command("AppVoicePromptEnable", 85, 183);
        App_0xB8 = new Command("App_0xB8", 86, 184);
        App_0xB9 = new Command("App_0xB9", 87, 185);
        App_0xBA = new Command("App_0xBA", 88, 186);
        App_0xBB = new Command("App_0xBB", 89, 187);
        App_0xBC = new Command("App_0xBC", 90, 188);
        App_0xBD = new Command("App_0xBD", 91, 189);
        App_0xBE = new Command("App_0xBE", 92, 190);
        App_0xBF = new Command("App_0xBF", 93, 191);
        AppPushHeartrateSensorData = new Command("AppPushHeartrateSensorData", 94, 192);
        AppPushANCEnable = new Command("AppPushANCEnable", 95, 197);
        AppPushANCAwarenessPreset = new Command("AppPushANCAwarenessPreset", 96, 198);
        AppPush9AxisRawData = new Command("AppPush9AxisRawData", 97, 199);
        AppPushTapDetect = new Command("AppPushTapDetect", 98, 200);
        AppPushCustomEvent = new Command("AppPushCustomEvent", 99, 201);
        App_0xCA = new Command("App_0xCA", 100, 202);
        App_0xCB = new Command("App_0xCB", 101, 203);
        App_0xCC = new Command("App_0xCC", 102, 204);
        App_0xCD = new Command("App_0xCD", 103, 205);
        App_0xCE = new Command("App_0xCE", 104, 206);
        App_0xCF = new Command("App_0xCF", 105, 207);
        App_0xD0 = new Command("App_0xD0", 106, 208);
        App_0xD1 = new Command("App_0xD1", 107, 209);
        App_0xD2 = new Command("App_0xD2", 108, 210);
        App_0xD3 = new Command("App_0xD3", 109, 211);
        App_0xD4 = new Command("App_0xD4", 110, 212);
        App_0xD5 = new Command("App_0xD5", 111, 213);
        App_0xD6 = new Command("App_0xD6", 112, 214);
        App_0xD7 = new Command("App_0xD7", 113, 215);
        App_0xD8 = new Command("App_0xD8", 114, 216);
        App_0xD9 = new Command("App_0xD9", 115, 217);
        App_0xDA = new Command("App_0xDA", 116, 218);
        App_0xDB = new Command("App_0xDB", 117, 219);
        App_0xDC = new Command("App_0xDC", 118, 220);
        App_0xDD = new Command("App_0xDD", 119, 221);
        App_0xDE = new Command("App_0xDE", 120, 222);
        App_0xDF = new Command("App_0xDF", 121, 223);
        App_0xE0 = new Command("App_0xE0", 122, 224);
        App_0xE1 = new Command("App_0xE1", 123, 225);
        App_0xE2 = new Command("App_0xE2", 124, 226);
        App_0xE3 = new Command("App_0xE3", 125, 227);
        App_0xE4 = new Command("App_0xE4", 126, 228);
        App_0xE5 = new Command("App_0xE5", 127, 229);
        App_0xE6 = new Command("App_0xE6", 128, 230);
        App_0xE7 = new Command("App_0xE7", 129, 231);
        App_0xE8 = new Command("App_0xE8", 130, 232);
        App_0xE9 = new Command("App_0xE9", 131, 233);
        App_0xEA = new Command("App_0xEA", 132, 234);
        App_0xEB = new Command("App_0xEB", 133, 235);
        App_0xEC = new Command("App_0xEC", 134, 236);
        App_0xED = new Command("App_0xED", 135, 237);
        App_0xEE = new Command("App_0xEE", 136, 238);
        App_0xEF = new Command("App_0xEF", 137, 239);
        App_0xF0 = new Command("App_0xF0", 138, 240);
        App_0xF1 = new Command("App_0xF1", 139, 241);
        App_0xF2 = new Command("App_0xF2", 140, 242);
        App_0xF3 = new Command("App_0xF3", 141, 243);
        App_0xF4 = new Command("App_0xF4", 142, 244);
        App_0xF5 = new Command("App_0xF5", 143, 245);
        App_0xF6 = new Command("App_0xF6", 144, 246);
        App_0xF7 = new Command("App_0xF7", 145, 247);
        App_0xF8 = new Command("App_0xF8", 146, 248);
        App_0xF9 = new Command("App_0xF9", 147, 249);
        App_0xFA = new Command("App_0xFA", 148, 250);
        App_0xFB = new Command("App_0xFB", 149, 251);
        App_0xFC = new Command("App_0xFC", 150, 252);
        App_0xFD = new Command("App_0xFD", 151, 253);
        App_0xFE = new Command("App_0xFE", 152, 254);
        App_0xFF = new Command("App_0xFF", 153, 255);
        ConfigFirst = new Command("ConfigFirst", 154, 1);
        ConfigLast = new Command("ConfigLast", 155, 31);
        BootFirst = new Command("BootFirst", 156, 32);
        BootLast = new Command("BootLast", 157, 63);
        AppFirst = new Command("AppFirst", 158, 128);
        AppLast = new Command("AppLast", 159, 255);
        PushFirst = new Command("PushFirst", 160, 192);
        PushLast = new Command("PushLast", 161, 255);
        LastEntry = new Command("LastEntry", 162, 0);
        $VALUES = (new Command[] {
            Reserved, ConfigProductName, ConfigManufacturerName, ConfigModelNumber, ConfigSerialNumber, ConfigHardwareVersion, ConfigFirmwareVersion, ConfigBTDisplayName, ConfigSDHMProductName, ConfigSDHMManufacturerName, 
            ConfigSDHMModelNumber, ConfigSDHMSerialNumber, ConfigSDHMBootVersion, ConfigSDHMHardwareVersion, BootImageType, BootAuthStatus, BootAuthChallenge, BootAuthResponse, BootJumpToBootloader, BootEraseImageSector, 
            BootWriteImage, BootReadImage, BootCommitImage, BootJumpToApplication, BootVerifyCRC, BootEraseRsrc1Sector, BootWriteRsrc1, BootReadRsrc1, BootEraseRsrc2Sector, BootWriteRsrc2, 
            BootReadRsrc2, AppShutdown, App_0x80, AppAudioEQPreset, AppMicEQ, AppSysState, AppIsHeadsetOn, AppANCEnable, AppANCAwarenessPreset, AppFirmwareVersion, 
            AppANCLevel, AppAwarenessRawLeft, AppAwarenessRawRight, AppAwarenessRawSteps, AppSpatialPreset, AppGraphicEQSetting, AppGraphicEQLimits, AppGraphicEQDefaultPreset, AppGraphicEQCurrentPreset, AppGraphicEQFactoryPresetReset, 
            AppGraphicEQPresetBandSettings, AppGraphicEQBand, AppGraphicEQPersistPreset, AppGraphicEQBandFreq, AppGraphicEQFactoryResetPreset, App_0x99, App_0x9A, App_0x9B, App_0x9C, App_0x9D, 
            App_0x9E, App_0x9F, AppSensorStatus, AppTapThresholdHigh, AppTapThresholdLow, AppTapPulseWindow, AppTapTimeout, AppTapDebounce, AppTapIdleDebounce, AppTapCommit, 
            App9AxisSensorStatus, App9AxisPushFrequency, App9AxisData, App_0xAB, App_0xAC, App_0xAD, App_0xAE, App_0xAF, AppBatteryLevel, App_0xB1, 
            App_0xB2, App_0xB3, App_0xB4, AppSmartButtonFeatureIndex, AppOnEarDetectionWithAutoOff, AppVoicePromptEnable, App_0xB8, App_0xB9, App_0xBA, App_0xBB, 
            App_0xBC, App_0xBD, App_0xBE, App_0xBF, AppPushHeartrateSensorData, AppPushANCEnable, AppPushANCAwarenessPreset, AppPush9AxisRawData, AppPushTapDetect, AppPushCustomEvent, 
            App_0xCA, App_0xCB, App_0xCC, App_0xCD, App_0xCE, App_0xCF, App_0xD0, App_0xD1, App_0xD2, App_0xD3, 
            App_0xD4, App_0xD5, App_0xD6, App_0xD7, App_0xD8, App_0xD9, App_0xDA, App_0xDB, App_0xDC, App_0xDD, 
            App_0xDE, App_0xDF, App_0xE0, App_0xE1, App_0xE2, App_0xE3, App_0xE4, App_0xE5, App_0xE6, App_0xE7, 
            App_0xE8, App_0xE9, App_0xEA, App_0xEB, App_0xEC, App_0xED, App_0xEE, App_0xEF, App_0xF0, App_0xF1, 
            App_0xF2, App_0xF3, App_0xF4, App_0xF5, App_0xF6, App_0xF7, App_0xF8, App_0xF9, App_0xFA, App_0xFB, 
            App_0xFC, App_0xFD, App_0xFE, App_0xFF, ConfigFirst, ConfigLast, BootFirst, BootLast, AppFirst, AppLast, 
            PushFirst, PushLast, LastEntry
        });
    }
}
