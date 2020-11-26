package com.example.cpsc411homework2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

open class MainActivity : AppCompatActivity()
{
    lateinit var claimService: ClaimService

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainlayout)
        claimService = ClaimService.getInstance(this)

        val addButton : Button = findViewById(R.id.add_button)
        val title : EditText = findViewById(R.id.claim_title)
        val status : TextView = findViewById(R.id.status_message)
        val date : EditText = findViewById(R.id.claim_date)

        addButton.setOnClickListener{
            if (TextUtils.isEmpty(title.text.toString())
                || TextUtils.isEmpty(date.text.toString())) {
                status.text = "Status: Blank Required Field(s)"
            }

            else
            {
                status.text = "Status: Claim '${title.text}' Added."
                val claim = Claim(title.text.toString(), date.text.toString())
                claimService.addClaim(claim)
                title.text.clear()
                date.text.clear()
            }
        }
    }
}