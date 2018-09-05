package com.example.picky.picky.login.dagger

import com.example.picky.picky.login.LoginPresenter
import com.example.picky.picky.login.interfacing.ILoginPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class BinderModule {

    @Binds
    abstract fun bindLoginPresenterForView(loginPresenter: LoginPresenter): ILoginPresenter.forView

    @Binds
    abstract fun bindLoginPresenterForModel(loginPresenter: LoginPresenter): ILoginPresenter.forModel
}
