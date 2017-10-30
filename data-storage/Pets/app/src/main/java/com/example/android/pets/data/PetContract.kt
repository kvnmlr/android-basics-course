package com.example.android.pets.data

import android.provider.BaseColumns

class PetContract {
    class PetEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "pets"

            val _ID: String = BaseColumns._ID
            val COLUMN_NAME: String = "name"
            val COLUMN_GENDER: String = "gender"
            val COLUMN_BREED: String = "breed"
            val COLUMN_WEIGHT: String = "weight"

            val GENDER_UNKNOWN = 0
            val GENDER_MALE = 1
            val GENDER_FEMALE = 2
        }
    }
}
