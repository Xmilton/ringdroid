package com.ringdroid;

import com.ringdroid.soundfile.SoundFile;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milton on 17/3/6.
 */

public class EffectManager {
    private static EffectManager instance = null;
    private List<AudioEffect> mEffects = new ArrayList<>();
    private ByteBuffer mDecodedBytes;
    protected SoundFile mSoundFile;

    public static EffectManager getInstance(){
        if(instance==null){
            instance = new EffectManager();
        }
        return instance;
    }

    public void setSoundFile(SoundFile soundFile){
        mSoundFile = soundFile;
        addEffects(new FadeInAndOutEffect(this));
    }

    private EffectManager() {

    }

    public EffectManager addEffects(AudioEffect effect) {
        mEffects.clear();
        mEffects.add(effect);
        return this;
    }

    public void handle(float playStartTime, float playEndTime){
        mDecodedBytes = null;
        mDecodedBytes = ByteBuffer.allocate(mSoundFile.getDecodedBytes().capacity());
        mDecodedBytes.order(ByteOrder.LITTLE_ENDIAN);
        mDecodedBytes.put(mSoundFile.getDecodedBytes());
        mDecodedBytes.rewind();
        mSoundFile.getDecodedBytes().rewind();
        for (AudioEffect effect:mEffects){
            mDecodedBytes = effect.handle(mDecodedBytes,playStartTime,playEndTime);
        }
    }

    public ByteBuffer getDecodedBytes() {
        return mDecodedBytes;
    }
}
