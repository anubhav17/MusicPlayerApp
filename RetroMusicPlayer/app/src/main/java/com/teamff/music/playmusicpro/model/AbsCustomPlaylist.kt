package com.teamff.music.playmusicpro.model

import com.teamff.music.playmusicpro.repository.LastAddedRepository
import com.teamff.music.playmusicpro.repository.SongRepository
import com.teamff.music.playmusicpro.repository.TopPlayedRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class AbsCustomPlaylist(
    id: Long,
    name: String
) : Playlist(id, name), KoinComponent {

    abstract fun songs(): List<Song>

    protected val songRepository by inject<SongRepository>()

    protected val topPlayedRepository by inject<TopPlayedRepository>()

    protected val lastAddedRepository by inject<LastAddedRepository>()
}