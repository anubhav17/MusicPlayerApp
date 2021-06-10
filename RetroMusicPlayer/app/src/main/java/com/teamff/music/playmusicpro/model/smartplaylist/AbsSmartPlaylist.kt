package com.teamff.music.playmusicpro.model.smartplaylist

import androidx.annotation.DrawableRes
import com.teamff.music.playmusicpro.R
import com.teamff.music.playmusicpro.model.AbsCustomPlaylist

abstract class AbsSmartPlaylist(
    name: String,
    @DrawableRes val iconRes: Int = R.drawable.ic_queue_music
) : AbsCustomPlaylist(
    id = PlaylistIdGenerator(name, iconRes),
    name = name
)