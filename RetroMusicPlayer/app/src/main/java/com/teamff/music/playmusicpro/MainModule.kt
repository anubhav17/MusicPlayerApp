package com.teamff.music.playmusicpro

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.teamff.music.playmusicpro.db.BlackListStoreDao
import com.teamff.music.playmusicpro.db.BlackListStoreEntity
import com.teamff.music.playmusicpro.db.PlaylistWithSongs
import com.teamff.music.playmusicpro.db.RetroDatabase
import com.teamff.music.playmusicpro.fragments.LibraryViewModel
import com.teamff.music.playmusicpro.fragments.albums.AlbumDetailsViewModel
import com.teamff.music.playmusicpro.fragments.artists.ArtistDetailsViewModel
import com.teamff.music.playmusicpro.fragments.genres.GenreDetailsViewModel
import com.teamff.music.playmusicpro.fragments.playlists.PlaylistDetailsViewModel
import com.teamff.music.playmusicpro.model.Genre
import com.teamff.music.playmusicpro.network.provideDefaultCache
import com.teamff.music.playmusicpro.network.provideLastFmRest
import com.teamff.music.playmusicpro.network.provideLastFmRetrofit
import com.teamff.music.playmusicpro.network.provideOkHttp
import com.teamff.music.playmusicpro.repository.*
import com.teamff.music.playmusicpro.util.FilePathUtil
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {

    factory {
        provideDefaultCache()
    }
    factory {
        provideOkHttp(get(), get())
    }
    single {
        provideLastFmRetrofit(get())
    }
    single {
        provideLastFmRest(get())
    }
}

private val roomModule = module {

    single {
        Room.databaseBuilder(androidContext(), RetroDatabase::class.java, "playlist.db")
            .allowMainThreadQueries()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    GlobalScope.launch(IO) {
                        FilePathUtil.blacklistFilePaths().map {
                            get<BlackListStoreDao>().insertBlacklistPath(BlackListStoreEntity(it))
                        }
                    }
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    }
    factory {
        get<RetroDatabase>().lyricsDao()
    }

    factory {
        get<RetroDatabase>().playlistDao()
    }

    factory {
        get<RetroDatabase>().blackListStore()
    }

    factory {
        get<RetroDatabase>().playCountDao()
    }

    factory {
        get<RetroDatabase>().historyDao()
    }

    single {
        RealRoomRepository(get(), get(), get(), get(), get())
    } bind RoomRepository::class
}
private val mainModule = module {
    single {
        androidContext().contentResolver
    }
}
private val dataModule = module {
    single {
        RealRepository(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    } bind Repository::class

    single {
        RealSongRepository(get())
    } bind SongRepository::class

    single {
        RealGenreRepository(get(), get())
    } bind GenreRepository::class

    single {
        RealAlbumRepository(get())
    } bind AlbumRepository::class

    single {
        RealArtistRepository(get(), get())
    } bind ArtistRepository::class

    single {
        RealPlaylistRepository(get())
    } bind PlaylistRepository::class

    single {
        RealTopPlayedRepository(get(), get(), get(), get())
    } bind TopPlayedRepository::class

    single {
        RealLastAddedRepository(
            get(),
            get(),
            get()
        )
    } bind LastAddedRepository::class

    single {
        RealSearchRepository(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    single {
        RealLocalDataRepository(get())
    } bind LocalDataRepository::class
}

private val viewModules = module {

    viewModel {
        LibraryViewModel(get())
    }

    viewModel { (albumId: Long) ->
        AlbumDetailsViewModel(
            get(),
            albumId
        )
    }

    viewModel { (artistId: Long) ->
        ArtistDetailsViewModel(
            get(),
            artistId
        )
    }

    viewModel { (playlist: PlaylistWithSongs) ->
        PlaylistDetailsViewModel(
            get(),
            playlist
        )
    }

    viewModel { (genre: Genre) ->
        GenreDetailsViewModel(
            get(),
            genre
        )
    }
}

val appModules = listOf(mainModule, dataModule, viewModules, networkModule, roomModule)