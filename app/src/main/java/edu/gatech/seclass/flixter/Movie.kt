package edu.gatech.seclass.flixter

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class Movie(
    val movieId: Int,
    private val posterPath: String,
    val title: String,
    val overview: String,
    val voteAverage: Double
): Parcelable{
    @IgnoredOnParcel
    val posterImageUrl= "https://image.tmdb.org/t/p/w342/$posterPath"
    companion object{
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies= mutableListOf<Movie>()
            for(i in 0 until movieJsonArray.length()){
                val movieJSON = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJSON.getInt("id"),
                        movieJSON.getString("poster_path"),
                        movieJSON.getString("title"),
                        movieJSON.getString("overview"),
                        movieJSON.getDouble("vote_average")
                    )
                )
            }
            return movies
        }
    }
}