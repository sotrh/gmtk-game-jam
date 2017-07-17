package io.sotrh.gmtk_game_jam.entities

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.sotrh.gmtk_game_jam.managers.FontManager

/**
 * Created by benjamin on 7/16/17
 */
class TextEntity(color: Color, text: String, fontResource: String) : BaseEntity() {
    override var resourceString: String = ""
    override val type: EntityType get() = EntityType.HUD
    val font: BitmapFont = FontManager.getFont(fontResource)

    override fun update(deltaTime: Float) {

    }

    override fun draw(batch: SpriteBatch) {

    }
}