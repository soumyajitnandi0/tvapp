package com.example.tvapp.data.model

data class VideoResponse(
    val page: Int,
    val per_page: Int,
    val videos: List<Video>,
    val total_results: Int,
    val next_page: String?,
    val url: String
)

data class Video(
    val id: Int,
    val width: Int,
    val height: Int,
    val duration: Int,
    val url: String,
    val image: String,
    val user: User,
    val video_files: List<VideoFile>
)

data class User(
    val id: Int,
    val name: String,
    val url: String
)

data class VideoFile(
    val id: Int,
    val quality: String,
    val file_type: String,
    val width: Int,
    val height: Int,
    val fps: Double,
    val link: String,
    val size: Long
)