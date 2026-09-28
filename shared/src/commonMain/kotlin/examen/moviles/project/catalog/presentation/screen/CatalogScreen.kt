package examen.moviles.project.catalog.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import examen.moviles.project.catalog.domain.model.MovieModel
import examen.moviles.project.catalog.presentation.viewmodel.CatalogEffect
import examen.moviles.project.catalog.presentation.viewmodel.CatalogEvent
import examen.moviles.project.catalog.presentation.viewmodel.CatalogViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is CatalogEffect.ShowToast -> {
                    // Acción para Toast / Snackbar
                }
                is CatalogEffect.NavigateToDetail -> {
                    // Acción para navegar al detalle
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Películas") }
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
                    Button(onClick = { viewModel.onEvent(CatalogEvent.LoadMovies) }) {
                        Text("Reintentar")
                    }
                }
            } else {
                CatalogGrid(
                    movies = state.movies,
                    onMovieClick = { movieId ->
                        viewModel.onEvent(CatalogEvent.OnMovieClick(movieId))
                    }
                )
            }
        }
    }
}

@Composable
fun CatalogGrid(
    movies: List<MovieModel>,
    onMovieClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                onClick = { onMovieClick(movie.id) }
            )
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f), // Proporción estándar de póster de película
                contentScale = ContentScale.Crop
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}