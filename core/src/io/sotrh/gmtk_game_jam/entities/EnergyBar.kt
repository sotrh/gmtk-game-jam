package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.sotrh.gmtk_game_jam.managers.TextureManager

/**
 * gmtk-game-jam
 * Date: 7/15/17
 */
class EnergyBar(val parentEntity: BaseEntity) : BaseEntity() {
    override val resourceString: String = "energy-bar-color.png"
    private val frameResourceString: String = "energy-bar-frame.png"
    private var barScale: Float = 1f

    override val type: EntityType
        get() = EntityType.HUD

    override fun update(deltaTime: Float) {
        health = parentEntity.health
        barScale = health.toFloat() / parentEntity.maxHealth
        position.set(parentEntity.position)
    }

    override fun draw(batch: SpriteBatch) {
        super.draw(batch)
        val energyBarColorTexture = TextureManager.getTexture(resourceString)
        batch.draw(energyBarColorTexture, position.x, position.y, energyBarColorTexture.width * barScale, energyBarColorTexture.height.toFloat())
        batch.draw(TextureManager.getTexture(frameResourceString), position.x, position.y)
    }
}