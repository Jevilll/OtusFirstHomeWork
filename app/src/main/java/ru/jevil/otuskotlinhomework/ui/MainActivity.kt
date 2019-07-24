package ru.jevil.otuskotlinhomework.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import ru.jevil.otuskotlinhomework.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemAdapter = ItemAdapter<RecipeItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)

        fastAdapter.onClickListener = { _, _, item, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", item.id)
            startActivity(intent)
            false
        }

        rv.adapter = fastAdapter
        rv.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProviders.of(this).get(CommonViewModel::class.java)
        viewModel.fetchRecipes()
        viewModel.recipesLiveData.observe(this, Observer { item ->
            if (item == null) Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            else itemAdapter.add(item)
        })
    }

    override fun onStop() {
        viewModel.cancelAllRequests()
        super.onStop()
    }
}