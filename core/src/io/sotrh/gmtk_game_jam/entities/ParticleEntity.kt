package io.sotrh.gmtk_game_jam.entities

/**
 * Created by benjamin on 7/16/17
 */
abstract class ParticleEntity : BaseEntity() {
    open val maxLife = 1.0f
    var life = 0.0f

    override fun update(deltaTime: Float) {
        moveDelta(deltaTime)
        life += deltaTime
        if (life > maxLife) energy = 0
    }
}