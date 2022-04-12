package com.app.tatvasoftprac.ui.activity

import com.app.tatvasoftprac.BR
import com.app.tatvasoftprac.R
import com.app.tatvasoftprac.base.BaseActivity
import com.app.tatvasoftprac.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutId = R.layout.activity_main
    override val bindingVariable = BR.mainViewModel

    override fun setUpObserver() {

    }

    override fun init() {

    }
}