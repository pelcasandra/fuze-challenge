package com.ui.base.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.domain.models.Opponent
import com.fuze.R
import com.ui.base.theme.Dimens
import com.ui.base.theme.FuzeTheme
import com.ui.base.theme.custom.Typography

@Composable
fun MatchTeam(
    modifier: Modifier = Modifier,
    entity: MatchTeamEntity
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.padding(Dimens.medium_padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.team_preview), contentDescription = "")
            Text(
                modifier = Modifier.padding(top = Dimens.small_padding),
                text = entity.firstTeam.name,
                style = Typography.Label.xsmall.copy(fontSize = 10.sp),
                color = Color.White
            )
        }

        Text(
            text = "vs",
            style = Typography.Label.xxsmall,
            color = Color.White.copy(alpha = 0.5f)
        )

        Column(
            modifier = Modifier.padding(Dimens.medium_padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.team_preview), contentDescription = "")
            Text(
                modifier = Modifier.padding(top = Dimens.small_padding),
                text = entity.secondTeam.name,
                style = Typography.Label.xsmall.copy(fontSize = 10.sp),
                color = Color.White
            )
        }
    }
}

data class MatchTeamEntity(
    val firstTeam: Opponent,
    val secondTeam: Opponent
)

@Preview
@Composable
private fun PrevMatchTeam() {
    FuzeTheme {
//        MatchTeam()
    }
}