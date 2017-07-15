package io.sotrh.gmtk_game_jam.screens

import com.badlogic.gdx.Screen
import io.sotrh.gmtk_game_jam.GMTKJamGame

/**
 * gmtk-game-jam
 * Date: 7/14/17
 */
abstract class BaseScreen(val parent: GMTKJamGame) : Screen {

    override fun hide() {
    }

    override fun show() {
    }

    override fun render(delta: Float) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
    }
}