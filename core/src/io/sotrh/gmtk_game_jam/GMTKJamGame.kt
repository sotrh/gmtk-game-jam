package io.sotrh.gmtk_game_jam

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Graphics
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import io.sotrh.gmtk_game_jam.managers.EntityManager
import io.sotrh.gmtk_game_jam.managers.FontManager
import io.sotrh.gmtk_game_jam.managers.SoundManager
import io.sotrh.gmtk_game_jam.managers.TextureManager
import io.sotrh.gmtk_game_jam.screens.TestScreen

class GMTKJamGame : Game() {
    lateinit var batch: SpriteBatch private set

    override fun create() {
        batch = SpriteBatch()
        setScreen(TestScreen(this))
    }

    override fun dispose() {
        batch.dispose()
        TextureManager.disposeAll()
        SoundManager.disposeAll()
        FontManager.disposeAll()
        EntityManager.dispose()
    }
}
