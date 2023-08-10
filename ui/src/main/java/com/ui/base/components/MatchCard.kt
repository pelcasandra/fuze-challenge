package com.ui.base.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.domain.match.models.Match
import com.domain.match.models.Status
import com.domain.utils.getDayOfWeek
import com.fuze.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.ui.base.theme.Colors
import com.ui.base.theme.Dimens
import com.ui.base.theme.FuzeTheme
import com.ui.base.theme.custom.Typography
import com.ui.base.theme.custom.redF42A35

@Composable
fun MatchCard(modifier: Modifier = Modifier, match: Match, onClick: (String) -> Unit) {
    val matchTime = when (match.status) {
        Status.Running -> stringResource(id = R.string.match_card_time_now)
        else -> match.beginAt?.getDayOfWeek() ?: ""
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick.invoke(matchTime) },
        backgroundColor = Colors.backgroundComponent,
        shape = RoundedCornerShape(Dimens.biggest_corner_radius)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .background(
                        when (match.status) {
                            Status.Running -> redF42A35
                            else -> Color.White.copy(alpha = 0.2f)
                        },
                        shape = RoundedCornerShape(
                            topEnd = Dimens.biggest_corner_radius,
                            bottomStart = Dimens.biggest_corner_radius
                        )
                    )
                    .padding(Dimens.small_padding),
                text = matchTime,
                style = Typography.Label.xxsmall.copy(
                    fontSize = 8.sp,
                    fontWeight = FontWeight.W700
                ),
                color = Color.White
            )

            MatchTeam(
                modifier = Modifier.padding(top = Dimens.small_padding),
                entity = MatchTeamEntity(
                    firstTeam = match.opponents!![0],
                    secondTeam = match.opponents!![1]
                )
            )

            Divider(
                modifier = Modifier.padding(top = Dimens.small_padding),
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.2f)
            )

            Row(
                modifier = Modifier.padding(Dimens.smedium_padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (match.league.image.isNotEmpty())
                    GlideImage(
                        modifier = Modifier.size(16.dp),
                        imageModel = match.league.image,
                        imageOptions = ImageOptions(contentScale = ContentScale.Fit)
                    )
                else
                    Image(
                        painter = painterResource(id = R.drawable.league_preview),
                        contentDescription = ""
                    )

                Text(
                    modifier = Modifier.padding(start = Dimens.small_padding),
                    text = "${match.league.name} ${match.serie.name}",
                    style = Typography.Label.xsmall.copy(fontSize = 8.sp),
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun PrevMatchCard() {
    FuzeTheme {
//        MatchCard()
    }
}