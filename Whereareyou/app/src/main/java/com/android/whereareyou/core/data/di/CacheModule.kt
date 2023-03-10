package com.android.whereareyou.core.data.di

import android.content.Context
import androidx.room.ProvidedTypeConverter
import androidx.room.Room
import com.android.whereareyou.core.data.cache.Cache
import com.android.whereareyou.core.data.cache.FriendsListConverters
import com.android.whereareyou.core.data.cache.RoomCache
import com.android.whereareyou.core.data.cache.WhereAreYouDatabase
import com.android.whereareyou.core.data.cache.dao.DailyScheduleDao
import com.android.whereareyou.core.data.cache.dao.WeeklyScheduleDao
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {
        @Provides
        @Singleton
        fun provideMoshi(): Moshi = Moshi.Builder().build()

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context,
            friendsListConverter : FriendsListConverters
        ): WhereAreYouDatabase {
            return Room.databaseBuilder(
                context,
                WhereAreYouDatabase::class.java,
                "whereareyou.db"
            )
                .addTypeConverter(friendsListConverter)
                .build()
        }

        @Provides
        fun provideWeeklySchedulesDao(
            whereAreYouDatabase: WhereAreYouDatabase
        ): WeeklyScheduleDao = whereAreYouDatabase.weeklyScheduleDao()

        @Provides
        fun provideDailySchedulesDao(
            whereAreYouDatabase: WhereAreYouDatabase
        ): DailyScheduleDao = whereAreYouDatabase.dailyScheduleDao()
    }
}