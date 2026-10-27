package vn.edu.vlsc.thicuoiky_2500115853

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // 1. Ánh xạ toàn bộ View bằng findViewById
        val tvProfileTitle = findViewById<TextView>(R.id.tvProfileTitle)
        val tvUserEmail = findViewById<TextView>(R.id.tvUserEmail)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // 2. Nhận dữ liệu Email truyền từ LoginActivity qua Intent
        val email = intent.getStringExtra("USER_EMAIL") ?: "admin@gmail.com"
        tvUserEmail.text = "Email: $email"

        // 3. Xử lý nút ĐĂNG XUẤT bằng findViewById
        btnLogout.setOnClickListener {
            // Đóng màn hình Profile để quay lại màn hình Login
            finish()
        }
    }
}
