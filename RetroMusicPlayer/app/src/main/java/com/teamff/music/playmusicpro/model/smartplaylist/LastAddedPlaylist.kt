package com.teamff.music.playmusicpro.model.smartplaylist

import com.teamff.music.playmusicpro.App
import com.teamff.music.playmusicpro.R
import com.teamff.music.playmusicpro.model.Song
import kotlinx.android.parcel.Parcelize

@Parcelize
class LastAddedPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.last_added),
    iconRes = R.drawable.ic_library_add
) {
    override fun songs(): List<Song> {
        return lastAddedRepository.recentSongs()
    }
}