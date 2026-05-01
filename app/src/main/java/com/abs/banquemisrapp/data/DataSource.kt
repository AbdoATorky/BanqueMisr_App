package com.abs.banquemisrapp.data

import com.abs.banquemisrapp.R
import com.abs.banquemisrapp.model.Services

class DataSource {
    fun getServiceItemsData() = listOf(
        Services(R.drawable.our_products, R.string.our_products),
        Services(R.drawable.exchange_rate, R.string.exchange_rate),
        Services(R.drawable.security_tips, R.string.security_tips),
        Services(R.drawable.nearest_branch_or_atm, R.string.nearest_branch),
    )
}