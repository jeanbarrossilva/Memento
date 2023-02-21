object Metadata {
    const val GROUP = "com.jeanbarrossilva.memento"
    const val ARTIFACT = "memento"
    const val NAMESPACE = GROUP

    fun namespace(suffix: String): String {
        return "$NAMESPACE.$suffix"
    }
}
