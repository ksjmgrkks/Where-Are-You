package com.android.whereareyou.core.data.cache

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.android.whereareyou.core.domain.model.Friend
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import java.sql.Wrapper
import javax.inject.Inject

@ProvidedTypeConverter
class FriendsListConverters @Inject constructor(
    private val moshi: Moshi) {
    @TypeConverter
    fun listToJson(value: List<Friend>?): String? {
        return moshi.adapter<List<Friend>>(Types.newParameterizedType(
            MutableList::class.java,
            String::class.java
        )).toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Friend>? {
        return moshi.adapter<List<Friend>>(Types.newParameterizedType(
            MutableList::class.java,
            String::class.java
        )).fromJson(value)
    }
}