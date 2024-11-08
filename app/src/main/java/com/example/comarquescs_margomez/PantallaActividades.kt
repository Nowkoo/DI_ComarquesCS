package com.example.comarquescs_margomez

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comarquescs_margomez.ui.theme.leckenlioneregular
import kotlinx.coroutines.launch

@Composable
fun PantallaActividades(modifier: Modifier) {
    val rvState = rememberLazyGridState()
    val currentScope = rememberCoroutineScope()

    val chipLabels = listOf("Turisme rural", "Turisme de costa", "Turisme cultural")
    val checkboxLabels = listOf(
        "Senderisme",
        "Barranquisme",
        "Submarinisme",
        "Escalada",
        "Cicloturisme",
        "Golf",
        "Pesca",
        "Infantils",
        "Gastronomia",
        "Festes",
        "Rutes Culturals",
        "Museus",
        "Monuments",
        "Festivals",
        "Apendre valencià",
        "Naùtica",
        "Ciclisme",
        "Ioga",
        "Taichi",
        "Running",
        "Aeróbic",
        "Pàdel Surf",
        "Pàdel",
        "Tenis",
        "Aquagym",
        "Ritmofit",
        "Streching"
    )
    val context = LocalContext.current

    val showButton by remember {
        derivedStateOf {
            rvState.firstVisibleItemIndex > 0
        }
    }

    Column (modifier.fillMaxWidth().padding(5.dp)) {
        Text (
            text = "Tipus de turisme:",
            fontFamily = leckenlioneregular,
            fontSize = 20.sp
        )

        ChipArray(chipLabels, context)

        Text (
            text = "Activitats:",
            fontFamily = leckenlioneregular,
            fontSize = 20.sp
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize().weight(1f),
            content = {
                items(checkboxLabels.size) { index ->
                    ItemCheckbox(checkboxLabels[index])
                }
            },
            state = rvState
        )

        if (showButton) {
            Button(onClick = {
                currentScope.launch {
                    rvState.animateScrollToItem(0)
                }
            },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Cerca la teva activitat")
            }
        }
    }
}

@Composable
fun ItemCheckbox(label: String) {
    var selected by remember { mutableStateOf(false) }
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = selected,
            onCheckedChange = {
                selected = !selected
            }
        )
        Text(text = label)
    }
}

@Composable
fun ChipArray(chipLabels: List<String>, context: Context) {
    var selectedChip: String? by remember { mutableStateOf(null) }

    Row {
        for (label in chipLabels) {
            FilterChip(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
                },
                label = {
                    Text(label)
                },
                selected = false,
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            )
        }
    }
}