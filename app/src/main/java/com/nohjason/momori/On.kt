package com.nohjason.momori

import android.print.PrintAttributes.Margins
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjason.momori.R

@Composable
fun OnBoardScreen(msg: String) {

    val imageModifier = Modifier
        .size(50.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Yellow)

    Column (Modifier.padding(horizontal = 20.dp)){
        Spacer(modifier = Modifier.height(200.dp))
        Column {
            Text(text = "당신의", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = "추억을", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = "공유해보세요 🔥", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {}, 
            shape = RoundedCornerShape(10.dp), 
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff363535)),
            modifier = Modifier.width(200.dp)) {
            Text(text = "🤨                       >")
        }
        Spacer(modifier = Modifier
            .height(160.dp)
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .border(1.dp, Color.Black, CircleShape)
            )
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .border(1.dp, Color.Black, CircleShape)
            )
        }
    }
}