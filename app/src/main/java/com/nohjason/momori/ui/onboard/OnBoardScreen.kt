package com.nohjason.momori.ui.onboard

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nohjason.momori.R

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardScreen(
    viewModel: OnBoardViewModel = viewModel()
) {
//    val imageModifier = Modifier
//        .size(50.dp)
//        .border(BorderStroke(1.dp, Color.Black))
//        .background(Color.Yellow)

    val context = LocalContext.current
//    val state by viewModel.state.collectAsState()

    Column (
        Modifier
            .background(color = Color(0xffF1F1F1))
            .padding(horizontal = 20.dp)){

        Text(text = "momori", modifier = Modifier.align(CenterHorizontally))

//        TextField(value = state.id, onValueChange = { viewModel.updateId(it) })
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
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
                        context.startActivity(intent)
                    }
            )
            Spacer(modifier = Modifier.width(34.dp))
            Image(
                painter = painterResource(id = R.drawable.kaka),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(45.dp)
            )
            Spacer(modifier = Modifier.width(34.dp))
            Image(
                painter = painterResource(id = R.drawable.naver),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(45.dp)
            )
        }
    }
}