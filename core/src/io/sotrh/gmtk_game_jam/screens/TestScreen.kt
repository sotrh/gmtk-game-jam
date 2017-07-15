package io.sotrh.gmtk_game_jam.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import io.sotrh.gmtk_game_jam.GMTKJamGame
import io.sotrh.gmtk_game_jam.entities.Player
import org.w3c.dom.Text

/**
 * gmtk-game-jam
 * Date: 7/14/17
 */
class TestScreen(parent: GMTKJamGame) : BaseScreen(parent) {

    private lateinit var texture: Texture
    private lateinit var playerTexture: Texture
    private val player: Player = Player()

    override fun show() {
        parent.apply {
            texture = textureManager.getTexture("badlogic.jpg")
            playerTexture = textureManager.getTexture(player.resourceString)
        }
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        player.update(delta)
        parent.apply {
            batch.begin()
            batch.draw(texture, player.position.x, player.position.y)
            font.draw(batch, "FPS: ${Gdx.graphics.framesPerSecond}", 10f, 20f)
            batch.end()
        }
    }
}