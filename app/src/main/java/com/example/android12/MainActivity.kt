package com.example.android12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android12.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    companion object {
        lateinit var database:Database
        lateinit var list:ArrayList<UserModel>
        lateinit var adapter:UserDataAdapter

        fun Updated() {
            list.clear()
            list = database.showData()
            adapter.update(list)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Database(applicationContext)

        list = database.showData()
        adapter = UserDataAdapter(list)
        binding.rcvList.layoutManager = LinearLayoutManager(applicationContext)
        binding.rcvList.adapter = adapter

        var date = Calendar.getInstance()
        var formate = SimpleDateFormat("d/M/y h:m:s a")
        var current = formate.format(date.time)

        Log.e(TAG, "onCreate: Current Date ==== $current" )

        binding.btnAdd.setOnClickListener {





            var name = binding.name.text.toString()
            var surname = binding.surname.text.toString()
            var address = binding.address.text.toString()
            database.insertData(name, surname, address,2)

            Updated()

        }



    }


}