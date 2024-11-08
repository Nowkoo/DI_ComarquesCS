package com.example.comarquescs_margomez

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comarquescs_margomez.ui.theme.leckenlioneregular
import kotlinx.coroutines.launch

data class Comarca(
    val comarca: String,
    val capital: String,
    val poblacion: String,
    @DrawableRes var imagen: Int,
    val descripcion: String,
    val latitud: Double,
    val longitud: Double,

)

@Composable
fun PantallaPrincipal(modifier: Modifier, snackbarHostState: SnackbarHostState) {
    LazyColumn (modifier.fillMaxWidth()){
        items(getComarques()) { comarca ->
            ItemComarca(comarca, snackbarHostState)
        }
    }
}

@Composable
fun ItemComarca(comarca: Comarca, snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                scope.launch {
                    snackbarHostState.showSnackbar(comarca.descripcion)
                }
            }
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(comarca.imagen),
                    contentDescription = comarca.comarca,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = comarca.comarca,
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxWidth(),
                    fontSize = 20.sp,
                    fontFamily = leckenlioneregular,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = comarca.capital,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    text = "Habitants: ${comarca.poblacion}",
                    fontSize = 14.sp
                )

                HorizontalDivider()

                Text(
                    text = "Lat: ${comarca.latitud}",
                    fontSize = 14.sp
                )

                Text(
                    text = "Long: ${comarca.longitud}",
                    fontSize = 14.sp
                )
            }
        }
    }
}



fun getComarques(): List<Comarca> {
    return listOf(
        Comarca(
            "L'Alcalatén",
            "L'Alcora",
            "16.036",
            R.drawable.penyagolosa,
            "L'Alcalatén és una comarca del nord del País Valencià, amb capital a l'Alcora. Limita al nord amb l'Alt Maestrat i l'Aragó, a l'est amb la Plana Alta, al sud amb la Plana Baixa i a l'oest amb l'Alt Millars.",
            40.0744223,
            -0.2138589
        ),
        Comarca(
            "L'Alt Maestrat",
            "Albocàsser",
            "7.128",
            R.drawable.albocasser,
            "L'Alt Maestrat és una comarca valenciana del nord i muntanyosa, amb capital a Albocàsser. Limita al nord amb els Ports, a l'oest amb l'Aragó, al sud amb l'Alcalatén i a l'est amb el Baix Maestrat i la Plana Alta.",
            40.3571915,
            0.0245058
        ),
        Comarca("El Baix Maestrat",
            "Vinaròs",
            "82.120",
            R.drawable.alcala_chivert,
            "El Baix Maestrat és una comarca del nord del País Valencià, amb capital a Vinaròs. Fa de límit nord-oriental amb Catalunya, per la comarca del Montsià, i toca la comarca aragonesa del Matarranya. A l'est limita amb la mar Mediterrània. Al sud limita amb la Plana Alta i a l'interior (oest) limita amb l'Alt Maestrat i els Ports.",
            40.4703992,
            0.4746076
        ),
        Comarca(
            "La Plana Alta",
            "Castelló de la Plana",
            "243.056",
            R.drawable.castellon_de_la_plana,
            "La Plana Alta és una comarca costera del nord del País Valencià, amb capital a Castelló de la Plana.",
            39.9860347,
            -0.0377354
        ),
        Comarca(
            "La Plana Baixa",
            "Borriana",
            "191.859",
            R.drawable.mijares_sitjar_ribesalbes,
            "La Plana Baixa és una comarca valenciana, costanera i valencianoparlant del nord del País Valencià, amb capital a Borriana. Limita al nord amb la Plana Alta i l'Alcalatén, a l'est amb la mar Mediterrània, al sud amb el Camp de Morvedre, a l'oest amb l'Alt Millars i l'Alt Palància. ",
            39.8873342,
            -0.0838341
        ),
        Comarca(
            "Els Ports",
            "Morella",
            "4.434",
            R.drawable.morella,
            "Els Ports és una comarca valenciana del nord muntanyenca, amb capital a Morella; de paisatges espectaculars molt muntanyencs, nombrosos jaciments arqueològics i paleontològics, i gastronomia diversa. És la comarca amb major percentatge de valencianoparlants del País Valencià (més del 80% saben parlar valencià)",
            40.6188277,
            -0.0998026
        ),
        Comarca(
            "L'Alt Palànicia",
            "Sogorb",
            "24.737",
            R.drawable.sogorb,
            "L'Alt Palància és una comarca de l'interior del País Valencià, amb capital a Sogorb. Limita al nord amb l'Alt Millars, a l'est amb la Plana Baixa i el Camp de Morvedre, al sud amb el Camp de Túria i a l'oest amb els Serrans i l'Aragó.",
            39.8519011,
            -0.4895537
        ),
        Comarca(
            "L'Alt Millars",
            "Cirat",
            "4.055",
            R.drawable.alt_millars,
            "L'Alt Millars, o la Conca del Millars, és una comarca d'interior i muntanyosa del nord de la Comunitat Valenciana, amb capital a Cirat. Limita al nord i oest amb Aragó, a l'est amb l'Alcalatén i al sud amb la Plana Baixa i l'Alt Palància. Es tracta d'una de les dues comarques de l'interior de la província de Castelló on es parla el castellà.",
            40.0547973,
            -0.462651
        )
    )
}