package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2

/**
 * Created by ryanberger on 7/14/17.
 */


abstract class BaseEntity {
    abstract val resourceString: String
    var textureRegion: TextureRegion? = null
    var position: Vector2 = Vector2()
    var health: Int = 100
    var angle: Float = 0f
    var velocity: Float = 0f


    fun moveDelta(deltaTime: Float) {
        val vx = MathUtils.cos(angle) * velocity * deltaTime
        val vy = MathUtils.sin(angle) * velocity * deltaTime
        val target = Vector2(position).add(vx, vy)
        position.interpolate(target, 1f, Interpolation.elastic)
    }

    fun update(deltaTime: Float) {
        val direction = Vector2(Gdx.input.x.toFloat(), Gdx.graphics.height - Gdx.input.y.toFloat())

        direction
                .sub(position)
                .nor()

        angle = direction.angleRad()
        moveDelta(deltaTime)
    }

    fun draw(batch: SpriteBatch) {
        textureRegion?.let {
            batch.draw(it,
                    position.x - it.texture.width / 2f,
                    position.y - it.texture.height / 2f,
                    it.texture.width / 2f,
                    it.texture.height / 2f,
                    it.texture.width.toFloat(),
                    it.texture.height.toFloat(), 1.0f, 1.0f,
                    angle * MathUtils.radiansToDegrees - 90)
        }
    }
}