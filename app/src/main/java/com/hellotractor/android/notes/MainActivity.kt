package com.hellotractor.android.notes

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hellotractor.android.notes.R
import com.hellotractor.app.features.farms.FarmsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check if this is the first time the activity is created
        if (savedInstanceState == null) {
            // Optionally load a default fragment
            // loadFragment(SomeOtherFragment())
        }

        val btnViewFarms = findViewById<Button>(R.id.btn_view_farms)
        btnViewFarms.setOnClickListener {
            loadFragment(FarmsFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // This allows user to press back to return
            .commit()
    }
}
