package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout


class like_med : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.like_med)
        var menu_btn = findViewById<Button>(R.id.butMenu3)

        var tomain_btn = findViewById<Button>(R.id.tomain)
        var tobook_btn = findViewById<Button>(R.id.tobook)
        var topharmacy_btn = findViewById<Button>(R.id.topharmacy)

        val intent_toexplain = Intent(this, explain::class.java)
        val intent_tomain = Intent(this, MainActivity::class.java)
        val intent_tobook = Intent(this, like_med::class.java)
        val intent_topharmacy = Intent(this, map::class.java)
        menu_btn.setOnClickListener {
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
            if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.openDrawer(Gravity.LEFT)
            } else {
                drawerLayout.closeDrawer(Gravity.LEFT)
            }
        }

        // to main
        tomain_btn.setOnClickListener {
            startActivity(intent_tomain)
        }

        // to book
        tobook_btn.setOnClickListener {
            startActivity(intent_tobook)
        }

        // to pharmacy
        topharmacy_btn.setOnClickListener {
            startActivity(intent_topharmacy)
        }
    }
}

