package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2

/**
 * Created by ryanberger on 7/15/17.
 */


class Bullet(player: Player) : BaseEntity() {
    override var velocity: Float = 600f
    override val resourceString: String = "bullet.png"

    init {
        position = player.position.cpy()
        angle = player.angle
    }

    override fun update(deltaTime: Float) {
        moveDelta(deltaTime)
    }

}