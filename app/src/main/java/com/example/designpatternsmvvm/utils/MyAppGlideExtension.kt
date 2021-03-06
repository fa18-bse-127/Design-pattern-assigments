/*
 * Developed by: @ImaginativeShohag
 *
 * Md. Mahmudul Hasan Shohag
 * imaginativeshohag@gmail.com
 *
 * MVVM Pattern Source: https://github.com/ImaginativeShohag/Simple-MVVM
 */

package com.example.designpatternsmvvm

import androidx.annotation.NonNull
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.request.BaseRequestOptions
import org.imaginativeworld.simplemvvm.R

// help: https://futurestud.io/tutorials/glide-4-request-options-generated-api

@GlideExtension
object MyAppGlideExtension {

    @NonNull
    @GlideOption
    @JvmStatic
    fun profilePhoto(options: BaseRequestOptions<*>): BaseRequestOptions<*> {
        return options
            .fitCenter()
            .circleCrop()
            .placeholder(R.drawable.ic_user)
            .fallback(R.drawable.ic_user)
            .error(R.drawable.ic_user)
    }

}