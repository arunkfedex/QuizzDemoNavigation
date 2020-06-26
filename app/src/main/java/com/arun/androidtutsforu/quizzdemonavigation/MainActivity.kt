package com.arun.androidtutsforu.quizzdemonavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
  private lateinit var drawerlayout : DrawerLayout
    private lateinit var navView : NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerlayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val navController : NavController = this.findNavController(R.id.navhost)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerlayout)
        NavigationUI.setupWithNavController(navView,navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("nav listener","navigation change")
            if(destination.id==R.id.qaFragment){
                Log.d("nav QAfragment","navigation change")
                supportActionBar?.hide()
            }
            else{
                Log.d("nav others","navigation change")
                supportActionBar?.show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController : NavController = findNavController(R.id.navhost)
        return NavigationUI.navigateUp(navController,drawerlayout)
    }
}
