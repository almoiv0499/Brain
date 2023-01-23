package ru.game.data.mapper

import ru.game.data.model.GameSettings
import ru.game.data.model.Question
import ru.game.domain.model.GameSettings
import ru.game.domain.model.Question

fun GameSettings.mapToGameSettingsDomain(): ru.game.domain.model.GameSettings =
    ru.game.domain.model.GameSettings(
        maxSumValue = maxSumValue,
        minCountOfRightAnswer = minCountOfRightAnswer,
        minPercentOfRightAnswer = minPercentOfRightAnswer,
        gameTimeInSecond = gameTimeInSecond
    )

fun Question.mapToQuestionDomain(): ru.game.domain.model.Question = ru.game.domain.model.Question(
    sum = sum,
    visibleNumber = visibleNumber,
    options = options
)