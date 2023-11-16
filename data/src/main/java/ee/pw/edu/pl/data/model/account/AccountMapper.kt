package ee.pw.edu.pl.data.model.account

import ee.pw.edu.pl.data.model.account.remote.GetAccountInfoResponse
import ee.pw.edu.pl.domain.usecase.account.Account

fun GetAccountInfoResponse.toModel() =
    Account(
        name = name,
        surname = surname,
        avatar = avatar,
        shortDescription = description,
        images = images,
        interests = interests,
    )
