package jt.projects.topstoriesapp.di


import jt.projects.topstoriesapp.App
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    // контекст приложения
    single<App> { androidApplication().applicationContext as App }
}

val repoModule = module {
    // interactors
 //   single<FilmsInteractor> { FilmsInteractor(repo = get<IFilmsRepo>()) }

    // data sources
 //   single<IFilmsRepo> { FilmsRemoteDataSource() }
}


val vmModule = module {
 //   viewModel { HomeViewModel(interactor = get()) }
  //  viewModel { DetailsViewModel(interactor = get()) }
}
