package com.popular.broadcast.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.popular.broadcast.R
import com.popular.broadcast.data.session.SessionContext
import com.popular.broadcast.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var optionsMenu: Menu? = null
    private lateinit var navController: NavController

    @Inject
    lateinit var sessionContext: SessionContext

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.actionBar.actionBarToolBar)

        supportActionBar?.apply {

            setHomeButtonEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onStart() {

        super.onStart()

        initializeNavComponents()
    }

    private fun initializeNavComponents() {

        if(!::navController.isInitialized) {

            navController = findNavController(R.id.nav_host_fragment_activity_main)
            setupActionBarWithNavController(this, navController)
        }
    }

    fun showHideMenu(state: Boolean) {
        optionsMenu?.setGroupVisible(R.id.period_group, state)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_home, menu)

        when(sessionContext.getNewsFetchPeriod()) {

            1 -> {
                menu.findItem(R.id.period_one_day).isChecked = true
            }
            7 -> {
                menu.findItem(R.id.period_seven_days).isChecked = true
            }
            30 -> {
                menu.findItem(R.id.period_thirty_days).isChecked = true
            }
        }

        optionsMenu = menu

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            android.R.id.home -> {

                findNavController(R.id.nav_host_fragment_activity_main).popBackStack()
                return true
            }

            R.id.period_one_day -> {

                updateNewsFetchPeriod(1, item)
            }

            R.id.period_seven_days -> {

                updateNewsFetchPeriod(7, item)
            }

            R.id.period_thirty_days -> {

                updateNewsFetchPeriod(30, item)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun updateNewsFetchPeriod(period: Int, item: MenuItem) {

        item.isChecked = !item.isChecked

        sessionContext.setNewsFetchPeriod(period)

        reloadHome()
    }

    private fun reloadHome() {

        val id = navController.currentDestination?.id
        navController.popBackStack(id!!,true)
        navController.navigate(id)
    }
}