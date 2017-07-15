package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.math.Vector2

/**
 * Created by ryanberger on 7/15/17.
 */


class Bullet : BaseEntity() {
    override val type: EntityType get() = EntityType.BULLET
    override var velocity: Float = 600f
    override val resourceString: String = "bullet.png"

    init {
        this.position = position.cpy()
        this.angle = angle
    }

    override fun update(deltaTime: Float) {
        moveDelta(deltaTime)
    }

}