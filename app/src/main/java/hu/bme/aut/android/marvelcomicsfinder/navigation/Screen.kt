package hu.bme.aut.android.marvelcomicsfinder.navigation

sealed class Screen(val route: String) {
    object MarvelComicsList: Screen("marvel-comics")
    object MarvelComicDetail: Screen("marvel-comics/{id}") {
        fun passId(id: String) = "marvel-comics/$id"
    }
    object FavMarvelComicsList: Screen("fav-marvel-comics")
    object FavMarvelComicDetail: Screen("fav-marvel-comics/{id}") {
        fun passId(id: String) = "fav-marvel-comics/$id"
    }
    object RandomFavMarvelComic: Screen("random-fav-marvel-comics")
}