package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import io.sotrh.gmtk_game_jam.managers.EntityManager
import io.sotrh.gmtk_game_jam.managers.SoundManager

/**
 * Created by ryanberger on 7/14/17.
 */

open class Player : BaseEntity() {
    override var resourceString: String = "player.png"
    override val type: EntityType get() = EntityType.PLAYER

    protected var boostSound = SoundManager.getSound("engine.ogg")
    protected var soundId = -1L

    private var regenCounter: Float = 0f
    private var shootCounter: Float = 0f
    var canShoot: Boolean = false
        get() {
            if (shootCounter > SHOOT_COOLDOWN && energy > ENERGY_DRAINED_PER_SHOT) {
                shootCounter = 0f
                energy -= ENERGY_DRAINED_PER_SHOT
                return true
            }
            return false
        }

    open fun adjustBoostVolume(volume: Float) {
        if (soundId == -1L) soundId = boostSound.loop(volume)
        else boostSound.setVolume(soundId, volume)
    }

    fun boost() {
        val direction = calcDirection().nor().scl(20f)
        velocity.add(direction)
        val mag = velocity.len()
        if (mag > maxVelocity) {
            velocity.scl(maxVelocity/mag)
            adjustBoostVolume(1.0f)
        } else {
            adjustBoostVolume(mag/maxVelocity)
        }
    }

    fun stop() {
        velocity.scl(0.9f)
    }

    override fun update(deltaTime: Float) {
        val direction = calcDirection()

        if (this.calcMove()) {
            this.boost()
        } else {
            this.stop()
        }
        angle = direction.angleRad()
        moveDelta(deltaTime)

        shootCounter += deltaTime
        if (this.calcShoot()) {
            shoot()
        }

        regenCounter += deltaTime
        if (regenCounter > ENERGY_REGEN_COOLDOWN) {
            energy += ENERGY_REGEN_AMOUNT
            regenCounter = 0f
            if (energy > maxEnergy) energy = maxEnergy
        }
    }

    private fun shoot() {
        EntityManager.spawnBullet(this)
        SoundManager.playSound("laser.wav", true)
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