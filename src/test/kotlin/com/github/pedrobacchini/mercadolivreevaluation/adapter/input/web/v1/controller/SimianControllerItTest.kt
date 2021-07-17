package com.github.pedrobacchini.mercadolivreevaluation.adapter.input.web.v1.controller

import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisStatsUseCase
import com.github.pedrobacchini.mercadolivreevaluation.application.port.input.SimianAnalysisUseCase
import com.github.pedrobacchini.mercadolivreevaluation.extension.objectToJson
import com.github.pedrobacchini.mercadolivreevaluation.helper.dummyObject
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Tag(value = "integration")
@WebMvcTest(controllers = [SimianController::class])
internal class SimianControllerItTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var simianAnalysisUseCase: SimianAnalysisUseCase

    @MockBean
    private lateinit var simianAnalysisStatsUseCase: SimianAnalysisStatsUseCase

    private val analysisSimianRequestBuilder = post("/v1/simian")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)

    private data class SimianAnalysisRequestTest(var dna: List<String?>?)

    @Test
    fun `given that empty body at simian analysis request, must successfully invalidate it`() {
        mockMvc.perform(analysisSimianRequestBuilder).andExpect(status().isBadRequest)
    }

    @Test
    fun `given that a valid simian analysis request, must successfully validate it`() {
        val validSimianAnalysisRequest = SimianAnalysisRequestTest(
            dna = mutableListOf("TTGAGA", "CTATGC", "TATTGT", "AGATGG", "CCCCTA", "TCACTG")
        )
        mockMvc.perform(analysisSimianRequestBuilder.content(validSimianAnalysisRequest.objectToJson()))
            .andExpect(status().isOk)
    }

    @Test
    fun `given that a null dna at simian analysis request, must successfully invalidate it`() {
        val nullSimianAnalysisRequest = SimianAnalysisRequestTest(dna = null)
        mockMvc.perform(analysisSimianRequestBuilder.content(nullSimianAnalysisRequest.objectToJson()))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `given that a empty dna at simian analysis request, must successfully invalidate it`() {
        val emptySimianAnalysisRequest = SimianAnalysisRequestTest(dna = mutableListOf())
        mockMvc.perform(analysisSimianRequestBuilder.content(emptySimianAnalysisRequest.objectToJson()))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `given that a null element dna at simian analysis request, must successfully invalidate it`() {
        val nullElementSimianAnalysisRequest = SimianAnalysisRequestTest(dna = mutableListOf(null, "ACTT"))
        mockMvc.perform(analysisSimianRequestBuilder.content(nullElementSimianAnalysisRequest.objectToJson()))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `given that a elements with different size at simian analysis request, must successfully invalidate it`() {
        val elementsWithDifferentSize = SimianAnalysisRequestTest(dna = mutableListOf("ACT", "ACTT"))
        mockMvc.perform(analysisSimianRequestBuilder.content(elementsWithDifferentSize.objectToJson()))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `given that a elements out of pattern at simian analysis request, must successfully invalidate it`() {
        val elementsOutOfPattern = SimianAnalysisRequestTest(dna = mutableListOf("QWERYUIOPSDFHJKLZXVBNM"))
        mockMvc.perform(analysisSimianRequestBuilder.content(elementsOutOfPattern.objectToJson()))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `should return stats of analysis simian`() {

        whenever(simianAnalysisStatsUseCase.execute()).thenReturn(dummyObject())

        mockMvc.perform(
            get("/v1/simian/stats")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
    }
}