package com.tolgahantutar.bexworkfloww

import org.kodein.di.KodeinAware
import android.app.Application
import com.tolgahantutar.bexworkfloww.data.network.apis.VisaServicesApi
import com.tolgahantutar.bexworkfloww.data.network.apis.WorkFlowServicesApi
import com.tolgahantutar.bexworkfloww.data.network.repositories.AuthorizeSessionRepository
import com.tolgahantutar.bexworkfloww.data.network.repositories.GetDomainRepository
import org.kodein.di.Kodein
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.kodein.di.generic.instance

class MVVMApplication: Application() ,KodeinAware{

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
        //APIS
        bind() from singleton { WorkFlowServicesApi() }
        bind() from singleton { VisaServicesApi() }
        //Repositories
        bind() from singleton { GetDomainRepository(instance()) }
        bind() from singleton { AuthorizeSessionRepository(instance()) }
        //ViewModelFactories
        //bind() from singleton { AuthViewModelFactory(instance()) }
        //bind() from singleton { GetDomainViewModelFactory(instance()) }
    }
}