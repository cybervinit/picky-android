package com.example.picky.picky.helpers

import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.Cookie

class PickyCookieJar(
        setCookieCache: SetCookieCache,
        sharedPrefsCookiePersistor: SharedPrefsCookiePersistor
) {
    public var sharedPrefsCookiePersistor = sharedPrefsCookiePersistor
    public var setCookieCache = setCookieCache
    public var sessionCookie: Cookie = Cookie.Builder()
            .name("preinit")
            .value("")
            .domain("some.com")
            .build()

    init {
        var it: MutableIterator<Cookie> = setCookieCache.iterator()
        var currCookie: Cookie? = null
        while (it.hasNext()) {
            currCookie = it.next()
            if (currCookie.name() == "session") {
                break
            }
        }
        if (currCookie != null && currCookie.name() == "session") {
            this.sessionCookie = currCookie
        }
    }


    fun retrieveSessionCookie(): Cookie? {
        var it: MutableIterator<Cookie> = setCookieCache.iterator()
        var currCookie: Cookie? = null
        while (it.hasNext()) {
            currCookie = it.next()
            if (currCookie.name() == "session") {
                break
            }
        }
        if (currCookie != null && currCookie.name() == "session") {
            return currCookie
        }
        return null
    }
}