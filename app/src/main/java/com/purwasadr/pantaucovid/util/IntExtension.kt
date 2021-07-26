package com.purwasadr.pantaucovid.util

import java.text.DecimalFormat

fun Int.toDecimalFormat() =  DecimalFormat("#,###").format(this)