package com.example.cipipeline

import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {
    // Simple test but...
    @Test
    fun testExample() {
        assertTrue(Greeting().greet().contains("Hello"), "Check 'Hello' is mentioned")
    }
}
