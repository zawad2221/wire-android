package com.waz.zclient.shared.backup.datasources.local

import com.waz.zclient.storage.db.messages.MessagesDao
import com.waz.zclient.storage.db.messages.MessagesEntity
import kotlinx.serialization.Serializable
import java.util.Arrays

class MessagesLocalDataSource(dao: MessagesDao, batchSize: Int = BatchSize) :
BackupLocalDataSource<MessagesEntity, MessagesJSONEntity>("messages", dao, batchSize, MessagesJSONEntity.serializer()) {
    override fun toJSON(entity: MessagesEntity) = MessagesJSONEntity.from(entity)
    override fun toEntity(json: MessagesJSONEntity) = json.toEntity()
}

@SuppressWarnings("ComplexMethod")
@Serializable
data class MessagesJSONEntity(
    val id: String,
    val conversationId: String = "",
    val messageType: String = "",
    val userId: String = "",
    val content: String? = null,
    val protos: ByteArray? = null,
    val time: Int = 0,
    val firstMessage: Boolean = false,
    val members: String? = null,
    val recipient: String? = null,
    val email: String? = null,
    val name: String? = null,
    val messageState: String = "",
    val contentSize: Int = 0,
    val localTime: Int = 0,
    val editTime: Int = 0,
    val ephemeral: Int? = null,
    val expiryTime: Int? = null,
    val expired: Boolean = false,
    val duration: Int? = null,
    val quote: String? = null,
    val quoteValidity: Int = 0,
    val forceReadReceipts: Int? = null,
    val assetId: String? = null
) {
    override fun hashCode() =
        id.hashCode() + conversationId.hashCode() + messageType.hashCode() + userId.hashCode() +
        content.hashCode() + time.hashCode() + firstMessage.hashCode() + members.hashCode() +
        recipient.hashCode() + email.hashCode() + name.hashCode() + messageState.hashCode() +
        contentSize.hashCode() + localTime.hashCode() + editTime.hashCode() + ephemeral.hashCode() +
        expiryTime.hashCode() + expired.hashCode() + duration.hashCode() + quote.hashCode() +
        quoteValidity.hashCode() + forceReadReceipts.hashCode() + assetId.hashCode() + Arrays.hashCode(protos)

    override fun equals(other: Any?) =
        other != null && other is MessagesJSONEntity &&
        id == other.id && conversationId == other.conversationId && messageType == other.messageType &&
        userId == other.userId && content == other.content && time == other.time && firstMessage == other.firstMessage &&
        members == other.members && recipient == other.recipient && email == other.email &&
        name == other.name && messageState == other.messageState && contentSize == other.contentSize &&
        localTime == other.localTime && editTime == other.editTime && ephemeral == other.ephemeral &&
        expiryTime == other.expiryTime && expired == other.expired && duration == other.duration &&
        quote == other.quote && quoteValidity == other.quoteValidity && forceReadReceipts == other.forceReadReceipts &&
        assetId == other.assetId &&
        ((protos == other.protos) || protos != null && other.protos != null && other.protos.contentEquals(protos))

    fun toEntity() = MessagesEntity(
        id = id,
        conversationId = conversationId,
        messageType = messageType,
        userId = userId,
        content = content,
        protos = protos,
        time = time,
        firstMessage = firstMessage,
        members = members,
        recipient = recipient,
        email = email,
        name = name,
        messageState = messageState,
        contentSize = contentSize,
        localTime = localTime,
        editTime = editTime,
        ephemeral = ephemeral,
        expiryTime = expiryTime,
        expired = expired,
        duration = duration,
        quote = quote,
        quoteValidity = quoteValidity,
        forceReadReceipts = forceReadReceipts,
        assetId = assetId
    )

    companion object {
        fun from(entity: MessagesEntity) = MessagesJSONEntity(
            id = entity.id,
            conversationId = entity.conversationId,
            messageType = entity.messageType,
            userId = entity.userId,
            content = entity.content,
            protos = entity.protos,
            time = entity.time,
            firstMessage = entity.firstMessage,
            members = entity.members,
            recipient = entity.recipient,
            email = entity.email,
            name = entity.name,
            messageState = entity.messageState,
            contentSize = entity.contentSize,
            localTime = entity.localTime,
            editTime = entity.editTime,
            ephemeral = entity.ephemeral,
            expiryTime = entity.expiryTime,
            expired = entity.expired,
            duration = entity.duration,
            quote = entity.quote,
            quoteValidity = entity.quoteValidity,
            forceReadReceipts = entity.forceReadReceipts,
            assetId = entity.assetId
        )
    }
}