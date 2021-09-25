package com.example.datastoreex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.datastoreex.DataStore.Companion.PROFILE_AGE
import com.example.datastoreex.DataStore.Companion.PROFILE_NAME
import com.example.datastoreex.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var dataStore: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        dataStore= DataStore(this)
        binding.buttonSubmit.setOnClickListener {
            if(binding.editTextPersonName.text.length==0){
                Toast.makeText(this,"Enter Name",Toast.LENGTH_LONG).show()
            }else if(binding.editTextPersonAge.text.length==0){
                Toast.makeText(this,"Enter Age",Toast.LENGTH_LONG).show()
            } else{
                dataStore.apply {
                    setValue(PROFILE_NAME,binding.editTextPersonName.text.toString().trim())
                    setValue(PROFILE_AGE,binding.editTextPersonAge.text.toString().toInt())
                }
                Thread.sleep(1000)
                lifecycleScope.launch {
                    binding.textviewShowInfo.setText(
                        dataStore.getValue(PROFILE_NAME,"name")+"-"+dataStore.getValue(PROFILE_AGE,0))
                }
            }
        }
    }
}