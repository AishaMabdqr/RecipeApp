package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var clMain : ConstraintLayout
    lateinit var rvItems :  RecyclerView
    lateinit var itemList : ArrayList<RecipeDetails.Details>
    lateinit var rvAdapter : RVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clMain = findViewById(R.id.clMAin)
        rvItems = findViewById(R.id.rvItems)
        itemList = ArrayList()

        rvAdapter = RVAdapter(itemList)
        rvItems.adapter =rvAdapter
        rvItems.layoutManager = LinearLayoutManager(this)

        getRecipe()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mAdd -> {
                val intent = Intent(this, AddActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.mView -> {
                getRecipe()
                rvItems.scrollToPosition(itemList.size-1)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getRecipe(){

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getRecipe()?.enqueue(object : Callback<List<RecipeDetails.Details>> {
            override fun onResponse(
                call: Call<List<RecipeDetails.Details>>,
                response: Response<List<RecipeDetails.Details>>
            ) {
                var result: String? = ""
                var result2: String? = ""
                for (i in response.body()!!) {
//                    result = i.title +" \n"+ i.author+" \n"+ i.ingredients +" \n"+ i.instructions
//                    itemList.add(result!!)
                    var listTitle= i.title.toString()
                    var listAuthor= i.author.toString()
                    var listIngred= i.ingredients.toString()
                    var listInstruc= i.instructions.toString()
                    itemList.add(RecipeDetails.Details(listTitle,listAuthor,listIngred,listInstruc))

                }
                rvAdapter.notifyDataSetChanged()

            }
            override fun onFailure(call: Call<List<RecipeDetails.Details>>, t: Throwable) {
                Log.d("Main", "Unable to get data")
            }
        })
    }
}