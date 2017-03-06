package com.ringdroid;

import com.ringdroid.soundfile.SoundFile;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by milton on 17/3/6.
 */

public abstract class AudioEffect{
    protected EffectManager mEffectManager;
    public AudioEffect(EffectManager effectManager){
        mEffectManager = effectManager;
    }

    public abstract ByteBuffer handle(ByteBuffer byteBuffer,float playStartTime, float playEndTime);

}
