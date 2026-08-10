package com.auramislab.happypaws.ui.foundation

import androidx.lifecycle.ViewModel
import com.auramislab.happypaws.core.common.Outcome
import com.auramislab.happypaws.domain.foundation.GetArchitectureStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class ArchitectureHomeUiState(
    val summaryLine: String,
)

@HiltViewModel
class ArchitectureHomeViewModel @Inject constructor(
    private val getArchitectureStatus: GetArchitectureStatusUseCase,
) : ViewModel() {
    fun status(): Outcome<ArchitectureHomeUiState> =
        when (val outcome = getArchitectureStatus()) {
            is Outcome.Success -> Outcome.Success(
                ArchitectureHomeUiState(
                    summaryLine = buildString {
                        append(outcome.value.productName)
                        append(" · layers=")
                        append(outcome.value.layeringReady)
                        append(" · di=")
                        append(outcome.value.diReady)
                    },
                ),
            )
            is Outcome.Failure -> outcome
        }
}
