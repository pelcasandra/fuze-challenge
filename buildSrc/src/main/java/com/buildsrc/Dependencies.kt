import org.gradle.api.artifacts.dsl.DependencyHandler
object Dependencies {
    private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    private const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    private const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    private const val materialGoogle = "com.google.android.material:material:${Versions.googleMaterial}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    private const val composeUi = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    private const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    private const val composeLifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
    private const val ComposeConstraint = "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraint}"
    private const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    private const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    private const val gson = "com.google.code.gson:gson:${Versions.gson}"
    private const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.swipeRefresh}"

    private const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    private const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    private const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    //test libs
    private const val junit = "junit:junit:${Versions.junit}"
    private const val assertj = "org.assertj:assertj-core:${Versions.assertj}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val mockk = "io.mockk:mockk:${Versions.mockk}"
    private const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val composeUiJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    private const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    private const val coreTest = "androidx.arch.core:core-testing:${Versions.coreTest}"

    val appLibraries = mutableListOf<String>().apply {
        add(kotlinStdLib)
        add(appCompat)
        add(fragment)
        add(materialGoogle)
        add(coreKtx)
        add(composeMaterial)
        add(composeUi)
        add(composeUiTooling)
        add(composeLifeCycleRuntime)
        add(ComposeConstraint)
        add(navigationFragment)
        add(navigationUi)
        add(coroutines)
        add(viewModel)
        add(coil)
        add(hilt)
    }

    val dataLibraries = mutableListOf<String>().apply {
        add(retrofit)
        add(coroutinesCore)
        add(coroutines)
        add(gson)
        add(gsonConverter)
        add(hilt)
    }

    val androidTestLibraries = mutableListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
        add(composeUiJunit)
        add(assertj)
        add(coreTest)
    }

    val testLibraries = mutableListOf<String>().apply {
        add(junit)
        add(mockk)
        add(coroutinesTest)
        add(coreTest)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}