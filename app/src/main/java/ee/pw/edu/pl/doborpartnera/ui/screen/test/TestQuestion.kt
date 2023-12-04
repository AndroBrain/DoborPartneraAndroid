package ee.pw.edu.pl.doborpartnera.ui.screen.test

import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.domain.usecase.account.TestQuestion

val TestQuestion.question
    get() = when (this) {
        TestQuestion.EYES -> R.string.question_eyes
        TestQuestion.HAIR -> R.string.question_hair
        TestQuestion.TATTOO -> R.string.question_tattoo
        TestQuestion.SPORT -> R.string.question_sport
        TestQuestion.EDUCATION -> R.string.question_education
        TestQuestion.RECREATION -> R.string.question_recreation
        TestQuestion.FAMILY -> R.string.question_family
        TestQuestion.CHARITY -> R.string.question_charity
        TestQuestion.PEOPLE -> R.string.question_people
        TestQuestion.WEDDING -> R.string.question_wedding
        TestQuestion.BELIEF -> R.string.question_belief
        TestQuestion.MONEY -> R.string.question_money
        TestQuestion.RELIGIOUS -> R.string.question_religious
        TestQuestion.MIND -> R.string.question_mind
        TestQuestion.HUMOUR -> R.string.question_humour
    }
