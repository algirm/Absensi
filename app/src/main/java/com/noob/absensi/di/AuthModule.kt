package com.noob.absensi.di

import com.google.firebase.auth.FirebaseAuth
import com.noob.absensi.data.AuthRepository
import com.noob.absensi.data.AuthRepositoryImpl
import com.noob.absensi.data.network.AuthApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class AuthModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    companion object {

        @ActivityRetainedScoped
        @Provides
        fun provideAuthApi(firebaseAuth: FirebaseAuth): AuthApi = AuthApi(firebaseAuth)

    }

}