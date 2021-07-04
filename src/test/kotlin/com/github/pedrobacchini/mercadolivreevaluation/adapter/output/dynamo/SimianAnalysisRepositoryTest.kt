package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList
import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.entity.SimianAnalysisEntity
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.helper.dummyObject
import com.github.pedrobacchini.mercadolivreevaluation.helper.validHumanSimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.helper.validSimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.helper.validSimianAnalysisEntity
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class SimianAnalysisRepositoryTest {

    private val dynamoDBMapper: DynamoDBMapper = mock()

    private val simianAnalysisRepository = SimianAnalysisRepository(dynamoDBMapper)

    @ParameterizedTest
    @MethodSource("generateValidSimianAnalysis")
    fun `given that simian analysis, then a SimianAnalysisEntity must be saved correctly`(pair: Pair<SimianAnalysis, String>) {

        val expectedSimianAnalysis: SimianAnalysis = pair.first
        simianAnalysisRepository.save(expectedSimianAnalysis)

        val dynamoDBMapperCaptor = argumentCaptor<SimianAnalysisEntity>()
        verify(dynamoDBMapper).save(dynamoDBMapperCaptor.capture())
        val capturedSimianAnalysis = dynamoDBMapperCaptor.firstValue

        assertEquals(expectedSimianAnalysis.dna.hashCode().toString(), capturedSimianAnalysis.pk)
        assertEquals(pair.second, capturedSimianAnalysis.sk)
    }

    companion object {
        @JvmStatic
        fun generateValidSimianAnalysis(): Stream<Arguments> =
            Stream.of(
                Arguments.arguments(Pair(validSimianAnalysis(), "simian")),
                Arguments.arguments(Pair(validHumanSimianAnalysis(), "human"))
            )
    }

    @Test
    fun `should find all sequences by dna`() {

        val queryReturnMock: PaginatedQueryList<SimianAnalysisEntity> = mock()
        val iteratorMock: MutableIterator<SimianAnalysisEntity> = mock()
        whenever(queryReturnMock.iterator()).thenReturn(iteratorMock)
        whenever(iteratorMock.hasNext()).thenReturn(true).thenReturn(false)
        whenever(iteratorMock.next()).thenReturn(validSimianAnalysisEntity())
        whenever(dynamoDBMapper.query(any(), any<DynamoDBQueryExpression<SimianAnalysisEntity>>()))
            .thenReturn(queryReturnMock)

        simianAnalysisRepository.findSequenceByDna(dummyObject())

        verify(dynamoDBMapper, times(1)).query(any(), any<DynamoDBQueryExpression<SimianAnalysisEntity>>())
    }
}