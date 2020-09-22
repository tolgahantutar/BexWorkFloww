package com.tolgahantutar.bexworkfloww

import org.kodein.di.KodeinAware
import android.app.Application
import com.tolgahantutar.bexworkfloww.data.network.VisaServicesApi
import com.tolgahantutar.bexworkfloww.data.network.repositories.AuthorizeSessionRepository
import com.tolgahantutar.bexworkfloww.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.kodein.di.generic.instance

class MVVMApplication: Application() ,KodeinAware{

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { VisaServicesApi() }
        bind() from singleton { AuthorizeSessionRepository(instance()) }
        bind() from singleton { AuthViewModelFactory(instance()) }
    }
}