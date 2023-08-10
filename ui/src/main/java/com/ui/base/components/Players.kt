package com.ui.base.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.domain.team.models.Player
import com.fuze.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.ui.base.theme.Colors
import com.ui.base.theme.Dimens
import com.ui.base.theme.FuzeTheme
import com.ui.base.theme.custom.Typography
import com.ui.base.theme.custom.blue6C6B7E
import java.util.Date

@Composable
fun Players(
    modifier: Modifier = Modifier,
    entity: PlayersTeamEntity
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.xxsmall_padding)
    ) {
        val (
            firstPlayerBox,
            firstPlayerColumn,
            firstPlayerImage,
            secondPlayerBox,
            secondPlayerColumn,
            secondPlayerImage
        ) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(firstPlayerBox) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth(0.48f)
                .height(54.dp)
                .background(
                    Colors.backgroundComponent,
                    shape = RoundedCornerShape(
                        topEnd = Dimens.biggest_corner_radius,
                        bottomEnd = Dimens.biggest_corner_radius
                    )
                )
        )

        Column(
            modifier = Modifier
                .constrainAs(firstPlayerColumn) {
                    top.linkTo(firstPlayerBox.top)
                    bottom.linkTo(firstPlayerBox.bottom)
                    end.linkTo(firstPlayerImage.start)
                }
                .padding(end = Dimens.medium_padding)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = Dimens.xxsmall_padding)
                    .align(Alignment.End),
                text = entity.playerFirstTeam.nickName,
                style = Typography.Label.xsmall.copy(fontSize = 14.sp),
                color = Color.White,
                textAlign = TextAlign.End
            )

            Text(
                modifier = Modifier.align(Alignment.End),
                text = entity.playerFirstTeam.name,
                style = Typography.Label.xsmall.copy(fontSize = 12.sp),
                color = blue6C6B7E,
                textAlign = TextAlign.End
            )
        }

        if (entity.playerFirstTeam.image.isEmpty())
            Image(
                modifier = Modifier
                    .constrainAs(firstPlayerImage) {
                        end.linkTo(firstPlayerBox.end)
                        top.linkTo(firstPlayerBox.top)
                        bottom.linkTo(firstPlayerColumn.bottom)
                    }
                    .padding(end = Dimens.smedium_padding, bottom = Dimens.small_padding),
                painter = painterResource(id = R.drawable.player_preview),
                contentDescription = ""
            )
        else
            GlideImage(
                modifier = Modifier
                    .constrainAs(firstPlayerImage) {
                        end.linkTo(firstPlayerBox.end)
                        top.linkTo(firstPlayerBox.top)
                        bottom.linkTo(firstPlayerColumn.bottom)
                    }
                    .padding(end = Dimens.smedium_padding, bottom = Dimens.small_padding)
                    .size(50.dp)
                    .shadow(0.dp, RoundedCornerShape(Dimens.big_corner_radius))
                    .clip(RoundedCornerShape(Dimens.big_corner_radius)),
                imageModel = entity.playerFirstTeam.image,
                imageOptions = ImageOptions(contentScale = ContentScale.FillBounds)
            )

        Box(
            modifier = Modifier
                .constrainAs(secondPlayerBox) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(0.48f)
                .height(54.dp)
                .background(
                    Colors.backgroundComponent,
                    shape = RoundedCornerShape(
                        topStart = Dimens.biggest_corner_radius,
                        bottomStart = Dimens.biggest_corner_radius
                    )
                )
        )

        Column(
            modifier = Modifier
                .constrainAs(secondPlayerColumn) {
                    top.linkTo(secondPlayerBox.top)
                    bottom.linkTo(secondPlayerBox.bottom)
                    start.linkTo(secondPlayerImage.end)
                }
                .padding(start = Dimens.medium_padding)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = Dimens.xxsmall_padding)
                    .align(Alignment.Start),
                text = entity.playerSecondTeam.nickName,
                style = Typography.Label.xsmall.copy(fontSize = 14.sp),
                color = Color.White,
                textAlign = TextAlign.End
            )

            Text(
                modifier = Modifier.align(Alignment.Start),
                text = entity.playerSecondTeam.name,
                style = Typography.Label.xsmall.copy(fontSize = 12.sp),
                color = blue6C6B7E,
                textAlign = TextAlign.End
            )
        }

        if (entity.playerSecondTeam.image.isEmpty())
            Image(
                modifier = Modifier
                    .constrainAs(secondPlayerImage) {
                        start.linkTo(secondPlayerBox.start)
                        top.linkTo(secondPlayerBox.top)
                        bottom.linkTo(secondPlayerColumn.bottom)
                    }
                    .padding(start = Dimens.smedium_padding, bottom = Dimens.small_padding),
                painter = painterResource(id = R.drawable.player_preview),
                contentDescription = ""
            )
        else
            GlideImage(
                modifier = Modifier
                    .constrainAs(secondPlayerImage) {
                        start.linkTo(secondPlayerBox.start)
                        top.linkTo(secondPlayerBox.top)
                        bottom.linkTo(secondPlayerColumn.bottom)
                    }
                    .padding(start = Dimens.smedium_padding, bottom = Dimens.small_padding)
                    .size(50.dp)
                    .shadow(0.dp, RoundedCornerShape(Dimens.big_corner_radius))
                    .clip(RoundedCornerShape(Dimens.big_corner_radius)),
                imageModel = entity.playerSecondTeam.image,
                imageOptions = ImageOptions(contentScale = ContentScale.FillBounds)
            )
    }
}

data class PlayersTeamEntity(
    val playerFirstTeam: Player,
    val playerSecondTeam: Player
)


@Preview
@Composable
private fun PrevMatchTeam() {
    FuzeTheme {
        Players(
            entity = PlayersTeamEntity(
                Player(0, "João", "Jvrni", "", Date()),
                Player(0, "João", "Jvrni", "", Date())
            )
        )
    }
}