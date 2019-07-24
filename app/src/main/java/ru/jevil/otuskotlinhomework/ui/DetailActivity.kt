package ru.jevil.otuskotlinhomework.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import ru.jevil.otuskotlinhomework.R
import ru.jevil.otuskotlinhomework.data.getIngredients

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra("id")

        viewModel = ViewModelProviders.of(this).get(CommonViewModel::class.java)

        viewModel.fetchRecipe(id)

        viewModel.recipeLiveData.observe(this, Observer {
            Glide.with(this).load(it.url).into(iv_recipe)

            tv_title.text = it.title
            tv_recipe.text = it.getIngredients()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        viewModel.cancelAllRequests()
        super.onStop()
    }
}
