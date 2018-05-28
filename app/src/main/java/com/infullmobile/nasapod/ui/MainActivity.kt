package com.infullmobile.nasapod.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.infullmobile.nasapod.R
import com.infullmobile.nasapod.model.AstronomyMedia

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private val mediaObserver = Observer<AstronomyMedia> { media ->
        media?.let { showMedia(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.picture.observe(this, mediaObserver)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPicture()
    }

    private fun showMedia(media: AstronomyMedia) {
        when (media.mediaType) {
            "video" -> showVideoMedia(media)
            "picture" -> showPictureMedia(media)
        }
    }

    private fun showVideoMedia(media: AstronomyMedia) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.rootView, VideoFragment.newInstance(media))
                .commit()

    }

    private fun showPictureMedia(media: AstronomyMedia) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.rootView, ImageFragment.newInstance(media))
                .commit()
    }
}
