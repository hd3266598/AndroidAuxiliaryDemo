package com.example.hd.androidauxiliarydemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import android.view.View


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var accessibilityEnabled = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_go_setting.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    applicationContext.contentResolver,
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED)
            Log.v(localClassName, "accessibilityEnabled = $accessibilityEnabled")
        } catch (e: Settings.SettingNotFoundException) {
            Log.e(localClassName, "Error finding setting, default accessibility to not found: " + e.message)
        }

        if (accessibilityEnabled != 1) {
            btn_go_setting.text = getString(R.string.btn_main_content)
            btn_go_setting.isClickable = true
        } else {
            btn_go_setting.text = getString(R.string.btn_main_content_2)
            btn_go_setting.isClickable = false
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_go_setting -> {
                startActivity(Intent(ACTION_ACCESSIBILITY_SETTINGS))
            }
            else -> {
            }
        }
    }
}
