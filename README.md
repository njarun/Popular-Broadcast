## Most Viewed Articles - Test Application
[![codecov](https://codecov.io/gh/njarun/Popular-Broadcast/branch/main/graph/badge.svg?token=N4I2MIEZHC)](https://codecov.io/gh/njarun/Popular-Broadcast)   

### Implementation:
A simple list/detail app to load the most viewed articles that are fetched from an API or loaded from local database. 

### Project Breakdown
**Project Architecture:**  
Clean - MVVM

**Project Language:**  
Kotlin  

**Project Highlights:**   
1. Object oriented programming approach
2. DI using Hilt
3. Usage of Jetpack components such as Room, NavGraph, View/DataBinding, ViewModel, LiveData...
4. Kotlin Coroutines/Flow
5. Retrofit/okHttp/Gson network layer
6. Coverage report, Unit/Instrumented tests for Api, Database, ViewModel and App UI
7. Code Obfuscation

**Database used:**   
[Room](https://developer.android.com/jetpack/androidx/releases/room)

**SCM/Git:**   
[GitHub](https://github.com/njarun/Popular-Broadcast)

### Screens
1. Main Article listing page
2. Article Detail page
3. Custom Tab view for the full article reading

### Application flow:
1. Initial launch app will list an empty page with an progress view
2. Loads the data from API or database and populates the list
3. User has option to change the news loading time period from options menu (1,7 or 30 days. Default - 7)
4. Up on clicking on any article the user is navigated to the Detail page
5. The detail page has a button at bottom to open the article in a custom browser tab for full screen reading
6. On back press or action bar back, the user is navigated back to the article list page.

### Capabilities
1. The List <-> Detail transition is with NavGraph component with NavArgs
2. UI is loaded by View/Data binding
3. Functional/Method bindings for views
4. Room database is updated with API results for persistence
5. Hilt for member instance
6. Day/Night theme support

### View Coverage:    
[Codecov](https://app.codecov.io/gh/njarun/Popular-Broadcast)

### Application file, Coverage, test results and screenshots:   
[Resource Folder](https://drive.neptunelabs.xyz/s/BroadcastApp)      
[Direct APK Link](https://drive.neptunelabs.xyz/s/BroadcastApp/download?path=%2F&files=Broadcast%20%28Popular%20Articles%20App%29.apk)   

### To Generate a new coverage from cli:   
1. Clone the repo to local machine   
2. Open terminal in projects root folder and execute 'gradlew connectedCheck'   
3. And for unit test and instrumented test reports, execute    
a) gradlew testDebugUnitTest    
b) gradlew connectedDebugAndroidTest    
   
Generated reports will be available in the build folder with path:   
AppModule > build > reports >   

### Screenshots:

**Home List Page:**     

<img src="https://drive.neptunelabs.xyz/s/BroadcastApp/download?path=%2FScreenshots&files=Day_1_Home.png" width="360" height="780">


**Detail Page:**

<img src="https://drive.neptunelabs.xyz/s/BroadcastApp/download?path=%2FScreenshots&files=Day_2_Detail.png" width="360" height="780">


**Tab View:**

<img src="https://drive.neptunelabs.xyz/s/BroadcastApp/download?path=%2FScreenshots&files=Day_3_Tab.png" width="360" height="780">


**Options Menu:**

<img src="https://drive.neptunelabs.xyz/s/BroadcastApp/download?path=%2FScreenshots&files=Day_4_Options.png" width="360" height="780">


**Dark Theme - Home:**

<img src="https://drive.neptunelabs.xyz/s/BroadcastApp/download?path=%2FScreenshots&files=Night_1_Home.png" width="360" height="780">

