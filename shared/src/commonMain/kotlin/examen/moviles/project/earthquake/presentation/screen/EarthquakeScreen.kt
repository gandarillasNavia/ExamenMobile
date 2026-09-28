package examen.moviles.project.earthquake.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import examen.moviles.project.earthquake.domain.model.EarthquakeModel
import examen.moviles.project.earthquake.presentation.viewmodel.EarthquakeEvent
import examen.moviles.project.earthquake.presentation.viewmodel.EarthquakeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EarthquakeScreen(
    viewModel: EarthquakeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Terremotos -USGS") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (state.error != null) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.error ?: "",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.onEvent(EarthquakeEvent.LoadEarthquakes) }) {
                        Text("Reintentar")
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.earthquakes) { earthquake ->
                        EarthquakeItem(earthquake = earthquake)
                    }
                }
            }
        }
    }
}

@Composable
fun EarthquakeItem(earthquake: EarthquakeModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = earthquake.place,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Magnitud: ${earthquake.magnitude}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Fecha: ${earthquake.time}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Coordenadas: Latitud ${earthquake.latitude}, Longitud ${earthquake.longitude}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Profundidad: ${earthquake.depth} km",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Enlace al evento (URL): ${earthquake.url}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}