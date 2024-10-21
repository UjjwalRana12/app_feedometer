package com.example.feedometer.naviagtion

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feedometer.screens.SplashScreen

@Composable
fun NavGraph(navController:NavHostController, activity:ComponentActivity){

    NavHost(navController = navController, startDestination = Routes.Splash.routes){
      composable(Routes.Splash.routes){
        SplashScreen(navController)
      }
    }
}