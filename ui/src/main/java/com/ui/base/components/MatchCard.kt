package com.ui.base.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.domain.models.Match
import com.fuze.R
import com.ui.base.theme.Colors
import com.ui.base.theme.Dimens
import com.ui.base.theme.FuzeTheme
import com.ui.base.theme.custom.Typography
import com.ui.base.theme.custom.redF42A35

@Composable
fun MatchCard(modifier: Modifier = Modifier, match: Match) {
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = Colors.backgroundMatchCard,
        shape = RoundedCornerShape(Dimens.biggest_corner_radius)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .background(
                        redF42A35,
                        shape = RoundedCornerShape(
                            topEnd = Dimens.biggest_corner_radius,
                            bottomStart = Dimens.biggest_corner_radius
                        )
                    )
                    .padding(Dimens.small_padding),
                text = "AGORA",
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
                Image(
                    painter = painterResource(id = R.drawable.league_preview),
                    contentDescription = ""
                )

                Text(
                    modifier = Modifier.padding(start = Dimens.small_padding),
                    text = "League + serie",
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