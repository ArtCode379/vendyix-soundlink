package vendyix.musical.vendyixsoundlink.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import vendyix.musical.vendyixsoundlink.R

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val supportUrl = stringResource(R.string.udlxj_customer_support_link)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(stringResource(R.string.udlxj_about), style = MaterialTheme.typography.titleLarge)
        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                SettingRow(Icons.Default.Business, stringResource(R.string.udlxj_settings_screen_company_label), stringResource(R.string.udlxj_company_name))
                SettingRow(Icons.Default.Info, stringResource(R.string.udlxj_settings_screen_version_label), stringResource(R.string.udlxj_app_version))
            }
        }
        Text(stringResource(R.string.udlxj_support), style = MaterialTheme.typography.titleLarge)
        Button(
            onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(supportUrl))) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(Icons.Default.HeadsetMic, contentDescription = null)
            Text(stringResource(R.string.udlxj_settings_screen_customer_support_label), modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
private fun SettingRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Column {
            Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
            Text(value, style = MaterialTheme.typography.titleMedium)
        }
    }
}
