package com.marcosoliveira.myshopapp.notification

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService

//const val channelId = "notification_channel"
//const val channelName = "com.marcosoliveira.myshopapp.notification"

// This class allows me to have notifications from the cloud with FireBase
@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    // THIS CLASS MESSAGES THE USER WITH FIREBASE "MESSAGING" FUNCTION
    // BUT IT NEEDS TO BE DONE MANUALLY, IN FIREBASE CONSOLE.

//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        if(remoteMessage.getNotification() != null){
//            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
//        }
//    }
//
//    //
//    private fun getRemoteView(title: String, message: String): RemoteViews {
//        val remoteViews = RemoteViews("com.marcosoliveira.myshopapp.notification", R.layout.notification)
//
//        remoteViews.setTextViewText(R.id.title, title)
//        remoteViews.setTextViewText(R.id.message, message)
//        remoteViews.setImageViewResource(R.id.app_logo, R.drawable.ic_notification_icon)
//
//        return remoteViews
//    }
//
//    // this method gerenets the notification
//    fun generateNotification(title: String, message: String){
//        //This intent takes the user to the profile?
//        val intent = Intent(this,MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
//
//        // Android OREO or higher
//        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
//            .setSmallIcon(R.drawable.ic_notification_icon)
//            .setAutoCancel(true)
//            .setVibrate(longArrayOf(1000,1000,1000,1000))
//            .setOnlyAlertOnce(true)
//            .setContentIntent(pendingIntent)
//
//        builder = builder.setContent(getRemoteView(title, message))
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        // Checking the version of Android
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//
//        notificationManager.notify(0, builder.build())
//
//    }

}