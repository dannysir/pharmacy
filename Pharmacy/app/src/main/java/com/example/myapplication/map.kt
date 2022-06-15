package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplication.databinding.LikeMedBinding

class map : AppCompatActivity() {
    private lateinit var binding : LikeMedBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = LikeMedBinding.inflate(layoutInflater)

        var menu_btn = binding.butMenu3

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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

