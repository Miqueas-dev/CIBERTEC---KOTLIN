package pe.cibertec.restaurante.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import pe.cibertec.restaurante.R
import pe.cibertec.restaurante.ui.model.Producto
import pe.cibertec.restaurante.ui.model.productosDemo

data class NavigationItem(
    val title: String,
    val icon: Int
)

@Composable
fun MainScreen(
    onLogoutClick: () -> Unit = {}
) {

    var selectedItem by remember {
        mutableStateOf(0)
    }

    var productos by remember {
        mutableStateOf(productosDemo)
    }

    var productoSeleccionado by remember {
        mutableStateOf<Producto?>(null)
    }

    val navigationItems = listOf(

        NavigationItem(
            title = "Inicio",
            icon = R.drawable.ic_restaurant
        ),

        NavigationItem(
            title = "Productos",
            icon = R.drawable.ic_products
        ),

        NavigationItem(
            title = "Ofertas",
            icon = R.drawable.ic_orders
        ),

        NavigationItem(
            title = "Perfil",
            icon = R.drawable.ic_person
        )
    )

    Scaffold(
        bottomBar = {

            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {

                navigationItems.forEachIndexed { index, item ->

                    NavigationBarItem(
                        selected = selectedItem == index,

                        onClick = {
                            selectedItem = index
                            productoSeleccionado = null
                        },

                        icon = {
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = item.title
                            )
                        },

                        label = {
                            Text(
                                text = item.title
                            )
                        },

                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor =
                                MaterialTheme.colorScheme.primary,

                            indicatorColor =
                                MaterialTheme.colorScheme.primaryContainer,

                            unselectedIconColor =
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }

    ) { innerPadding ->

        when (selectedItem) {

            // INICIO
            0 -> {

                InicioScreen(
                    modifier = Modifier.padding(innerPadding),

                    onProductosClick = {
                        selectedItem = 1
                    },

                    onOfertasClick = {
                        selectedItem = 2
                    },

                    onPerfilClick = {
                        selectedItem = 3
                    }
                )
            }

            // PRODUCTOS
            1 -> {

                if (productoSeleccionado == null) {

                    ListaProductosScreen(
                        modifier = Modifier.padding(innerPadding),

                        productos = productos,

                        onProductoClick = { producto ->
                            productoSeleccionado = producto
                        },

                        onAgregarClick = {
                            // Luego se conecta con Nuevo Producto
                        },

                        onBack = {
                            selectedItem = 0
                        }
                    )

                } else {

                    DetalleProductoScreen(
                        modifier = Modifier.padding(innerPadding),

                        producto = productoSeleccionado!!,

                        onBack = {
                            productoSeleccionado = null
                        },

                        onEditar = {
                            // Luego se conecta con Editar Producto
                        },

                        onEliminar = {

                            productos = productos.filter {
                                it.id != productoSeleccionado!!.id
                            }

                            productoSeleccionado = null
                        }
                    )
                }
            }

            // OFERTAS
            2 -> {

                TemporaryScreen(
                    title = "Ofertas",
                    modifier = Modifier.padding(innerPadding)
                )
            }

            // PERFIL
            3 -> {

                PerfilScreen(
                    modifier = Modifier.padding(innerPadding),
                    onLogoutClick = onLogoutClick
                )
            }
        }
    }
}

@Composable
fun TemporaryScreen(
    title: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = title,

            style =
                MaterialTheme.typography.headlineMedium,

            color =
                MaterialTheme.colorScheme.primary
        )
    }
}