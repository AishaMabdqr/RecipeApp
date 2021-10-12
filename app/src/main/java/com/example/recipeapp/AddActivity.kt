package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {

    lateinit var eTitle : EditText
    lateinit var eAuthor : EditText
    lateinit var eIngred : EditText
    lateinit var eInstruc : EditText
    lateinit var bAdd : Button
    lateinit var bView : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        eTitle = findViewById(R.id.eTitle)
        eAuthor = findViewById(R.id.eAuthor)
        eIngred = findViewById(R.id.eIngred)
        eInstruc = findViewById(R.id.eInstruc)
        bAdd = findViewById(R.id.bAdd)
        bView = findViewById(R.id.bView)

        bAdd.setOnClickListener {
             addRecipe()
        }

        bView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun addRecipe(){

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.addRecipe(AddRecipe(eTitle.text.toString(), eAuthor.text.toString(), eIngred.text.toString(), eInstruc.text.toString()))?.enqueue(object:
            Callback<AddRecipe> {
            override fun onResponse(call: Call<AddRecipe>, response: Response<AddRecipe>) {
                Toast.makeText(this@AddActivity,"Recipe has been Added", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<AddRecipe>, t: Throwable) {
                Toast.makeText(this@AddActivity,"Something went wrong", Toast.LENGTH_LONG).show()
            }

        })


    }
}