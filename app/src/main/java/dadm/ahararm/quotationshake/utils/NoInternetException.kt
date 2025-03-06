package dadm.ahararm.quotationshake.utils

class NoInternetException : Exception() {
    override val message: String
        get() = "No internet connection"
}