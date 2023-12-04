package ee.pw.edu.pl.doborpartnera.ui.screen.test

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ee.pw.edu.pl.doborpartnera.ui.theme.App
import ee.pw.edu.pl.domain.usecase.account.TestAnswer
import ee.pw.edu.pl.domain.usecase.account.TestQuestion

@Composable
fun TestQASection(
    modifier: Modifier = Modifier,
    question: TestQuestion,
    answer: TestAnswer?,
    onClick: () -> Unit,
) {
    OutlinedCard(modifier = modifier, shape = RectangleShape) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(
                    horizontal = App.dimens.screen_spacing_small,
                    vertical = App.dimens.views_spacing_medium,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = stringResource(id = question.question),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.size(App.dimens.views_spacing_small))
            Box(modifier = Modifier.width(App.dimens.answer_width)) {
                if (answer == null) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        text = "...",
                        textAlign = TextAlign.End,
                    )
                } else {
                    Text(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        text = stringResource(id = answer.answer),
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
    }
}
