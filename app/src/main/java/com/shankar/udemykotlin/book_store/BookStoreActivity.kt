package com.shankar.udemykotlin.book_store

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.book_store.ProfileActivity.Companion.FIRST_NAME
import com.shankar.udemykotlin.book_store.ProfileActivity.Companion.LAST_NAME
import com.shankar.udemykotlin.book_store.ProfileActivity.Companion.SHARED_PREF
import com.shankar.udemykotlin.databinding.ActivityBookStoreBinding

class BookStoreActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBookStoreBinding
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)


        drawerLayout = binding.drawerLayout
        setSupportActionBar(binding.toolbar)
        val actionBar: ActionBar? = supportActionBar

        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        val navigationView : NavigationView = binding.navigationView

        navigationView.setNavigationItemSelectedListener {
            menuItem ->
            when (menuItem.itemId) {
                R.id.profile_menu -> {
                    val intent = Intent(this@BookStoreActivity, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.about_menu -> {
                    val intent = Intent(this@BookStoreActivity, AboutActivity::class.java)
                    startActivity(intent)
                }
            }

            drawerLayout.closeDrawers()
            true
        }
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        val pagerAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
             android.R.id.home -> {
                 val sharedPreferences = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
                 val firstName = sharedPreferences.getString(FIRST_NAME, "first name")
                 val lastName = sharedPreferences.getString(LAST_NAME, "last name")

                 drawerLayout.findViewById<TextView>(R.id.full_name).text ="$firstName $lastName"
                 drawerLayout.openDrawer(GravityCompat.START)
                 true
             }
            else -> return super.onOptionsItemSelected(item)
        }

    }
}