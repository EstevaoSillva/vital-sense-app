package com.mindsense.core.ui.feedback

import androidx.compose.runtime.Composable
import com.mindsense.core.designsystem.components.LoadingState as DsLoadingState

@Composable
fun LoadingState(message: String = "Carregando dados...") {
    DsLoadingState(message = message)
}
