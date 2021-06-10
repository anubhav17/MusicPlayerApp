package com.teamff.music.playmusicpro.interfaces

import android.view.View
import com.teamff.music.playmusicpro.model.Genre

interface IGenreClickListener {
    fun onClickGenre(genre: Genre, view: View)
}