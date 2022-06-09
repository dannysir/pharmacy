package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import androidx.drawerlayout.widget.DrawerLayout

open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menu_btn = findViewById<Button>(R.id.butMenu)
        val gettext_btn = findViewById<Button>(R.id.gettext)
        val tomain_btn=findViewById<Button>(R.id.tomain)
        val tobook_btn=findViewById<Button>(R.id.tobook)
        val topharmacy_btn=findViewById<Button>(R.id.topharmacy)

        val intent_toexplain = Intent(this,explain::class.java)
        val intent_tomain = Intent(this,MainActivity::class.java)
        val intent_tobook = Intent(this,like_med::class.java)
        val intent_topharmacy = Intent(this,map::class.java)

        //메뉴 바 클릭시 펼쳐짐
        menu_btn.setOnClickListener {
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
            if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.openDrawer(Gravity.LEFT)
            } else {
                drawerLayout.closeDrawer(Gravity.LEFT)
            }
        }

        // 검색
        gettext_btn.setOnClickListener{
            startActivity(intent_toexplain)
        }

        // to main
        tomain_btn.setOnClickListener{
            startActivity(intent_tomain)
        }

        // to book
        tobook_btn.setOnClickListener{
            startActivity(intent_tobook)
        }

        // to pharmacy
        topharmacy_btn.setOnClickListener{
            startActivity(intent_topharmacy)
        }


    }
}