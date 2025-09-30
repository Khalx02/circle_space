package com.example.circle_space.ui.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch


sealed class NotificationType {
    object Post : NotificationType()
    object Repost : NotificationType()
    object Reaction : NotificationType()
    object ProfileView : NotificationType()
    object Mention : NotificationType()
    object Tag : NotificationType()
    object Like : NotificationType()
    object Comment : NotificationType()
    object JobAlert : NotificationType()
    object Birthday : NotificationType()
    object Achievement : NotificationType()
    object Promotion : NotificationType()
    object Pitch : NotificationType()
    object InvestorInterest : NotificationType()
}

data class NotificationItem(
    val id: String,
    val type: NotificationType,
    val message: String,
    val timestamp: String,
    val isRead: Boolean = false
)

val sampleNotifications = listOf(
    NotificationItem("1", NotificationType.Post, "Alex posted a new article on AI trends", "2h ago"),
    NotificationItem("2", NotificationType.Reaction, "Maya reacted to your post", "1h ago"),
    NotificationItem("3", NotificationType.ProfileView, "John viewed your profile", "3h ago"),
    NotificationItem("4", NotificationType.JobAlert, "New job opportunity: Backend Engineer at Flutterwave", "Today"),
    NotificationItem("5", NotificationType.Birthday, "It’s Tolu’s birthday today 🎉", "Today"),
    NotificationItem("6", NotificationType.Pitch, "Chidi is pitching a fintech idea", "Yesterday"),
    NotificationItem("7", NotificationType.InvestorInterest, "An investor is exploring logistics startups", "Yesterday")
)

@Composable
fun NotificationScreen(navController: NavController) {
    val allNotifications = remember { mutableStateListOf(*sampleNotifications.toTypedArray()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val tabs = listOf("All", "Pitch", "Jobs", "My posts", "Mentions")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(8.dp)
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color(0xFFF9F9F9),
            indicator = {},
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        val filteredNotifications = when (tabs[selectedTabIndex]) {
            "Pitch" -> allNotifications.filter {
                it.type == NotificationType.Pitch || it.type == NotificationType.InvestorInterest
            }
            "Jobs" -> allNotifications.filter { it.type == NotificationType.JobAlert }
            "My posts" -> allNotifications.filter {
                it.type == NotificationType.Post || it.type == NotificationType.Reaction ||
                        it.type == NotificationType.Like || it.type == NotificationType.Comment
            }
            "Mentions" -> allNotifications.filter {
                it.type == NotificationType.Mention || it.type == NotificationType.Tag
            }
            else -> allNotifications
        }

        LazyColumn {
            items(filteredNotifications, key = { it.id }) { notification ->
                DismissibleNotificationCard(
                    notification = notification,
                    onDelete = { toRemove ->
                        allNotifications.remove(toRemove)

                        coroutineScope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Notification deleted",
                                actionLabel = "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                allNotifications.add(toRemove)
                            }
                        }
                    }
                )
            }
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}

@Composable
fun DismissibleNotificationCard(
    notification: NotificationItem,
    onDelete: (NotificationItem) -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    val animatedOffsetX by animateDpAsState(targetValue = offsetX.dp, label = "SwipeOffset")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = animatedOffsetX)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    offsetX += dragAmount
                    if (offsetX < -50f) { // swipe left threshold
                        onDelete(notification)
                    }
                }
            }
    ) {
        NotificationCard(notification)
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (notification.isRead) Color.White else Color(0xFFE3F2FD)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = notification.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.timestamp,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = Color.Gray
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White) // Menu background
                ) {
                    DropdownMenuItem(
                        text = { Text("Change notification preference", color = Color.Black) },
                        onClick = {
                            // Handle preference change
                            expanded = false
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Color.Black,
                            leadingIconColor = Color.Black,
                            trailingIconColor = Color.Black
                        )
                    )
                    DropdownMenuItem(
                        text = { Text("Delete notification", color = Color.Black) },
                        onClick = {
                            // Handle delete
                            expanded = false
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Color.Black,
                            leadingIconColor = Color.Black,
                            trailingIconColor = Color.Black
                        )
                    )
                    DropdownMenuItem(
                        text = { Text("Show less like this", color = Color.Black) },
                        onClick = {
                            // Handle show less
                            expanded = false
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ThumbDown,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Color.Black,
                            leadingIconColor = Color.Black,
                            trailingIconColor = Color.Black
                        )
                    )

                }
            }
        }
    }
}