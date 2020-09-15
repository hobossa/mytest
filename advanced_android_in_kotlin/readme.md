- [Advanced Android in Kotlin](https://developer.android.com/courses/kotlin-android-advanced/overview)

----

- Starting with API level 26, all notifications must be assigned to a channel.
- the notification is displayed outside your app. To make an intent work outside your app, you need to create a new PendingIntent.
- To support devices running Android 7.1 (API level 25) or lower, you must also call setPriority() for each notification, using a priority constant from the NotificationCompat class.
- You cannot get the size of the view in onCreate(), because the size has not been determined at this point. We can get the size in onSzieChanged()
- Any constraints specified in a ConstraintSet will override the constraints specified in the layout file. When using MotionLayout, you should specify the constraints of any views that are animated in the motion scene XML file instead of in the layout. Any views that are not animated may be constrained in the layout instead of the motion scene.
- Pure view model tests usually go in the test source set because the view model code shouldn't rely on the Android framework or OS. As a local test, it will also run faster because you run the tests on your local machine and not on an emulator or device.
- The [AndroidX Test libraries](https://developer.android.com/training/testing/set-up-project) include classes and methods that provide you with versions of components like Applications and Activities that are meant for tests. When you have a local test where you need simulated Android framework classes (such as an Application Context), follow these steps to properly set up AndroidX Test:
    1. Add the [AndroidX](https://developer.android.com/training/testing/set-up-project) Test core and ext dependencies
    2. Add the [Robolectric Testing library](http://robolectric.org/) dependency
    3. Annotate the class with the AndroidJunit4 test runner
    4. Write AndroidX Test code
- AndroidX Test APIs is that they are built to work both for local tests and instrumented tests.
- What is [Robolectric](http://robolectric.org/)?
    - The simulated Android environment that AndroidX Test uses for local tests is provided by Robolectric. Robolectric is a library that creates a simulated Android environment for tests and runs faster than booting up an emulator or running on a device.
-