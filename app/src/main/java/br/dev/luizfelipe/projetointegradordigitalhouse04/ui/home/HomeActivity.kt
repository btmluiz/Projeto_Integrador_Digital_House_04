package br.dev.luizfelipe.projetointegradordigitalhouse04.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import br.dev.luizfelipe.projetointegradordigitalhouse04.adapters.GameAdpter
import br.dev.luizfelipe.projetointegradordigitalhouse04.databinding.ActivityHomeBinding
import br.dev.luizfelipe.projetointegradordigitalhouse04.models.Game

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)

        val layoutManager = GridLayoutManager(this, 2)
        val adapter = GameAdpter(this, getGames())
        binding.list.layoutManager = layoutManager
        binding.list.adapter = adapter
        setContentView(binding.root)
    }

    fun getGames(): MutableList<Game>{
        return mutableListOf(
            Game(
                "a",
                "Teste 1",
                2120,
                "adfghjjalgh"
            ),Game(
                "a",
                "Teste 2",
                2123,
                "adfghjjalgh"
            ),Game(
                "a",
                "Teste 3",
                2125,
                "adfghjjalgh"
            ),Game(
                "a",
                "Teste 4",
                2122,
                "adfghjjalgh"
            ),Game(
                "a",
                "Teste 5",
                2121,
                "adfghjjalgh"
            )
        )
    }
}