package com.game.babbel.di

import android.app.Application
import com.game.babbel.BabbelApp
import com.game.babbel.game.di.GameDomainModule
import com.game.babbel.game.di.GamePresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        FragmentBuilderModule::class,
        ActivityBuilderModule::class,GamePresentationModule::class,GameDomainModule::class,
        AppModule::class
    ]
)
interface BabbelAppComponent : AndroidInjector<BabbelApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): BabbelAppComponent
    }

    override fun inject(app: BabbelApp)
}