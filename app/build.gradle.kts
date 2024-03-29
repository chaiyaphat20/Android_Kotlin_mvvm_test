plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android") //dagger-hilt
    id ("kotlin-kapt")
}

android {
    namespace = "com.cpfit.examplehiltkotlin"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.cpfit.examplehiltkotlin"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    //dagger - hilt
//    implementation ("com.google.dagger:hilt-android:2.42")
//    kapt ("com.google.dagger:hilt-compiler:2.42")
//    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")


    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10")


    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")


    //another
    implementation ("androidx.activity:activity-ktx:1.5.1")
    implementation ("androidx.fragment:fragment-ktx:1.5.2")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")


    implementation ("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-compiler:2.48")

    // For instrumentation tests
    androidTestImplementation  ("com.google.dagger:hilt-android-testing:2.48")
    kaptAndroidTest ("com.google.dagger:hilt-compiler:2.48")

    // For local unit tests
    testImplementation ("com.google.dagger:hilt-android-testing:2.48")
    kaptTest ("com.google.dagger:hilt-compiler:2.48")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
}

kapt {
    correctErrorTypes = true
}