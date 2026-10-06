package pe.cibertec.restaurante.ui.navigation

sealed class AppDestinations(
    val route: String
) {
    data object Login : AppDestinations("login")

    data object Register : AppDestinations("register")

    data object Inicio : AppDestinations("inicio")
}