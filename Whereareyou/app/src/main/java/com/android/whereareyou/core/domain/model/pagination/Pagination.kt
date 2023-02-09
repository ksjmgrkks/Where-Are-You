package com.android.whereareyou.core.domain.model.pagination

data class Pagination(
    val currentPage: Int,
    val totalPages: Int
) {

  companion object {
    const val UNKNOWN_TOTAL = -1
    const val DEFAULT_PAGE_SIZE = 20
  }

  val canLoadMore: Boolean
    get() = totalPages == UNKNOWN_TOTAL || currentPage < totalPages
}
