package com.example.circle_space.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.circle_space.R

data class Service(
    val id: String,
    val title: String,
    val category: String, // e.g., "Skill", "Job", "Profession"
    val description: String,
    val isAvailable: Boolean
)

val sampleServices = listOf(
    Service("1", "Graphic Design", "Skill", "Logo, branding, and UI design", true),
    Service("2", "Plumbing", "Profession", "Home and office plumbing repairs", true),
    Service("3", "Mobile App Development", "Job", "Android/iOS development", true),
    Service("4", "Tutoring", "Skill", "Math and science tutoring for students", true),
    Service("5", "Photography", "Profession", "Event and portrait photography", true),
    Service("6", "Content Creation", "Skill", "Social media, blogs, and video content", true),
    Service("7", "Backend Engineering", "Job", "Server-side development and APIs", true),
    Service("8", "Customer Care Services", "Profession", "Support and client engagement", true),
    Service("9", "Data Analysis", "Skill", "Insights from data using tools like Excel, Python", true),
    Service("10", "Tailoring", "Profession", "Custom clothing and alterations", true),
    Service("11", "Painting", "Profession", "Interior, exterior, and artistic painting", true),
    Service("12", "Language Translation", "Skill", "Translation between languages like English, French", true),
    Service("13", "Kotlin Development", "Job", "Android apps and backend services using Kotlin", true),
    Service("14", "Python Development", "Job", "Web, data, and automation projects in Python", true),
    Service("15", "Robotic Engineering", "Profession", "Design and build intelligent machines", true),
    Service("16", "Web Development", "Job", "Frontend and backend website creation", true),
    Service("17", "Web3", "Skill", "Blockchain, smart contracts, and decentralized apps", true),
    Service("18", "Fitness training", "Profession", "Fitness training and wellness coaching", true)
)

@Composable
fun ServiceScreen(navController: NavController) {
    val services = remember { sampleServices }
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(8.dp)
    ) {
        Text(
            text = "Services You Can Provide or Seek",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier.padding(6.dp)
        )
        Text(
            text = "You can also connect with people in various fields here",
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            modifier = Modifier.padding(6.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search any services here") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        val filteredServices = services.filter { service ->
            service.title.contains(searchQuery, ignoreCase = true) ||
                    service.description.contains(searchQuery, ignoreCase = true)
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredServices) { service ->
                ServiceCard(service)
            }
        }
    }
}

@Composable
fun ServiceCard(service: Service) {
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
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = service.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Text(
                    text = "Profession/Skill",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Text(
                    text = service.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (service.isAvailable) "✅ Available" else "⏳ Not Available",
                    color = if (service.isAvailable) Color(0xFF4CAF50) else Color(0xFFFF9800)
                )
            }

            Image(
                painter = painterResource(id = getServiceIcon(service.title)),
                contentDescription = "${service.title} Icon",
                modifier = Modifier
                    .size(64.dp) // Increased from 48.dp to 64.dp
                    .padding(start = 12.dp)
            )
        }
    }
}

fun getServiceIcon(serviceTitle: String): Int {
    return when (serviceTitle) {
        "Graphic Design" -> R.drawable.graphics_design
        "Plumbing" -> R.drawable.plumbing
        "Mobile App Development" -> R.drawable.mobile_app_dev
        "Content Creation" -> R.drawable.content
        "Backend Engineering" -> R.drawable.code
        "Customer Care Services" -> R.drawable.customer
        "Data Analysis" -> R.drawable.data_analysis
        "Tailoring" -> R.drawable.tailoring
        "Painting" -> R.drawable.paint
        "Language Translation" -> R.drawable.translation
        "Kotlin Development" -> R.drawable.code
        "Python Development" -> R.drawable.code
        "Robotic Engineering" -> R.drawable.robotic
        "Web Development" -> R.drawable.code
        "Web3" -> R.drawable.web3
        "Fitness training" -> R.drawable.weightlifting
        "Tutoring" -> R.drawable.training2
        "Photography" -> R.drawable.photography
        else -> R.drawable.pc
    }
}