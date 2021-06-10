package com.teamff.music.playmusicpro.interfaces

import android.view.View
import com.teamff.music.playmusicpro.db.PlaylistWithSongs

interface IPlaylistClickListener {
    fun onPlaylistClick(playlistWithSongs: PlaylistWithSongs, view: View)
}