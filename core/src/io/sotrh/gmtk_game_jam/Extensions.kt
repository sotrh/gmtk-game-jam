package io.sotrh.gmtk_game_jam

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable

/**
 * Created by benjamin on 7/16/17
 */

fun <T> gdxArrayOf(vararg items: T): Array<T> {
    val array = Array<T>()
    items.forEach(array::add)
    return array
}

fun <T: Disposable> T.use(block: (T)->Unit) {
    block(this)
    this.dispose()
}

fun GlyphLayout.centerAlign(font: BitmapFont, text: String) {
    this.setText(font, text, 0, text.length, Color.WHITE, Gdx.graphics.width.toFloat(), Align.center, true, null)
}