package com.purwasadr.pantaucovid.ui.main

import com.google.common.truth.Truth.assertThat
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.data.repository.city.CityRepository
import com.purwasadr.pantaucovid.data.repository.covid.CovidRateRepository
import com.purwasadr.pantaucovid.data.repository.hospital.HospitalRepository
import com.purwasadr.pantaucovid.data.repository.province.ProvinceRepository
import com.purwasadr.pantaucovid.util.DummyData
import com.purwasadr.pantaucovid.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MainViewModelTest {

    lateinit var covidRateRepository: CovidRateRepository
    lateinit var provinceRepository: ProvinceRepository
    lateinit var cityRepository: CityRepository
    lateinit var hospitalRepository: HospitalRepository

    private lateinit var mainViewModel: MainViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        covidRateRepository = mock()
        provinceRepository = mock()
        cityRepository = mock()
        hospitalRepository = mock()

        mainViewModel = MainViewModel(
            covidRateRepository,
            provinceRepository, cityRepository, hospitalRepository
        )
    }

    fun testGetCovidData() {

    }

    fun testGetProvincePos() {}

    fun testSetProvincePos() {}

    fun testGetCityPos() {}

    fun testSetCityPos() {}

    fun testRefreshCovidData() {}

    fun testGetProvince() {}

    @ExperimentalCoroutinesApi
    @Test
    fun testGetCities() {
        val cities = DummyData.cityDomains

        val citiesFlow = flowOf(Resource.Success(cities))

        val citiesFlow2 = flowOf(Resource.Success(cities))

        whenever(cityRepository.getCities(anyString())).thenReturn(citiesFlow)

        val expected = mainViewModel.getCities("saber")

        verify(cityRepository).getCities(anyString())

        assertThat(citiesFlow2).isEqualTo(expected)
    }

    fun testGetHospitals() {}

    fun testSetHospitals() {}

    fun testTestGetCities() {}

    fun testTestGetHospitals() {}
}