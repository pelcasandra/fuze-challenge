package com.ui.base.theme.custom

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ui.base.theme.custom.Typography.Font.Roboto
import com.fuze.R

sealed class Typography {
    object Font {
        val Roboto = FontFamily(
            Font(R.font.roboto_light, FontWeight.W300),
            Font(R.font.roboto_regular, FontWeight.W400),
            Font(R.font.roboto_medium, FontWeight.W500),
            Font(R.font.roboto_bold, FontWeight.W700)
        )
    }

    object Label {
        val large = TextStyle(fontSize = 21.sp, lineHeight = 24.sp, fontFamily = Roboto, fontWeight = FontWeight.W700)
        val medium = TextStyle(fontSize = 18.sp, lineHeight = 24.sp, fontFamily = Roboto, fontWeight = FontWeight.W600)
        val small = TextStyle(fontSize = 16.sp, lineHeight = 20.sp, fontFamily = Roboto, fontWeight = FontWeight.W500)
        val xsmall = TextStyle(fontSize = 13.sp, lineHeight = 16.sp, fontFamily = Roboto, fontWeight = FontWeight.W400)
        val xxsmall = TextStyle(fontSize = 11.sp, lineHeight = 16.sp, fontFamily = Roboto, fontWeight = FontWeight.W300)
    }
}
