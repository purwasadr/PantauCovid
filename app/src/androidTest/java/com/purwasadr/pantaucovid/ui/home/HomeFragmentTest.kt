package com.purwasadr.pantaucovid.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.purwasadr.pantaucovid.R
import com.purwasadr.pantaucovid.data.Resource
import com.purwasadr.pantaucovid.data.repository.covid.CovidRateRepository
import com.purwasadr.pantaucovid.data.source.local.entity.CovidRateEntity
import com.purwasadr.pantaucovid.ui.launchFragmentInHiltContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import javax.inject.Inject
import javax.inject.Singleton

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class HomeFragmentTest {

    @Module
    @InstallIn(SingletonComponent::class)
    object TestModule {

        @Singleton
        @Provides
        fun provideCovidRateRepository(): CovidRateRepository = mock()

    }

    @get:Rule
    var hitRule = HiltAndroidRule(this)

    @Inject
    lateinit var repo: CovidRateRepository

    @Before
    fun setUp() {
        hitRule.inject()
    }

    @Test
    fun test1() {
        `when`(repo.getCovidRate()).thenReturn(
            flowOf(
                Resource.Success(
                    CovidRateEntity(
                        id = 0,
                        dataJumlahOdp = null,
                        dataJumlahPdp = null,
                        dataTotalSpesimen = null,
                        dataTotalSpesimenNegatif = null,
                        penambahanCreated = null,
                        penambahanJumlahMeninggal = null,
                        penambahanTanggal = null,
                        penambahanJumlahSembuh = null,
                        penambahanJumlahPositif = null,
                        penambahanJumlahDirawat = null,
                        totalJumlahMeninggal = null,
                        totalJumlahSembuh = null,
                        totalJumlahPositif = 10,
                        totalJumlahDirawat = null
                    )
                )
            )
        )

        launchFragmentInHiltContainer<HomeFragment>(null, R.style.Theme_PantauCovid)
        onView(withId(R.id.txt_positive_info)).check(matches((isDisplayed())))
        onView(withId(R.id.txt_positive)).check(matches(withText("10")))
    }
}
