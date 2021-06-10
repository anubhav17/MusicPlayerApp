package com.teamff.music.playmusicpro.interfaces

import com.teamff.music.playmusicpro.model.Album
import com.teamff.music.playmusicpro.model.Artist
import com.teamff.music.playmusicpro.model.Genre

interface IHomeClickListener {
    fun onAlbumClick(album: Album)

    fun onArtistClick(artist: Artist)

    fun onGenreClick(genre: Genre)
}