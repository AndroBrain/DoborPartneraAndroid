package ee.pw.edu.pl.domain.usecase.test

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

enum class TestQuestion(
    val answers: List<TestAnswer>,
) {
    EYES(
        answers = listOf(
            EYES_GREEN,
            EYES_GRAY,
            EYES_HAZEL,
            EYES_HONEY,
            EYES_LIGHT_BLUE,
            EYES_BROWN,
            EYES_AMETHYST,
            EYES_DARK_BLUE,
        )
    ),
    HAIR(
        answers = listOf(
            HAIR_BLONDE, HAIR_BROWN, HAIR_BLACK, HAIR_RED,
        )
    ),
    TATTOO(answers = listOf(YES, NO)),
    SPORT(answers = listOf(YES, NO)),

    EDUCATION(answers = listOf(YES, NO)),
    RECREATION(answers = listOf(RECREATION_HOME, RECREATION_OUTSIDE)),
    FAMILY(answers = listOf(YES, NO)),
    CHARITY(answers = listOf(YES, NO)),
    PEOPLE(answers = listOf(PEOPLE_SMALLER, PEOPLE_BIGGER)),
    WEDDING(answers = listOf(YES, NO)),
    BELIEF(answers = listOf(BELIEF_LEFT, BELIEF_LIBERAL, BELIEF_RIGHT)),
    MONEY(answers = listOf(MONEY_HIGH, MONEY_MEDIUM, MONEY_LOW)),
    RELIGIOUS(answers = listOf(YES, NO)),
    HUMOUR(answers = listOf(YES, NO)),
    MIND(
        answers = listOf(
            MIND_LOGICAL,
            MIND_INTERPERSONAL,
            MIND_VISUAL,
            MIND_MUSICAL,
            MIND_LINGUISTIC,
            MIND_KINESTHETIC,
            MIND_INTRAPERSONAL,
            MIND_NATURAL,
        )
    ),
}
