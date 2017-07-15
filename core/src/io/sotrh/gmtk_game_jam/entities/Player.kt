package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import com.sun.org.apache.xpath.internal.operations.Bool
import io.sotrh.gmtk_game_jam.managers.EntityManager

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


    fun boost() {
        if (velocity < maxVelocity) {
            velocity += 20
        }
    }

    fun stop() {
        if (velocity > 0) {
            velocity -= 10
        }
    }

    override fun update(deltaTime: Float) {
        // Direction
        val direction = calcDirection()

        // Move
        if (this.calcMove()) {
            this.boost()
        } else {
            this.stop()
        }
        angle = direction.angleRad()
        moveDelta(deltaTime)

        // Shoot
        shootCounter += deltaTime
        if (this.calcShoot()) {
            EntityManager.spawnBullet(this)
        }

    }

    open protected fun calcDirection(): Vector2 {
        return TEMP_VEC.set(Gdx.input.x.toFloat(), Gdx.graphics.height - Gdx.input.y.toFloat()).sub(position).nor()
    }

    open protected fun calcShoot(): Boolean {
        return Gdx.input.isButtonPressed(Input.Buttons.LEFT) && this.canShoot
    }

    open protected fun calcMove(): Boolean {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE)
    }
}