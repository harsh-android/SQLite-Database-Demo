package com.example.android12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android12.databinding.ActivityMainBinding

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


        binding.btnAdd.setOnClickListener {
            var name = binding.name.text.toString()
            var surname = binding.surname.text.toString()
            var address = binding.address.text.toString()
            database.insertData(name, surname, address)

            Updated()

        }



    }


}