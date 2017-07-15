package io.sotrh.gmtk_game_jam.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import io.sotrh.gmtk_game_jam.GMTKJamGame
import io.sotrh.gmtk_game_jam.managers.EntityManager
import io.sotrh.gmtk_game_jam.managers.SoundManager

/**
 * gmtk-game-jam
 * Date: 7/14/17
 */
class TestScreen(parent: GMTKJamGame) : BaseScreen(parent) {


    override fun show() {
        EntityManager.createPlayer()
        val music = SoundManager.getMusic("background.ogg")
        music.play()
        music.isLooping = true

        EntityManager.spawnEnemy(Gdx.graphics.width * 0.25f, Gdx.graphics.height * 0.25f)
        EntityManager.spawnEnemy(Gdx.graphics.width * 0.25f, Gdx.graphics.height * 0.75f)
        EntityManager.spawnEnemy(Gdx.graphics.width * 0.75f, Gdx.graphics.height * 0.25f)
        EntityManager.spawnEnemy(Gdx.graphics.width * 0.75f, Gdx.graphics.height * 0.75f)
    }

    override fun render(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
            return
        }

        EntityManager.update(delta)

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        parent.apply {
            batch.begin()
            EntityManager.draw(batch)
            font.draw(batch, "FPS: ${Gdx.graphics.framesPerSecond}", 10f, 20f)
            batch.end()
        }
    }

    override fun dispose() {
        super.dispose()
    }
}