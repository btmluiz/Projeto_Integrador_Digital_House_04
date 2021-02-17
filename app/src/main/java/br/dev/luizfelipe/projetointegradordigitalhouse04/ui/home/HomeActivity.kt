package br.dev.luizfelipe.projetointegradordigitalhouse04.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.dev.luizfelipe.projetointegradordigitalhouse04.R
import br.dev.luizfelipe.projetointegradordigitalhouse04.adapters.GameAdapter
import br.dev.luizfelipe.projetointegradordigitalhouse04.databinding.ActivityHomeBinding
import br.dev.luizfelipe.projetointegradordigitalhouse04.interfaces.GameClickListener
import br.dev.luizfelipe.projetointegradordigitalhouse04.models.Game
import br.dev.luizfelipe.projetointegradordigitalhouse04.ui.addgame.AddGameActivity
import br.dev.luizfelipe.projetointegradordigitalhouse04.ui.viewgame.ViewGameActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class HomeActivity : AppCompatActivity(), GameClickListener {

    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private var gameList: MutableList<Game> = mutableListOf()

    val viewModel: HomeViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(db, storage) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage

        viewModel.popListGame()
        viewModel.autoUpdate()

        val list: RecyclerView = findViewById(R.id.list)
        val addButton: FloatingActionButton = findViewById(R.id.add_button)
        val layoutManager = GridLayoutManager(this, 2)
        val adapter = GameAdapter(this, gameList, storage)

        viewModel.listGames.observe(this) {
            if (it != null) {
                gameList = it
            }
            Log.d("Firebase result main", "$it")
            adapter.replaceList(gameList)
        }
        list.layoutManager = layoutManager
        list.adapter = adapter

        // Floating
        addButton.setOnClickListener {
            startActivity(Intent(this, AddGameActivity::class.java))
        }


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

    override fun onGameClick(game: Game) {
        val intent = Intent(this, ViewGameActivity::class.java)
        intent.putExtra("game", game)
        startActivity(intent)
    }
}