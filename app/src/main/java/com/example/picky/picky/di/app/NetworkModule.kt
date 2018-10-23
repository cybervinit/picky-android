package com.example.picky.picky.di.app

import android.content.Context
import com.example.picky.picky.di.scopes.PickyApplicationScope
import com.example.picky.picky.helpers.PickyCookieJar
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.CookieCache
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import dagger.Module
import dagger.Provides
import okhttp3.CookieJar
import okhttp3.OkHttpClient

@Module
class NetworkModule {


    @Provides
    @PickyApplicationScope
    fun providesSetCookieCache(): SetCookieCache {
        return SetCookieCache()
    }

    @Provides
    @PickyApplicationScope
    fun providesSharedPrefsCookiePersistor(context: Context): SharedPrefsCookiePersistor {
        return SharedPrefsCookiePersistor(context)
    }

    @Provides
    @PickyApplicationScope
    fun providesPickyCookieJar(setCookieCache: SetCookieCache, sharedPrefsCookiePersistor: SharedPrefsCookiePersistor): PickyCookieJar {
        return PickyCookieJar(setCookieCache, sharedPrefsCookiePersistor)
    }

    @Provides
    @PickyApplicationScope
    fun providesPersistentCookieJar(setCookieCache: SetCookieCache,
                                    sharedPrefsCookiePersistor: SharedPrefsCookiePersistor):
            PersistentCookieJar {
        return PersistentCookieJar(setCookieCache, sharedPrefsCookiePersistor)
    }

    @Provides
    @PickyApplicationScope
    fun providesOkHttpClient(cookieJar: PersistentCookieJar): OkHttpClient {
        return OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build()
    }

}