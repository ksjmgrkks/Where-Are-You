package com.android.whereareyou.core.di

import com.android.whereareyou.core.data.repository.WeeklyScheduleRepositoryImpl
import com.android.whereareyou.core.domain.repository.WeeklyScheduleRepository
import com.android.whereareyou.core.util.CoroutineDispatchersProvider
import com.android.whereareyou.core.util.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.disposables.CompositeDisposable

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindWeeklyScheduleRepository(repository: WeeklyScheduleRepositoryImpl): WeeklyScheduleRepository

    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: CoroutineDispatchersProvider):
            DispatchersProvider

    companion object {
        @Provides
        fun provideCompositeDisposable() = CompositeDisposable()
    }
}
