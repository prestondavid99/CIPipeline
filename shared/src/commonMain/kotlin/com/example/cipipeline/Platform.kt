package com.example.cipipeline

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
