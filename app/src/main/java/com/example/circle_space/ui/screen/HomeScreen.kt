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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.circle_space.R

sealed class FeedItem {
    data class UserPost(
        val id: String,
        val author: String,
        val imageVector: ImageVector,
        val content: String,
        val timestamp: String
    ) : FeedItem()

    data class CompanyUpdate(
        val id: String,
        val companyName: String,
        val iconResId: Int,
        val announcement: String,
        val timestamp: String
    ) : FeedItem()

    data class InvestorInsight(
        val id: String,
        val investorName: String,
        val imageVector: ImageVector,
        val insight: String,
        val timestamp: String
    ) : FeedItem()

    data class GovernmentNotice(
        val id: String,
        val agency: String,
        val iconResId: Int,
        val notice: String,
        val timestamp: String
    ) : FeedItem()
}

val sampleFeed = listOf(
    FeedItem.UserPost(
        id = "1",
        author = "Alex",
        imageVector = Icons.Default.Person,
        content = "Just published a new article on AI ethics",
        timestamp = "2h ago"
    ),
    FeedItem.CompanyUpdate(
        id = "2",
        companyName = "AltTech",
        iconResId = R.drawable.tech7,
        announcement = "We're hiring backend engineers!",
        timestamp = "Today"
    ),
    FeedItem.InvestorInsight(
        id = "3",
        investorName = "Ngozi Ventures",
        imageVector = Icons.Default.Person,
        insight = "Fintech is heating up in West Africa",
        timestamp = "Yesterday"
    ),
    FeedItem.GovernmentNotice(
        id = "4",
        agency = "NITDA",
        iconResId = R.drawable.nigeria_flag,
        notice = "New cybersecurity guidelines released",
        timestamp = "Today"
    )
)

@Composable
fun FeedItemCard(item: FeedItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                when (item) {
                    is FeedItem.UserPost -> {
                        Icon(
                            imageVector = item.imageVector,
                            contentDescription = "User avatar",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            tint = Color.Black
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("${item.author} posted", style = MaterialTheme.typography.titleSmall,
                            color = Color.Black
                        )
                    }

                    is FeedItem.CompanyUpdate -> {
                        Image(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = "Company logo",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("${item.companyName} update", style = MaterialTheme.typography.titleSmall,
                            color = Color.Black
                        )
                    }

                    is FeedItem.InvestorInsight -> {
                        Icon(
                            imageVector = item.imageVector,
                            contentDescription = "Investor avatar",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            tint = Color.Black
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("${item.investorName} insight", style = MaterialTheme.typography.titleSmall,
                            color = Color.Black
                        )
                    }

                    is FeedItem.GovernmentNotice -> {
                        Image(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = "Agency icon",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("${item.agency} notice", style = MaterialTheme.typography.titleSmall,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = when (item) {
                    is FeedItem.UserPost -> item.content
                    is FeedItem.CompanyUpdate -> item.announcement
                    is FeedItem.InvestorInsight -> item.insight
                    is FeedItem.GovernmentNotice -> item.notice
                },
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = when (item) {
                    is FeedItem.UserPost -> item.timestamp
                    is FeedItem.CompanyUpdate -> item.timestamp
                    is FeedItem.InvestorInsight -> item.timestamp
                    is FeedItem.GovernmentNotice -> item.timestamp
                },
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { /* Like */ }) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Like", tint = Color.Gray)
                }
                IconButton(onClick = { /* Comment */ }) {
                    Icon(Icons.Default.ChatBubbleOutline, contentDescription = "Comment", tint = Color.Gray)
                }
                IconButton(onClick = { /* Repost */ }) {
                    Icon(Icons.Default.Repeat, contentDescription = "Repost", tint = Color.Gray)
                }
                IconButton(onClick = { /* Share */ }) {
                    Icon(Icons.Default.Share, contentDescription = "Share", tint = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    val feedItems = remember { sampleFeed }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(8.dp)
    ) {
        items(feedItems, key = { item ->
            when (item) {
                is FeedItem.UserPost -> item.id
                is FeedItem.CompanyUpdate -> item.id
                is FeedItem.InvestorInsight -> item.id
                is FeedItem.GovernmentNotice -> item.id
            }
        }) { item ->
            FeedItemCard(item)
        }
    }
}