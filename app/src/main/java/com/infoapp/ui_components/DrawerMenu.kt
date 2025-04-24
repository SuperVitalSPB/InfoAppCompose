package com.infoapp.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infoapp.R
import com.infoapp.ui.theme.BgTransparent
import com.infoapp.ui.theme.MainRed
import com.infoapp.utils.DrawerEvents
import com.infoapp.utils.DrawerEvents.OnItemClick

@Composable
fun DrawerMenu(topBarTitle: String, onEvents: (DrawerEvents) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.drawer_list_bg),
            contentDescription = "Main Bg Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Column (modifier = Modifier.fillMaxSize()) {
            Header()
            Body(topBarTitle, onEvents)
        }
    }
}

@Composable
fun Header() {
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(270.dp)
            .padding(top = 90.dp),
        shape = RoundedCornerShape(percent = 10),
        border = BorderStroke(1.dp, MainRed)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.header_bg),
                contentDescription = "header image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Справочник ботаника",
                modifier = Modifier.fillMaxWidth()
                    .background(MainRed)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun Body(topBarTitle: String, onEvents: (DrawerEvents) -> Unit) {
   val list = stringArrayResource(id = R.array.drawer_list)
   LazyColumn(
       modifier = Modifier.fillMaxSize()
   )  {
       itemsIndexed(list) { index, title ->
           Card (
               modifier = Modifier.fillMaxWidth()
                   .padding(3.dp)
           ){
               Text(
                   text = title,
                   modifier = Modifier
                       .fillMaxWidth()
                       .clickable {
                           onEvents(OnItemClick(title, index))
                       }
                       .background(if (title == topBarTitle) Color.LightGray else Color.Transparent)
                       .padding(10.dp)
                       .wrapContentWidth(),
                   fontWeight = FontWeight.Bold
               )
           }
       }
   }
}