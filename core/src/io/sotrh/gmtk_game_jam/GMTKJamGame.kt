package io.sotrh.gmtk_game_jam

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.sotrh.gmtk_game_jam.screens.TestScreen

class GMTKJamGame : Game() {
    lateinit var batch: SpriteBatch private set
    lateinit var font: BitmapFont private set

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()
        setScreen(TestScreen(this))
    }

    override fun dispose() {
        batch.dispose()
    }
}
