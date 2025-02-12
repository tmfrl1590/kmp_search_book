package com.kmp.book.presentation.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmp.book.util.formatIsoDate
import com.kmp.book.util.formatPrice

@Composable
fun BookInfoArea(
    title: String,
    contents: String,
    publisher: String,
    salePrice: Int,
    status: String,
    authors: String,
    datetime: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = contents,
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        BookInfoAreaItem(
            title = "저자",
            value = authors
        )

        BookInfoAreaItem(
            title = "판매가격",
            value = "${formatPrice(salePrice)}원",
        )

        BookInfoAreaItem(
            title = "상태",
            value = status
        )

        BookInfoAreaItem(
            title = "출판일",
            value = formatIsoDate(datetime)
        )

        BookInfoAreaItem(
            title = "출판사",
            value = publisher
        )
    }
}

@Composable
private fun BookInfoAreaItem(
    title: String,
    value: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black,
        )
        Text(
            text = value,
            fontSize = 12.sp,
            color = Color.DarkGray
        )
    }
}