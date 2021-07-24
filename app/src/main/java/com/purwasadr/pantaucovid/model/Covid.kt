package com.purwasadr.pantaucovid.model

data class Covid(
    val jumlahPositif: Int? = null,
    val jumlahSembuh: Int? = null,
    val jumlahMeninggal: Int? = null,
    val jumlahOdp: Int?,
    val jumlahPdp: Int?,
)
