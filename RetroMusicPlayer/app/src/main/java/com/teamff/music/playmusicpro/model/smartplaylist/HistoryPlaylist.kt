package com.teamff.music.playmusicpro.model.smartplaylist

import com.teamff.music.playmusicpro.App
import com.teamff.music.playmusicpro.R
import com.teamff.music.playmusicpro.model.Song
import kotlinx.android.parcel.Parcelize
import org.koin.core.KoinComponent

@Parcelize
class HistoryPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.history),
    iconRes = R.drawable.ic_history
), KoinComponent {

    override fun songs(): List<Song> {
        return topPlayedRepository.recentlyPlayedTracks()
    }
}