package ee.pw.edu.pl.domain.usecase.profile

data class ProfileAvatar(
    val bytes: ByteArray?,
    val url: String?,
    val format: String,
) {
    init {
        require((bytes == null && url != null) || (bytes != null && url == null)) {
            "Both bytes and url cannot be null or non-null"
        }
    }
}
