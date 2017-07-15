package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2

/**
 * Created by ryanberger on 7/14/17.
 */


abstract class BaseEntity {
    abstract val resourceString: String
    var textureRegion: TextureRegion? = null
    open var position: Vector2 = Vector2()
    open var health: Int = 100
    open var angle: Float = 0f
    open var velocity: Float = 0f

    abstract fun update(deltaTime: Float)

    fun moveDelta(deltaTime: Float) {
        val vx = MathUtils.cos(angle) * velocity * deltaTime
        val vy = MathUtils.sin(angle) * velocity * deltaTime
        position.add(vx, vy)
    }

    open fun draw(batch: SpriteBatch) {
        textureRegion?.let {
            batch.draw(it, position.x, position.y, it.texture.width / 2f, it.texture.height / 2f, it.texture.width.toFloat(), it.texture.height.toFloat(), 1.0f, 1.0f,
                    angle * MathUtils.radiansToDegrees - 90)
        }
    }
}