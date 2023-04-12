package com.example.morningintents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    lateinit var mSms: Button
    lateinit var mEmail: Button
    lateinit var mCamera: Button
    lateinit var mShare: Button
    lateinit var mMpesa:Button
    lateinit var mCall:Button
    lateinit var mWebsite:Button
    lateinit var mMap:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSms=findViewById(R.id.btnSms)
        mEmail=findViewById(R.id.btnEmail)
        mCamera=findViewById(R.id.btnCamera)
        mShare=findViewById(R.id.btnShare)
        mMpesa=findViewById(R.id.btnMpesa)
        mCall=findViewById(R.id.btnCall)
        mWebsite=findViewById(R.id.btnWebsite)
        mMap=findViewById(R.id.mBtnMap)
        //Set onClick listeners to all the buttons
        mSms.setOnClickListener {
            val uri: Uri = Uri.parse("smsto:0768106010")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello there,,,,")
            startActivity(intent)
        }
        mEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "abc@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JOB APPLICATION")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear sir,following the adv....")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        mCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)

        }
        mShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app from https://")
            startActivity(shareIntent)
        }
        mMpesa.setOnClickListener {
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }
        }
        mCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0768106010"))
            if (ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1)
            } else {
                startActivity(intent)
            }
        }
        mWebsite.setOnClickListener {
            val tembea=Intent(this@MainActivity, WebsiteActivity::class.java)
            startActivity(tembea)
        }
        mMap.setOnClickListener {
            val ramani = Intent(this,MapsActivity::class.java)
            startActivity(ramani)
        }

    }
}