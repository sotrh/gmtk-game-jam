package io.sotrh.gmtk_game_jam.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.math.MathUtils
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog
import io.sotrh.gmtk_game_jam.GMTKJamGame
import io.sotrh.gmtk_game_jam.centerAlign
import io.sotrh.gmtk_game_jam.managers.EntityManager
import io.sotrh.gmtk_game_jam.managers.FontManager
import io.sotrh.gmtk_game_jam.managers.SoundManager
import io.sotrh.gmtk_game_jam.managers.TextureManager
import io.sotrh.gmtk_game_jam.use

/**
 * gmtk-game-jam
 * Date: 7/14/17
 */
class PlayScreen(parent: GMTKJamGame) : BaseScreen(parent) {

    lateinit var backgroundTexture: Texture
    lateinit var font: BitmapFont
    lateinit var promptOverlay: Texture
    var glyphLayoutJustStarted: GlyphLayout = GlyphLayout()
    var glyphLayoutPlayerDied: GlyphLayout = GlyphLayout()

    private var promptText = ""
    private var gameState = -1
    companion object {
        val STATE_JUST_STARTING = 0
        val STATE_RUNNING = 1
        val STATE_PLAYER_DIED = 2

        val PROMPT_JUST_STARTING = "Space to boost\nClick to shoot\nPress any key to start"
        val PROMPT_PLAYER_DIED = "You died!\nPress any key to play again!\nPress escape to quit"
    }

    override fun show() {
        gameState = STATE_JUST_STARTING

        font = FontManager.getFont("default")
        Pixmap(1, 1, Pixmap.Format.RGBA4444).use {
            it.setColor(Color(0f, 0f, 0f, 0.75f))
            it.fill()
            promptOverlay = Texture(it)
        }

        glyphLayoutJustStarted.centerAlign(font, PROMPT_JUST_STARTING)
        glyphLayoutPlayerDied.centerAlign(font, PROMPT_PLAYER_DIED)

        val music = SoundManager.getMusic("background.ogg")
        music.play()
        music.isLooping = true

        backgroundTexture = TextureManager.getTexture("background.png")
    }

    override fun render(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
            return
        }

        if (EntityManager.isInited()) {
            if (EntityManager.isPlayerAlive()) {
                gameState = STATE_RUNNING

            } else {
                gameState = STATE_PLAYER_DIED
            }
        } else {
            gameState = STATE_JUST_STARTING
        }

        EntityManager.update(delta)

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (gameState == STATE_RUNNING && MathUtils.floor(MathUtils.random() * 100) % 100 == 0) {
            EntityManager.spawnEnemy(Gdx.graphics.width * MathUtils.random(), Gdx.graphics.height * MathUtils.random())
        }

        parent.apply {
            batch.begin()
            batch.draw(backgroundTexture, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            EntityManager.draw(batch)
            if (gameState == STATE_PLAYER_DIED || gameState == STATE_JUST_STARTING) {
                batch.draw(promptOverlay, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
                if (gameState == STATE_JUST_STARTING)
                    font.draw(batch, glyphLayoutJustStarted, 0f, Gdx.graphics.height / 2f)
                else if (gameState == STATE_PLAYER_DIED)
                    font.draw(batch, glyphLayoutPlayerDied, 0f, Gdx.graphics.height / 2f)
            }
            batch.end()
        }

        if ((gameState == STATE_JUST_STARTING || gameState == STATE_PLAYER_DIED) && Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            EntityManager.dispose()
            EntityManager.createDefaultEntities()
        }
    }

    override fun dispose() {
        super.dispose()
        promptOverlay.dispose()
    }
}