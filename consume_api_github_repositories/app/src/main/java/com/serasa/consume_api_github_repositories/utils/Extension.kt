package com.serasa.consume_api_github_repositories.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.serasa.consume_api_github_repositories.R

fun FragmentActivity.replaceView(fragment: Fragment, @IdRes containerId: Int = R.id.container) {
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment)
        .commitNow()
}

fun FragmentActivity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

private fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}