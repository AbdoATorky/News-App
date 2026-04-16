package com.abs.newsapp

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.abs.newsapp.databinding.ArticleListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class NewsAdapter(val a: Activity, val articles: ArrayList<Article>) :
    Adapter<NewsAdapter.NewsVH>() {

    class NewsVH(val binding: ArticleListItemBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val b = ArticleListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsVH(b)
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {

        val article = articles[position]
        val url = article.url
        holder.binding.articleText.text = article.title

        Glide
            .with(holder.binding.articleImage.context)
            .load(article.urlToImage)
            .error(R.drawable.broken_image)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(holder.binding.articleImage)

        holder.binding.articleContainer.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, url.toUri())
            a.startActivity(i)
        }

        holder.binding.shareFab.setOnClickListener {
            ShareCompat
                .IntentBuilder(a)
                .setType("text/plain")
                .setChooserTitle("share article with:")
                .setText(url)
                .startChooser()
        }
    }

    override fun getItemCount() = articles.size

}