package com.bindlish.chatbot.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.bindlish.chatbot.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.google.android.material.navigation.NavigationView

/**
 * Created by Aman Bindlish on 17,September,2019
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // for dependency injection
        AndroidInjection.inject(this)

        // implement drawer with hamburger
        setUpNavigationToggle()

        // check if fragment available in fragmentManager
        var chatFragment =
            supportFragmentManager.findFragmentByTag(ChatFragment.TAG)
        // if fragment is null then create instance and add
        if (chatFragment == null) {
            // by default initial user is aman
            chatFragment = ChatFragment.getInstance("Aman")
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_container, chatFragment, ChatFragment.TAG).commit()
        }
    }


    private fun setUpNavigationToggle() {
        toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.syncState()
        toggle.setToolbarNavigationClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        drawer_layout.addDrawerListener(toggle)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigation_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.user_aman,
            R.id.user_akshay,
            R.id.user_salman -> replaceFragment(menuItem.title.toString())
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    // common method to replace fragments on click from navigation drawer
    private fun replaceFragment(user: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_container, ChatFragment.getInstance(user), ChatFragment.TAG)
            .commit()
    }

    // on click of back button, close drawer if open
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // for handling direct click on hamburger
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
