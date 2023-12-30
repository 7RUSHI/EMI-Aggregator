package com.vrushi.emiaggregator.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream

@OptIn(ExperimentalSerializationApi::class)
object AppSettingsSerializer : Serializer<AppSettings> {
    override val defaultValue: AppSettings
        get() = AppSettings()

    override suspend fun readFrom(input: InputStream): AppSettings {
        return try {
            Json.decodeFromStream<AppSettings>(input)
        } catch (e: SerializationException) {
            e.printStackTrace()
            throw CorruptionException("Unable to read Settings", e)
        }
    }

    override suspend fun writeTo(t: AppSettings, output: OutputStream) {
        Json.encodeToStream<AppSettings>(t, output)
    }
}