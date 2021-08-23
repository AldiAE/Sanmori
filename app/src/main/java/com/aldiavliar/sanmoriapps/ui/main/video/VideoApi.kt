package com.aldiavliar.sanmoriapps.ui.main.video

object VideoApi {

    var BASE_URL = "https://youtube.googleapis.com/youtube/v3/search?key="
    var API_KEY = "AIzaSyDQlGSdBUXGuK3c8Ok617lEd9vp5ixD_pk"
    var PLAYLIST_ID = "PLRzLe0G9aha-dHvTJpsudgCygYezOPY4i"
    var CHANNEL_ID = "UC9lpUDGYg5kMX4ty70dLkuA"
    var MAX = "maxResults=50"
    var ORDER = "order=date"
    var PART = "part=snippet"
    var TYPE = "type=video"

    var ListVideo = "$BASE_URL$API_KEY&channelId=$CHANNEL_ID&$MAX&$ORDER&$PART"
    var SEARCHURl = "$BASE_URL$API_KEY&channelId=$CHANNEL_ID&$MAX&$ORDER&$PART"

    //https://youtube.googleapis.com/youtube/v3/search?key=AIzaSyDQlGSdBUXGuK3c8Ok617lEd9vp5ixD_pk&channelId=UC9lpUDGYg5kMX4ty70dLkuA&maxResults=50&order=date&part=snippet
}