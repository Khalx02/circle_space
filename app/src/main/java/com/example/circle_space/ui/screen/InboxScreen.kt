package com.example.circle_space.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Inbox(
    val sender: String,
    val preview: String,
    val timestamp: String,
    val category: String, // "All", "Unread", "Jobs", "Drafts", "Archive"
    val isUnread: Boolean
)

val inboxes = listOf(
    Inbox("Ada", "Hey, are you free to talk?", "Mon 9:12 AM", "Unread", true),
    Inbox("JobConnect", "Your application was received.", "Sun 4:45 PM", "Jobs", false),
    Inbox("Tunde", "Let’s meet at the studio.", "Sat 11:30 AM", "All", false),
    Inbox("Draft to Zainab", "Still thinking how to reply...", "Fri 6:00 PM", "Drafts", false),
    Inbox("Chuka", "Thanks for the feedback!", "Thu 2:15 PM", "Archive", false)
)

@Composable
fun InboxScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("All") }
    val expandedInboxId = remember { mutableStateOf<String?>(null) }
    val tabs = listOf("All", "Unread", "Jobs", "Drafts", "Archive")

    val tabIndex = tabs.indexOf(selectedTab)
    val filteredInboxes = inboxes.filter {
        selectedTab == "All" || it.category == selectedTab
    }

    val swipeModifier = Modifier.pointerInput(tabIndex) {
        detectHorizontalDragGestures { _, dragAmount ->
            if (dragAmount < -50 && tabIndex < tabs.lastIndex) {
                selectedTab = tabs[tabIndex + 1] // Swipe left → next tab
            } else if (dragAmount > 50 && tabIndex > 0) {
                selectedTab = tabs[tabIndex - 1] // Swipe right → previous tab
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Inbox", style = MaterialTheme.typography.headlineSmall, color = Color.Black)

        Spacer(Modifier.height(16.dp))

        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = Color.White
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    text = { Text(tab, color = Color.Black) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Box(modifier = swipeModifier.fillMaxSize()) {
            LazyColumn {
                items(filteredInboxes) { inbox ->
                    val inboxId = "${inbox.sender}-${inbox.timestamp}"
                    InboxItem(
                        inbox = inbox,
                        isMenuOpen = expandedInboxId.value == inboxId,
                        onMenuClick = { expandedInboxId.value = inboxId },
                        onDismissMenu = { expandedInboxId.value = null }
                    )
                }
            }
        }
    }
}

@Composable
fun InboxItem(
    inbox: Inbox,
    isMenuOpen: Boolean,
    onMenuClick: () -> Unit,
    onDismissMenu: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // ✅ Spacing between messages
            .background(Color(0xFFF5F3F0))
            .clickable { println("Clicked on ${inbox.sender}") }
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${inbox.timestamp} • ${inbox.sender}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (inbox.isUnread) Color.Black else Color.DarkGray
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = inbox.preview,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            }

            // ✅ Menu Button
            Box {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu",
                        tint = Color.Gray
                    )
                }

                DropdownMenu(
                    expanded = isMenuOpen,
                    onDismissRequest = onDismissMenu
                ) {
                    DropdownMenuItem(
                        text = { Text("Mark as read") },
                        onClick = { println("Mark as read: ${inbox.sender}") },
                        leadingIcon = {
                            Icon(Icons.Default.MarkEmailRead, contentDescription = null)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Archive") },
                        onClick = { println("Archive: ${inbox.sender}") },
                        leadingIcon = {
                            Icon(Icons.Default.Archive, contentDescription = null)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Move to Drafts") },
                        onClick = { println("Draft: ${inbox.sender}") },
                        leadingIcon = {
                            Icon(Icons.Default.Edit, contentDescription = null)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete message") },
                        onClick = { println("Delete: ${inbox.sender}") },
                        leadingIcon = {
                            Icon(Icons.Default.Delete, contentDescription = null)
                        }
                    )
                }
            }
        }
    }
}



