package io.sotrh.gmtk_game_jam

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.sotrh.gmtk_game_jam.managers.EntityManager
import io.sotrh.gmtk_game_jam.managers.FontManager
import io.sotrh.gmtk_game_jam.managers.SoundManager
import io.sotrh.gmtk_game_jam.managers.TextureManager
import io.sotrh.gmtk_game_jam.screens.PlayScreen

class GMTKJamGame : Game() {
    lateinit var batch: SpriteBatch private set

    override fun create() {
        batch = SpriteBatch()
        setScreen(PlayScreen(this))
    }

    override fun dispose() {
        batch.dispose()
        TextureManager.disposeAll()
        SoundManager.disposeAll()
        FontManager.disposeAll()
        EntityManager.dispose()
    }
}
