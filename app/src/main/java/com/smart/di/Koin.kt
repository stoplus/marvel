package com.smart.di

import com.google.gson.Gson
import com.smart.data.api.ApiInterface
import com.smart.utils.*
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val viewModelModule = module {
//    viewModel { EmptyViewModel() }
//    viewModel<LoginViewModel> { LoginViewModelImpl(get()) }
//    viewModel<MainViewModel> { MainViewModelImpl(get(), get()) }
//    viewModel<ProfileViewModel> { ProfileViewModelImpl(get()) }
//    viewModel<ProductListViewModel> { ProductListViewModelImpl(get()) }
//    viewModel<ProductDetailViewModel> { (args: FragmentDetailProductArgs) ->
//        ProductDetailViewModelImpl(args.product, get(), get())
//    }
}

private val networkModule = module {
    single(named(TEST_API)) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient.Builder>()
                .addInterceptor(
                    get<HttpLoggingInterceptor>()
//                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .addInterceptor { chain -> createParametersDefault(chain) }
                .build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    factory<HttpLoggingInterceptor> {
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
//            MarvelHashGenerate.generate(timeStamp, PRIVATE_KEY, PUBLIC_KEY)
            "$timeStamp${PRIVATE_KEY}${PUBLIC_KEY}".md5()
        )

        .addQueryParameter("ts", timeStamp.toString())

    request = request.newBuilder().url(builder.build()).build()
    return chain.proceed(request)

}

private val dataModule = module {
//    single { get<Context>().applicationContext.getSharedPreferences(PREF, Context.MODE_PRIVATE) }
//    single { PreferencesManager(get()) }
//    single { AuthManager() }
    single { OkHttpClient.Builder() }
}

private val apiModule = module {
    single { get<Retrofit>(named(TEST_API)).create(ApiInterface::class.java) }
}

private val useCaseModule = module {
//    factory { ProofUseCase(get()) }
//    factory<LoginUseCase> { LoginUseCaseImpl(get(), get(), get()) }
//    factory<ProfileUseCase> { ProfileUseCaseImpl(get()) }
//    factory<ProductUseCase> { ProductUseCaseImpl(get(), get()) }
}


val appModules = mutableListOf(viewModelModule, networkModule, dataModule, apiModule, useCaseModule)