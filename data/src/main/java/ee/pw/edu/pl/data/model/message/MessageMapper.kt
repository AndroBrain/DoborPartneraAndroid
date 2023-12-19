package ee.pw.edu.pl.data.model.message

import ee.pw.edu.pl.data.model.message.local.MessageEntity
import ee.pw.edu.pl.data.model.message.local.ProfileWithMessagesRelation
import ee.pw.edu.pl.data.model.message.remote.MessageResponse
import ee.pw.edu.pl.data.model.message.remote.ProfileWithMessagesResponse
import ee.pw.edu.pl.data.model.message.remote.SendMessageRequest
import ee.pw.edu.pl.domain.usecase.chat.SendMessageForm
import ee.pw.edu.pl.domain.usecase.message.Message
import ee.pw.edu.pl.domain.usecase.message.profile.ProfileWithMessages
import ee.pw.edu.pl.domain.usecase.profile.Profile

fun SendMessageForm.toRequest() = SendMessageRequest(
    receiverId = receiverId,
    message = message,
)

fun List<MessageResponse>.toEntities() = map { it.toEntity() }

fun MessageResponse.toEntity() = MessageEntity(
    id = id,
    fromUser = fromUser,
    toUser = toUser,
    text = messageText,
    timestamp = sentTimestamp,
)

@JvmName("messageEntityToModels")
fun List<MessageEntity>.toModels(id: Int) = map { it.toModel(id) }

fun MessageEntity.toModel(id: Int) = Message(
    text = text,
    isYour = fromUser != id
)

@JvmName("profileWithMessagesRelationToModels")
fun List<ProfileWithMessagesRelation>.toModels() = map { it.toModel() }

fun ProfileWithMessagesRelation.toModel() = ProfileWithMessages(
    id = profile.id,
    name = profile.name,
    avatar = profile.avatar,
    messages = messages.toModels(profile.id),
)

fun List<ProfileWithMessagesResponse>.toProfileModels() = map { it.toProfileModel() }

fun ProfileWithMessagesResponse.toProfileModel() = Profile(
    id = id,
    name = name,
    avatar = avatar,
)
