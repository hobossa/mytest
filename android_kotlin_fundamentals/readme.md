# [Android Kotlin Fundamentals](https://developer.android.com/courses/kotlin-android-fundamentals/overview)
- Code sample
    - https://developer.android.com/samples/index.html
    - https://github.com/android
    - https://github.com/googlesamples
    - https://github.com/google-developer-training
- Blog
    - https://blog.google/products/android/
    - http://android-developers.blogspot.com/
    - http://officialandroid.blogspot.com/
- Codelabs http://codelabs.developers.google.com/
- Vocabulary Glossary https://developers.google.com/android/for-all/vocab-words
- Youtube Channel https://www.youtube.com/user/androiddevelopers
- Google Developers Training https://developers.google.com/training
- Search on Stack Overflow http://stackoverflow.com/
    - In the search box, enter [android]. The [] brackets indicate that you want to search for posts that have been tagged as being about Android.
    - You can combine tags and search terms to make your search more specific. Try these searches:
        - [android] and [layout]
        - [android] "hello world"
----
```
maven { url 'https://maven.aliyun.com/repository/public/' }
maven { url 'https://maven.aliyun.com/repository/jcenter/' }
```
----
- Note the generatedJava folder. This folder contains files that Android Studio generates when it builds the app. Don't edit anything in this folder, because your changes might be overridden when you rebuild the app.
- The tools namespace is used when you want to define placeholder content that is only used in the preview or the design editor in Android Studio. Attributes using the tools namespace are removed when you compile the app.
- An important thing to note about vector drawables is that they are supported in API 21 onwards. We can use Android X compatibility library to support vector drawables(back to API level 7)
    - Open build.gradle (Module: app). Add this line to the defaultConfig section:
    ``` vectorDrawables.useSupportLibrary = true ```
    - Click the Sync Now button
    - Open the activity_main.xml layout file. Add this namespace to the root <LinearLayout> tag, underneath the tools namespace:
    ```xmlns:app="http://schemas.android.com/apk/res-auto"```
    - The app namespace is for attributes that come from either your custom code or from libraries and not the core Android framework.
    - Change the android:src attribute in the <ImageView> element to be app:srcCompat.
    ```app:srcCompat="@drawable/empty_dice"```
    - Build and run your app. You won't see anything different on the screen, but now your app doesn't need to use generated PNG files for the dice images no matter where the runs, which means a smaller app file.
- Right/left versus start/end
    - "Right" and "left" always refer to the right and left sides of the screen, whether your app uses a left-to-right (LTR) flow or a right-to-left (RTL) flow. "Start" and "end" always refer to the start and end of the flow:
        - For a LTR flow, start = left and end=right.
        - For a RTL flow, start=right and end=left.
    - If your app targets API level 17 (Android 4.2) or higher:
        - Use "start" and "end" instead of "left" and "right".
        - For example, android:layout_marginLeft should become android:layout_marginStart to support RTL languages.
    - If you want your app to work with versions lower than Android 4.2; that is, if the app's targetSdkVersion or minSdkVersion is 16 or lower:
        - Add "start" and end" in addition to "left" and "right".
        - For example, use both android:paddingLeft and android:paddingStart.
- A [chain](https://developer.android.com/training/constraint-layout/#constrain-chain) is a group of views that are linked to each other with bidirectional constraints.
- Data binding has the following benefits:
    - Code is shorter, easier to read, and easier to maintain than code that uses findByView().
    - Data and views are clearly separated. This benefit of data binding becomes increasingly important later in this course.
    - The Android system only traverses the view hierarchy once to get each view, and it happens during app startup, not at runtime when the user is interacting with the app.
    - You get type safety for accessing views. (Type safety means that the compiler validates types while compiling, and it throws an error if you try to assign the wrong type to a variable.)
- If you use the same ID for the menu item as for the destination fragment, you don't need to write any code at all to implement the onClick listener!
- the Safe Args plugin, which generates NavDirection classes
    - Use one of the generated NavDirection classes to pass type-safe arguments between the GameFragment and the game-state fragments.
    - pass parameters from one fragment to another. To prevent bugs in these transactions and make them type-safe, you use a Gradle plugin called [Safe Args](https://developer.android.com/topic/libraries/architecture/navigation/navigation-pass-data#Safe-args). The plugin generates NavDirection classes, and you add these classes to your code.
    - In some cases, for example if you are not using Gradle, you can't use the Safe Args plugin. In these cases, you can use Bundles to directly pass data.
- How to use the [Timber](https://github.com/JakeWharton/timber) library for logging in your app. Timber has several advantages over the built-in Android Log class. 
    - Generates the log tag for you based on the class name.
    - Helps you avoid showing logs in a release version of your Android app.
    - Allows for integration with crash-reporting libraries.
- Key points about lifecycle to remember:
    - When you set up a resource in a lifecycle callback, also tear the resource down.
    - Do setup and teardown in corresponding methods.
    - If you set up something in onStart(), stop or tear it down again in onStop().
- Use the Android lifecycle library. There are three main parts of the lifecycle library:
    - Lifecycle owners, which are the components that have (and thus "own") a lifecycle. Activity and Fragment are lifecycle owners. Lifecycle owners implement the LifecycleOwner interface.
    - The Lifecycle class, which holds the actual state of a lifecycle owner and triggers events when lifecycle changes happen.
    - Lifecycle observers, which observe the lifecycle state and perform tasks when the lifecycle changes. Lifecycle observers implement the LifecycleObserver interface.
- Restore bundle data
    - If the activity is being re-created, the onRestoreInstanceState() callback is called after onStart(), also with the bundle. Most of the time, you restore the activity state in onCreate(). But because onRestoreInstanceState() is called after onStart(), if you ever need to restore some state after onCreate() is called, you can use onRestoreInstanceState().
- Important: Always use ViewModelProvider to create ViewModel objects rather than directly instantiating an instance of ViewModel.
    - During configuration changes such as screen rotations, UI controllers such as fragments are re-created. However, ViewModel instances survive. If you create the ViewModel instance using the ViewModel class, a new object is created every time the fragment is re-created. 
    ```
    viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
    ```
- ViewModelFactory : You'll pass in the arguments during the ViewModel initialization using the factory method pattern.
    - In this task, you create a ViewModel with a parameterized constructor for the score fragment and a factory method to instantiate the ViewModel.
- 

- https://codelabs.developers.google.com/codelabs/kotlin-android-training-lifecycles-logging/index.html#0