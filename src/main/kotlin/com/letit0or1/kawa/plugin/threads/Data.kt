package com.letit0or1.kawa.plugin.threads

data class ThreadDto(
        val lineOfCode: String,
        val comments: List<CommentDto>
)

data class CommentDto(
        val name: String,
        val comment: String
)
