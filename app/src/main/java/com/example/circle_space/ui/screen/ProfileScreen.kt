package com.example.circle_space.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

object ProfileRoutes {
    const val ABOUT = "about_me"
    const val SAVED = "saved_posts"
    const val GROUPS = "groups"
    const val CAMPAIGN = "campaign"
    const val SETTINGS = "settings"
}

data class ProfileSection(
    val title: String,
    val route: String,
    val icon: ImageVector? = null
)

val profileSections = listOf(
    ProfileSection(title = "About Me", route = ProfileRoutes.ABOUT),
    ProfileSection(title = "Saved Posts", route = ProfileRoutes.SAVED),
    ProfileSection(title = "Groups", route = ProfileRoutes.GROUPS),
    ProfileSection(title = "Campaign", route = ProfileRoutes.CAMPAIGN),
    ProfileSection(title = "Settings", route = ProfileRoutes.SETTINGS, icon = Icons.Default.Settings)
)

@Composable
fun ProfileScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .width(280.dp)              // Covers part of the horizontal space
            .height(500.dp)             // Covers from top to part of bottom
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column {
            Text("Profile", style = MaterialTheme.typography.headlineSmall, color = Color.Black)

            Spacer(Modifier.height(16.dp))

            LazyColumn {
                items(profileSections) { section ->
                    ProfileItem(section = section, navController = navController)
                }
            }
        }
    }
}

@Composable
fun ProfileItem(section: ProfileSection, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(section.route) }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        section.icon?.let {
            Icon(
                imageVector = it,
                contentDescription = section.title,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(12.dp))
        }
        Text(section.title, style = MaterialTheme.typography.bodyLarge, color = Color.Black)
    }
}