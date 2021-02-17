package br.dev.luizfelipe.projetointegradordigitalhouse04.ui.viewgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.dev.luizfelipe.projetointegradordigitalhouse04.R
import br.dev.luizfelipe.projetointegradordigitalhouse04.databinding.ActivityViewGameBinding
import br.dev.luizfelipe.projetointegradordigitalhouse04.models.Game
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage

class ViewGameActivity : AppCompatActivity() {

    private lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewGameBinding.inflate(layoutInflater)

        storage = FirebaseStorage.getInstance()

        val game = intent.getSerializableExtra("game") as Game
        val ref = storage.reference

        binding.nome.text = game.title
        binding.nome2.text = game.title
        binding.lancamentoView.text = getString(R.string.lacado_at, game.year.toString())
        binding.descriptionView.text = game.description

        ref.child(game.img).downloadUrl
            .addOnSuccessListener {
                Glide.with(this).load(it).fitCenter().into(binding.cover)
            }
            .addOnFailureListener {
                Log.d("Error", "URI Traceback: ", it)
            }

        binding.backArrow.setOnClickListener {
            finish()
        }
        setContentView(binding.root)
    }
}