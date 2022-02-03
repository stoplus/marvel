package com.smart.di

import com.google.gson.Gson
import com.smart.data.api.ApiInterface
import com.smart.data.impl.MarvelRepositoryImpl
import com.smart.domain.api.GetCharacterDetailUseCase
import com.smart.domain.api.GetCharactersUseCase
import com.smart.domain.api.MarvelRepository
import com.smart.domain.impl.GetCharacterDetailUseCaseImpl
import com.smart.domain.impl.GetCharactersUseCaseImpl
import com.smart.presentation.api.CharacterViewModel
import com.smart.presentation.api.DetailsViewModel
import com.smart.presentation.api.Router
import com.smart.presentation.impl.RouterImpl
import com.smart.presentation.impl.charactersScreen.CharactersViewModelImpl
import com.smart.presentation.impl.detailsScreen.DetailsFragmentArgs
import com.smart.presentation.impl.detailsScreen.DetailsViewModelImpl
import com.smart.utils.*
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private val viewModelModule = module {
    viewModel<DetailsViewModel> { (args: DetailsFragmentArgs) ->
        DetailsViewModelImpl(get(), get(), args.characterId)
    }
    viewModel<CharacterViewModel> { CharactersViewModelImpl(get(), get()) }
}

private val networkModule = module {
    single(named(TEST_API)) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient.Builder>()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .addInterceptor { chain -> createParametersDefault(chain) }
                .build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    factory {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}

private fun createParametersDefault(chain: Interceptor.Chain): Response {
    val timeStamp = System.currentTimeMillis()
    var request = chain.request()
    val builder = request.url.newBuilder()

    builder.addQueryParameter("apikey", PUBLIC_KEY)
        .addQueryParameter(
            "hash",
            "$timeStamp$PRIVATE_KEY$PUBLIC_KEY".md5()
        )
        .addQueryParameter("ts", timeStamp.toString())

    request = request.newBuilder().url(builder.build()).build()
    return chain.proceed(request)

}

private val dataModule = module {
    single { OkHttpClient.Builder() }
    single<MarvelRepository> { MarvelRepositoryImpl(get()) }
}

private val apiModule = module {
    single { get<Retrofit>(named(TEST_API)).create(ApiInterface::class.java) }
}

private val useCaseModule = module {
    factory<GetCharacterDetailUseCase> { GetCharacterDetailUseCaseImpl(get()) }
    factory<GetCharactersUseCase> { GetCharactersUseCaseImpl(get()) }
}

private val presentModule = module { single<Router> { RouterImpl() } }

val appModules = mutableListOf(viewModelModule,
    networkModule,
    dataModule,
    apiModule,
    useCaseModule,
    presentModule)