package examen.moviles.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform