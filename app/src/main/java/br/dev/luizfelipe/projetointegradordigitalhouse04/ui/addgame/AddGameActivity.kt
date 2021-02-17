package br.dev.luizfelipe.projetointegradordigitalhouse04.ui.addgame

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.dev.luizfelipe.projetointegradordigitalhouse04.R
import br.dev.luizfelipe.projetointegradordigitalhouse04.databinding.ActivityAddGameBinding
import com.bumptech.glide.Glide
import com.fasterxml.uuid.Generators
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class AddGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddGameBinding
    private val PIC_ID = 201
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var photo: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGameBinding.inflate(layoutInflater)

        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage

        binding.gameImage.setOnClickListener {
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), PIC_ID)
        }

        binding.save.setOnClickListener {
            val game: MutableMap<String, Any> = HashMap()
            val storageRef = storage.reference
            val stringPath = "images/${Generators.timeBasedGenerator().generate()}"
            val mountainsRef = storageRef.child(stringPath)

            game["name"] = binding.name.editText!!.text.toString()
            game["created_at"] = binding.createdAt.editText!!.text.toString().toInt()
            game["description"] = binding.description.editText!!.text.toString()
            game["url"] = stringPath

            val baos = ByteArrayOutputStream()
            photo.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val data = baos.toByteArray()

            val uploadTask = mountainsRef.putBytes(data)

            uploadTask.addOnCompleteListener{ task ->
                Log.d("task", "${task.result}")
                if (task.isSuccessful){
                    db.collection("games")
                        .add(game)
                        .addOnSuccessListener {
                            Snackbar.make(binding.root, "Sucesso", Snackbar.LENGTH_LONG).show()
                        }
                        .addOnFailureListener {
                            Snackbar.make(binding.root, "Error", Snackbar.LENGTH_LONG).show()
                            Log.d("FirebaseError", it.message.toString())
                        }
                } else {
                    Snackbar.make(binding.root, "Error to upload image", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

            finish()
        }

        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when(requestCode){
            PIC_ID -> {
                if (data != null) {
                    photo = data.extras?.get("data") as Bitmap
                    Glide.with(this)
                        .load(photo)
                        .circleCrop()
                        .into(binding.gameImage)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}