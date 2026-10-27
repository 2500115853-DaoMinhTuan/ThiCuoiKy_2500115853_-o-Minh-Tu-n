package vn.edu.vlsc.thicuoiky_2500115853

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Ánh xạ các View hoàn toàn bằng findViewById
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        // 2. Dán sẵn thông tin đăng nhập vào 2 ô
        etEmail.setText("admin@gmail.com")
        etPassword.setText("123456")

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        fun showKeyboard(view: EditText) {
            view.requestFocus()
            view.postDelayed({
                imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
            }, 100)
        }

        etEmail.setOnClickListener { showKeyboard(etEmail) }
        etPassword.setOnClickListener { showKeyboard(etPassword) }

        // 3. Xử lý nút ĐĂNG NHẬP
        btnLogin.setOnClickListener {
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            when {
                email.isEmpty() -> {
                    tvStatus.text = "Lỗi: Vui lòng nhập email!"
                    tvStatus.setTextColor(Color.RED)
                    showKeyboard(etEmail)
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    tvStatus.text = "Lỗi: Định dạng email không hợp lệ!"
                    tvStatus.setTextColor(Color.RED)
                    showKeyboard(etEmail)
                }
                password.isEmpty() -> {
                    tvStatus.text = "Lỗi: Vui lòng nhập mật khẩu!"
                    tvStatus.setTextColor(Color.RED)
                    showKeyboard(etPassword)
                }
                password.length < 6 -> {
                    tvStatus.text = "Lỗi: Mật khẩu phải có ít nhất 6 ký tự!"
                    tvStatus.setTextColor(Color.RED)
                    showKeyboard(etPassword)
                }
                else -> {
                    if (email == "admin@gmail.com" && password == "123456") {
                        tvStatus.text = "Đăng nhập thành công!"
                        tvStatus.setTextColor(Color.parseColor("#2E7D32"))

                        // Chuyển sang màn hình ProfileActivity và gửi kèm Email qua
                        val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
                        intent.putExtra("USER_EMAIL", email)
                        startActivity(intent)
                    } else {
                        tvStatus.text = "Đăng nhập thất bại: Sai email hoặc mật khẩu!"
                        tvStatus.setTextColor(Color.RED)
                    }
                }
            }
        }
    }
}
