package ee.pw.edu.pl.data.model.account

import ee.pw.edu.pl.data.model.account.remote.GetAccountInfoResponse
import ee.pw.edu.pl.data.model.account.remote.SetTestRequest
import ee.pw.edu.pl.domain.usecase.account.Account
import ee.pw.edu.pl.domain.usecase.account.SetTestForm
import ee.pw.edu.pl.domain.usecase.account.TestAnswer
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.BELIEF_LEFT
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.BELIEF_LIBERAL
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.BELIEF_RIGHT
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.EYES_BLUE
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.EYES_BROWN
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.EYES_GREEN
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.HAIR_BLONDE
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.HAIR_BROWN
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.HAIR_RED
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.MIND_ARTISTIC
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.MIND_HUMANISTIC
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.MIND_SCIENTIFIC
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.MONEY_HIGH
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.MONEY_LOW
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.MONEY_MEDIUM
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.NO
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.PEOPLE_BIGGER
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.PEOPLE_SMALLER
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.RECREATION_HOME
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.RECREATION_OUTSIDE
import ee.pw.edu.pl.domain.usecase.account.TestAnswer.YES

fun GetAccountInfoResponse.toModel() =
    Account(
        name = name,
        surname = surname,
        avatar = avatar,
        shortDescription = description,
        images = images,
        interests = interests,
    )

fun SetTestForm.toRequest() =
    SetTestRequest(
        eyes = eyes.toRequest(),
        hair = hair.toRequest(),
        tattoo = tattoo.toRequest(),
        sport = sport.toRequest(),
        education = education.toRequest(),
        recreation = recreation.toRequest(),
        family = family.toRequest(),
        charity = charity.toRequest(),
        people = people.toRequest(),
        wedding = wedding.toRequest(),
        belief = belief.toRequest(),
        money = money.toRequest(),
        religious = religious.toRequest(),
        mind = mind.toRequest(),
        humour = humour.toRequest()
    )

fun TestAnswer.toRequest() = when (this) {
    NO -> 0
    YES -> 1
    EYES_BROWN -> 0
    EYES_GREEN -> 1
    EYES_BLUE -> 2
    HAIR_BLONDE -> 0
    HAIR_BROWN -> 1
    HAIR_RED -> 2
    RECREATION_HOME -> 0
    RECREATION_OUTSIDE -> 1
    PEOPLE_SMALLER -> 0
    PEOPLE_BIGGER -> 1
    BELIEF_RIGHT -> 0
    BELIEF_LIBERAL -> 1
    BELIEF_LEFT -> 2
    MONEY_HIGH -> 0
    MONEY_MEDIUM -> 1
    MONEY_LOW -> 2
    MIND_SCIENTIFIC -> 0
    MIND_HUMANISTIC -> 1
    MIND_ARTISTIC -> 2
}
