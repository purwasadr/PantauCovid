package com.purwasadr.pantaucovid.data.repository.city

import com.google.common.truth.Truth.assertThat
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.data.mapper.CityEntityToDomain
import com.purwasadr.pantaucovid.data.mapper.CityResponseToEntity
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.CityItemResponse
import com.purwasadr.pantaucovid.model.City
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


class CityRepositoryTest {
    private val cityDataSource: CityDataSource = mock()
    private val cityStore: CityStore = mock()
    private val entityToDomainMapper = CityEntityToDomain()
    private val responseToEntity = CityResponseToEntity()

    private val cityRepository = CityRepository(
        cityDataSource,
        cityStore,
        responseToEntity,
        entityToDomainMapper
    )

    private val provinceId = "lambda"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetCities() = runBlockingTest {
        val citiesItemResponse = listOf(
            CityItemResponse(
                id = "tozuro", name = "Kartasura"
            ),
            CityItemResponse(
                id = "fuyuk", name = "Fuyuki"

            )
        )
        val cityDomains = listOf(
            City(
                id = "tozuro", name = "Kartasuras"
            ),
            City(
                id = "fuyuk", name = "Fuyuki"

            )
        )

        val apiResponse = ApiResponse.Success(citiesItemResponse)

        val flowResponse = flowOf(apiResponse)

        var cityEntities: List<CityEntity>? = null

        whenever(cityDataSource.getCities(provinceId)).thenReturn(flowResponse)

        whenever(cityStore.insertEntities(anyList())).thenAnswer {
            val param = it.arguments[0] as List<CityEntity>
            cityEntities = param
            param
        }

        whenever(cityStore.getEntities()).thenAnswer {
            flowOf(cityEntities.orEmpty())
        }

        val result = cityRepository.getCities(provinceId).last().data
        val actual = Resource.Success(cityDomains).data

        verify(cityDataSource).getCities(provinceId)
        verify(cityStore).insertEntities(anyList())
        verify(cityStore).getEntities()

        assertThat(actual).isEqualTo(result)
    }
}