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
- To use runBlockingTest you:
    - Annotate the function or class with @ExperimentalCoroutinesApi.
    - Wrap the code calling the suspend function with runBlockingTest.

- runBlockingTest
    - runBlockingTest handles both running the code deterministically and providing a synchronization mechanism.
    - runBlockingTest takes in a block of code and blocks the test thread until all of the coroutines it starts are finished. It also runs the code in the coroutines immediately (skipping any calls to delay) and in the order they are called–-in short, it runs them in a deterministic order.
    - runBlockingTest essentially makes your coroutines run like non-coroutines by giving you a coroutine context specifically for test code.
    - You do this in your tests because it's important that the code runs in the same way every single time (synchronous and deterministic).
- runBlocking
    - runBlocking is used when you need to use coroutines in your test doubles as opposed to your test classes.
    -  since runBlocking is not part of the kotlinx-coroutines-test library, you do not need to use the ExperimentalCoroutinesApi annotation.

- runBlocking vs. runBlockingTest
    - Both runBlocking and runBlockingTest block the current thread and wait until any associated coroutines launched in the lambda complete.
    - In addition, runBlockingTest has the following behaviors meant for testing:
        1. It skips delay, so your tests run faster.
        2. It adds testing related assertions to the end of the coroutine. These assertions fail if you launch a coroutine and it continues running after the end of the runBlocking lambda (which is a possible coroutine leak) or if you have an uncaught exception.
        3. It gives you timing control over the coroutine execution.
- So why use runBlocking in your test doubles, like FakeTestRepository? Sometimes you will need a coroutine for a test double, in which case you do need to block the current thread. This is, so that when your test doubles are used in a test case, the thread blocks and allows the coroutine to finish before the test does. Test doubles, though, aren't actually defining a test case, so they don't need and shouldn't use all of the test specific features of runBlockingTest.
    - Writing test classes, meaning classes with @Test functions, use runBlockingTest to get deterministic behavior.
    - Writing test doubles, use runBlocking.
- Which source set should you put your database tests in?
    - Note that in general, make database tests instrumented tests, meaning they will be in the androidTest source set. This is because if you run these tests locally, they will use whatever version of SQLite you have on your local machine, which could be very different from the version of SQLite that ships with your Android device! Different Android devices also ship with different SQLite versions, so it's helpful as well to be able to run these tests as instrumented tests on different devices.

