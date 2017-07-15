package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2

/**
 * Created by ryanberger on 7/14/17.
 */


abstract class BaseEntity {
    abstract val resourceString: String
    var position: Vector2 = Vector2()
    var health: Int = 100
    var angle: Float = 0f
    var velocity: Float = 10f

    fun moveDelta(deltaTime: Float) {
        val vx = MathUtils.cos(angle) * velocity * deltaTime
        val vy = MathUtils.sin(angle) * velocity * deltaTime
        position.add(vx, vy)
    }

    fun update(deltaTime: Float) {
        val direction = Vector2(Gdx.input.x.toFloat(), Gdx.graphics.height - Gdx.input.y.toFloat())
        direction
                .sub(position)
                .nor()
        angle = direction.angleRad()
        moveDelta(deltaTime)
    }


}