package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

class map : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map)

        val menu_btn = findViewById<Button>(R.id.butMenu)
        menu_btn.setOnClickListener {
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
            if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.openDrawer(Gravity.LEFT)
            } else {
                drawerLayout.closeDrawer(Gravity.LEFT)
            }
        }
    }

//메뉴 바 클릭시 펼쳐짐

}
