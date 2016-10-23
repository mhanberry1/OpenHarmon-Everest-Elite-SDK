// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HeadPhoneCtrl.java

package com.harman.everestelite;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import java.io.IOException;

// Referenced classes of package com.harman.everestelite:
//            LightX, BluetoothSocketWrapper, ANCAwarenessPreset, Command, 
//            ModuleId, Utility, Log, ANCCtrlListner, 
//            EQSettingListner, CalibrateListener, CommonListner, GraphicEQPreset

public class HeadPhoneCtrl
    implements LightX.Delegate
{
    public class CommonCtrl
    {

        public void getProgrammableIndexButton()
        {
            mLightX.readAppSmartButtonFeatureIndex();
        }

        public void setProgrammableIndexButton(int idx)
        {
            mLightX.writeAppSmartButtonFeatureIndex(idx);
        }

        public void getAppPushCustomEvent()
        {
            mLightX.readAppPushCustomEvent();
        }

        public void set9AxisSensorStatus(boolean enable)
        {
            mLightX.writeApp9AxisSensorStatus(enable);
        }

        public void set9AxisPushFrequency(int frequency)
        {
            mLightX.writeApp9AxisPushFrequency(frequency);
        }

        public void get9AxisSensorStatus()
        {
            mLightX.read9AxisSensorStatus();
        }

        public void get9AxisPushFrequency()
        {
            mLightX.readApp9AxisPushFrequency();
        }

        public void get9AxisRawData()
        {
            mLightX.read9AxisRawData();
        }

        public void getConfigModelNumber()
        {
            mLightX.readConfigModelNumber();
        }

        public void getConfigProductName()
        {
            mLightX.readConfigProductName();
        }

        public void getAutoOffFeature()
        {
            mLightX.readAppOnEarDetectionWithAutoOff();
        }

        public void setAutoOffFeature(boolean autoOff)
        {
            mLightX.writeAppOnEarDetectionWithAutoOff(autoOff);
        }

        public void getEnableVoicePrompt()
        {
            mLightX.readAppVoicePromptEnable();
        }

        public void setEnableVoicePrompt(boolean voiceprompt)
        {
            mLightX.writeAppVoicePromptEnable(voiceprompt);
        }

        public void getFirmwareVersion()
        {
            mLightX.readAppFirmwareVersion();
        }

        final HeadPhoneCtrl this$0;

        public CommonCtrl()
        {
            this.this$0 = HeadPhoneCtrl.this;
            super();
        }
    }

    public class CalibrateCtrl
    {

        public void startCalibration()
        {
            mLightX.writeAppWithUInt32Argument(Command.App_0xB2, 0L);
        }

        public void stopCalibration()
        {
            mLightX.writeAppWithUInt32Argument(Command.App_0xB3, 0L);
        }

        public void getCalibrationStatus()
        {
            mLightX.readApp(Command.App_0xB3);
        }

        final HeadPhoneCtrl this$0;

        public CalibrateCtrl()
        {
            this.this$0 = HeadPhoneCtrl.this;
            super();
        }
    }

    public class EQSettingCtrl
    {

        public void getCurrentPreset()
        {
            mLightX.readAppGraphicEQCurrentPreset();
        }

        public void applyPresetWithoutBand(GraphicEQPreset presetType)
        {
            mLightX.writeAppGraphicEQCurrentPreset(presetType);
        }

        public void applyPresetWithBand(GraphicEQPreset preset, int values[])
        {
            try
            {
                applyPresetWithoutBand(preset);
                int band = 9;
                int ai[] = values;
                int i = ai.length;
                for(int j = 0; j < i; j++)
                {
                    int one = ai[j];
                    mLightX.writeAppGraphicEQBand(preset, band, one);
                    band--;
                }

            }
            catch(IllegalArgumentException illegalargumentexception) { }
        }

        public void getAllGraphicEQValues(GraphicEQPreset preset)
        {
            int kGraphicEQNumPresets = 4;
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < mGraphicEQLimitNumBands; j++)
                    mLightX.readAppGraphicEQBand(preset, j);

            }

            mLightX.readAppGraphicEQBandFreq();
            mLightX.readAppGraphicEQCurrentPreset();
            for(int i = 0; i < 4; i++)
                mLightX.readAppGraphicEQPresetBandSettings(preset);

        }

        public int mGraphicEQLimitNumBands;
        public int mGraphicEQLimitNumSettings;
        public int minLimit;
        public int maxLimit;
        final HeadPhoneCtrl this$0;

        public EQSettingCtrl()
        {
            this.this$0 = HeadPhoneCtrl.this;
            super();
        }
    }

    public class ANCCtrl
    {

        public void switchANC(boolean onOff)
        {
            mLightX.writeAppANCEnable(onOff);
        }

        public void getANCEnable()
        {
            mLightX.readAppANCEnable();
        }

        public void getANCAwarenessPreset()
        {
            mLightX.readAppANCAwarenessPreset();
        }

        public void setANCAwarenessPreset(ANCAwarenessPreset preset)
        {
            mLightX.writeAppANCAwarenessPreset(preset);
        }

        public void setLeftAwarenessPresetValue(int leftANCvalue)
        {
            int rawSteps = ancValueConverter(leftANCvalue);
            mLightX.writeAppWithUInt32Argument(Command.AppAwarenessRawLeft, rawSteps);
        }

        public void setRightAwarenessPresetValue(int rightANCvalue)
        {
            int rawSteps = ancValueConverter(rightANCvalue);
            mLightX.writeAppWithUInt32Argument(Command.AppAwarenessRawRight, rawSteps);
        }

        public void getLeftANCvalue()
        {
            mLightX.readAppAwarenessRawLeft();
        }

        public void getRightANCvalue()
        {
            mLightX.readAppAwarenessRawRight();
        }

        public void getBatteryLevel()
        {
            mLightX.readApp(Command.AppBatteryLevel);
        }

        final HeadPhoneCtrl this$0;

        public ANCCtrl()
        {
            this.this$0 = HeadPhoneCtrl.this;
            super();
        }
    }


    public static BluetoothSocket getSocket()
    {
        return mSocket;
    }

    public void setAncListner(ANCCtrlListner ancListner)
    {
        this.ancListner = ancListner;
    }

    public void setEqListner(EQSettingListner eqListner)
    {
        this.eqListner = eqListner;
    }

    public void setCaliListener(CalibrateListener caliListener)
    {
        this.caliListener = caliListener;
    }

    public void setCommonListner(CommonListner commonListner)
    {
        this.commonListner = commonListner;
    }

    public int ancValueConverter(int ancValue)
    {
        int steps = 0;
        steps = (ancValue * 7) / 100;
        return steps;
    }

    public static synchronized HeadPhoneCtrl getInstance(Activity activity, BluetoothSocket bluetoothSocket)
    {
        if(instance == null)
        {
            mSocket = bluetoothSocket;
            instance = new HeadPhoneCtrl(activity);
        } else
        {
            instance.resetHeadPhoneCtrl(bluetoothSocket);
        }
        return instance;
    }

    private HeadPhoneCtrl(Activity activity)
    {
        TAG = com/harman/everestelite/HeadPhoneCtrl.getSimpleName();
        try
        {
            mLightX = new LightX(ModuleId.Application, this, new BluetoothSocketWrapper(mSocket));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        this.activity = activity;
        ancCtrl = new ANCCtrl();
        eqCtrl = new EQSettingCtrl();
        calCtrl = new CalibrateCtrl();
        commonCtrl = new CommonCtrl();
    }

    public void close()
    {
        if(mLightX != null)
            mLightX.close();
        mLightX = null;
    }

    public void resetHeadPhoneCtrl(BluetoothSocket bluetoothSocket)
    {
        mSocket = bluetoothSocket;
        try
        {
            mLightX = new LightX(ModuleId.Application, this, new BluetoothSocketWrapper(mSocket));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void lightXAppReadResult(LightX lightX, Command command, final boolean success, byte buffer[])
    {
        static class _cls27
        {

            static final int $SwitchMap$com$harman$everestelite$Command[];

            static 
            {
                $SwitchMap$com$harman$everestelite$Command = new int[Command.values().length];
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.App_0xB3.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppBatteryLevel.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppANCAwarenessPreset.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppANCEnable.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppAwarenessRawLeft.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppAwarenessRawRight.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppAwarenessRawSteps.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppGraphicEQCurrentPreset.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppGraphicEQBand.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppGraphicEQLimits.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppGraphicEQPresetBandSettings.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppSmartButtonFeatureIndex.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppPushCustomEvent.ordinal()] = 13;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppOnEarDetectionWithAutoOff.ordinal()] = 14;
                }
                catch(NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppVoicePromptEnable.ordinal()] = 15;
                }
                catch(NoSuchFieldError nosuchfielderror14) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppFirmwareVersion.ordinal()] = 16;
                }
                catch(NoSuchFieldError nosuchfielderror15) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.App9AxisSensorStatus.ordinal()] = 17;
                }
                catch(NoSuchFieldError nosuchfielderror16) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.App9AxisPushFrequency.ordinal()] = 18;
                }
                catch(NoSuchFieldError nosuchfielderror17) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.App9AxisData.ordinal()] = 19;
                }
                catch(NoSuchFieldError nosuchfielderror18) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppPushANCEnable.ordinal()] = 20;
                }
                catch(NoSuchFieldError nosuchfielderror19) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppPushANCAwarenessPreset.ordinal()] = 21;
                }
                catch(NoSuchFieldError nosuchfielderror20) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppPushTapDetect.ordinal()] = 22;
                }
                catch(NoSuchFieldError nosuchfielderror21) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppPush9AxisRawData.ordinal()] = 23;
                }
                catch(NoSuchFieldError nosuchfielderror22) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.App_0xB2.ordinal()] = 24;
                }
                catch(NoSuchFieldError nosuchfielderror23) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.AppGraphicEQBandFreq.ordinal()] = 25;
                }
                catch(NoSuchFieldError nosuchfielderror24) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.ConfigProductName.ordinal()] = 26;
                }
                catch(NoSuchFieldError nosuchfielderror25) { }
                try
                {
                    $SwitchMap$com$harman$everestelite$Command[Command.ConfigModelNumber.ordinal()] = 27;
                }
                catch(NoSuchFieldError nosuchfielderror26) { }
            }
        }

        if(success)
            switch(_cls27..SwitchMap.com.harman.everestelite.Command[command.ordinal()])
            {
            default:
                break;

            case 1: // '\001'
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(caliListener != null)
                            caliListener.calibrationCompleteReply(success);
                    }

                    final boolean val$success;
                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                success = flag;
                super();
            }
                });
                Log.e("Calibration", "write success");
                break;

            case 2: // '\002'
                uintVal = Utility.getUnsignedInt(buffer, 0);
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(ancListner != null)
                            ancListner.getBatteryLevelReply(uintVal);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                break;

            case 3: // '\003'
                intValue = Utility.getInt(buffer, 0);
                final ANCAwarenessPreset ancPreset;
                switch(intValue)
                {
                case 0: // '\0'
                default:
                    ancPreset = ANCAwarenessPreset.None;
                    break;

                case 1: // '\001'
                    ancPreset = ANCAwarenessPreset.Low;
                    break;

                case 2: // '\002'
                    ancPreset = ANCAwarenessPreset.Medium;
                    break;

                case 3: // '\003'
                    ancPreset = ANCAwarenessPreset.High;
                    break;
                }
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(ancListner != null)
                            ancListner.getANCAwarenessPresetReply(ancPreset);
                    }

                    final ANCAwarenessPreset val$ancPreset;
                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                ancPreset = ancawarenesspreset;
                super();
            }
                });
                Log.d(TAG, (new StringBuilder()).append(command).append(" is ").append(ANCAwarenessPreset.from(intValue)).toString());
                break;

            case 4: // '\004'
                boolValue = Utility.getBoolean(buffer, 0);
                Log.e("ANC result", String.valueOf(boolValue));
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(ancListner != null)
                            ancListner.getANCSwitchStateReply(boolValue);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                break;

            case 5: // '\005'
                uintVal = Utility.getUnsignedInt(buffer, 0);
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(ancListner != null)
                            ancListner.getLeftANCValueReply(uintVal);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                Log.d(TAG, (new StringBuilder()).append(command).append(" is ").append(uintVal).toString());
                break;

            case 6: // '\006'
                uintVal = Utility.getUnsignedInt(buffer, 0);
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(ancListner != null)
                            ancListner.getRightANCValueReply(uintVal);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                Log.d(TAG, (new StringBuilder()).append(command).append(" is ").append(uintVal).toString());
                break;

            case 7: // '\007'
                uintVal = Utility.getUnsignedInt(buffer, 0);
                Log.d(TAG, (new StringBuilder()).append(command).append(" is ").append(uintVal).toString());
                break;

            case 8: // '\b'
                intValue = Utility.getInt(buffer, 0);
                if(eqListner == null)
                {
                    Log.d(TAG, (new StringBuilder()).append(eqListner).append(" is null").toString());
                    return;
                }
                final String eqName;
                switch(intValue)
                {
                case 0: // '\0'
                    eqName = "Off";
                    break;

                case 1: // '\001'
                    eqName = "Jazz";
                    break;

                case 2: // '\002'
                    eqName = "Vocal";
                    break;

                case 3: // '\003'
                    eqName = "Bass";
                    break;

                default:
                    eqName = "User";
                    break;
                }
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        eqListner.getCurrentEQPresetReply(eqName, intValue);
                    }

                    final String val$eqName;
                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                eqName = s;
                super();
            }
                });
                break;

            case 9: // '\t'
                int eqPreset = Utility.getInt(buffer, 0);
                int band = Utility.getInt(buffer, 4);
                int value = Utility.getInt(buffer, 8);
                Log.d(String.format("graphic eq preset %d, band: %d value: %d", new Object[] {
                    Integer.valueOf(eqPreset), Integer.valueOf(band), Integer.valueOf(value)
                }));
                break;

            case 10: // '\n'
                eqCtrl.mGraphicEQLimitNumBands = Utility.getInt(buffer, 0);
                eqCtrl.mGraphicEQLimitNumSettings = Utility.getInt(buffer, 4);
                eqCtrl.minLimit = Utility.getInt(buffer, 8);
                eqCtrl.maxLimit = Utility.getInt(buffer, 12);
                Log.d(String.format("graphic eq limits, num bands: %d, num settings: %d, settings min: %d, settings max: %d", new Object[] {
                    Integer.valueOf(eqCtrl.mGraphicEQLimitNumBands), Integer.valueOf(eqCtrl.mGraphicEQLimitNumSettings), Integer.valueOf(eqCtrl.minLimit), Integer.valueOf(eqCtrl.maxLimit)
                }));
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(eqListner != null)
                            eqListner.getEQMinMaxParam(eqCtrl.mGraphicEQLimitNumBands, eqCtrl.mGraphicEQLimitNumSettings, eqCtrl.minLimit, eqCtrl.maxLimit);
                        eqCtrl.getAllGraphicEQValues(GraphicEQPreset.Off);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                break;

            case 11: // '\013'
                final int preset = Utility.getInt(buffer, 0);
                final int numBands = Utility.getInt(buffer, 4);
                final long valueArray[] = new long[numBands];
                int i = 0;
                for(int offset = 8; i < numBands; offset += 4)
                {
                    uintVal = Utility.getUnsignedInt(buffer, offset);
                    valueArray[i] = uintVal;
                    Log.d(String.format("graphic eq preset %d band %d setting: %d", new Object[] {
                        Integer.valueOf(preset), Integer.valueOf(i), Long.valueOf(uintVal)
                    }));
                    i++;
                }

                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(eqListner != null)
                            eqListner.getEQSettingParamReply(preset, numBands, valueArray);
                    }

                    final int val$preset;
                    final int val$numBands;
                    final long val$valueArray[];
                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                preset = i;
                numBands = j;
                valueArray = al;
                super();
            }
                });
                break;

            case 12: // '\f'
                intValue = Utility.getInt(buffer, 0);
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(commonListner != null)
                            commonListner.getProgrammableIndexButtonReply(intValue);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                break;

            case 13: // '\r'
                intValue = Utility.getInt(buffer, 0);
                break;

            case 14: // '\016'
                boolValue = Utility.getBoolean(buffer, 0);
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(commonListner != null)
                            commonListner.getAutoOffFeatureReply(boolValue);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                break;

            case 15: // '\017'
                boolValue = Utility.getBoolean(buffer, 0);
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(commonListner != null)
                            commonListner.getEnableVoicePromptReply(boolValue);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                break;

            case 16: // '\020'
                final int major = buffer[0];
                final int minor = buffer[1];
                final int revision = buffer[2];
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(commonListner != null)
                            commonListner.getFirmwareVersionReply(revision, minor, major);
                    }

                    final int val$revision;
                    final int val$minor;
                    final int val$major;
                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                revision = i;
                minor = j;
                major = k;
                super();
            }
                });
                break;

            case 17: // '\021'
                boolValue = Utility.getBoolean(buffer, 0);
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(commonListner != null)
                            commonListner.get9AxisSensorStatusReply(boolValue);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                break;

            case 18: // '\022'
                intValue = Utility.getInt(buffer, 0);
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(commonListner != null)
                            commonListner.get9AxisPushFrequencyReply(intValue);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                break;

            case 19: // '\023'
                accelX = Utility.getAccelValue(buffer, 0);
                accelY = Utility.getAccelValue(buffer, 4);
                accelZ = Utility.getAccelValue(buffer, 8);
                gyroX = Utility.getGyroValue(buffer, 12);
                gyroY = Utility.getGyroValue(buffer, 16);
                gyroZ = Utility.getGyroValue(buffer, 20);
                magnetoX = Utility.getMagnetoValue(buffer, 24);
                magnetoY = Utility.getMagnetoValue(buffer, 28);
                magnetoZ = Utility.getMagnetoValue(buffer, 32);
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(commonListner != null)
                            commonListner.get9AxisRawDataReply(accelX, accelY, accelZ, gyroX, gyroY, gyroZ, magnetoX, magnetoY, magnetoZ);
                    }

                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
                });
                break;
            }
    }

    public void lightXAppReceivedPush(LightX lightX, Command command, byte buffer[])
    {
        int i;
        switch(_cls27..SwitchMap.com.harman.everestelite.Command[command.ordinal()])
        {
        case 20: // '\024'
            ancCtrl.getANCEnable();
            break;

        case 21: // '\025'
            ancCtrl.getANCAwarenessPreset();
            break;

        case 22: // '\026'
            i = 0;
            break;

        case 13: // '\r'
            activity.runOnUiThread(new Runnable() {

                public void run()
                {
                    if(commonListner != null)
                        commonListner.getCustomButtonReply();
                }

                final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                super();
            }
            });
            commonCtrl.get9AxisRawData();
            break;

        case 23: // '\027'
            commonCtrl.get9AxisRawData();
            break;
        }
    }

    public void lightXAppWriteResult(LightX lightX, Command command, final boolean success)
    {
        long i = 0L;
        switch(_cls27..SwitchMap.com.harman.everestelite.Command[command.ordinal()])
        {
        case 24: // '\030'
            activity.runOnUiThread(new Runnable() {

                public void run()
                {
                    if(caliListener != null)
                        caliListener.calibrationCompleteReply(success);
                }

                final boolean val$success;
                final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                success = flag;
                super();
            }
            });
            Log.e("Calibration", "write succes");
            break;

        case 3: // '\003'
            ancCtrl.getLeftANCvalue();
            ancCtrl.getRightANCvalue();
            Log.e((new StringBuilder()).append(TAG).append("AppANCAwarenessPreset").toString());
            break;

        case 8: // '\b'
            Log.e(TAG, (new StringBuilder()).append("EQBand value=").append(success).toString());
            break;

        case 25: // '\031'
            Log.e(TAG, (new StringBuilder()).append("EQBand value=").append(success).toString());
            break;

        case 14: // '\016'
            activity.runOnUiThread(new Runnable() {

                public void run()
                {
                    if(commonListner != null)
                        commonListner.setAutoOffFeatureReply(success);
                }

                final boolean val$success;
                final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                success = flag;
                super();
            }
            });
            Log.d(TAG, command.name());
            break;

        case 15: // '\017'
            activity.runOnUiThread(new Runnable() {

                public void run()
                {
                    if(commonListner != null)
                        commonListner.setEnableVoicePromptReply(success);
                }

                final boolean val$success;
                final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                success = flag;
                super();
            }
            });
            Log.d(TAG, command.name());
            break;

        case 17: // '\021'
            activity.runOnUiThread(new Runnable() {

                public void run()
                {
                    if(commonListner != null)
                        commonListner.set9AxisSensorStatusReply(success);
                }

                final boolean val$success;
                final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                success = flag;
                super();
            }
            });
            break;

        case 18: // '\022'
            activity.runOnUiThread(new Runnable() {

                public void run()
                {
                    if(commonListner != null)
                        commonListner.set9AxisPushFrequencyReply(success);
                }

                final boolean val$success;
                final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                success = flag;
                super();
            }
            });
            break;
        }
    }

    public boolean lightXAwaitingReply(LightX lightX, Command command, final int elapsedMs)
    {
        activity.runOnUiThread(new Runnable() {

            public void run()
            {
                if(commonListner != null)
                    commonListner.waitCommandReplyElapsedTime(elapsedMs);
            }

            final int val$elapsedMs;
            final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                elapsedMs = i;
                super();
            }
        });
        return false;
    }

    public void lightXError(LightX lightX, final Exception exception)
    {
        activity.runOnUiThread(new Runnable() {

            public void run()
            {
                if(commonListner != null)
                    commonListner.headPhoneError(exception);
            }

            final Exception val$exception;
            final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                exception = exception1;
                super();
            }
        });
    }

    public boolean lightXFirmwareReadStatus(LightX lightX, LightX.FirmwareRegion region, int success, byte abyte0[], Exception exception1)
    {
        return false;
    }

    public boolean lightXFirmwareWriteStatus(LightX lightX, LightX.FirmwareRegion region, LightX.FirmwareWriteOperation operation, double d, Exception exception1)
    {
        return false;
    }

    public void lightXIsInBootloader(LightX lightx, boolean flag)
    {
    }

    public void lightXReadConfigResult(LightX lightX, Command command, boolean success, final String result)
    {
        if(success)
            switch(_cls27..SwitchMap.com.harman.everestelite.Command[command.ordinal()])
            {
            case 26: // '\032'
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(commonListner != null)
                            commonListner.getConfigProductNameReply(result);
                    }

                    final String val$result;
                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                result = s;
                super();
            }
                });
                break;

            case 27: // '\033'
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        if(commonListner != null)
                            commonListner.getConfigModelNumberReply(result);
                    }

                    final String val$result;
                    final HeadPhoneCtrl this$0;

            
            {
                this.this$0 = HeadPhoneCtrl.this;
                result = s;
                super();
            }
                });
                break;
            }
        else
            Log.e((new StringBuilder()).append("failed to read config for ").append(command).toString());
    }

    LightX mLightX;
    Activity activity;
    static HeadPhoneCtrl instance;
    static BluetoothSocket mSocket;
    String TAG;
    private final int kSizeofUInt32 = 4;
    public ANCCtrl ancCtrl;
    ANCCtrlListner ancListner;
    public EQSettingCtrl eqCtrl;
    EQSettingListner eqListner;
    public CalibrateCtrl calCtrl;
    CalibrateListener caliListener;
    public CommonCtrl commonCtrl;
    CommonListner commonListner;
    private long uintVal;
    private int intValue;
    private boolean boolValue;
    private double accelX;
    private double accelY;
    private double accelZ;
    private double gyroX;
    private double gyroY;
    private double gyroZ;
    private double magnetoX;
    private double magnetoY;
    private double magnetoZ;












}
