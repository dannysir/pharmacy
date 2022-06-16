package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import kotlin.concurrent.thread


class explain : AppCompatActivity() {

    private val key = "tPk5EHcaiFU0s8eRw9SXMzDgaDtM4Be%2BiALrcYRXB2%2FJ%2BM%2BulWDgqdHvxIFC4V8wV9CUdpBkk7jQpUKc9Dogtg%3D%3D"
    private val baseUrl = "http://apis.data.go.kr/1471000/DURIrdntInfoService01"
    private val codes = hashMapOf(
        // 약 이름 to 약 코드
        "돔페리돈" to "D000297",
        "메살라진" to "D000631",
        "이부프로펜" to "D000363",
        "로수바스타틴" to "D000219",
        "클로르디아제폭시드" to "D000056",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.explain)

        val menu_btn = findViewById<Button>(R.id.butMenu2)
        val tomain_btn = findViewById<Button>(R.id.tomain)
        val tobook_btn = findViewById<Button>(R.id.tobook)
        val topharmacy_btn = findViewById<Button>(R.id.topharmacy)

        val intent_toexplain = Intent(this, explain::class.java)
        val intent_tomain = Intent(this, MainActivity::class.java)
        val intent_tobook = Intent(this, like_med::class.java)
        val intent_topharmacy = Intent(this, map::class.java)

        //메뉴 바 클릭시 펼쳐짐
        menu_btn.setOnClickListener {
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout2)
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

        val intent = intent
        val name = intent.getStringExtra("name")
        findViewById<TextView>(R.id.textTitle).text = name

        thread(start = true) {
            val typeName = URLEncoder.encode("병용금기", StandardCharsets.UTF_8.toString())
            val ingrCode = URLEncoder.encode(codes[name], StandardCharsets.UTF_8.toString())
            val encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString())
            val urlStr =
                "serviceKey=$key&typeName=$typeName&ingrCode=$ingrCode&ingrName=$encodedName&pageNo=1&numOfRows=1&type=json"

            try {
                val url = URL("$baseUrl/getUsjntTabooInfoList?$urlStr")
                val `is`: InputStream = url.openStream()
                val isr = InputStreamReader(`is`)
                val reader = BufferedReader(isr)
                val buffer = StringBuffer()
                var line: String = reader.readLine()
                while (true) {
                    buffer.append(line.trimIndent())
                    line = reader.readLine() ?: break
                }
                val jsonData = buffer.toString()

                // jsonData를 먼저 JSONObject 형태로 바꾼다.
                val obj = JSONObject(jsonData)
                // obj의 "boxOfficeResult"의 JSONObject를 추출
                val side_effects = obj["body"] as JSONObject

                val buf_side_effects = side_effects["items"] as JSONArray
                val v_side_effects = buf_side_effects.getJSONObject(0)["PROHBT_CONTENT"] as String
                val v_mixed = buf_side_effects.getJSONObject(0)["MIXTURE_INGR_KOR_NAME"] as String

                val textView_a=findViewById<TextView>(R.id.textView_a)



                var textView_b=findViewById<TextView>(R.id.textView_b)
                runOnUiThread {
                    textView_a.text=v_side_effects
                    textView_b.text=v_mixed
                }
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }



        thread(start = true) {
            val typeName = URLEncoder.encode("용량주의", StandardCharsets.UTF_8.toString())
            val ingrCode = URLEncoder.encode(codes[name], StandardCharsets.UTF_8.toString())
            val encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString())
            val urlStr =
                "serviceKey=$key&typeName=$typeName&ingrCode=$ingrCode&ingrName=$encodedName&pageNo=1&numOfRows=1&type=json"

            try {
                val url = URL("$baseUrl/getCpctyAtentInfoList?$urlStr")
                val `is`: InputStream = url.openStream()
                val isr = InputStreamReader(`is`)
                val reader = BufferedReader(isr)
                val buffer = StringBuffer()
                var line: String = reader.readLine()
                while (true) {
                    buffer.append(line.trimIndent())
                    line = reader.readLine() ?: break
                }
                val jsonData = buffer.toString()

                // jsonData를 먼저 JSONObject 형태로 바꾼다.
                val obj = JSONObject(jsonData)
                // obj의 "boxOfficeResult"의 JSONObject를 추출
                val side_effects = obj["body"] as JSONObject

                val buf_side_effects = side_effects["items"] as JSONArray
                val v_max_qty = buf_side_effects.getJSONObject(0)["MAX_QTY"] as String

                var textView_c=findViewById<TextView>(R.id.textView_c)
                runOnUiThread {
                    textView_c.text = v_max_qty
                }
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

}