package ee.pw.edu.pl.data.model.account

import ee.pw.edu.pl.data.model.account.remote.GetAccountInfoResponse
import ee.pw.edu.pl.data.model.account.remote.SetTestRequest
import ee.pw.edu.pl.domain.usecase.account.Account
import ee.pw.edu.pl.domain.usecase.test.SetTestForm
import ee.pw.edu.pl.domain.usecase.test.TestAnswer
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.BELIEF_LEFT
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.BELIEF_LIBERAL
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.BELIEF_RIGHT
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.EYES_AMETHYST
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.EYES_BROWN
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.EYES_DARK_BLUE
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.EYES_GRAY
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.EYES_GREEN
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.EYES_HAZEL
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.EYES_HONEY
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.EYES_LIGHT_BLUE
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.HAIR_BLACK
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.HAIR_BLONDE
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.HAIR_BROWN
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.HAIR_RED
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MIND_INTERPERSONAL
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MIND_INTRAPERSONAL
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MIND_KINESTHETIC
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MIND_LINGUISTIC
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MIND_LOGICAL
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MIND_MUSICAL
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MIND_NATURAL
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MIND_VISUAL
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MONEY_HIGH
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MONEY_LOW
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.MONEY_MEDIUM
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.NO
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.PEOPLE_BIGGER
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.PEOPLE_SMALLER
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.RECREATION_HOME
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.RECREATION_OUTSIDE
import ee.pw.edu.pl.domain.usecase.test.TestAnswer.YES

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
    EYES_GRAY -> 2
    EYES_HAZEL -> 3
    EYES_HONEY -> 4
    EYES_LIGHT_BLUE -> 5
    EYES_DARK_BLUE -> 6
    EYES_AMETHYST -> 7
    HAIR_BLONDE -> 0
    HAIR_BROWN -> 1
    HAIR_BLACK -> 2
    HAIR_RED -> 3
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
    MIND_LOGICAL -> 0
    MIND_INTERPERSONAL -> 1
    MIND_VISUAL -> 2
    MIND_MUSICAL -> 3
    MIND_LINGUISTIC -> 4
    MIND_KINESTHETIC -> 5
    MIND_INTRAPERSONAL -> 6
    MIND_NATURAL -> 7
}
