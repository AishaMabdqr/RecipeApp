package com.example.recipeapp

class RecipeDetails {

    val items : List<Details>? = null

    class Details{

        var title: String? = null

        var author: String? = null

        var ingredients: String? = null

        var instructions: String? = null

        constructor(title: String?,author: String?, ingredients: String?,instructions:String? ){
            this.title = title
            this .author = author
            this.ingredients = ingredients
            this.instructions = instructions
        }
    }
}