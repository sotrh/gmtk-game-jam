package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

/**
 * Created by ryanberger on 7/14/17.
 */


abstract class BaseEntity {

    companion object {
        val TEMP_VEC = Vector2()
        val TEMP_RECT = Rectangle()
        val TEMP_RECT_OTHER = Rectangle()
        val SHOOT_COOLDOWN = .1f
        val MAX_VELOCITY = 500
    }


    abstract var resourceString: String
    abstract val type: EntityType
    var id: Int = -1
    var textureRegion: TextureRegion? = null
    open var position: Vector2 = Vector2()
    open var health: Int = 100
    open val maxHealth: Int = 100
    open var angle: Float = 0f
    open var velocity: Vector2 = Vector2()
    open var maxVelocity: Int = MAX_VELOCITY


    abstract fun update(deltaTime: Float)

    open fun moveDelta(deltaTime: Float) {
        position.add(velocity.cpy().scl(deltaTime))
    }

    open fun draw(batch: SpriteBatch) {
        textureRegion?.let {
            batch.draw(it,
                    position.x - it.regionWidth / 2f,
                    position.y - it.regionHeight / 2f,
                    it.regionWidth / 2f,
                    it.regionHeight / 2f,
                    it.regionWidth.toFloat(),
                    it.regionHeight.toFloat(), 1.0f, 1.0f,
                    angle * MathUtils.radiansToDegrees - 90)
        }
    }

    fun isColliding(other: BaseEntity): Boolean {
        val thisTextureRegion = textureRegion
        val entityTextureRegion = other.textureRegion
        if (entityTextureRegion != null && thisTextureRegion != null) {
            val minX = position.x - thisTextureRegion.regionWidth / 2f
            val maxX = position.x + thisTextureRegion.regionWidth / 2f
            val minY = position.y - thisTextureRegion.regionHeight / 2f
            val maxY = position.y + thisTextureRegion.regionHeight / 2f
            val otherMinX = other.position.x - entityTextureRegion.regionWidth / 2f
            val otherMaxX = other.position.x + entityTextureRegion.regionWidth / 2f
            val otherMinY = other.position.y - entityTextureRegion.regionHeight / 2f
            val otherMaxY = other.position.y + entityTextureRegion.regionHeight / 2f
            return otherMinX >= minX && otherMaxX <= maxX && otherMinY >= minY && otherMaxY <= maxY
        }
        return false
    }
}

