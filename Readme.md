#  Firebase Setup with android
# 1 goto firebase and crate project
# select Android as a platform
# give name others optional - SHA KEY and others
# copy google.service.json file put to App Level

# Add Dependencies add sync
   # project level build.gradle = app level build.gradle(project)
     # Top-level build file where you can add configuration options common to all sub-projects/modules.
    # buildscript { repositories { google()  // Google's Maven repository // Check that you have the following line (if not, add it):}
    # dependencies { classpath 'com.google.gms:google-services:4.3.13' } }
    # plugins { id 'com.android.application' version '7.1.2' apply false id 'com.android.library' version '7.1.2' apply false }
    # task clean(type: Delete) { delete rootProject.buildDir }
   # app level build.gradle(module)
    # plugins { id 'com.android.application' id 'com.google.gms.google-services' }
    # android {
    #  compileSdk 32
    #    defaultConfig {applicationId "com.example.extremeiptv_link" minSdk 21 targetSdk 32 versionCode 1 versionName "1.0" testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner" }
    #    buildTypes {release {minifyEnabled false proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'} }
    #    compileOptions {sourceCompatibility JavaVersion.VERSION_1_8 targetCompatibility JavaVersion.VERSION_1_8}}
    #    dependencies { implementation 'androidx.appcompat:appcompat:1.4.1' implementation 'com.google.android.material:material:1.5.0' implementation 'androidx.constraintlayout:constraintlayout:2.1.3' implementation 'com.google.firebase:firebase-firestore:23.0.2' testImplementation 'junit:junit:4.13.2'  androidTestImplementation 'androidx.test.ext:junit:1.1.3' androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0' implementation platform('com.google.firebase:firebase-bom:30.2.0') implementation 'com.google.firebase:firebase-analytics' 
    # }




# click firestore project
#  firebase firestore data save
## https://firebase.google.com/docs/firestore/manage-data/add-data#java

![Login Screen](https://user-images.githubusercontent.com/64083148/178135063-36ff972d-3c53-4463-b622-caa654b83d68.jpg)
![splash screen](https://user-images.githubusercontent.com/64083148/178135069-a540ad5c-a19e-48bb-9276-7819a72bd242.jpg)
![web view](https://user-images.githubusercontent.com/64083148/178135072-37e49964-c29c-42a8-90ef-db8a0270a47c.jpg)
![Firebase firestore](https://user-images.githubusercontent.com/64083148/178135074-2410b0a6-117e-4c50-9950-4a0de852109b.PNG)



