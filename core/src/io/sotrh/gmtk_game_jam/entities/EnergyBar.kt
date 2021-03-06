package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * gmtk-game-jam
 * Date: 7/15/17
 */
class EnergyBar(val parentEntity: BaseEntity) : BaseEntity() {
    override var resourceString: String = "energy-bar-color.png"
    private val frameResourceString: String = "energy-bar-frame.png"

    override val type: EntityType
        get() = EntityType.HUD

    override fun update(deltaTime: Float) {
        energy = parentEntity.energy
        position.set(parentEntity.position)
    }

    override fun draw(batch: SpriteBatch) {
        val thisTextureRegion = textureRegion
        val parentTextureRegion = parentEntity.textureRegion
        if (thisTextureRegion != null && parentTextureRegion != null) {
            batch.draw(thisTextureRegion, position.x - parentTextureRegion.regionWidth / 2f, position.y + 10f, parentTextureRegion.regionWidth.toFloat() * energy.toFloat() / parentEntity.maxEnergy, 5f)
        }
    }
}