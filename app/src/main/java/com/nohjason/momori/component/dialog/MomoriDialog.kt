package com.nohjason.momori.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.MomoriButton
import com.nohjason.momori.component.theme.Body
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.component.theme.Title
import com.nohjason.momori.ui.theme.MomoriTheme

@Composable
fun MomoriDialog(
    title: String,
    primaryButton: @Composable () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Horizontal = Arrangement.End,
    description: String? = null,
    secondaryButton: @Composable (() -> Unit)? = null,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = modifier
                .background(
                    color = MomoriColor.White,
                    shape = MomoriTheme.shape.medium,
                )
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
        ) {
            Title(text = title, modifier = Modifier.align(Alignment.CenterHorizontally))
            description?.let {
                Body(
                    text = it,
                    textColor = MomoriColor.Gray300,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 12.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = arrangement,
            ) { 
                secondaryButton?.let {
                    it()
                    Spacer(modifier = Modifier.width(16.dp))
                }
                primaryButton()
            }
        }
    }
}




@Preview
@Composable
private fun DialogPreview1() {
    var showPrompt by remember {
        mutableStateOf(true)
    }

    if (showPrompt) {
        MomoriDialog(
            title = "정말 삭제하시겠습니까?",
            primaryButton = {
                MomoriButton(text = "삭제하기", type = ButtonType.Red) {
                    showPrompt = false
                }
            },
            secondaryButton = {
                MomoriButton(text = "아니요", type = ButtonType.Transparent) {
                    showPrompt = false
                }
            },
            onDismiss = { showPrompt = false }
        )
    }

    Box(Modifier.fillMaxSize()) {
        MomoriButton(text = "Show Prompt", type = ButtonType.DarkGray) {
            showPrompt = true
        }
    }
}


@Preview
@Composable
private fun DialogPreview2() {
    var showPrompt by remember {
        mutableStateOf(true)
    }

    if (showPrompt) {
        MomoriDialog(
            title = "잠시만요..",
            description = "권한이 있어야 앱을 이용하실 수 있습니다",
            arrangement = Arrangement.Center,
            primaryButton = {
                MomoriButton(text = "권한 요청", type = ButtonType.Mint) {
                    showPrompt = false
                }
            },
            onDismiss = { showPrompt = false }
        )
    }

    Box(Modifier.fillMaxSize()) {
        MomoriButton(text = "Show Prompt", type = ButtonType.DarkGray) {
            showPrompt = true
        }
    }
}
