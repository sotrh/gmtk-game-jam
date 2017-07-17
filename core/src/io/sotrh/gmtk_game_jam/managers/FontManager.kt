package io.sotrh.gmtk_game_jam.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont

/**
 * Created by benjamin on 7/16/17
 */
object FontManager {
    private val fontMap = mutableMapOf<String, BitmapFont>()

    fun getFont(name: String): BitmapFont {
        return fontMap[name] ?: createFont(name)
    }

    private fun createFont(name: String): BitmapFont {
        val fileHandle = Gdx.files.internal("fonts/$name")
        if (fileHandle.exists()) return BitmapFont(fileHandle)
        else return BitmapFont()
    }

    fun disposeFont(name: String) {
        fontMap[name]?.dispose()
        fontMap.remove(name)
    }

    fun disposeAll() {
        fontMap.values.forEach(BitmapFont::dispose)
        fontMap.clear()
    }
}