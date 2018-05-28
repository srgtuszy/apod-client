package com.infullmobile.nasapod.ui

import android.net.http.SslError
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import com.infullmobile.nasapod.R
import com.infullmobile.nasapod.model.AstronomyMedia
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment: Fragment() {
    private inline val media: AstronomyMedia
        get() = arguments!!.getSerializable(ARG_MEDIA) as AstronomyMedia

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureWebView()
        descriptionTextView.text = media.description
        Log.v("webview", media.url)
        webView.loadUrl(media.url)
    }

    private fun configureWebView() {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
    }

    companion object {
        private const val ARG_MEDIA = "astronomy_media"

        fun newInstance(astronomyMedia: AstronomyMedia): VideoFragment {
            assert(astronomyMedia.mediaType == "video")
            val args = Bundle()
            args.putSerializable(ARG_MEDIA, astronomyMedia)
            val fragment = VideoFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
