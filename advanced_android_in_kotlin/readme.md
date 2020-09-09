- [Advanced Android in Kotlin](https://developer.android.com/courses/kotlin-android-advanced/overview)

----

- Starting with API level 26, all notifications must be assigned to a channel.
- the notification is displayed outside your app. To make an intent work outside your app, you need to create a new PendingIntent.
- To support devices running Android 7.1 (API level 25) or lower, you must also call setPriority() for each notification, using a priority constant from the NotificationCompat class.
- You cannot get the size of the view in onCreate(), because the size has not been determined at this point. We can get the size in onSzieChanged()