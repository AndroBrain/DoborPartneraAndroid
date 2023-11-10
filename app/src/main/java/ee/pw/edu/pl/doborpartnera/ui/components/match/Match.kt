package ee.pw.edu.pl.doborpartnera.ui.components.match

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.skydoves.landscapist.glide.GlideImage
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.theme.App
import ee.pw.edu.pl.domain.usecase.match.MatchProfile
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Match(
    modifier: Modifier = Modifier,
    profile: MatchProfile,
    onAccept: (() -> Unit)? = null,
    onDecline: (() -> Unit)? = null,
) {
    val pagerState = rememberPagerState { profile.images.size }
    ConstraintLayout(modifier = modifier) {
        val (background, profileImage, name, shortDescription, choicesContainer, nextButton, previousButton) = createRefs()
        HorizontalPager(
            state = pagerState,
            pageContent = { imageIndex ->
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    imageModel = { profile.images[imageIndex] },
                    loading = {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                )
            }
        )
        val coroutineScope = rememberCoroutineScope()

        if (pagerState.currentPage > 0) {
            IconButton(
                modifier = Modifier
                    .constrainAs(previousButton) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                onClick = {
                    coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }

        if (pagerState.currentPage < profile.images.lastIndex) {
            IconButton(
                modifier = Modifier
                    .constrainAs(nextButton) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                onClick = {
                    coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(background) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(profileImage.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding(top = App.dimens.find_match_profile_image_size / 2)
                .background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8F))
        )
        Text(
            modifier = Modifier
                .padding(start = App.dimens.views_spacing_small)
                .padding(bottom = App.dimens.views_spacing_small)
                .constrainAs(name) {
                    bottom.linkTo(profileImage.bottom)
                    start.linkTo(profileImage.end)
                },
            text = stringResource(id = R.string.match_find_name, profile.name, profile.age),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.surface,
        )
        GlideImage(
            modifier = Modifier
                .padding(start = App.dimens.screen_spacing_medium)
                .padding(bottom = App.dimens.views_spacing_small)
                .size(App.dimens.find_match_profile_image_size)
                .clip(CircleShape)
                .constrainAs(profileImage) {
                    start.linkTo(parent.start)
                    bottom.linkTo(shortDescription.top)
                },
            imageModel = { profile.avatar },
            loading = {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            },
        )

        Text(
            modifier = Modifier
                .constrainAs(shortDescription) {
                    start.linkTo(parent.start)
                    bottom.linkTo(choicesContainer.top)
                }
                .padding(bottom = App.dimens.views_spacing_medium)
                .padding(horizontal = App.dimens.screen_spacing_medium),
            text = profile.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.surface,
        )

        MatchChoices(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(choicesContainer) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(bottom = App.dimens.screen_spacing_medium)
                .padding(horizontal = App.dimens.screen_spacing_medium),
            onAccept = onAccept,
            onDecline = onDecline,
        )
    }
}
