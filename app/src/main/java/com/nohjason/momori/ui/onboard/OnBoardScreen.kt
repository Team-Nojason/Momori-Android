package com.nohjason.momori.ui.onboard

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.component1
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.nohjason.momori.BuildConfig
import com.nohjason.momori.R
import com.nohjason.momori.component.theme.Headline
import com.nohjason.momori.util.TAG
//import com.nohjason.momori.BuildConfig


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun OnBoardScreen(
    viewModel: OnBoardViewModel = viewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val sideEffect by viewModel.sideEffect.collectAsState()

    LaunchedEffect(sideEffect) {
        when (sideEffect) {
            OnBoardSideEffect.Success -> Toast.makeText(context, "성공", Toast.LENGTH_SHORT).show()
            OnBoardSideEffect.InvalidIdToken -> Toast.makeText(context, "잘못된 id Token", Toast.LENGTH_SHORT).show()
            OnBoardSideEffect.None -> {}
        }
    }

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken(BuildConfig.CLIENT_ID)
        .build()

    val mGoogleSignInClient = GoogleSignIn.getClient(context, gso)

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val idToken = account?.idToken.toString()
            Log.d(TAG, "handleSignInResult: $idToken")
            viewModel.login(idToken)
        } catch (e: ApiException) {
            Log.d(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val intent = result.data
            if (result.data != null) {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(intent)
                handleSignInResult(task)
            }
        }

    Column (
        Modifier
            .background(color = Color(0xffF1F1F1))
            .padding(horizontal = 20.dp)){

        Text(text = "momori", modifier = Modifier.align(CenterHorizontally))

        Spacer(modifier = Modifier.height(200.dp))
        Column {
            Headline(text = "당신의")
            Headline(text = "추억을")
            Headline(text = "공유해보세요 \uD83D\uDD25")
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 190.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .clickable {
                        val signInIntent = mGoogleSignInClient.signInIntent
                        startForResult.launch(signInIntent)
                    }
            )
            Spacer(modifier = Modifier.width(34.dp))
            Image(
                painter = painterResource(id = R.drawable.kaka),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
            )
            Spacer(modifier = Modifier.width(34.dp))
            Image(
                painter = painterResource(id = R.drawable.naver),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
            )
        }
    }
}