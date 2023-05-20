package com.example.mybudget.data.model.db

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mybudget.R

enum class ProductTypesEnum (@StringRes internal val text: Int, internal val imageVector: ImageVector) {
    SHOP(R.string.product_type_shop, Icons.Filled.ShoppingCart),
    TECH(R.string.product_type_tech, Icons.Filled.Phone);

    override fun toString(): String {
        return text.toString()
    }
}