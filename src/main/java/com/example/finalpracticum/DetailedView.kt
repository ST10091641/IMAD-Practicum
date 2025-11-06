package com.example.moviereviewer

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.finalpracticum.R

class DetailActivity : AppCompatActivity() {

    private lateinit var moviesContainer: LinearLayout
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)

        initializeViews()
        setupClickListeners()
        displayMovieDetails()
    }

    private fun initializeViews() {
        moviesContainer = findViewById(R.id.moviesContainer)
        backButton = findViewById(R.id.backButton)
    }

    private fun setupClickListeners() {
        backButton.setOnClickListener {
            // Navigate back to main screen
            finish()
        }
    }

    private fun displayMovieDetails() {
        // Get data from intent
        val titles = intent.getStringArrayExtra("titles") ?: return
        val directors = intent.getStringArrayExtra("directors") ?: return
        val ratings = intent.getIntArrayExtra("ratings") ?: return
        val comments = intent.getStringArrayExtra("comments") ?: return

        // Use loop to display all movie details
        for (i in titles.indices) {
            val movieView = createMovieItemView(
                titles[i],
                directors[i],
                ratings[i],
                comments[i]
            )
            moviesContainer.addView(movieView)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun createMovieItemView(
        title: String,
        director: String,
        rating: Int,
        comment: String
    ): LinearLayout {

        val movieLayout = LinearLayout(this)
        movieLayout.orientation = LinearLayout.VERTICAL
        movieLayout.setPadding(0, 16, 0, 16)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 0, 0, 16)
        movieLayout.layoutParams = layoutParams
        movieLayout.background = ContextCompat.getDrawable(this, R.drawable.movie_item_bg)

        // Title
        val titleView = TextView(this)
        titleView.text = "Title: $title"
        titleView.textSize = 18f
        titleView.setTypeface(null, android.graphics.Typeface.BOLD)
        movieLayout.addView(titleView)

        // Director
        val directorView = TextView(this)
        directorView.text = "Director: $director"
        directorView.textSize = 16f
        movieLayout.addView(directorView)

        // Rating
        val ratingView = TextView(this)
        ratingView.text = "Rating: $rating/5"
        ratingView.textSize = 16f
        movieLayout.addView(ratingView)

        // Comment
        val commentView = TextView(this)
        commentView.text = "Comment: $comment"
        commentView.textSize = 14f
        commentView.setPadding(0, 8, 0, 0)
        movieLayout.addView(commentView)

        return movieLayout
    }
}