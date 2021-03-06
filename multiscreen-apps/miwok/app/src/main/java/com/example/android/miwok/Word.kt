package com.example.android.miwok

class Word {
    companion object {
        const val NO_IMAGE_PROVIDED = -1
        const val NO_AUDIO_PROVIDED = -1
    }
    private var mEnglishTranslation: String
    private var mMiwokTranslation: String
    private var mImageResourceID: Int = NO_IMAGE_PROVIDED
    private var mAudioResourceID: Int = NO_AUDIO_PROVIDED

    constructor(mEnglishTranslation: String, mMiwokTranslation: String) {
        this.mEnglishTranslation = mEnglishTranslation
        this.mMiwokTranslation = mMiwokTranslation
    }
    constructor(mEnglishTranslation: String, mMiwokTranslation: String, mImageResourceID: Int, mAudioResourceID: Int) {
        this.mEnglishTranslation = mEnglishTranslation
        this.mMiwokTranslation = mMiwokTranslation
        this.mImageResourceID = mImageResourceID
        this.mAudioResourceID = mAudioResourceID
    }

    fun getDefaultTranslation() = mEnglishTranslation
    fun getMiwokTranslation() = mMiwokTranslation
    fun getImageResourceID() = mImageResourceID
    fun getAudioResrouceID() = mAudioResourceID
    fun hasImage() = mImageResourceID != NO_IMAGE_PROVIDED
    fun hasAudio() = mAudioResourceID != NO_AUDIO_PROVIDED
}

