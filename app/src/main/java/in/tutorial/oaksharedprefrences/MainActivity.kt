package `in`.tutorial.oaksharedprefrences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import `in`.tutorial.oaksharedprefrences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding? = null
    var count : Int = 0
    var name:String? = null
    var message:String? = null
    var isChecked:Boolean? = null
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding?.btnZero?.setOnClickListener {
            count++
            binding?.btnZero?.text = count.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }
    fun saveData(){
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
        name = binding?.etPName?.text.toString()
        message = binding?.etMulti?.text.toString()
        isChecked = binding?.cbCB?.isChecked
        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("message", message)
        editor.putInt("count", count)
        editor.putBoolean("remember", isChecked!!)
        editor.apply()
        Toast.makeText(applicationContext, "Your data is saved", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        retrieveData()
    }

    fun retrieveData(){
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
        name = sharedPreferences.getString("name", null)
        message = sharedPreferences.getString("message", null)
        count = sharedPreferences.getInt("count", 0)
        isChecked = sharedPreferences.getBoolean("remember", false)
        binding?.etPName?.setText(name.toString())
        binding?.etMulti?.setText(message.toString())
        binding?.btnZero?.text = count.toString()
        binding?.cbCB?.isChecked = isChecked as Boolean
    }
}