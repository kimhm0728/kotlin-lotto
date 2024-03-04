package lotto.utils

fun <T> retryWhileNoException(action: () -> T): T {
    return try {
        action()
    } catch (e: IllegalArgumentException) {
        println(e.localizedMessage)
        retryWhileNoException(action)
    }
}
