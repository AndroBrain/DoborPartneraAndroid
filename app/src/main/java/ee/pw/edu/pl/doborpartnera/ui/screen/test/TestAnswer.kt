package ee.pw.edu.pl.doborpartnera.ui.screen.test

import ee.pw.edu.pl.doborpartnera.R
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

val TestAnswer.answer
    get() = when (this) {
        YES -> R.string.answer_yes
        NO -> R.string.answer_no
        EYES_BROWN -> R.string.answer_eyes_brown
        EYES_GREEN -> R.string.answer_eyes_green
        EYES_GRAY -> R.string.answer_eyes_gray
        EYES_HAZEL -> R.string.answer_eyes_hazel
        EYES_HONEY -> R.string.answer_eyes_honey
        EYES_LIGHT_BLUE -> R.string.answer_eyes_light_blue
        EYES_DARK_BLUE -> R.string.answer_eyes_dark_blue
        EYES_AMETHYST -> R.string.answer_eyes_amethyst
        HAIR_BLONDE -> R.string.answer_hair_blond
        HAIR_BROWN -> R.string.answer_hair_brown
        HAIR_BLACK -> R.string.answer_hair_black
        HAIR_RED -> R.string.answer_hair_red
        RECREATION_HOME -> R.string.answer_recreation_home
        RECREATION_OUTSIDE -> R.string.answer_recreation_outside
        PEOPLE_SMALLER -> R.string.answer_people_smaller
        PEOPLE_BIGGER -> R.string.answer_people_bigger
        BELIEF_RIGHT -> R.string.answer_belief_right
        BELIEF_LIBERAL -> R.string.answer_belief_liberal
        BELIEF_LEFT -> R.string.answer_belief_left
        MONEY_HIGH -> R.string.answer_money_high
        MONEY_MEDIUM -> R.string.answer_money_medium
        MONEY_LOW -> R.string.answer_money_low
        MIND_LOGICAL -> R.string.answer_mind_logical
        MIND_INTERPERSONAL -> R.string.answer_mind_interpersonal
        MIND_VISUAL -> R.string.answer_mind_visual
        MIND_MUSICAL -> R.string.answer_mind_musical
        MIND_LINGUISTIC -> R.string.answer_mind_linguistic
        MIND_KINESTHETIC -> R.string.answer_mind_kinesthetic
        MIND_INTRAPERSONAL -> R.string.answer_mind_intrapersonal
        MIND_NATURAL -> R.string.answer_mind_natural
    }
