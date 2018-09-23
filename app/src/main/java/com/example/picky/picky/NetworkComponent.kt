package com.example.picky.picky

import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Component (modules = [NetworkModule::class])
@Singleton // FIXME: Change to application level for more customization
interface NetworkComponent {
    fun inject(pickyApplication: PickyApplication)

    fun getHttpClient(): OkHttpClient
}
