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
maven { url 'https://maven.aliyun.com/repository/public/' }
maven { url 'https://maven.aliyun.com/repository/jcenter/' }
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

- https://codelabs.developers.google.com/codelabs/kotlin-android-training-data-binding-basics/index.html#0
