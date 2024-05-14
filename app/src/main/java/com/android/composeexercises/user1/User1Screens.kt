package com.android.composeexercises.user1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun JetpackComposeApp() {
    var isLoggedIn by remember { mutableStateOf(false) }

    if (isLoggedIn) {
        HomeScreen()
    } else {
        LoginScreen(onLoginClick = { username, password ->
            if (username == "admin" && isPasswordValid(password)) {
                isLoggedIn = true
            }
        })
    }
}

@Composable
fun LoginScreen(onLoginClick: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Username") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                isPasswordValid = isPasswordValid(it)
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
            })
        )

        val passwordRequirementsText = if (isPasswordValid) {
            "Password meets requirements"
        } else {
            "Password must be at least 8 characters long, contain at least one uppercase letter, and contain at least one lowercase letter."
        }

        Text(
            text = passwordRequirementsText,
            color = if (isPasswordValid) Color.Green else Color.Red,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onLoginClick(username, password) }) {
            Text("Login")
        }
    }
}

fun isPasswordValid(password: String): Boolean {
    val regex = Regex("^(?=.*[A-Z])(?=.*[a-z]).{8,}$")
    return regex.matches(password)
}

@Composable
fun HomeScreen() {
    Text("Welcome!")
}

@Preview(showBackground = true)
@Composable
fun PreviewJetpackComposeApp() {
    JetpackComposeApp()
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(onLoginClick = { _, _ -> })
}