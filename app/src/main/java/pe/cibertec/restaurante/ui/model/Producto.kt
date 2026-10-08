package pe.cibertec.restaurante.ui.model

import java.util.Locale

data class Producto(
    val id: Int,
    val nombre: String,
    val categoria: String,
    val precio: Double,
    val descripcion: String,
    val estado: String
) {

    fun precioFormateado(): String {
        return "S/ " + String.format(Locale.US, "%.2f", precio)
    }

    fun estaActivo(): Boolean {
        return estado == "Activo"
    }
}