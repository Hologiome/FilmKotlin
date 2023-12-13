package com.example.premiereapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
//import androidx.navigation.safe.args.generator.models.Destination
import com.example.premiereapplication.ui.theme.PremiereApplicationTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberImagePainter
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewmodel: MainViewModel by viewModels()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val destinations = listOf(Destination.Profil, Destination.Films, Destination.Series, Destination.Acteurs)
            val windowSizeClass = calculateWindowSizeClass(this)
            PremiereApplicationTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            if (currentDestination?.hierarchy?.any { it.route == Destination.Profil.destination } != true) {
                                BottomNavigation {
                                    destinations.forEach { screen ->
                                        BottomNavigationItem(
                                            icon = { Icon(screen.icon, contentDescription = null) },
                                            label = { Text(screen.label) },
                                            selected =
                                            currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
                                            onClick = { navController.navigate(screen.destination) })
                                    }
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(navController, startDestination = Destination.Profil.destination,
                            Modifier.padding(innerPadding)) {
                            composable(Destination.Profil.destination) { Profil(windowSizeClass, { navController.navigate("films") }) }
                            composable(Destination.Films.destination) { Films(navController = navController, viewmodel = viewmodel) { movieId ->
                                Log.d("uuu", movieId )
                                navController.navigate("filmdetail/$movieId")
                            }}

                            composable(Destination.Acteurs.destination) { Actors(navController = navController, viewmodel = viewmodel) { actorId ->
                                Log.d("uuu", actorId )
                                navController.navigate("actordetail/$actorId")
                            }}
                            composable(Destination.Series.destination) { Series(navController = navController, viewmodel = viewmodel) { serieId ->
                                Log.d("uuu", serieId )
                                navController.navigate("seriedetail/$serieId")
                            }}
                            composable("filmdetail/{movieId}") { backStackEntry ->
                                val id = backStackEntry.arguments?.getString("movieId")?:""
                                Log.d("uuuuuuuuuuu", id )
                                FilmDetail(movieId = id , viewModel = viewmodel) }
                            composable("seriedetail/{serieId}") { backStackEntry ->
                                val id = backStackEntry.arguments?.getString("serieId")?:""
                                Log.d("uuuuuuuuuuu", id )
                                SerieDetail(serieId = id , viewModel = viewmodel) }
                            composable("actordetail/{actorId}") { backStackEntry ->
                                val id = backStackEntry.arguments?.getString("actorId")?:""
                                Log.d("uuuuuuuuuuu", id )
                                ActorDetail(actorId = id , viewModel = viewmodel) }

                        }
                    }
//                    NavHost(navController = navController, startDestination = "profil") {
//                        composable("profil") { Profil ( windowSizeClass, {navController.navigate("films") }) }
//                        composable("films") {
//                            Films { navController.navigate("profil") }
//                        }
//                    }
                }
            }
        }
    }
}
sealed class Destination(val destination: String, val label: String, val icon: ImageVector) {
    object Profil : Destination("profil", "Profil", Icons.Filled.Person)
    object Films : Destination("films", "Films", Icons.Filled.Movie)
    object Series : Destination("series", "Séries", Icons.Filled.Tv)
    object Acteurs : Destination("acteurs", "Acteurs", Icons.Filled.Group)
    object FilmDetail : Destination("filmdetail/{movieId}","Detail", Icons.Filled.Group)
    object SerieDetail : Destination("seriedetail/{serieId}","DetailSerie", Icons.Filled.Group)
    object ActorDetail : Destination("actordetail/{actorId}","DetailActor", Icons.Filled.Group)
}
@Composable
fun Profil(windowClass: WindowSizeClass, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painterResource(R.drawable.moib),
                        contentDescription = "moi",
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .clip(CircleShape)
                            .size(200.dp)
                    )
                    Text(
                        text = "Keran Saint-Blancat",
                        style = TextStyle(
                            fontSize = 30.sp
                        ),
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )

                    Text(
                        text = "Etudiant en BUT",
                        style = TextStyle(
                            fontSize = 18.sp
                        ),
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )

                    Text(
                        text = "IUT Métier du Multimédia et de l'Internet",
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )

        MyButton(onClick = onClick)
                }

            }

            else -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Column() {
                        Image(
                            painterResource(R.drawable.moib),
                            contentDescription = "moi",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(200.dp)
                        )
                        Text(
                            text = "Keran Saint-Blancat",
                            style = TextStyle(
                                fontSize = 30.sp
                            ),
                            modifier = Modifier
                                .padding(top = 20.dp)
                        )

                        Text(
                            text = "Etudiant en BUT",
                            style = TextStyle(
                                fontSize = 18.sp
                            ),
                            modifier = Modifier
                                .padding(top = 20.dp)
                        )

                        Text(
                            text = "IUT Métier du Multimédia et de l'Internet",
                            style = TextStyle(
                                fontSize = 18.sp
                            )
                        )

                    }
            MyButton(onClick = onClick)
                }
            }
        }
    }
}

@Composable
fun MyButton(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .width(150.dp)
                .height(56.dp), // Hauteur du bouton
            shape = RoundedCornerShape(16.dp), // Coins arrondis
        ) {
            Text(text = "Démarrer")
        }
    }
}

@Composable
fun Films(navController: NavHostController, viewmodel: MainViewModel, onCardClick: (String) -> Unit) {
    LaunchedEffect(key1 = true) {
        viewmodel.getMovies()
    }

    val movies by viewmodel.movies.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(movies) { movie ->
                FilmItem(movie = movie, navController = navController) {
                    onCardClick(movie.id)
                }
            }
        }
    }
}

@Composable
fun FilmItem(movie: TmdbMovie, navController: NavHostController, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("filmdetail/${movie.id}")
                Log.d("uu", movie.id)
                onClick()
            } // Handle click events
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight() // Adjusted to wrap content in height
                .padding(16.dp) // Added padding for better spacing
        ) {
            // Use Coil to load and display the movie poster
            val painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/${movie.poster_path}",
                builder = {
                    crossfade(true)
                }
            )

            Image(
                painter = painter,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Adjust the height as needed
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            // Display movie title in bold
            Text(
                text = movie.title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, // Set the text to bold
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            // Display release date
            Text(
                text = movie.release_date,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun FilmDetail(movieId : String, viewModel: MainViewModel) {
//    val movieId = navController.previousBackStackEntry?.arguments?.getInt("movieId")?:0
//    val movie = viewModel.getMovieById(movieId)
    Log.d("xxx", "on film detail:" + movieId )

    LaunchedEffect(key1 = true) {
        viewModel.getMovieById(movieId)
    }

    val movie by viewModel.details.collectAsStateWithLifecycle()


    // Use a Column to arrange details vertically
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val painter = rememberImagePainter(
            data = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/${movie.poster_path}",
            builder = {
                crossfade(true)
            }
        )

        Image(
            painter = painter,
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp) // Adjust the height as needed
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        // Display movie title
        Text(
            text = movie.title,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Display release date
        Text(
            text = movie.release_date,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier
                .padding(top = 4.dp)
        )

        Text(
            text = "Synopsis :",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Display movie overview
        Text(
            text = movie.overview,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

//         You can add more details as needed
//
//         Add a back button to navigate back
//        IconButton(
//            onClick = {
//                navController.Films
//            },
//            modifier = Modifier
//                .align(Alignment.End)
//                .padding(8.dp)
//        ) {
//            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//        }
    }
}







@Composable
fun Actors(navController: NavHostController, viewmodel: MainViewModel, onCardClick: (String) -> Unit) {
    LaunchedEffect(key1 = true) {
        viewmodel.getActors()
    }

    val actors by viewmodel.actors.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(actors) { actor ->
                ActorItem(actor = actor, navController = navController) {
                    onCardClick(actor.id)
                }
            }
        }
    }
}

@Composable
fun ActorItem(actor: TmdbActor, navController: NavHostController, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("actordetail/${actor.id}")
                Log.d("uu", actor.id)
                onClick()
            } // Handle click events
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight() // Adjusted to wrap content in height
                .padding(16.dp) // Added padding for better spacing
        ) {
            // Use Coil to load and display the movie poster
            val painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/${actor.profile_path}",
                builder = {
                    crossfade(true)
                }
            )

            Image(
                painter = painter,
                contentDescription = actor.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Adjust the height as needed
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            // Display movie title in bold
            Text(
                text = actor.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, // Set the text to bold
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ActorDetail(actorId : String, viewModel: MainViewModel) {
//    val movieId = navController.previousBackStackEntry?.arguments?.getInt("movieId")?:0
//    val movie = viewModel.getMovieById(movieId)
    Log.d("xxx", "on actor detail:" + actorId)

    LaunchedEffect(key1 = true) {
        viewModel.getActorById(actorId)
    }

    val actor by viewModel.detailsActor.collectAsStateWithLifecycle()


    // Use a Column to arrange details vertically
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val painter = rememberImagePainter(
            data = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/${actor.profile_path}",
            builder = {
                crossfade(true)
            }
        )

        Image(
            painter = painter,
            contentDescription = actor.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp) // Adjust the height as needed
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        // Display movie title
        Text(
            text = actor.name,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Display release date
        actor.birthday?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(top = 4.dp)
            )
        }

        actor.deathday?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(top = 4.dp)
            )
        }

        Text(
            text = "Biography :",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Display movie overview
        Text(
            text = actor.biography,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

    }
}
@Composable
fun Series(navController: NavHostController, viewmodel: MainViewModel, onCardClick: (String) -> Unit) {
    LaunchedEffect(key1 = true) {
        viewmodel.getSerie()
    }

    val series by viewmodel.series.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(series) { serie ->
                SerieItem(serie = serie, navController = navController) {
                    onCardClick(serie.id)
                }
            }
        }
    }
}

@Composable
fun SerieItem(serie: TmdbSeries, navController: NavHostController, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("seriedetail/${serie.id}")
                Log.d("uu", serie.id)
                onClick()
            } // Handle click events
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight() // Adjusted to wrap content in height
                .padding(16.dp) // Added padding for better spacing
        ) {
            // Use Coil to load and display the movie poster
            val painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/${serie.poster_path}",
                builder = {
                    crossfade(true)
                }
            )

            Image(
                painter = painter,
                contentDescription = serie.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Adjust the height as needed
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            // Display movie title in bold
            Text(
                text = serie.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, // Set the text to bold
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            // Display release date
            Text(
                text = serie.first_air_date,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun SerieDetail(serieId : String, viewModel: MainViewModel) {
//    val movieId = navController.previousBackStackEntry?.arguments?.getInt("movieId")?:0
//    val movie = viewModel.getMovieById(movieId)
    Log.d("xxx", "on serie detail:" + serieId )

    LaunchedEffect(key1 = true) {
        viewModel.getSerieById(serieId)
    }

    val serie by viewModel.detailsSerie.collectAsStateWithLifecycle()


    // Use a Column to arrange details vertically
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val painter = rememberImagePainter(
            data = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/${serie.poster_path}",
            builder = {
                crossfade(true)
            }
        )

        Image(
            painter = painter,
            contentDescription = serie.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp) // Adjust the height as needed
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        // Display movie title
        Text(
            text = serie.name,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Display release date
        Text(
            text = serie.first_air_date,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier
                .padding(top = 4.dp)
        )

        Text(
            text = "Synopsis :",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Display movie overview
        Text(
            text = serie.overview,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    PremiereApplicationTheme {
//        Accueil(WindowSizeClass)
//    }
//}