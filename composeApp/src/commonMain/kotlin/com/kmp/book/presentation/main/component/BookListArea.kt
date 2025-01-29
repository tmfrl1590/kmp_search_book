package com.kmp.book.presentation.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmp.book.domain.model.Documents
import com.kmp.book.presentation.main.MainScreenState
import com.kmp.book.util.convertToAuthor
import com.kmp.book.util.formatPrice
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun BookListArea(
    state: MainScreenState,
    onClick: (Documents) -> Unit,
    onScrolledToEnd: () -> Unit,
) {
    val listState = rememberLazyListState()

    // 리스트가 끝까지 스크롤되었는지 감지
    val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
    val isScrolledToEnd = if (listState.layoutInfo.totalItemsCount == 0) {
        false
    } else {
        val lastVisibleItem = visibleItemsInfo.last()
        val viewportHeight = listState.layoutInfo.viewportEndOffset + listState.layoutInfo.viewportStartOffset
        (lastVisibleItem.index == listState.layoutInfo.totalItemsCount - 1) && (lastVisibleItem.offset + lastVisibleItem.size <= viewportHeight)
    }

    // 스크롤이 끝까지 가면 콜백 호출
    LaunchedEffect(isScrolledToEnd) {
        if (isScrolledToEnd) {
            onScrolledToEnd()
        }
    }

    when {
        state.isLoading -> LoadingArea()
        state.bookList.documents.isEmpty() -> NoData()
        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                state = listState
            ) {
                itemsIndexed(
                    items = state.bookList.documents,
                    key = { index, item ->
                        index
                    }
                ){ _,  item ->
                    SearchedBookItem(
                        documents = item,
                        onClick = {
                            onClick(item)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingArea() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
private fun NoData() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("책 정보가 없습니다")
    }

}

@Composable
private fun SearchedBookItem(
    documents: Documents,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        BookImage(
            imageUrl = documents.thumbnail,
        )

        Spacer(modifier = Modifier.width(12.dp))

        BookInfo(documents = documents)
    }
}

@Composable
private fun BookImage(
    imageUrl: String,
) {
    CoilImage(
        modifier = Modifier
            .size(80.dp),
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )
    )
}

@Composable
private fun BookInfo(
    documents: Documents,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = documents.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1, // 한 줄로 제한
            overflow = TextOverflow.Ellipsis, // 한 줄이 넘을 경우 "..." 표시
        )
        Text(
            text = convertToAuthor(documents.authors),
            maxLines = 1, // 한 줄로 제한
            overflow = TextOverflow.Ellipsis, // 한 줄이 넘을 경우 "..." 표시
        )
        Text(
            text = "${formatPrice(documents.salePrice)}원",
        )
    }
}

