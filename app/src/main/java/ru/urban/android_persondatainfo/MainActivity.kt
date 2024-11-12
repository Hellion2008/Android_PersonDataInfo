package ru.urban.android_persondatainfo

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var nameET: EditText
    private lateinit var surnameET: EditText
    private lateinit var adressET: EditText
    private lateinit var phoneNumberET: EditText

    private lateinit var saveBTN: Button

    private lateinit var personsLV: ListView

    private val persons: MutableList<Person> = mutableListOf()

    private var adapter: ArrayAdapter<Person>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameET = findViewById(R.id.nameET)
        surnameET = findViewById(R.id.surnameET)
        adressET = findViewById(R.id.adressET)
        phoneNumberET = findViewById(R.id.phoneNumberET)

        saveBTN = findViewById(R.id.saveBTN)

        personsLV = findViewById(R.id.personsLV)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, persons)
        personsLV.adapter = adapter


        saveBTN.setOnClickListener {
            if (checkFieldsEditText()) {
                return@setOnClickListener
            }

            val person = Person(
                nameET.text.toString(),
                surnameET.text.toString(),
                adressET.text.toString(),
                phoneNumberET.text.toString(),
            )

            persons.add(person)
            clearFieldsEditText()

            adapter!!.notifyDataSetChanged()
        }

        personsLV.onItemClickListener = AdapterView.OnItemClickListener{ parent, v, position, id ->
            val person = adapter!!.getItem(position)

            val intent = Intent(this, PersonInfoActivity::class.java)
                .putExtra(Person::class.java.simpleName, person)
            startActivity(intent)
        }
    }

    private fun clearFieldsEditText() {
        nameET.text.clear()
        surnameET.text.clear()
        adressET.text.clear()
        phoneNumberET.text.clear()
    }

    private fun checkFieldsEditText() = nameET.text.isEmpty() ||
            surnameET.text.isEmpty() ||
            adressET.text.isEmpty() ||
            phoneNumberET.text.isEmpty()
}