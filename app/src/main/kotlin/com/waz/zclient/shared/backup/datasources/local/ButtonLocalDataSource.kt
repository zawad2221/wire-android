package com.waz.zclient.shared.backup.datasources.local

import com.waz.zclient.storage.db.buttons.ButtonDao
import com.waz.zclient.storage.db.buttons.ButtonEntity
import kotlinx.serialization.Serializable

class ButtonLocalDataSource(dao: ButtonDao, batchSize: Int = BatchSize) :
BackupLocalDataSource<ButtonEntity, ButtonJSONEntity>("buttons", dao, batchSize, ButtonJSONEntity.serializer()) {
    override fun toJSON(entity: ButtonEntity) = ButtonJSONEntity.from(entity)
    override fun toEntity(json: ButtonJSONEntity) = json.toEntity()
}

@Serializable
data class ButtonJSONEntity(
    val messageId: String = "",
    val buttonId: String = "",
    val title: String = "",
    val ordinal: Int = 0,
    val state: Int = 0
) {
    fun toEntity() = ButtonEntity(
        messageId = messageId,
        buttonId = buttonId,
        title = title,
        ordinal = ordinal,
        state = state
    )

    companion object {
        fun from(entity: ButtonEntity) = ButtonJSONEntity(
            messageId = entity.messageId,
            buttonId = entity.buttonId,
            title = entity.title,
            ordinal = entity.ordinal,
            state = entity.state
        )
    }
}