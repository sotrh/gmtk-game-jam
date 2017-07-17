package io.sotrh.gmtk_game_jam.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Array
import io.sotrh.gmtk_game_jam.entities.*
import io.sotrh.gmtk_game_jam.gdxArrayOf

/**
 * gmtk-game-jam
 * Date: 7/15/17
 */
object EntityManager {
    private var currentId = 0
    private var playerId = -1
    private var scoreId = -1
    private val entitiesToRemove = Array<BaseEntity>()
    private val entityQueue = Array<BaseEntity>()
    private val idEntityMap = mutableMapOf<Int, BaseEntity>()
    private val typeEntityMap = mutableMapOf<EntityType, Array<BaseEntity>>()

    private fun addEntity(entity: BaseEntity) {
        typeEntityMap[entity.type]?.add(entity) ?: typeEntityMap.put(entity.type, gdxArrayOf(entity))
        idEntityMap.put(entity.id, entity)
    }

    fun createDefaultEntities() {
        EntityManager.createPlayer(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f)
        EntityManager.createScoreText(10f, Gdx.graphics.height - 10f)
        EntityManager.createPlayerEnergyText(getPlayer()!!, 10f, Gdx.graphics.height - 30f)
    }

    private fun createPlayerEnergyText(player: Player, x: Float, y: Float): Int {
        return PlayerEnergyText(player).also {
            it.id = currentId++
            it.position.set(x, y)
            entityQueue.add(it)
        }.id
    }

    fun createPlayer(x: Float, y: Float): Int {
        val player = Player()
        player.id = currentId++
        player.position.set(x, y)
        player.textureRegion = TextureManager.getTextureRegion(player.resourceString)
        playerId = player.id
        addEntity(player)
        val energyBar = EnergyBar(player)
        energyBar.textureRegion = TextureManager.getTextureRegion(energyBar.resourceString)
        entityQueue.add(energyBar)
        return player.id
    }

    fun getEntity(entityId: Int): BaseEntity? {
        return idEntityMap[entityId]
    }

    fun getPlayer(): Player? {
        if (playerId == -1) return null
        return idEntityMap[playerId] as? Player ?: return null
    }

    fun createScoreText(x: Float, y: Float): Int {
        val score = ScoreText()
        score.id = currentId++
        score.position.set(x, y)
        scoreId = score.id
        entityQueue.add(score)
        return score.id
    }

    fun incrementScore(amount: Int) {
        (idEntityMap[scoreId] as? ScoreText)?.let { it.score += amount }
    }

    fun spawnBullet(ownerId: Int): Int {
        getEntity(ownerId)?.let { parentEntity ->
            return spawnBullet(parentEntity)
        }
        return -1
    }

    fun spawnBullet(parentEntity: BaseEntity): Int {
        val bullet = Bullet()
        bullet.id = currentId++
        bullet.ownerType = parentEntity.type
        if (bullet.ownerType == EntityType.ENEMY) {
            bullet.resourceString = "bullet.png"
        }
        bullet.position.set(parentEntity.position)
        bullet.angle = parentEntity.angle
        bullet.textureRegion = TextureManager.getTextureRegion(bullet.resourceString)

        entityQueue.add(bullet)
        return bullet.id
    }

    fun spawnEnemy(x: Float, y: Float): Int {
        val enemy = Enemy()
        enemy.textureRegion = TextureManager.getTextureRegion(enemy.resourceString)
        enemy.id = currentId++
        enemy.position.set(x, y)
        entityQueue.add(enemy)
        val energyBar = EnergyBar(enemy)
        energyBar.textureRegion = TextureManager.getTextureRegion(energyBar.resourceString)
        entityQueue.add(energyBar)
        return enemy.id
    }

    fun removeEntity(entityId: Int) {
        if (entityId == -1) return
        val entity = idEntityMap[entityId] ?: return
        removeEntity(entity)
    }

    private fun removeEntity(entity: BaseEntity) {
        idEntityMap.remove(entity.id)
        typeEntityMap[entity.type]?.removeValue(entity, true)
    }

    fun update(deltaTime: Float) {
        typeEntityMap.values.forEach {
            it.forEach {
                it.update(deltaTime)
                if (it.energy <= 0) entitiesToRemove.add(it)
            }
        }

        typeEntityMap[EntityType.BULLET]?.map { it as? Bullet }?.filterNotNull()?.forEach { bullet ->
            typeEntityMap.forEach { (type, entityList) ->
                if (type != EntityType.BULLET) entityList.forEach {
                    if (bullet.ownerType != it.type && it.isColliding(bullet)) {
                        it.energy -= bullet.damage
                    }
                }
            }
        }

        entityQueue.forEach { addEntity(it) }
        entityQueue.clear()
        entitiesToRemove.forEach {
            if (it.type == EntityType.ENEMY) {
                incrementScore(1)
                spawnExplosion(it)
            } else if (it.type == EntityType.PLAYER) {
                spawnExplosion(it)
            }
            removeEntity(it)
        }
        entitiesToRemove.clear()
    }

    private fun spawnExplosion(parent: BaseEntity): Int {
        val explosion = Explosion()
        explosion.id = currentId++
        explosion.position.set(parent.position)
        explosion.angle = parent.angle
        explosion.textureRegion = TextureManager.getTextureRegion(explosion.resourceString)
        entityQueue.add(explosion)
        return explosion.id
    }

    fun spawnExplosionBit(parent: BaseEntity): Int {
        val explosionBit = ExplosionBit()
        explosionBit.id = currentId++
        explosionBit.position.set(parent.position)
        entityQueue.add(explosionBit)
        return explosionBit.id
    }

    fun draw(batch: SpriteBatch) {
        val defaultDstBlendFunc = batch.blendDstFunc
        val defaultSrcBlendFunc = batch.blendSrcFunc
        EntityType.values().forEach {
            if (it == EntityType.PARTICLE || it == EntityType.BULLET) {
                batch.setBlendFunction(GL20.GL_BLEND_SRC_ALPHA, GL20.GL_ONE)
            } else {
                batch.setBlendFunction(defaultSrcBlendFunc, defaultDstBlendFunc)
            }
            typeEntityMap[it]?.forEach { it.draw(batch) }
        }
    }

    fun dispose() {
        currentId = 0
        playerId = -1
        scoreId = -1
        idEntityMap.clear()
        typeEntityMap.clear()
    }
}