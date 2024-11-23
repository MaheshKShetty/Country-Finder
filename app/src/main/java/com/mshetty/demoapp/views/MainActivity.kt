package com.mshetty.demoapp.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mshetty.demoapp.adapter.CountryAdapter
import com.mshetty.demoapp.adapter.OnClickListner
import com.mshetty.demoapp.databinding.ActivityMainBinding
import com.mshetty.demoapp.model.CountryResponseItem
import com.mshetty.demoapp.model.NetworkState
import com.mshetty.demoapp.viewmodel.CountryViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryViewModel
    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        setUpObserVer()
    }


    private fun setUpObserVer() {
        binding.pgBar.visibility= View.VISIBLE
        viewModel.fetchCountries()
        viewModel.countriesLiveData.observe(this,{ res ->
            when(res) {
                is NetworkState.SUCCESS -> {
                    binding.pgBar.visibility= View.GONE
                    val response = res.data
                    response?.let { setupAdapter(it) }
                }
                is NetworkState.LOADING -> {
                    binding.pgBar.visibility= View.VISIBLE
                }
                is NetworkState.ERROR -> {

                }
            }
        })
    }

    private fun setupAdapter(data:List<CountryResponseItem?>) {
        val adapter = CountryAdapter(object: OnClickListner {

            override fun countryClicked(item: CountryResponseItem) {
                val intent = Intent(this@MainActivity, CountryDetailsActivity::class.java)
                intent.putExtra("countryName", item?.name?.common)
                intent.putExtra("countryFlag", item?.flags?.png)
                startActivity(intent)
            }

        })
        data?.let {
            adapter.setData(it)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter =  adapter
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }
        } )

        binding.searchView.setOnCloseListener {
            adapter.setData(data)
            false
        }
    }

}