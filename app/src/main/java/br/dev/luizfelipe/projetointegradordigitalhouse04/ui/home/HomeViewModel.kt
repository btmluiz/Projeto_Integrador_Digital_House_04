package br.dev.luizfelipe.projetointegradordigitalhouse04.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.dev.luizfelipe.projetointegradordigitalhouse04.models.Game
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class HomeViewModel(val db: FirebaseFirestore, val storage: FirebaseStorage) : ViewModel() {
    val listGames = MutableLiveData<MutableList<Game>>()

    fun popListGame(){
        val aux = mutableListOf<Game>()
        db.collection("games")
            .get()
            .addOnSuccessListener {
                for (doc in it){
                    val game = Game(
                        doc.data["url"].toString(),
                        doc.data["name"].toString(),
                        doc.data["created_at"].toString().toInt(),
                        doc.data["description"].toString()
                    )
                    aux.add(game)
                }
            }
            .addOnFailureListener {
                Log.d("Firebase error", "traceback: ", it)
            }
            .addOnCompleteListener {
                if (it.isSuccessful)
                    listGames.value = aux
            }
    }

    fun autoUpdate(){
        db.collection("games")
            .addSnapshotListener { snapshot, e ->
                if (e !=null)
                    return@addSnapshotListener

                popListGame()
            }
    }
}