package com.example.myapplication.sandbox.patterns

class AudioPlayer {
    fun playAudio(file: String) = println("Playing audio $file")
}

class VideoPlayer {
    fun playVideo(file: String) = println("Playing video: $file")
}

class SubtitleService {
    fun loadSubtitles(file: String) = println("Loading subtitles: $file")
}

class MediaFacade {
    private val audio = AudioPlayer()
    private val video = VideoPlayer()
    private val subtitles = SubtitleService()

    fun playMovieWithSubtitles(videoFile: String, subtitleFile: String) {
        video.playVideo(videoFile)
        subtitles.loadSubtitles(subtitleFile)
    }

    fun playMusic(audioFile: String) {
        audio.playAudio(audioFile)
    }
}

fun main() {
    val mediaPlayer = MediaFacade()
    mediaPlayer.playMusic("song.mp3")
    mediaPlayer.playMovieWithSubtitles("movie.mp4", "movie.srt")
}