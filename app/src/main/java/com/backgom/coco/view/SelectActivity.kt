package com.backgom.coco.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.backgom.coco.background.GetCoinPriceRecentContractedWorkManager
import com.backgom.coco.databinding.ActivitySelectBinding
import com.backgom.coco.view.adapter.SelectRVAdapter
import com.backgom.coco.view.main.MainActivity
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectBinding
    private val viewModel: SelectViewModel by viewModels()

    private lateinit var selectRVAdapter: SelectRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCurrentCoinList()
        viewModel.currentPriceResult.observe(this, Observer {

            selectRVAdapter = SelectRVAdapter(this, it)

            binding.coinListRV.adapter = selectRVAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

            Timber.d(it.toString())
        })

        binding.laterTextArea.setOnClickListener {
            viewModel.setUpFirstFlag()
            viewModel.saveSelectedCoinList(selectRVAdapter.selectedCoinList)
        }

        viewModel.save.observe(this, Observer {
            if(it.equals("done")) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                //가장 처음으로 우리가 저장한 코인 정보가 시작되는 시점
                saveInterestCoinDataPeriodic()
            }
        })
    }


    private fun saveInterestCoinDataPeriodic() {

        val myWork = PeriodicWorkRequest.Builder(
            /* workerClass = */ GetCoinPriceRecentContractedWorkManager::class.java,
            /* repeatInterval = */ 900,
            /* repeatIntervalTimeUnit = */ TimeUnit.SECONDS
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "GetCoinPriceRecentContractedWorkManager",
            ExistingPeriodicWorkPolicy.KEEP,
            myWork
        )

    }

}