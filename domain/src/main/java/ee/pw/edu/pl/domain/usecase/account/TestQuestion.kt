package ee.pw.edu.pl.domain.usecase.account

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

enum class TestQuestion(
    val answers: List<TestAnswer>,
) {
    EYES(answers = listOf(EYES_BROWN, EYES_GREEN, EYES_BLUE)),
    HAIR(answers = listOf(HAIR_BLONDE, HAIR_BROWN, HAIR_RED)),
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
    MIND(answers = listOf(MIND_SCIENTIFIC, MIND_HUMANISTIC, MIND_ARTISTIC)),
    HUMOUR(answers = listOf(YES, NO)),
}
