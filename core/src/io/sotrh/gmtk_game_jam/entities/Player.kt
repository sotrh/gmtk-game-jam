package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2

/**
 * Created by ryanberger on 7/14/17.
 */

open class Player : BaseEntity() {
    override val resourceString: String = "player.png"
    override val type: EntityType get() = EntityType.PLAYER

    private var shootCounter: Float = 0f
    var canShoot: Boolean = false
        get() {
            if (shootCounter > SHOOT_COOLDOWN) {
                shootCounter = 0f
                return true
            }
            return false
        }

    companion object {
        val SHOOT_COOLDOWN = .1f
        val MAX_VELOCITY = 500
    }

    fun boost() {
        if (velocity < MAX_VELOCITY) {
            velocity += 20
        }
    }

    fun stop() {
        if (velocity > 0) {
            velocity -= 10
        }
    }

    override fun update(deltaTime: Float) {
        shootCounter += deltaTime
        val direction = calcDirection()
        angle = direction.angleRad()
        moveDelta(deltaTime)
    }

    open protected fun calcDirection(): Vector2 {
        return TEMP_VEC.set(Gdx.input.x.toFloat(), Gdx.graphics.height - Gdx.input.y.toFloat()).sub(position).nor()
    }
}