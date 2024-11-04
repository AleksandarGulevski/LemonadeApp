package com.denofdevelopers.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denofdevelopers.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
private fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        when (currentStep) {
            1 -> {
                LemonImageAndText(
                    textTitleId = R.string.select_lemon,
                    imageId = R.drawable.lemon_tree,
                    contentDescId = R.string.select_lemon_desc,
                    onImageClick = {
                        //update step to next one
                        currentStep = 2
                        //generate random number between 2 and 4 for squeeze count
                        squeezeCount = (2..4).random()
                    }
                )
            }

            2 -> {
                LemonImageAndText(
                    textTitleId = R.string.squeeze_lemon,
                    imageId = R.drawable.lemon_squeeze,
                    contentDescId = R.string.squeeze_lemon_desc,
                    onImageClick = {
                        //on click should decrease squeeze count and when it reaches 0, to go to next step
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }

            3 -> {
                LemonImageAndText(
                    textTitleId = R.string.drink_lemonade,
                    imageId = R.drawable.lemon_drink,
                    contentDescId = R.string.drink_lemonade_desc,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }

            4 -> {
                LemonImageAndText(
                    textTitleId = R.string.empty_glass,
                    imageId = R.drawable.lemon_restart,
                    contentDescId = R.string.empty_glass_desc,
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
private fun LemonImageAndText(
    @StringRes textTitleId: Int,
    @DrawableRes imageId: Int,
    @StringRes contentDescId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(textTitleId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(imageId),
            contentDescription = stringResource(contentDescId),
            modifier = Modifier
                .wrapContentSize()
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
                .clickable(
                    onClick = onImageClick
                )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeAppTheme {

    }
}