package jt.projects.topstoriesapp.di


import jt.projects.topstoriesapp.App
import jt.projects.topstoriesapp.interactors.StoryInteractor
import jt.projects.topstoriesapp.repository.IStoryRepo
import jt.projects.topstoriesapp.repository.RemoteStoryDataSource
import jt.projects.topstoriesapp.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // контекст приложения
    single<App> { androidApplication().applicationContext as App }
}

val repoModule = module {
    // interactors
    single<StoryInteractor> { StoryInteractor(repo = get<IStoryRepo>()) }

    // data sources (RemoteStoryDataSource or FakeStoryDataSource)
    single<IStoryRepo> { RemoteStoryDataSource() }
}


val vmModule = module {
    viewModel { HomeViewModel(interactor = get()) }
}
