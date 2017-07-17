package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.math.MathUtils
import io.sotrh.gmtk_game_jam.managers.EntityManager
import io.sotrh.gmtk_game_jam.managers.SoundManager

/**
 * Created by benjamin on 7/16/17
 */
class Explosion : ParticleEntity() {
    override var resourceString: String = "explosion.png"
    override val type: EntityType get() = EntityType.PARTICLE

    override val maxLife: Float = 0.125f

    private var explosionStage = 0

    override fun update(deltaTime: Float) {
        if (life < 0.0000001f) SoundManager.playSound("explosion.ogg", randomize = true)
        else if (life > 0.1f && explosionStage < 1) {
            repeat(MathUtils.random(3, 10)) { EntityManager.spawnExplosionBit(this) }
            explosionStage = 1
        }
        super.update(deltaTime)
    }
}