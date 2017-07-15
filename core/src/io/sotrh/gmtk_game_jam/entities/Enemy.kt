package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import io.sotrh.gmtk_game_jam.managers.EntityManager

/**
 * gmtk-game-jam
 * Date: 7/15/17
 */
class Enemy : Player() {
    override val type: EntityType get() = EntityType.ENEMY
    override val resourceString: String get() = "enemy3.png"
    override var health: Int = 30
    override val maxHealth: Int = 30
    override var maxVelocity: Int = 100

    override fun calcDirection(): Vector2 {
        EntityManager.getPlayer()?.let {
            return TEMP_VEC.set(it.position).sub(position).nor()
        }
        return Vector2.Zero
    }

    override fun calcShoot(): Boolean {
        if(canShoot) {
            return MathUtils.floor(MathUtils.random() * 10) % 10 == 0
        } else {
            return false
        }
    }

    override fun calcMove(): Boolean {
        return true
    }


}