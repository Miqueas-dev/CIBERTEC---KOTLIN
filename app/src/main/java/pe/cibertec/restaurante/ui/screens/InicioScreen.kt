package pe.cibertec.restaurante.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.cibertec.restaurante.R
import pe.cibertec.restaurante.ui.theme.RestauranteAppTheme

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    onProductosClick: () -> Unit = {},
    onOfertasClick: () -> Unit = {},
    onPerfilClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 24.dp,
                    vertical = 28.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "D’Verano",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Bienvenido, D'Henry",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "¿Qué deseas gestionar hoy?",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(28.dp)
            ) {
                Column {

                    Text(
                        text = "Gestión del restaurante",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "Administra productos, ofertas y la información de D’Verano.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Text(
                text = "Accesos rápidos",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium
            )

            AccesoCard(
                icono = R.drawable.ic_restaurant,
                titulo = "Menú del restaurante",
                descripcion = "Consulta los platos disponibles",
                onClick = {}
            )

            AccesoCard(
                icono = R.drawable.ic_products,
                titulo = "Productos",
                descripcion = "Administra los productos del menú",
                onClick = onProductosClick
            )

            AccesoCard(
                icono = R.drawable.ic_orders,
                titulo = "Ofertas",
                descripcion = "Revisa y configura promociones",
                onClick = onOfertasClick
            )

            AccesoCard(
                icono = R.drawable.ic_person,
                titulo = "Mi perfil",
                descripcion = "Consulta los datos de tu cuenta",
                onClick = onPerfilClick
            )
        }
    }
}

@Composable
private fun AccesoCard(
    icono: Int,
    titulo: String,
    descripcion: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                onClick()
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.tertiary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icono),
                contentDescription = titulo,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.onTertiary
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .weight(1f)
        ) {

            Text(
                text = titulo,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = descripcion,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioScreenPreview() {
    RestauranteAppTheme {
        InicioScreen()
    }
}