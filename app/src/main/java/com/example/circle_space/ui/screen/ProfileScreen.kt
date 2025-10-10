package com.example.circle_space.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenWithDrawer(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ProfileDrawerContent(navController = navController)
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Profile") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                // Main content goes here
                Text("Welcome to your profile!")
            }
        }
    }
}

@Composable
fun ProfileDrawerContent(navController: NavController) {
    Column(
        modifier = Modifier
            .width(280.dp)
            .fillMaxHeight()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Profile", style = MaterialTheme.typography.headlineSmall, color = Color.Black)
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(profileSections) { section ->
                ProfileItem(section = section, navController = navController)
            }
        }
    }
}

@Composable
fun ProfileItem(section: ProfileSection, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(section.route)
            }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        section.icon?.let {
            Icon(
                imageVector = it,
                contentDescription = section.title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = section.title,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings Icon",
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("Settings", style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        }

        Spacer(Modifier.height(16.dp))
        Text("Customize your preferences and account settings here.", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun CampaignScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Campaign", style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        Spacer(Modifier.height(12.dp))
        Text("Your campaigns and promotions will show up here.", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun GroupsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Groups", style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        Spacer(Modifier.height(12.dp))
        Text("Explore and manage your groups here.", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun SavedPostsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Saved Posts", style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        Spacer(Modifier.height(12.dp))
        Text("Your saved posts will be listed here.", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun AboutMeScreen() {
    var isEditing by remember { mutableStateOf(false) }
    var aboutText by remember { mutableStateOf("This is where your bio and personal info will appear.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "About Me",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )

            IconButton(onClick = { isEditing = true }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit About Me",
                    tint = Color.Gray
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        if (isEditing) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = aboutText,
                    onValueChange = { aboutText = it },
                    placeholder = { Text("Write something about yourself...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = { isEditing = false },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Save")
                }
            }
        } else {
            Text(
                text = aboutText,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
        }
    }
}