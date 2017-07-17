package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.graphics.Color

/**
 * Created by benjamin on 7/16/17
 */
class ScoreText : TextEntity(Color.WHITE, "", "default") {
    var score: Int = 0

    override fun update(deltaTime: Float) {
        text = "Score: $score"
    }
}