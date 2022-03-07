package com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo

import com.github.pedrobacchini.mercadolivreevaluation.adapter.output.dynamo.entity.SimianAnalysisEntity
import com.github.pedrobacchini.mercadolivreevaluation.application.domain.SimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.helper.dummyObject
import com.github.pedrobacchini.mercadolivreevaluation.helper.validHumanSimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.helper.validSimianAnalysis
import com.github.pedrobacchini.mercadolivreevaluation.helper.validSimianAnalysisEntity
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import software.amazon.awssdk.core.pagination.sync.SdkIterable
import software.amazon.awssdk.enhanced.dynamodb.*
import software.amazon.awssdk.enhanced.dynamodb.model.Page
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest
import java.util.function.Consumer
import java.util.stream.Stream

internal class SimianAnalysisRepositoryTest {

    private val dynamoDbEnhancedClient: DynamoDbEnhancedClient = mock()
    private val dynamoDbTable: DynamoDbTable<SimianAnalysisEntity> = mock()


    private val tableName = "tableName"
    private lateinit var simianAnalysisRepository: SimianAnalysisRepository

    @BeforeEach
    fun setup() {
        whenever(dynamoDbEnhancedClient.table(eq(tableName), any<TableSchema<SimianAnalysisEntity>>()))
            .thenReturn(dynamoDbTable)
        simianAnalysisRepository = SimianAnalysisRepository(tableName, dynamoDbEnhancedClient)
    }

    @ParameterizedTest
    @MethodSource("generateValidSimianAnalysis")
    fun `given that generateValidSimianAnalysis, then a SimianAnalysisEntity must be saved correctly`(
        pair: Pair<SimianAnalysis, String>
    ) {

        val expectedSimianAnalysis: SimianAnalysis = pair.first
        simianAnalysisRepository.save(expectedSimianAnalysis)

        val dynamoDBMapperCaptor = argumentCaptor<SimianAnalysisEntity>()
        verify(dynamoDbTable, times(1)).putItem(dynamoDBMapperCaptor.capture())
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
    fun `should find sequence by dna`() {
        whenever(dynamoDbTable.getItem(any<Key>()))
            .thenReturn(validSimianAnalysisEntity())

        val dna = dummyObject<List<List<Char>>>()
        val findSequenceByDna = simianAnalysisRepository.findSequenceByDna(dna)

        assertNotNull(findSequenceByDna)
        val keyCaptor = argumentCaptor<Key>()
        verify(dynamoDbTable, times(1)).getItem(keyCaptor.capture())
        val key = keyCaptor.firstValue

        assertEquals(dna.hashCode().toString(), key.partitionKeyValue().s())
    }

    @Test
    fun `should count simian analysis by resultType`() {

        val dynamoDbIndex: DynamoDbIndex<SimianAnalysisEntity> = mock()
        val queryReturnMock: SdkIterable<Page<SimianAnalysisEntity>> = mock()
        val iteratorMock: MutableIterator<Page<SimianAnalysisEntity>> = mock()
        whenever(queryReturnMock.iterator()).thenReturn(iteratorMock)
        whenever(iteratorMock.hasNext()).thenReturn(true).thenReturn(false)
        whenever(iteratorMock.next()).thenReturn(Page.create(listOf(validSimianAnalysisEntity())))
        whenever(dynamoDbTable.index(any())).thenReturn(dynamoDbIndex)
        whenever(dynamoDbIndex.query(any<Consumer<QueryEnhancedRequest.Builder>>()))
            .thenReturn(PageIterable.create(queryReturnMock))

        simianAnalysisRepository.countSimianAnalysisByResultType("simian")

        verify(dynamoDbTable, times(1)).index(any())
        verify(dynamoDbIndex, times(1)).query(any<Consumer<QueryEnhancedRequest.Builder>>())
    }
}