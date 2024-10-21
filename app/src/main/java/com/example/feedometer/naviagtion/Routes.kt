package com.example.feedometer.naviagtion

sealed class Routes(val routes:String) {
    object Splash:Routes("splash")
    object HomeScreen:Routes("home_screen")
}