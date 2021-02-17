package br.dev.luizfelipe.projetointegradordigitalhouse04.ui.splash

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.dev.luizfelipe.projetointegradordigitalhouse04.R
import br.dev.luizfelipe.projetointegradordigitalhouse04.ui.home.HomeActivity
import br.dev.luizfelipe.projetointegradordigitalhouse04.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)
    }
}