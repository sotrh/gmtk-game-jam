package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2

/**
 * Created by ryanberger on 7/15/17.
 */


class Bullet : BaseEntity() {
    var ownerType: EntityType = EntityType.NONE
    var damage: Int = 10
    override val type: EntityType get() = EntityType.BULLET
    override val resourceString: String = "bullet.png"

    init {
        this.position = position.cpy()
        this.angle = angle
        this.velocity = Vector2(MathUtils.cos(angle), MathUtils.sin(angle)).scl(600f)
    }

    override fun update(deltaTime: Float) {
        moveDelta(deltaTime)
    }
}