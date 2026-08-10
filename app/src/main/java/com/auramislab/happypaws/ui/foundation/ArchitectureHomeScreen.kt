package com.auramislab.happypaws.ui.foundation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.auramislab.happypaws.R
import com.auramislab.happypaws.core.common.Outcome
import com.auramislab.happypaws.ui.theme.HappyPawsTheme

@Composable
fun ArchitectureHomeRoute(
    viewModel: ArchitectureHomeViewModel = hiltViewModel(),
) {
    val status = remember { viewModel.status() }
    ArchitectureHomeScreen(status = status)
}

@Composable
fun ArchitectureHomeScreen(
    status: Outcome<ArchitectureHomeUiState>,
    modifier: Modifier = Modifier,
) {
    val title = stringResource(R.string.app_name)
    val subtitle = stringResource(R.string.app_subtitle)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .semantics { contentDescription = "$title. $subtitle" },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 8.dp),
        )
        when (status) {
            is Outcome.Success -> {
                Text(
                    text = status.value.summaryLine,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
            is Outcome.Failure -> {
                Text(
                    text = status.message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArchitectureHomeScreenPreview() {
    HappyPawsTheme {
        ArchitectureHomeScreen(
            status = Outcome.Success(
                ArchitectureHomeUiState(
                    summaryLine = "Architecture foundation ready (placeholder).",
                ),
            ),
        )
    }
}
