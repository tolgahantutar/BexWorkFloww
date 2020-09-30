package com.tolgahantutar.bexworkfloww.dagger.components

import com.tolgahantutar.bexworkfloww.dagger.modules.AuthorizeSessionRepositoryModule
import com.tolgahantutar.bexworkfloww.ui.auth.LoginActivity
import dagger.Component

@Component(modules = arrayOf(AuthorizeSessionRepositoryModule::class))
interface AuthorizeSessionRepositoryComponent{
    fun inject(loginActivity: LoginActivity)
}