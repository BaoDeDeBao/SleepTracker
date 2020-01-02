package com.example.sleeptracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sleepViewModel: SleepViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Declare and initialize Recycleview + Adapter
        val recyclerview: RecyclerView = findViewById(R.id.recyclerview)
        val adapter = SleepAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        //Initialize ViewModel
        sleepViewModel = ViewModelProvider(this).get(SleepViewModel::class.java)

        //Add an observer to the ViewModel (LiveData)
        sleepViewModel.sleepList.observe(
            this,
            Observer {
                if(it.isNotEmpty()) {
                    //Set list of sleep to the adapter
                    adapter.setSleep(it)
                    //Toast.makeText(applicationContext, "Size = "  + it.size, Toast.LENGTH_SHORT).show()
                }
            }
        )

        fab.setOnClickListener { view ->
            intent = Intent(this, AddActity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                //data?.getStringExtra(AddActity.EXTRA_QUALITY)?.let {
                    //val sleep = Sleep(4,  System.currentTimeMillis(), System.currentTimeMillis(), 5)
                    //sleepViewModel.insert(sleep)
                //}
                val _quality = data?.getIntExtra(AddActity.EXTRA_QUALITY, 0)
                val sleep = Sleep(id = 0, startDate = 0, endDate = System.currentTimeMillis(),
                    quality = _quality!!)

                //TODO: Insert a new record to database
                sleepViewModel.insertSleep(sleep)
            }
        super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        const val REQUEST_CODE = 1
    }
}
