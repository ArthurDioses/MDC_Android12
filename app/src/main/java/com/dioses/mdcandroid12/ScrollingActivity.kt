package com.dioses.mdcandroid12

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dioses.mdcandroid12.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener {
            if (binding.bottomAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                binding.bottomAppBar.fabAlignmentMode =
                    BottomAppBar.FAB_ALIGNMENT_MODE_END
            } else {
                binding.bottomAppBar.fabAlignmentMode =
                    BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
        }
        binding.bottomAppBar.setNavigationOnClickListener {
            Snackbar.make(binding.root, R.string.message_action_success, Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.fab)
                .show()
        }
        binding.content.btnSkip.setOnClickListener {
            binding.content.cvAdd.visibility = View.GONE
        }
        binding.content.btnBuy.setOnClickListener {
            Snackbar.make(it, R.string.card_buying, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .setAction(R.string.card_to_go, {
                    Toast.makeText(this, R.string.card_historial, Toast.LENGTH_SHORT).show()
                })
                .show()
        }
        renderImager()
        binding.content.cbEnablePass.setOnClickListener {
            binding.content.tilPassword.isEnabled = !binding.content.tilPassword.isEnabled
        }
        binding.content.etUrl.onFocusChangeListener = View.OnFocusChangeListener { view, focused ->
            val url = binding.content.etUrl.text.toString()
            if (!focused) {
                renderImager(url)
            }
        }
    }

    private fun renderImager(url: String = "https://s1.eestatic.com/2017/07/25/elandroidelibre/el_androide_libre_233990287_181198536_600x315.jpg") {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.content.imgCover)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
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
}