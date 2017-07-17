package io.sotrh.gmtk_game_jam.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import io.sotrh.gmtk_game_jam.GMTKJamGame
import io.sotrh.gmtk_game_jam.managers.EntityManager
import io.sotrh.gmtk_game_jam.managers.SoundManager
import io.sotrh.gmtk_game_jam.managers.TextureManager

/**
 * gmtk-game-jam
 * Date: 7/14/17
 */
class TestScreen(parent: GMTKJamGame) : BaseScreen(parent) {

    lateinit var backgroundTexture: Texture

    override fun show() {
        EntityManager.createPlayer(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f)
        
        val music = SoundManager.getMusic("background.ogg")
        music.play()
        music.isLooping = true

        backgroundTexture = TextureManager.getTexture("background.png")
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat)
    }

    override fun render(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
            return
        }

        EntityManager.update(delta)

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (MathUtils.floor(MathUtils.random() * 100) % 100 == 0) {
            EntityManager.spawnEnemy(Gdx.graphics.width * MathUtils.random(), Gdx.graphics.height * MathUtils.random())
        }

        parent.apply {
            batch.begin()
            batch.draw(backgroundTexture, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            EntityManager.draw(batch)
            font.draw(batch, "FPS: ${Gdx.graphics.framesPerSecond}", 10f, 20f)
            batch.end()
        }
    }

    override fun dispose() {
        super.dispose()
    }
}