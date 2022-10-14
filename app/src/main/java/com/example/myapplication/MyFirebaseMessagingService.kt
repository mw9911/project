package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val TAG = "FirebaseTest"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        createNotificationChannel() // 채널생성함수 호출
        val type = remoteMessage.data["type"]?.let {
            NotificationType.valueOf(it)
        }
        val textTitle = message.data["title"]
        val body = message.data["body"]

        type ?: return
        if(remoteMessage.data.isNotEmpty()){
            sendNotification(remoteMessage.notification?.title,
                remoteMessage.notification?.body!!)

        }
        else{

        }
    }

    // Firebase Cloud Messaging Server 가 대기중인 메세지를 삭제 시 호출
    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }

    // 메세지가 서버로 전송 성공 했을때 호출
    override fun onMessageSent(p0: String) {
        super.onMessageSent(p0)
    }

    // 메세지가 서버로 전송 실패 했을때 호출
    override fun onSendError(p0: String, p1: Exception) {
        super.onSendError(p0, p1)
    }

    // 새로운 토큰이 생성 될 때 호출
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sendRegistrationToServer(token)
    }

    private fun sendNotification(title: String?, body: String){
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // 액티비티 중복 생성 방지
        val pendingIntent = PendingIntent.getActivity(this, 0 , intent,
            PendingIntent.FLAG_ONE_SHOT) // 일회성

        val channelId = "channel" // 채널 아이디
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) // 소리
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title) // 제목
            .setContentText(body) // 내용
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 예외처리
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 , notificationBuilder.build()) // 알림 생성
    }

    // 받은 토큰을 서버로 전송
    private fun sendRegistrationToServer(token: String){

    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_DESCRIPTION

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



    companion object {
        private const val CHANNEL_NAME = "ATCCHANEL"
        private const val CHANNEL_DESCRIPTION = "ATCCHANEL를 위한 채널"
        private const val CHANNEL_ID = "ATC_ID"
    }
    private fun createNotification(type: NotificationType?, textTitle:String?, body: String?) : Notification {

        // 알림을 누르면 실행될 수 있게 intent를 만들어줌
        val intent = Intent(this,MainActivity::class.java).apply {
            putExtra("notificationType","${type?.title} 타입")
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP) // 이미 해당 액티비티일때 인텐트가 연결되면 1개만 유지함
        }

        val resultPendingIntent = PendingIntent.getActivity(this,type!!.id,intent, FLAG_UPDATE_CURRENT)



        // 기본 빌더
        val builder =  NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(textTitle)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(resultPendingIntent) // 알림을 누를경우 생기는 인탠트
            .setAutoCancel(true)

        Log.d("createNotification",type?.title.toString())

        // 빌더에 type별로 속성을 추가해준다.
        when(type){
            NotificationType.NORMAL -> Unit

            // 많은 text가 올 경우
            NotificationType.EXPANDABLE -> {
                builder.setStyle(NotificationCompat.BigTextStyle()
                    .bigText(bigEmoji))
            }

            //layout을 통해 custom하기
            NotificationType.CUSTOM -> {
                builder.setStyle(NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(RemoteViews(packageName,R.layout.view_custom_notification)
                        .apply {
                            setTextViewText(R.id.title, textTitle)
                            setTextViewText(R.id.message, body)
                        })

            }
        }

        // build()하여 반환함으로써 notify에서 한번에 사용
        return builder.build()
    }
}