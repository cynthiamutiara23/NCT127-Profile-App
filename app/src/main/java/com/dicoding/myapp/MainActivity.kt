package com.dicoding.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Member>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "NCT 127 Members"

        binding.rvMembers.setHasFixedSize(true)

        list.addAll(getListMembers())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListMembers(): ArrayList<Member> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataBirthdate = resources.getStringArray(R.array.data_birthdate)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataEmoji = resources.getStringArray(R.array.data_emoji)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listMember = ArrayList<Member>()
        for (i in dataName.indices) {
            val member = Member(dataName[i], dataBirthdate[i], dataDescription[i], dataEmoji[i], dataPhoto[i])
            listMember.add(member)
        }
        return listMember
    }

    private fun showRecyclerList() {
        binding.rvMembers.layoutManager = LinearLayoutManager(this)
        val listMemberAdapter = ListMemberAdapter(list)
        binding.rvMembers.adapter = listMemberAdapter

        listMemberAdapter.setOnItemClickCallback(object : ListMemberAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Member) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }
}