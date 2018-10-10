package com.example.picky.picky.di.context

import com.example.picky.picky.di.scopes.ActivityScope
import com.example.picky.picky.login.LoginPresenter
import com.example.picky.picky.login.interfacing.ILoginPresenter
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ContextBindModule {

    @Binds
    @ActivityScope
    abstract fun bindLoginPresenterForView(loginPresenter: LoginPresenter): ILoginPresenter.forView

    @Binds
    @ActivityScope
    abstract fun bindLoginPresenterForModel(loginPresenter: LoginPresenter): ILoginPresenter.forModel
}
