package com.example.comarquescs_margomez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comarquescs_margomez.ui.theme.ComarquesCS_MarGomezTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComarquesCS_MarGomezTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                var fabAlfaValue by remember { mutableStateOf(0f) }
                var topBarTitle by remember { mutableStateOf("Comarques Castelló") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    topBar = { CreateTopAppBar(
                        topBarTitle,
                        navController
                    ) }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "PantallaPrincipal") {
                        composable("PantallaPrincipal") {
                            fabAlfaValue = 0f
                            topBarTitle = "Comarques Castelló"
                            PantallaPrincipal(Modifier.padding(innerPadding), snackbarHostState)
                        }
                        composable("PantallaActividades") {
                            fabAlfaValue = 1f
                            topBarTitle = "Cerca d'Activitats"
                            PantallaActividades(Modifier.padding(innerPadding))
                        }
                    }

                    Box(Modifier.padding(20.dp).fillMaxSize()) {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate("PantallaPrincipal")
                                },
                            Modifier
                                .align(Alignment.BottomEnd)
                                .padding(bottom = 40.dp)
                                .alpha(fabAlfaValue),
                            containerColor = MaterialTheme.colorScheme.secondary
                        ) {
                            Icon(Icons.Filled.ArrowBack, "Floating action button.")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTopAppBar(titulo: String, navController: NavController) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        ),
        title = {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(50.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = titulo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(PaddingValues(start = 20.dp))
                )
            }
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Localized description"
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = {showMenu = false}
            ) {
                DropdownMenuItem(
                    text = { Text("Activitats") },
                    onClick = {
                        navController.navigate("PantallaActividades")
                        showMenu = false
                    }
                )
            }
        }
    )
}