package com.android.composeexercises.user2

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.android.composeexercises.R

@Preview
@Composable
fun LoginUIDisplay() {
    LoginUI()
    
}

@Composable
fun LoginUI(modifier: Modifier = Modifier) {
    var usernameValue by remember {
        mutableStateOf("")
    }
    var passwordValue by remember {
        mutableStateOf("")
    }
    ConstraintLayout(
        modifier
            .fillMaxSize()
            .background(Color.Black)) 
    {
        val(usernameTextField,passwordTextField,pwdLengthTxt,pwdNumberTxt,pwdUpeerCaseTxt,pwdLowerCaseTxt,pwdSpecialCharTxt,loginBtn) = createRefs()
        TextField(value = usernameValue, onValueChange = {},
            modifier=modifier.constrainAs(usernameTextField){
                top.linkTo(parent.top)
                bottom.linkTo(passwordTextField.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height=Dimension.wrapContent
            },
            label = {
                Text(text = stringResource(id = R.string.enter_user_name))
            },
            isError = !Patterns.EMAIL_ADDRESS.matcher(usernameValue).matches(),
            maxLines = 1)
        TextField(value = passwordValue, onValueChange = {newValue ->
            if (newValue.length <= 25 && newValue.length >= 8 &&
                newValue.contains(Regex("\\d")) &&
                newValue.contains(Regex("[A-Z]")) &&
                newValue.contains(Regex("[a-z]")) &&
                newValue.contains(Regex("[^A-Za-z0-9]"))
            ) {
                passwordValue = newValue
            }
        },
            modifier=modifier.constrainAs(passwordTextField){
                top.linkTo(usernameTextField.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            label = {
                Text(text = stringResource(id = R.string.enter_password))
            },
            maxLines = 1
        )
        Text(
            text = stringResource(id = R.string.password_length),
            modifier = modifier.constrainAs(pwdLengthTxt) {
                top.linkTo(passwordTextField.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            color = MaterialTheme.colorScheme.primary,
            fontSize = TextUnit(20f, TextUnitType.Sp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
        )
        Text(
            text = stringResource(id = R.string.password_number),
            modifier = modifier.constrainAs(pwdNumberTxt) {
                top.linkTo(pwdLengthTxt.bottom)
                start.linkTo(pwdLengthTxt.start)
                end.linkTo(parent.end)
            },
            color = MaterialTheme.colorScheme.primary,
            fontSize = TextUnit(20f, TextUnitType.Sp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
        )
        Text(
            text = stringResource(id = R.string.password_upper_case),
            modifier = modifier.constrainAs(pwdUpeerCaseTxt) {
                top.linkTo(pwdNumberTxt.bottom)
                start.linkTo(pwdLengthTxt.start)
                end.linkTo(parent.end)
            },
            color = MaterialTheme.colorScheme.primary,
            fontSize = TextUnit(20f, TextUnitType.Sp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
        )
        Text(
            text = stringResource(id = R.string.password_lower_case),
            modifier = modifier.constrainAs(pwdLowerCaseTxt) {
                top.linkTo(pwdUpeerCaseTxt.bottom)
                start.linkTo(pwdLengthTxt.start)
                end.linkTo(parent.end)
            },
            color = MaterialTheme.colorScheme.primary,
            fontSize = TextUnit(20f, TextUnitType.Sp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
        )
        Text(
            text = stringResource(id = R.string.password_special_character),
            modifier = modifier.constrainAs(pwdSpecialCharTxt) {
                top.linkTo(pwdLowerCaseTxt.bottom)
                start.linkTo(pwdLengthTxt.start)
                end.linkTo(parent.end)
            },
            color = MaterialTheme.colorScheme.primary,
            fontSize = TextUnit(20f, TextUnitType.Sp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
        )
        Button(onClick = {},
            modifier = Modifier
                .constrainAs(loginBtn) {
                    top.linkTo(pwdSpecialCharTxt.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = dimensionResource(id = R.dimen.vertical_margin))
        ) {
            Text(text = stringResource(id = R.string.login),
                fontSize = TextUnit(16f,TextUnitType.Sp),
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold
            )

        }

    }
    
}