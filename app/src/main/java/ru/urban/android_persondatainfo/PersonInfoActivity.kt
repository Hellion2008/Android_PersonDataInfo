package ru.urban.android_persondatainfo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PersonInfoActivity : AppCompatActivity() {

    private lateinit var fioInfoTV: TextView
    private lateinit var adressInfoTV: TextView
    private lateinit var phoneNumberInfoTV: TextView

    private var person: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_person_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fioInfoTV = findViewById(R.id.fioInfoTV)
        adressInfoTV = findViewById(R.id.adressInfoTV)
        phoneNumberInfoTV = findViewById(R.id.phoneNumberInfoTV)

        person = intent.extras?.getParcelable(Person::class.java.simpleName) as Person?

        fioInfoTV.setText("${person?.name} ${person?.surname}")
        adressInfoTV.setText(person?.adress)
        phoneNumberInfoTV.setText(person?.phoneNumber)

    }
}