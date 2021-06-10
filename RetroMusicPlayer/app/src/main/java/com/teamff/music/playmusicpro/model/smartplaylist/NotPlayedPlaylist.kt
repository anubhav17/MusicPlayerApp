package com.teamff.music.playmusicpro.model.smartplaylist

import com.teamff.music.playmusicpro.App
import com.teamff.music.playmusicpro.R
import com.teamff.music.playmusicpro.model.Song
import kotlinx.android.parcel.Parcelize

@Parcelize
class NotPlayedPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.not_recently_played),
    iconRes = R.drawable.ic_watch_later
) {
    override fun songs(): List<Song> {
        return topPlayedRepository.notRecentlyPlayedTracks()
    }
}