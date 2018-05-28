package com.infullmobile.nasapod.ui

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.infullmobile.nasapod.R
import com.infullmobile.nasapod.model.AstronomyMedia
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment: Fragment() {
    private val picasso: Picasso
        get() = Picasso.get()
    private val media: AstronomyMedia
        get() = arguments!!.getSerializable(ARG_MEDIA) as AstronomyMedia

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        descriptionTextView.text = media.description
        picasso.load(Uri.parse(media.url)).into(imageView)
    }

    companion object {
        private const val ARG_MEDIA = "astronomy_media"

        fun newInstance(astronomyMedia: AstronomyMedia): ImageFragment {
            assert(astronomyMedia.mediaType == "image")
            val args = Bundle()
            args.putSerializable(ARG_MEDIA, astronomyMedia)
            val fragment = ImageFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
