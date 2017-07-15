package io.sotrh.gmtk_game_jam.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import io.sotrh.gmtk_game_jam.GMTKJamGame

/**
 * gmtk-game-jam
 * Date: 7/14/17
 */
class TestScreen(parent: GMTKJamGame) : BaseScreen(parent) {

    private lateinit var texture: Texture

    override fun show() {
        parent.apply {
            texture = textureManager.getTexture("badlogic.jpg")
        }
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        parent.apply {
            batch.begin()
            batch.draw(texture, Gdx.graphics.width / 2f - texture.width / 2f, Gdx.graphics.height / 2f - texture.height / 2f)
            font.draw(batch, "FPS: ${Gdx.graphics.framesPerSecond}", 10f, 20f)
            batch.end()
        }
    }
}