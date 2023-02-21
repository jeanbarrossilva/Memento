package com.jeanbarrossilva.aurelius.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.aurelius.databinding.ActivityComposableBinding

abstract class ComposableActivity : AppCompatActivity() {
    private var binding: ActivityComposableBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComposableBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpContent()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    @Composable
    protected abstract fun Content()

    private fun setUpContent() {
        binding?.root?.setContent {
            ThemedContent()
        }
    }

    @Composable
    private fun ThemedContent() {
        AureliusTheme {
            Content()
        }
    }
}
