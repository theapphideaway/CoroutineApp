package com.example.coroutineapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainFragment : Fragment() {

    private  lateinit var mainViewModel: MainViewModel
    private lateinit var startBtn: Button
    private lateinit var resetBtn: Button
    private lateinit var testOneBtn: Button
    private lateinit var testTwoBtn: Button
    private lateinit var statusTv: TextView
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false).apply{
            mainViewModel = ViewModelProvider(this@MainFragment).get(MainViewModel::class.java)

            startBtn = findViewById(R.id.start_btn)
            resetBtn = findViewById(R.id.reset_btn)
            testOneBtn = findViewById(R.id.test_one_btn_)
            testTwoBtn = findViewById(R.id.test_two_btn)
            statusTv = findViewById(R.id.status_tv)

            startBtn.setOnClickListener {
                getDataFromViewModel()
            }

            resetBtn.setOnClickListener {
                statusTv.text = "Click Start to Gather Data"
            }
            testOneBtn.setOnClickListener {
                Toast.makeText(requireContext(), "Hello World", Toast.LENGTH_SHORT).show()
            }
            testTwoBtn.setOnClickListener {
                Toast.makeText(requireContext(), "Main Thread Test", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDataFromViewModel() = runBlocking {
        coroutineScope.launch {
            val responseOne= mainViewModel.getData()
            statusTv.text = responseOne
            val responseTwo= mainViewModel.getDataTwo(responseOne)
            statusTv.text = responseTwo
            val responseThree= mainViewModel.getDataThree(responseTwo)
            statusTv.text = responseThree
        }
    }
}