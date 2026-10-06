package pe.cibertec.restaurante.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.cibertec.restaurante.ui.components.ProductoItem
import pe.cibertec.restaurante.ui.model.Producto
import pe.cibertec.restaurante.ui.model.productosDemo
import pe.cibertec.restaurante.ui.theme.RestauranteAppTheme

@Composable
fun ListaProductosScreen(
    modifier: Modifier = Modifier,
    productos: List<Producto> = productosDemo,
    onProductoClick: (Producto) -> Unit = {},
    onAgregarClick: () -> Unit = {},
    onBack: () -> Unit = {}
) {

    var busqueda by remember {
        mutableStateOf("")
    }

    val productosFiltrados = productos.filter {
        it.nombre.contains(
            busqueda,
            ignoreCase = true
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // ENCABEZADO
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "←",
                modifier = Modifier
                    .clickable {
                        onBack()
                    }
                    .padding(8.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Productos",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        // BUSCADOR
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                ),
            value = busqueda,
            onValueChange = {
                busqueda = it
            },
            placeholder = {
                Text("Buscar producto...")
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        // LISTA
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                bottom = 80.dp
            )
        ) {

            items(productosFiltrados) { producto ->

                ProductoItem(
                    producto = producto,
                    onClick = {
                        onProductoClick(producto)
                    }
                )
            }
        }

        // BOTÓN +
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = 16.dp,
                    bottom = 12.dp
                ),
            horizontalArrangement = Arrangement.End
        ) {

            FloatingActionButton(
                onClick = onAgregarClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaProductosScreenPreview() {
    RestauranteAppTheme {
        ListaProductosScreen()
    }
}