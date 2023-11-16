package ee.pw.edu.pl.data.model.profile

import ee.pw.edu.pl.data.model.profile.local.ProfileEntity
import ee.pw.edu.pl.domain.usecase.profile.Profile

fun List<Profile>.toEntities() = map { it.toEntity() }

fun Profile.toEntity() = ProfileEntity(
    id = id,
    name = name,
    avatar = avatar
)
