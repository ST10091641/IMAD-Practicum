package com.example.moviereviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.finalpracticum.R

class MainActivity : AppCompatActivity() {

    // Parallel arrays for movie data
    private val movieTitles = arrayOf(
        "The Godfather",
        "The Dark Knight",
        "Pulp Fiction",
        "Inception",
        "The Matrix"
    )

    private val movieDirectors = arrayOf(
        "Francis Ford Coppola",
        "Christopher Nolan",
        "Quentin Tarantino",
        "Christopher Nolan",
        "The Wachowskis"
    )

    private val movieRatings = arrayOf(5, 5, 4, 4, 5)

    private val movieComments = arrayOf(
        "A masterpiece of cinema.",
        "Best superhero movie ever made.",
        "Quirky and captivating.",
        "Mind-bending thriller.",
        "Revolutionary sci-fi action."
    )

    private lateinit var averageRatingText: TextView
    private lateinit var addMovieButton: Button
    private lateinit var viewReviewsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupClickListeners()
        calculateAndDisplayAverageRating()
    }

    private fun initializeViews() {
        averageRatingText = findViewById(R.id.averageRatingText)
        addMovieButton = findViewById(R.id.addMovieButton)
        viewReviewsButton = findViewById(R.id.viewReviewsButton)
    }

    private fun setupClickListeners() {
        addMovieButton.setOnClickListener {
            // Show input dialog to add new movie
            showAddMovieDialog()
        }

        viewReviewsButton.setOnClickListener {
            // Navigate to detail screen
            val intent = Intent(this, MainActivity::class.java)

            // Pass data to detail activity
            intent.putExtra("titles", movieTitles)
            intent.putExtra("directors", movieDirectors)
            intent.putExtra("ratings", movieRatings)
            intent.putExtra("comments", movieComments)

            startActivity(intent)
        }
    }

    private fun calculateAndDisplayAverageRating() {
        // Use loop to calculate average rating
        var totalRating = 0.0

        for (rating in movieRatings) {
            totalRating += rating
        }

        val averageRating = totalRating / movieRatings.size
        averageRatingText.text = "Average Rating: ${"%.2f".format(averageRating)}/5"
    }

    private fun showAddMovieDialog() {
        // For simplicity, we'll add a predefined movie
        // In a real app, you would show a dialog or new activity for input

        // Simulate adding a new movie
        val newTitle = "New Movie ${movieTitles.size + 1}"
        val newDirector = "New Director"
        val newRating = 4
        val newComment = "Recently added movie."

        // In a real app, you would add to arrays and update
        Toast.makeText(this,
            "Add Movie feature would open input form",
            Toast.LENGTH_SHORT).show()

        // Note: For persistent storage, you'd need to use SharedPreferences,
        // Room Database, or other storage solutions
    }
}