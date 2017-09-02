package com.example.android.miwok

class Word(mEnglishTranslation: String, mMiwokTranslation: String) {
    private var mEnglishTranslation: String = mEnglishTranslation
    private var mMiwokTranslation: String = mMiwokTranslation

    fun getDefaultTranslation() = mEnglishTranslation
    fun getMiwokTranslation() = mMiwokTranslation
}

