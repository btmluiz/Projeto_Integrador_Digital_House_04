package br.dev.luizfelipe.projetointegradordigitalhouse04.interfaces

import android.view.View
import br.dev.luizfelipe.projetointegradordigitalhouse04.models.Game

interface GameClickListener {
    fun onGameClick(game: Game)
}