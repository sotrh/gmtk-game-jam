package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.math.MathUtils
import io.sotrh.gmtk_game_jam.managers.TextureManager

/**
 * Created by benjamin on 7/16/17
 */

class ExplosionBit : ParticleEntity() {
    override var resourceString: String = "explosion2.png"
    override val type: EntityType = EntityType.PARTICLE

    override val maxLife: Float = 0.25f

    init {
        val randomTheta = MathUtils.random() * MathUtils.PI2
        velocity.set(TEMP_VEC.set(MathUtils.cos(randomTheta), MathUtils.sin(randomTheta)).scl(500f))
        textureRegion = TextureManager.getTextureRegion(resourceString)
        angle = MathUtils.random() * MathUtils.PI2
    }
}