package com.example.mypetproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mypetproject.api.Doc
import com.example.mypetproject.databinding.ActivityMainBinding
import com.example.mypetproject.screens.favorite.FavoriteFragment
import com.example.mypetproject.screens.home.HomeFragment
import com.example.mypetproject.screens.details.MovieDetailsFragment
import com.example.mypetproject.screens.profile.ProfileFragment
import com.example.mypetproject.screens.search.SearchFragment
import com.example.mypetproject.screens.setting.SettingsFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            startFragment(HomeFragment())
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> { startFragment(HomeFragment())
                    true
                }
                R.id.search -> { startFragment(SearchFragment())
                    true
                }
                R.id.favorite -> { startFragment(FavoriteFragment())
                    true
                }
                R.id.profile -> { startFragment(ProfileFragment())
                    true
                }
                R.id.setting -> { startFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun startFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(androidx.appcompat.R.anim.abc_popup_enter, androidx.appcompat.R.anim.abc_popup_exit)
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showDetails(movie: Doc) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(androidx.appcompat.R.anim.abc_popup_enter, androidx.appcompat.R.anim.abc_popup_exit)
            .replace(R.id.fragment_container, MovieDetailsFragment.newInstance(movie))
            .addToBackStack(null)
            .commit()
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun toast(messageRes: Int) {
        Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
    }
}