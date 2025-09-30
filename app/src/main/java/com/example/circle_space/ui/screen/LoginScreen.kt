package com.example.circle_space.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var rememberMeChecked by remember { mutableStateOf(false) }

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
                    text = "Hello!",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF03DAC5),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(8.dp)) // Adds space between the texts

                Text(
                    text = "Sign in to your account",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF03DAC5)
                )

                Spacer(modifier = Modifier.height(100.dp))

                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text(
                        text ="Full Name",
                        color = Color(0xFFA9A9A9)) },
                    placeholder = {
                        Text(
                            text = "Enter Your Full Name",
                            color = Color(0xFFA9A9A9)
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Full Name Icon",
                            tint = Color.Black
                        )
                    },
                    textStyle = TextStyle(color = Color.Black),
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(
                        text ="Password",
                        color = Color(0xFFA9A9A9)) },
                    placeholder = {
                        Text(
                            text = "Enter Your Password",
                            color = Color(0xFFA9A9A9),
                        )
                    },
                    textStyle = TextStyle(color = Color.Black),
                    singleLine = true,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
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
                                contentDescription = "Toggle Password Visibility",
                                tint = Color.Black
                            )
                        }
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = rememberMeChecked,
                            onCheckedChange = { rememberMeChecked = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF03DAC5)
                            )
                        )
                        Text(
                            text = "Remember me",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }

                    Text(
                        text = "Forgot password?",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        ),
                        color = Color(0xFF03DAC5),
                        modifier = Modifier.clickable {
                            // TODO: Handle forgot password navigation
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

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
                        text = "Sign in",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}