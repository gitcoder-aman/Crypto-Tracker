package com.tech.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tech.cryptotracker.core.presentation.util.ObserveAsEvents
import com.tech.cryptotracker.core.presentation.util.toString
import com.tech.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.tech.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.tech.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.tech.cryptotracker.crypto.presentation.coin_list.CoinListState
import com.tech.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import com.tech.cryptotracker.crypto.presentation.coin_list.components.previewCoin
import com.tech.cryptotracker.ui.theme.CryptoTrackerTheme
import kotlinx.coroutines.flow.emptyFlow
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val context = LocalContext.current
                    ObserveAsEvents(events = viewModel.events) { event ->
                        when (event) {
                            is CoinListEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }
                    }

                    when{
                        state.selectedCoin != null->{
                            CoinDetailScreen(
                                state = state,
                                modifier = Modifier
                                    .padding(innerPadding)
                            )
                        }
                        else->{
                            CoinListScreen(
                                state = state,
                                modifier = Modifier
                                    .padding(innerPadding),
                                onAction = viewModel::onAction
                            )
                        }
                    }
                }
            }
        }
    }
}