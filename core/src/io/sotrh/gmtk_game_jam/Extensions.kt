package io.sotrh.gmtk_game_jam

import com.badlogic.gdx.utils.Array

/**
 * Created by benjamin on 7/16/17
 */

fun <T> gdxArrayOf(vararg items: T): Array<T> {
    val array = Array<T>()
    items.forEach(array::add)
    return array
}