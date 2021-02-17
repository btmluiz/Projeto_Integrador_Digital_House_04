package br.dev.luizfelipe.projetointegradordigitalhouse04.ui.login

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import br.dev.luizfelipe.projetointegradordigitalhouse04.R
import br.dev.luizfelipe.projetointegradordigitalhouse04.databinding.ActivityLoginBinding
import br.dev.luizfelipe.projetointegradordigitalhouse04.ui.home.HomeActivity
import br.dev.luizfelipe.projetointegradordigitalhouse04.ui.signup.SignupActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        binding.login.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.signup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        setContentView(binding.root)
    }
}