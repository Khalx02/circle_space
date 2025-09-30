package com.example.circle_space.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun RegisterScreen(navController: NavHostController) {
    var credentials by remember { mutableStateOf(Credentials("", "", "")) }

    Column(modifier = Modifier.fillMaxSize()) {
        // 🔷 Top 30% Green Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.15f)
                .background(Color(0xFF03DAC5))
        )

        // ⚪ Bottom 70% White Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.85f)
                .background(Color.White)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                // 🏷️ Title now in white section
                Text(
                    text = "Create an account",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF03DAC5),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                // 🔳 Input Fields
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    RegisterField(
                        value = credentials.register,
                        onChange = { data -> credentials = credentials.copy(register = data) },
                        modifier = Modifier.fillMaxWidth(),
                        label = "Full Name",
                        placeholder = "Enter Your Full Name"
                    )

                    EmailField(
                        value = credentials.email,
                        onChange = { data -> credentials = credentials.copy(email = data) },
                        modifier = Modifier.fillMaxWidth(),
                        label = "Email",
                        placeholder = "Enter Your Email Address"
                    )

                    PasswordField(
                        value = credentials.password,
                        onChange = { data -> credentials = credentials.copy(password = data) },
                        submit = { checkCredentials(credentials) },
                        modifier = Modifier.fillMaxWidth(),
                        label = "Password",
                        placeholder = "Enter Your Password",
                    )

                    ConfirmPasswordField(
                        value = credentials.confirmPassword,
                        onChange = { data -> credentials = credentials.copy(confirmPassword = data) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                // 🎨 Sign Up Button with gradient
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                        .background(
                            color = Color(0xFF03DAC5),
                            shape = RoundedCornerShape(25.dp)
                        )
                        .clickable {
                            navController.navigate("home") {
                                popUpTo("register") { inclusive = true }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sign up",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Already have an account? ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )

                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF03DAC5),
                        modifier = Modifier.clickable {
                            navController.navigate("login")
                        }
                    )
                }
            }
        }
    }
}

private fun Credentials.isNotEmpty(): Boolean {
    return register.isNotEmpty() && password.isNotEmpty()
}
private fun Credentials.isValid(): Boolean {
    return register.isNotEmpty() &&
            email.isNotEmpty() &&
            password.isNotEmpty() &&
            confirmPassword == password
}
private fun ColumnScope.checkCredentials(credentials: Credentials) {}
data class Credentials(
    var register: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var remember: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit = {},
    modifier: Modifier = Modifier,
    label: String = "Full Name",
    placeholder: String = "Enter your full name"
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = {
            Text(
                text = label,
                color = Color(0xFFA9A9A9)
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFFA9A9A9)
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Full Name Icon",
                tint = Color.Black
            )
        },
        textStyle = TextStyle(color = Color.Black),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit = {},
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter your password"
) {
    val focusManager = LocalFocusManager.current
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = {
            Text(
                text = label,
                color = Color(0xFFA9A9A9)
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFFA9A9A9)
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Password Icon",
                tint = Color.Black
            )
        },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "Toggle password visibility",
                    tint = Color.Black
                )
            }
        },
        textStyle = TextStyle(color = Color.Black),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { submit() })
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit = {},
    modifier: Modifier = Modifier,
    label: String = "Email",
    placeholder: String = "Enter your email address"
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = {
            Text(
                text = label,
                color = Color(0xFFA9A9A9)
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFFA9A9A9)
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon",
                tint = Color.Black
            )
        },
        textStyle = TextStyle(color = Color.Black),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasswordField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Confirm Password",
    placeholder: String = "Re-enter your password"
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = {
            Text(
                text = label,
                color = Color(0xFFA9A9A9)
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFFA9A9A9)
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Confirm Password Icon",
                tint = Color.Black
            )
        },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "Toggle password visibility",
                    tint = Color.Black
                )
            }
        },
        textStyle = TextStyle(color = Color.Black),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
    )
}