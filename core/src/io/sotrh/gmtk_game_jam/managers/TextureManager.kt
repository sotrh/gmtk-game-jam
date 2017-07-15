package io.sotrh.gmtk_game_jam.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

/**
 * gmtk-game-jam
 * Date: 7/14/17
 */
object TextureManager {
    private val textureMap = mutableMapOf<String, Texture>()

    fun getTexture(name: String): Texture {
        return textureMap[name] ?: Texture(Gdx.files.internal("textures/$name")).also { textureMap.put(name, it) }
    }

    fun getTextureRegion(name: String): TextureRegion {
        return TextureRegion(getTexture(name))
    }

    fun disposeTexture(name: String) {
        textureMap[name]?.dispose()
        textureMap.remove(name)
    }

    fun disposeAll() {
        textureMap.keys.forEach { disposeTexture(it) }
    }
}