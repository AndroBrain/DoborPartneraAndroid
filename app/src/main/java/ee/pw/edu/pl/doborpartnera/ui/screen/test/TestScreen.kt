package ee.pw.edu.pl.doborpartnera.ui.screen.test

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.domain.usecase.account.TestQuestion

@Composable
fun TestScreen(
    modifier: Modifier = Modifier,
    viewModel: TestViewModel,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    state.value.errorMsg?.let { errorMsg ->
        val context = LocalContext.current
        LaunchedEffect(errorMsg) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            viewModel.clearErrorMsg()
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.test_title))
                },
            )
        }
    ) { insets ->
        LazyColumn(modifier = Modifier.padding(insets)) {
//            TODO weight, height (numbers) > questions with picker > send button
            items(TestQuestion.entries) { question ->
                Box {
                    TestQASection(
                        question = question,
                        answer = state.value.qa[question],
                        onClick = {
                            viewModel.setExpandedQuestion(question)
                        }
                    )
                    Box(
                        modifier = Modifier.align(Alignment.BottomEnd),
                    ) {
                        DropdownMenu(
                            expanded = state.value.expandedQuestion == question,
                            onDismissRequest = { viewModel.setExpandedQuestion(null) },
                        ) {
                            question.answers.forEach { answer ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = stringResource(id = answer.answer),
                                            textAlign = TextAlign.End,
                                        )
                                    },
                                    onClick = {
                                        viewModel.setAnswer(question, answer)
                                        viewModel.setExpandedQuestion(null)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
