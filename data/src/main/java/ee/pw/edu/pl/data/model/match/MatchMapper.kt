package ee.pw.edu.pl.data.model.match

import ee.pw.edu.pl.data.model.match.remote.MatchResponse
import ee.pw.edu.pl.domain.usecase.match.MatchProfile
import java.util.*
import java.util.concurrent.TimeUnit

fun List<MatchResponse>.toModels() = map { it.toModel() }
fun MatchResponse.toModel() = MatchProfile(
    id = userId,
    name = name,
    age = inYearsToNow(birthdate).toString(),
    description = description,
    avatar = avatar,
    images = images,
    interests = interests,
)

private fun inYearsToNow(startDate: Date): Long {
    val startCalendar = Calendar.getInstance()
    startCalendar.time = startDate
    val currentCalendar = Calendar.getInstance()
    val diffInMillis = currentCalendar.timeInMillis - startCalendar.timeInMillis
    return TimeUnit.MILLISECONDS.toDays(diffInMillis) / 365
}
