package io.sotrh.gmtk_game_jam.managers

import com.badlogic.gdx.utils.Array
import io.sotrh.gmtk_game_jam.entities.BaseEntity
import io.sotrh.gmtk_game_jam.entities.EntityType
import io.sotrh.gmtk_game_jam.entities.Player

/**
 * gmtk-game-jam
 * Date: 7/15/17
 */
object EntityManager {
    private var currentId = 0
    private var playerId = -1
    private val idEntityMap = mutableMapOf<Int, BaseEntity>()
    private val deadEntityMap = mutableMapOf<EntityType, Array<BaseEntity>>()
    private val typeEntityMap = mutableMapOf<EntityType, Array<BaseEntity>>()

    private fun addEntity(entity: BaseEntity) {
        typeEntityMap[entity.type]?.add(entity) ?: typeEntityMap.put(entity.type, Array<BaseEntity>().also { it.add(entity) })
        idEntityMap.put(entity.id, entity)
    }

    fun createPlayer(): Int {
        val player = Player()
        player.id = currentId
        addEntity(player)
        return player.id
    }

    fun getEntity(entityId: Int): BaseEntity? {
        return idEntityMap[entityId]
    }

    fun getPlayer(): Player? {
        if (playerId == -1) return null
        return idEntityMap[playerId] as? Player ?: return null
    }

    fun removeEntity(entityId: Int) {
        if (entityId == -1) return
        val entity = idEntityMap[entityId] ?: return
        idEntityMap.remove(entity.id)
        typeEntityMap.remove(entity.type)
        deadEntityMap[entity.type]?.add(entity) ?: deadEntityMap.put(entity.type, Array<BaseEntity>().also { it.add(entity) })
    }
}