package com.example.premiereapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
//import androidx.navigation.safe.args.generator.models.Destination
import com.example.premiereapplication.ui.theme.PremiereApplicationTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val destinations = listOf(Destination.Profil, Destination.Edition)
            val windowSizeClass = calculateWindowSizeClass(this)
            PremiereApplicationTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = { BottomNavigation {
                            destinations.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(screen.icon, contentDescription = null) },
                                    label = { Text(screen.label) },
                                    selected =
                                    currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
                                    onClick = { navController.navigate(screen.destination) })
                            }}
                        }) { innerPadding ->
                        NavHost(navController, startDestination = Destination.Profil.destination,
                            Modifier.padding(innerPadding)) {
                            composable(Destination.Profil.destination) { Profil ( windowSizeClass, {navController.navigate("films") }) }
                            composable(Destination.Edition.destination) { Films { navController.navigate("profil") } }
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
    object Profil : Destination("profil", "Mon Profil", Icons.Filled.Person)
    object Edition : Destination("edition", "Edition du profil", Icons.Filled.Edit)
}
@Composable
fun Profil(windowClass: WindowSizeClass, onClick: () -> Unit) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
    Column( horizontalAlignment = Alignment.CenterHorizontally) {
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

//        MyButton(onClick = onClick)
        }

        }
        else -> {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {

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
//            MyButton(onClick = onClick)
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
fun Films(onClick: () -> Unit) {
//    MyButton(onClick = onClick)
}

@Composable
fun Lien(){

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    PremiereApplicationTheme {
//        Accueil(WindowSizeClass)
//    }
//}