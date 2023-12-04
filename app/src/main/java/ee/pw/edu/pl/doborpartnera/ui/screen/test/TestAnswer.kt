package ee.pw.edu.pl.doborpartnera.ui.screen.test

import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.domain.usecase.account.TestAnswer

val TestAnswer.answer
    get() = when (this) {
        TestAnswer.YES -> R.string.answer_yes
        TestAnswer.NO -> R.string.answer_no
        TestAnswer.EYES_BROWN -> R.string.answer_eyes_brown
        TestAnswer.EYES_GREEN -> R.string.answer_eyes_green
        TestAnswer.EYES_BLUE -> R.string.answer_eyes_blue
        TestAnswer.HAIR_BLONDE -> R.string.answer_hair_blond
        TestAnswer.HAIR_BROWN -> R.string.answer_hair_brown
        TestAnswer.HAIR_RED -> R.string.answer_hair_red
        TestAnswer.RECREATION_HOME -> R.string.answer_recreation_home
        TestAnswer.RECREATION_OUTSIDE -> R.string.answer_recreation_outside
        TestAnswer.PEOPLE_SMALLER -> R.string.answer_people_smaller
        TestAnswer.PEOPLE_BIGGER -> R.string.answer_people_bigger
        TestAnswer.BELIEF_RIGHT -> R.string.answer_belief_right
        TestAnswer.BELIEF_LIBERAL -> R.string.answer_belief_liberal
        TestAnswer.BELIEF_LEFT -> R.string.answer_belief_left
        TestAnswer.MONEY_HIGH -> R.string.answer_money_high
        TestAnswer.MONEY_MEDIUM -> R.string.answer_money_medium
        TestAnswer.MONEY_LOW -> R.string.answer_money_low
        TestAnswer.MIND_SCIENTIFIC -> R.string.answer_mind_scientific
        TestAnswer.MIND_HUMANISTIC -> R.string.answer_mind_humanistic
        TestAnswer.MIND_ARTISTIC -> R.string.answer_mind_artistic
    }
