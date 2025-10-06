package com.example.circle_space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.circle_space.ui.screen.CircleScreen
import com.example.circle_space.ui.screen.HomeScreen
import com.example.circle_space.ui.screen.InboxScreen
import com.example.circle_space.ui.screen.LoginScreen
import com.example.circle_space.ui.screen.NotificationScreen
import com.example.circle_space.ui.screen.PitchScreen
import com.example.circle_space.ui.screen.PostScreen
import com.example.circle_space.ui.screen.ProfileItem
import com.example.circle_space.ui.screen.RegisterScreen
import com.example.circle_space.ui.screen.SearchScreen
import com.example.circle_space.ui.screen.ServiceScreen
import com.example.circle_space.ui.screen.profileSections
import com.example.circle_space.ui.theme.Circle_spaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Circle_spaceTheme {
                val navController = rememberNavController()
                MainLayout(navController)
            }
        }
    }
}

@Composable
fun MainLayout(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBars = currentRoute != "login" && currentRoute != "register"

    Scaffold(
        topBar = { if (showBars) TopBar(navController) },
        bottomBar = { if (showBars) BottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "register",
            modifier = Modifier.padding(innerPadding)
        )

 {
            composable("register") {
                RegisterScreen(navController)
            }
            composable("login") {
                LoginScreen(navController)
            }
            composable("home") {
                HomeScreen(navController)
            }
     composable("profile") {
         var showProfile by remember { mutableStateOf(true) }

         Box(modifier = Modifier.fillMaxSize()) {
             HomeScreen(navController) // Always visible

             if (showProfile) {
                 // Profile panel
                 Box(
                     modifier = Modifier
                         .width(280.dp)
                         .height(500.dp)
                         .background(Color(0xFFF5F3F0)) // ✅ Your custom color
                         .padding(16.dp)
                         .align(Alignment.TopStart)
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

                 // ✅ Dismiss layer: only covers the rest of the screen
                 Box(
                     modifier = Modifier
                         .fillMaxSize()
                         .clickable { showProfile = false }
                         .padding(start = 280.dp, top = 500.dp) // ✅ restrict to area outside profile
                 )
             }
         }
     }
            composable("inbox") {
                InboxScreen(navController)
            }
            composable("search") {
                SearchScreen(navController)
            }
            composable("post") {
                PostScreen(navController)
            }
            composable("circle") {
                CircleScreen(navController)
            }
            composable("pitch") {
                PitchScreen(navController)
            }
            composable("service") {
                ServiceScreen(navController)
            }
            composable("notification") {
                NotificationScreen(navController)
            }
            composable("about_me") {
                AboutMeScreen()
             }
            composable("saved_posts") {
                SavedPostsScreen()
             }
            composable("groups") {
                GroupsScreen()
             }
            composable("campaign") {
                CampaignScreen()
             }
            composable("settings") {
                SettingsScreen()
             }


 }
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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

            IconButton(onClick = { isEditing = !isEditing }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit About Me",
                    tint = Color.Gray
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        if (isEditing) {
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
        } else {
            Text(
                text = aboutText,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    Surface(color = Color.White) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = Icons.Default.Home,
                label = "Home",
                onClick = { navController.navigate("home") }
            )
            BottomNavItem(
                icon = Icons.Default.Group,
                label = "Circle",
                onClick = { navController.navigate("circle") }
            )
            BottomNavItem(
                icon = Icons.Default.Campaign,
                label = "Pitch",
                onClick = { navController.navigate("pitch") }
            )
            BottomNavItem(
                icon = Icons.Default.Build,
                label = "Service",
                onClick = { navController.navigate("service") }
            )
            BottomNavItem(
                icon = Icons.Default.Notifications,
                label = "Notification",
                onClick = { navController.navigate("notification") }
            )
        }
    }
}

@Composable
fun TopBar(navController: NavController) {
    Surface(color = Color.White) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.Black, // Changed to black
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.navigate("profile") }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { navController.navigate("search") }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Black, // Changed to black
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { navController.navigate("post") }
            ) {
                Icon(
                    imageVector = Icons.Default.PostAdd,
                    contentDescription = "Post",
                    tint = Color.Black, // Changed to black
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Post",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.Message,
                contentDescription = "Inbox",
                tint = Color.Black, // Changed to black
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.navigate("inbox") }
            )
        }
    }
}

@Composable
fun BottomNavItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black
        )
    }
}