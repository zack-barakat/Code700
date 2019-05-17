package com.android.code700.services

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import com.android.code700.R
import com.android.code700.ui.offerdetail.OfferDetailActivity
import com.android.code700.ui.offers.OffersActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.jetbrains.anko.intentFor


class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        const val KEY_OFFER_ID = "offer_id"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        processMessage(remoteMessage)
    }

    private fun processMessage(remoteMessage: RemoteMessage) {

        val offerId = remoteMessage.data[KEY_OFFER_ID]?.toInt()

        remoteMessage.notification?.let { notificationObject ->
            val notificationBuilder = NotificationCompat.Builder(this, getString(R.string.title_offers))
            with(notificationBuilder) {
                setContentTitle(notificationObject.title)
                setContentText(notificationObject.body)
                setPriority(NotificationCompat.PRIORITY_DEFAULT)
                setStyle(NotificationCompat.BigTextStyle())
                setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                setSmallIcon(R.mipmap.ic_launcher)
                setAutoCancel(true)
            }

            offerId?.let { offerId ->
                val resultIntent = OfferDetailActivity.getStartIntent(this, offerId)
                val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
                    addNextIntentWithParentStack(intentFor<OffersActivity>())
                    addNextIntent(resultIntent);
                    getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
                }
                notificationBuilder.setContentIntent(resultPendingIntent)
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0, notificationBuilder.build())
        }
    }
}