package com.example.circle_space.ui.screen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.circle_space.R

data class User(
    val id: String,
    val name: String,
    val isMutual: Boolean,
    val sharedInterests: List<String>
)

val sampleUsers = listOf(
    User("1", "User1", isMutual = true, sharedInterests = listOf("Music", "Tech")),
    User("2", "User2", isMutual = false, sharedInterests = listOf("Art")),
    User("3", "User3", isMutual = true, sharedInterests = listOf("Books", "Travel")),
    User("4", "User4", isMutual = false, sharedInterests = listOf("Gaming", "Tech"))
)

@Composable
fun CircleScreen(navController: NavController) {
    val users = remember { sampleUsers }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Surrounding color
            .padding(8.dp) // Space between outer color and card
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White) // Set screen background to white
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Invitations",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black, // Set text color to black
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White)
                )
            }
            items(users.filter { !it.isMutual }) { user ->
                UserCard(user)
            }

            item {
                Text(
                    text = "My Network",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black, // Set text color to black
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(users.filter { it.isMutual }) { user ->
                UserCard(user)
            }
        }
    }
}

@Composable
fun UserCard(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar image on the left
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.Top) // Moves avatar higher in the Row
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "User Avatar",
                    modifier = Modifier.fillMaxSize()
                )
            }

            // User info and actions
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Text(
                    text = "Interests: ${user.sharedInterests.joinToString()}",
                    color = Color.Black
                )
                Text(
                    text = if (user.isMutual) "🌐 My Network" else "📩 Invitation",
                    color = if (user.isMutual) Color.Green else Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { /* TODO */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (user.isMutual) Color(0xFF03DAC5) else Color(0xFF03DAC5)
                        )
                    ) {
                        Text(
                            text = if (user.isMutual) "Network" else "Accept",
                            color = Color.White
                        )
                    }

                    Button(
                        onClick = { /* TODO */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (user.isMutual) Color(0xFF00ACEB) else Color(0xFFFF0033)
                        )
                    ) {
                        Text(
                            text = if (user.isMutual) "Message" else "Reject",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}