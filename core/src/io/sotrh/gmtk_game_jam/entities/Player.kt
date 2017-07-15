package io.sotrh.gmtk_game_jam.entities

/**
 * Created by ryanberger on 7/14/17.
 */

class Player : BaseEntity() {
    override val resourceString: String = "player.png"


    fun boost() {
        if (velocity < 500) {
            velocity += 20
        }
    }

    fun stop() {
        if (velocity > 0) {
            velocity -= 10
        }
    }


}