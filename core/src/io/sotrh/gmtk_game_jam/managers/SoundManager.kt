package io.sotrh.gmtk_game_jam.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.math.MathUtils

/**
 * Created by ryanberger on 7/15/17.
 */


object SoundManager {
    private val musicMap: MutableMap<String, Music> = mutableMapOf()
    private val soundMap: MutableMap<String, Sound> = mutableMapOf()

    fun getSound(filepath: String): Sound {
        return soundMap[filepath] ?: Gdx.audio.newSound(Gdx.files.internal("sounds/$filepath")).also { soundMap.put(filepath, it) }
    }

    fun getMusic(filepath: String): Music {
        return musicMap[filepath] ?: Gdx.audio.newMusic(Gdx.files.internal("music/$filepath")).also { musicMap.put(filepath, it) }
    }

    fun disposeMusic(filepath: String) {
        musicMap[filepath]?.dispose()
        musicMap.remove(filepath)

    }

    fun disposeSound(filepath: String) {
        soundMap[filepath]?.dispose()
        soundMap.remove(filepath)
    }

    fun disposeAllSound() {
        soundMap.values.forEach(Sound::dispose)
        soundMap.clear()
    }

    fun disposeAllMusic() {
        musicMap.values.forEach(Music::dispose)
    }

    fun disposeAll() {
        disposeAllMusic()
        disposeAllSound()
    }

    fun playSound(soundName: String, randomize: Boolean = false) {
        if (randomize) {
            getSound(soundName).play(1.0f, when (MathUtils.random(0, 2)) {
                2 -> 3.0f
                1 -> 2.0f
                else -> 1.0f
            }, 0.0f)
        } else {
            getSound(soundName).play()
        }
    }
}