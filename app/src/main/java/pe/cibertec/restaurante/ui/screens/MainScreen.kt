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

    val navigationItems = listOf(
        NavigationItem(
            title = "Inicio",
            icon = R.drawable.ic_restaurant
        ),
        NavigationItem(
            title = "Pedidos",
            icon = R.drawable.ic_orders
        ),
        NavigationItem(
            title = "Productos",
            icon = R.drawable.ic_products
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
                        },
                        icon = {
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = item.title
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

            0 -> {
                InicioScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }

            1 -> {
                TemporaryScreen(
                    title = "Pedidos",
                    modifier = Modifier.padding(innerPadding)
                )
            }

            2 -> {
                TemporaryScreen(
                    title = "Productos",
                    modifier = Modifier.padding(innerPadding)
                )
            }

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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}