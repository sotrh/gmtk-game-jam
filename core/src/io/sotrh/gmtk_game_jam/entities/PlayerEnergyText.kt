package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.graphics.Color

/**
 * Created by benjamin on 7/16/17
 */
class PlayerEnergyText(val player: Player) : TextEntity(Color.WHITE, "", "default") {

    override fun update(deltaTime: Float) {
        text = "Energy: ${player.energy} / ${player.maxEnergy}"
    }
}