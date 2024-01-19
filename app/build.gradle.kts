plugins {

    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.iplabank"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.iplabank"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    android {

    }


    kotlin {
        android {

        }




        dependencies {

            implementation("androidx.core:core-ktx:1.12.0")
            implementation("androidx.appcompat:appcompat:1.6.1")
            implementation("com.google.android.material:material:1.11.0")
            implementation("androidx.constraintlayout:constraintlayout:2.1.4")
            implementation("androidx.room:room-common:2.6.1")
            implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
            implementation("com.google.android.gms:play-services-location:21.0.1")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
            val camerax_version = "androidx.camera:camera-view:1.3.1"
            implementation("androidx.camera:camera-core:1.1.0")
            implementation("androidx.camera:camera-camera2:1.1.0")
            implementation("androidx.camera:camera-lifecycle:1.1.0")
            implementation("androidx.camera:camera-view:1.1.0")
            testImplementation("junit:junit:4.13.2")
            androidTestImplementation("androidx.test.ext:junit:1.1.5")
            androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
            val room_version = "2.6.1"
            implementation("androidx.room:room-runtime:$room_version")
            ksp("androidx.room:room-compiler:$room_version")
            implementation("androidx.room:room-ktx:$room_version")
            annotationProcessor("androidx.room:room-compiler:$room_version")



        }
    }
}
