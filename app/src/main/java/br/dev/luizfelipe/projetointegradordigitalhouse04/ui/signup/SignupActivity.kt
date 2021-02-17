package br.dev.luizfelipe.projetointegradordigitalhouse04.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.dev.luizfelipe.projetointegradordigitalhouse04.R
import br.dev.luizfelipe.projetointegradordigitalhouse04.databinding.ActivitySignupBinding
import br.dev.luizfelipe.projetointegradordigitalhouse04.ui.home.HomeActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySignupBinding.inflate(layoutInflater)

        binding.signup.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        setContentView(binding.root)
    }
}