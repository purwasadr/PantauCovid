package com.purwasadr.pantaucovid.data.repository.province

import com.google.common.truth.Truth
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.data.mapper.ProvinceEntityToDomain
import com.purwasadr.pantaucovid.data.mapper.ProvinceResponseToEntity
import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceEntity
import com.purwasadr.pantaucovid.data.source.remote.network.ApiResponse
import com.purwasadr.pantaucovid.data.source.remote.response.ProvincesItemResponse
import com.purwasadr.pantaucovid.model.Province
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyList
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ProvinceRepositoryTest {
    private val remoteDataSource: ProvinceDataSource = mock()
    private val localDataSource: ProvinceStore = mock()

    private val repository = ProvinceRepository(
        remoteDataSource,
        localDataSource,
        ProvinceResponseToEntity(),
        ProvinceEntityToDomain()
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getProvince() = runBlockingTest {
        val listProvince = listOf<ProvincesItemResponse>(
            ProvincesItemResponse("cir1","Cirebon"),
            ProvincesItemResponse("bek1","Bekasi")
        )

        val apiResult = flowOf(ApiResponse.Success(listProvince))

        var listProvinceEntity2: List<ProvinceEntity>? = null

        val listProvinceDomain = listOf<Province>(
            Province("cir1", "Cirebon"),
            Province("bek1", "Bekasi")
        )

        val argCap = ArgumentCaptor.forClass(List::class.java)

        whenever(remoteDataSource.getProvince()).thenReturn(apiResult)

        whenever(localDataSource.delsertEntities(anyList())).thenAnswer {
            println(it.arguments[0] as List<ProvinceEntity>)
            listProvinceEntity2 = it.arguments[0] as List<ProvinceEntity>
            it.getArgument<List<ProvinceEntity>>(0)
        }

        whenever(localDataSource.getEntries()).then {
           flowOf(listProvinceEntity2!!)
        }

        val data = repository.getProvinces().last().data

        val actual = Resource.Success(listProvinceDomain).data

        verify(remoteDataSource).getProvince()
        verify(localDataSource).getEntries()
        verify(localDataSource).delsertEntities(anyList())

        Truth.assertThat(actual).isEqualTo(data)
    }

}