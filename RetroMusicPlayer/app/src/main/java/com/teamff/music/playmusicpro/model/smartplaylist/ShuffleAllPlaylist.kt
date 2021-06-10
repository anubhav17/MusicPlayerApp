package com.teamff.music.playmusicpro.model.smartplaylist

import com.teamff.music.playmusicpro.App
import com.teamff.music.playmusicpro.R
import com.teamff.music.playmusicpro.model.Song
import kotlinx.android.parcel.Parcelize

@Parcelize
class ShuffleAllPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.action_shuffle_all),
    iconRes = R.drawable.ic_shuffle
) {
    override fun songs(): List<Song> {
        return songRepository.songs()
    }
}