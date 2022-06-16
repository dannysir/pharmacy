package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplication.databinding.LikeMedBinding

class like_med : AppCompatActivity() {
    private lateinit var binding : LikeMedBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = LikeMedBinding.inflate(layoutInflater)

        var menu_btn = binding.butMenu3

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var tomain_btn = binding.tomain
        var tobook_btn = binding.tobook
        var topharmacy_btn = binding.topharmacy

        val intent_toexplain = Intent(this,explain::class.java)
        val intent_tomain = Intent(this,MainActivity::class.java)
        val intent_tobook = Intent(this,like_med::class.java)
        val intent_topharmacy = Intent(this,map::class.java)

        menu_btn.setOnClickListener {
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
            if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.openDrawer(Gravity.LEFT)
            } else {
                drawerLayout.closeDrawer(Gravity.LEFT)
            }
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


//메뉴 바 클릭시 펼쳐짐

}

