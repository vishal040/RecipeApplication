package com.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.task.data.dto.recipes.Recipes
import com.task.data.dto.recipes.RecipesItem
import com.task.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import com.task.data.remote.moshiFactories.MyStandardJsonAdapters
import java.io.File
import java.lang.reflect.Type

class TestModelsGenerator {
    private var recipes: Recipes = Recipes(arrayListOf())

    init {
        val moshi = Moshi.Builder()
                .add(MyKotlinJsonAdapterFactory())
                .add(MyStandardJsonAdapters.FACTORY)
                .build()
        val type: Type = Types.newParameterizedType(List::class.java, RecipesItem::class.java)
        val adapter: JsonAdapter<List<RecipesItem>> = moshi.adapter(type)
        val jsonString = jsonResponse //getJson("resources/RecipesApiResponse.json")
        adapter.fromJson(jsonString)?.let {
            recipes = Recipes(ArrayList(it))
        }
        print("this is $recipes")
    }

    fun generateRecipes(): Recipes {
        return recipes
    }

    fun generateRecipesModelWithEmptyList(): Recipes {
        return Recipes(arrayListOf())
    }

    fun generateRecipesItemModel(): RecipesItem {
        return recipes.recipesList[0]
    }

    fun getStubSearchTitle(): String {
        return recipes.recipesList[0].name
    }


    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }

    companion object {
        const val jsonResponse = "[\n" +
                "    {\n" +
                "        \"calories\": \"516 kcal\",\n" +
                "        \"carbos\": \"47 g\",\n" +
                "        \"description\": \"There\\u2019s nothing like the simple things in life - the smell of freshly cut grass, sitting outside on a nice sunny day, spending time with friends and family. Well here is a recipe that delivers simple culinary pleasures - some nice fresh fish with a crispy crust, crunchy potato wedges and some delightfully sweet sugar snap peas flavoured with cooling mint. Slip into something comfortable and relax into a delicious dinner!\",\n" +
                "        \"difficulty\": 0,\n" +
                "        \"fats\": \"8 g\",\n" +
                "        \"headline\": \"with Sweet Potato Wedges and Minted Snap Peas\",\n" +
                "        \"id\": \"533143aaff604d567f8b4571\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/533143aaff604d567f8b4571.jpg\",\n" +
                "        \"name\": \"Crispy Fish Goujons \",\n" +
                "        \"proteins\": \"43 g\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/533143aaff604d567f8b4571.jpg\",\n" +
                "        \"time\": \"PT35M\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"calories\": \"397 kcal\",\n" +
                "        \"carbos\": \"26 g\",\n" +
                "        \"description\": \"Close your eyes, open up your Ras El Hanout and inhale deeply. You are no longer standing in your kitchen. Around you are the sounds of a bustling market. Robed men sell ornate carpets and a camel nibbles affectionately at your ear.  OK, we\\u2019re pretty sure Paul McKenna\\u2019s job is safe for now, but get cooking this recipe and take dinnertime on a magic carpet ride to Casablanca! Our tip for this recipe is to take your meat out of the fridge at least 30 minutes before dinner which will allow you to cook it more evenly.\",\n" +
                "        \"difficulty\": 0,\n" +
                "        \"fats\": \"5 g\",\n" +
                "        \"headline\": \"with Spinach and Lemon Couscous\",\n" +
                "        \"id\": \"53314247ff604d44808b4569\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/53314247ff604d44808b4569.jpg\",\n" +
                "        \"name\": \"Moroccan Skirt Steak \",\n" +
                "        \"proteins\": \"31 g\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/53314247ff604d44808b4569.jpg\",\n" +
                "        \"time\": \"PT30M\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"calories\": \"458 kcal\",\n" +
                "        \"carbos\": \"29 g\",\n" +
                "        \"description\": \"World-renowned people generally all have one thing in common: a legacy. For Henry Ford it was the motorcar, for Thomas Edison it was the lightbulb. For our intern Simon, it was this lip-smackingly awesome Sea Bream. Taking the warm crunchiness of potatoes against the fresh southern asian flavours of fish with coriander, ginger and lime, it\\u2019s the perfect dish for transporting your tastebuds. Don\\u2019t let the smell of the fish sauce throw you - add it gradually to your sauce for a really authentic asian spin!\",\n" +
                "        \"difficulty\": 1,\n" +
                "        \"fats\": \"6 g\",\n" +
                "        \"headline\": \"with Tomato Concasse and Crispy Potatoes\",\n" +
                "        \"id\": \"53314276ff604d28828b456b\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/53314276ff604d28828b456b.jpg\",\n" +
                "        \"name\": \"Simple Sumptuous Sea Bream\",\n" +
                "        \"proteins\": \"29 g\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/53314276ff604d28828b456b.jpg\",\n" +
                "        \"time\": \"PT35M\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"calories\": \"717 kcal\",\n" +
                "        \"carbos\": \"44 g\",\n" +
                "        \"description\": \"Nostalgia is a powerful thing. For some it\\u2019s triggered by the smell of freshly cut grass, for others by the sound of a love song from their heady teenage years. For Head Chef Patrick it\\u2019s all about Swiss Roll. A firm dinnertime favourite from his primary school years, we still see him go all misty eyed at the mere mention of it. We\\u2019re pretty sure that was the inspiration behind this little number. Tonight, prepare to create a little nostalgia that the little \\u2018uns will remember for years!\",\n" +
                "        \"difficulty\": 2,\n" +
                "        \"fats\": \"10 g\",\n" +
                "        \"headline\": \"with Roasted Rocket Potatoes\",\n" +
                "        \"id\": \"533143bfff604d44808b456a\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/533143bfff604d44808b456a.jpg\",\n" +
                "        \"name\": \"Mozzarella and Pesto Roulades\",\n" +
                "        \"proteins\": \"67 g\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/533143bfff604d44808b456a.jpg\",\n" +
                "        \"time\": \"PT35M\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"calories\": \"751 kcal\",\n" +
                "        \"carbos\": \"105 g\",\n" +
                "        \"description\": \"Head Chef Patrick doesn\\u2019t like fuss. He\\u2019s always telling us that the best kind of food is simple, soulful grub that makes you feel loved. That said, every dinner is a chance to practice your presentation skills. Bigger plates are a great way of framing your food and a sprinkle of herbs or a drizzle of olive oil at the end gives everything a bit more pizzazz. For this recipe, we took classic Mexican ingredients and played with the presentation to create something that\\u2019s as tasty to the eye as it is to the tongue. Arriba!\",\n" +
                "        \"difficulty\": 1,\n" +
                "        \"fats\": \"4 g\",\n" +
                "        \"headline\": \"with Homemade Guacamole and Black Bean Salsa\",\n" +
                "        \"id\": \"5331430fff604d557f8b456d\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/5331430fff604d557f8b456d.jpg\",\n" +
                "        \"name\": \"Mexican Tortilla Stack\",\n" +
                "        \"proteins\": \"35 g\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/5331430fff604d557f8b456d.jpg\",\n" +
                "        \"time\": \"PT35M\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"calories\": \"689 kcal\",\n" +
                "        \"carbos\": \"84 g\",\n" +
                "        \"description\": \"We\\u2019ve all heard that much overused culinary phrase \\u201cfusion food\\u201d, but this recipe is fusion of a slightly different kind. With the onset of Winter Patrick has taken some decidedly seasonal squash and the deep, heady scent of rosemary and combined them with the lighter flavour of feta and wild rice. The squash takes a little bit of work with a vegetable peeler but once you\\u2019ve tackled and roasted it you\\u2019ll never look back. We use any leftovers for butternut squash soup!\",\n" +
                "        \"difficulty\": 0,\n" +
                "        \"fats\": \"8 g\",\n" +
                "        \"headline\": \"with Wild Rice, Feta and Pine Nuts\",\n" +
                "        \"id\": \"53314328ff604d4d808b456b\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/53314328ff604d4d808b456b.jpg\",\n" +
                "        \"name\": \"Roasted Rosemary Butternut Squash \",\n" +
                "        \"proteins\": \"23 g\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/53314328ff604d4d808b456b.jpg\",\n" +
                "        \"time\": \"PT40M\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"calories\": \"\",\n" +
                "        \"carbos\": \"\",\n" +
                "        \"description\": \"\\u2018Allo Genovese\\u2019 simply means \\u2018in the style of Genoa\\u2019, which is the northern Italian city famous for the pesto that you\\u2019ll be making tonight. \\u2018Pesto\\u2019 actually comes from the word \\u2018pestare\\u2019, which means to pound or crush, referring to the old fashioned method of making it in a pestle & mortar. If you happen to have a food processor, you can whizz the pesto together in that, or alternatively just chop, chop, chop everything until it is tiny. Andiamo! \",\n" +
                "        \"difficulty\": 0,\n" +
                "        \"fats\": \"\",\n" +
                "        \"headline\": \"with Toasted Pine Nuts and Tenderstem Broccoli\",\n" +
                "        \"id\": \"53314343ff604d28828b456c\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/53314343ff604d28828b456c.jpg\",\n" +
                "        \"name\": \"Gnocchi Allo Genovese\",\n" +
                "        \"proteins\": \"\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/53314343ff604d28828b456c.jpg\",\n" +
                "        \"time\": \"PT25M\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"calories\": \"790 kcal\",\n" +
                "        \"carbos\": \"100 g\",\n" +
                "        \"description\": \"\\u201cPolpetti?!\\u201d we hear you say. That\\u2019s meatballs to you and me. But meatballs so good you want \\r\\n\\r\\nto parade them down the street waving your arms aloft like a passionate Italian grandmother. \\r\\n\\r\\nThese little rascals are perfect for your little rascals (both big and small!) as you can get \\r\\n\\r\\neveryone involved in rolling them up. Once served, everyone around the table must choose an \\r\\n\\r\\nItalian name and act Italian for the rest of dinner time. Andiamo!*\",\n" +
                "        \"difficulty\": 3,\n" +
                "        \"fats\": \"9 g\",\n" +
                "        \"headline\": \"with Creamy Parmesan Polenta\",\n" +
                "        \"id\": \"53314398ff604d6c7a8b456a\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/53314398ff604d6c7a8b456a.jpg\",\n" +
                "        \"name\": \"Herbed Italian Polpetti\",\n" +
                "        \"proteins\": \"39 g\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/53314398ff604d6c7a8b456a.jpg\",\n" +
                "        \"time\": \"PT35M\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"calories\": \"556 kcal\",\n" +
                "        \"carbos\": \"62 g\",\n" +
                "        \"description\": \"Patrick has been working on a theory that the fewer utensils you use to eat a meal, the tastier it\\u2019s likely to be. Think about it - everything you eat with only a fork is usually delicious. Dispense with cutlery entirely to use your fingers and suddenly you\\u2019re in taste bud paradise. That was the thinking behind this Japanese favourite. A surefire winner in the eateries of Tokyo, pick it up with your fingers and get stuck in! The first person to finish has to shout \\u201cBanzaaaiiii\\u201d!\",\n" +
                "        \"difficulty\": 2,\n" +
                "        \"fats\": \"4 g\",\n" +
                "        \"headline\": \"and Sweet and Sour Noodles\",\n" +
                "        \"id\": \"5252b20c301bbf46038b477e\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/5252b20c301bbf46038b477e.jpg\",\n" +
                "        \"name\": \"Genki Yakitori with Crispy Red Onions\",\n" +
                "        \"proteins\": \"32 g\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/5252b20c301bbf46038b477e.jpg\",\n" +
                "        \"time\": \"PT40M\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"calories\": \"751 kcal\",\n" +
                "        \"carbos\": \"105 g\",\n" +
                "        \"country\": \"GB\",\n" +
                "        \"description\": \"Head Chef Patrick doesn\\u2019t like fuss. He\\u2019s always telling us that the best kind of food is simple, soulful grub that makes you feel loved. That said, every dinner is a chance to practice your presentation skills. Bigger plates are a great way of framing your food and a sprinkle of herbs or a drizzle of olive oil at the end gives everything a bit more pizzazz. For this recipe, we took classic Mexican ingredients and played with the presentation to create something that\\u2019s as tasty to the eye as it is to the tongue. Arriba!\",\n" +
                "        \"difficulty\": 0,\n" +
                "        \"fats\": \"4 g\",\n" +
                "        \"headline\": \"with Cucumber Salad\",\n" +
                "        \"id\": \"5331430fff604d557f8b456c\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/sausage-risotto-wk41-a201d2fa.jpg\",\n" +
                "        \"name\": \"Sausage Risotto\",\n" +
                "        \"proteins\": \"35 g\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/sausage-risotto-wk41-a201d2fa.jpg\",\n" +
                "        \"time\": \"PT35M\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"calories\": \"689 kcal\",\n" +
                "        \"carbos\": \"84 g\",\n" +
                "        \"description\": \"We\\u2019ve all heard that much overused culinary phrase \\u201cfusion food\\u201d, but this recipe is fusion of a slightly different kind. With the onset of Winter Patrick has taken some decidedly seasonal squash and the deep, heady scent of rosemary and combined them with the lighter flavour of feta and wild rice. The squash takes a little bit of work with a vegetable peeler but once you\\u2019ve tackled and roasted it you\\u2019ll never look back. We use any leftovers for butternut squash soup!\",\n" +
                "        \"difficulty\": 0,\n" +
                "        \"fats\": \"8 g\",\n" +
                "        \"headline\": \"with Peppercorn Sauce (F)\",\n" +
                "        \"id\": \"53314328ff604d4d128b456b\",\n" +
                "        \"image\": \"https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/54511c31ff604dee7b8b4571.jpg\",\n" +
                "        \"name\": \"Bacon Wrapped Pork Loin \",\n" +
                "        \"proteins\": \"23 g\",\n" +
                "        \"thumb\": \"https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/54511c31ff604dee7b8b4571.jpg\",\n" +
                "        \"time\": \"PT40M\"\n" +
                "    }\n" +
                "]"
    }
}



