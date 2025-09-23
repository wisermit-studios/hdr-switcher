package com.wisermit.hdrswitcher.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.io.File

enum class HdrMode {
    Default, On, Off;
}

@Immutable
@Serializable
data class Application(
    @Serializable(with = FileSerializer::class)
    val file: File,
    val description: String,
    val hdr: HdrMode = HdrMode.Default,
) {
    @Transient
    val id: String = file.path
}

private object FileSerializer : KSerializer<File> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("File", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: File) = encoder.encodeString(value.path)
    override fun deserialize(decoder: Decoder) = File(decoder.decodeString())
}