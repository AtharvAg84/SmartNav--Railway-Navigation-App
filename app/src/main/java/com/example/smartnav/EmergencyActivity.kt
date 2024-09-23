package com.example.smartnav

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class EmergencyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)  // Linking to the new activity layout

        // Police Call Button - Opens the dialer with a phone number
        val policeCallButton: ImageButton = findViewById(R.id.police_button)  // Changed to ImageButton
        policeCallButton.setOnClickListener {
            makePhoneCall("100")
        }
        val womenCallButton: ImageButton = findViewById(R.id.women_button)  // Changed to ImageButton
        womenCallButton.setOnClickListener {
            makePhoneCall("1090")
        }
        val fireCallButton: ImageButton = findViewById(R.id.fire_button)  // Changed to ImageButton
        fireCallButton.setOnClickListener {
            makePhoneCall("101")
        }
        val ambCallButton: ImageButton = findViewById(R.id.ambulance_button)  // Changed to ImageButton
        ambCallButton.setOnClickListener {
            makePhoneCall("108")
        }
    }

    // Function to open dialer with a phone number
    private fun makePhoneCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }
}
