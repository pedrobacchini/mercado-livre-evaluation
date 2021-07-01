package com.github.pedrobacchini.mercadolivreevaluation.extension

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule

object JacksonExtension {

    val jacksonObjMapper: ObjectMapper by lazy {
        ObjectMapper().registerModule(JavaTimeModule())
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .registerModule(KotlinModule())
    }
}

fun <T> String.jsonToObject(t: Class<T>): T =
    JacksonExtension.jacksonObjMapper.readValue(this, t)

fun <T> T.objectToJson(): String =
    JacksonExtension.jacksonObjMapper.writeValueAsString(this)