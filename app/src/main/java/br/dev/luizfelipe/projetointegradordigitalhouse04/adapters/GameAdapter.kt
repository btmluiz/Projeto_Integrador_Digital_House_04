package br.dev.luizfelipe.projetointegradordigitalhouse04.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.dev.luizfelipe.projetointegradordigitalhouse04.R
import br.dev.luizfelipe.projetointegradordigitalhouse04.interfaces.GameClickListener
import br.dev.luizfelipe.projetointegradordigitalhouse04.models.Game
import br.dev.luizfelipe.projetointegradordigitalhouse04.ui.viewgame.ViewGameActivity
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage

class GameAdapter(val context: Context, var gameList: MutableList<Game>, val storage: FirebaseStorage): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_game, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val ref = storage.reference

        val title: TextView = holder.layout.findViewById(R.id.titulo)
        val year: TextView = holder.layout.findViewById(R.id.lancamento)
        val cover: ImageView = holder.layout.findViewById(R.id.game_cover)


        title.text = gameList[position].title
        year.text = gameList[position].year.toString()

        ref.child(gameList[position].img).downloadUrl
            .addOnSuccessListener {
                Glide.with(context).load(it).fitCenter().into(cover)
            }
            .addOnFailureListener {
                Log.d("Error", "URI Traceback: ", it)
            }

        holder.layout.setOnClickListener {
            val game = gameList[position]
            val intent = Intent(context, ViewGameActivity::class.java)
            intent.putExtra("game", game)
            context.startActivity(intent)
        }
    }

    fun replaceList(list: MutableList<Game>){
        gameList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = gameList.size
}