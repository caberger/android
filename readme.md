# Jetpack Compose in Java

This app demonstrates how to use well established java jakarta practices to implement an Android Jetpack Compose Application.

The following enterprise apis are used

- Microprofile config to store configuration settings in application.properties
- Microprofile reasteasy to consume rest - apis
- Dagger to implement Dependency Injection with jakarta.inject.Inject;
- RxJava to handle the Single [Source Of Truth](https://redux.js.org/understanding/thinking-in-redux/motivation) design pattern (see also Immer.java)

## Building

We do not check in gradle wrapper files, because this is not our source code.
You must have gradle installed.

``` bash
brew update
brew upgrade
brew install gradle
```
Befor opening the project in Android Studio for the first time, run the following command once:

``` bash
gradle wrapper
```

Then you can built the project either from the command line with ./gradlew or use Andriod Studio.
