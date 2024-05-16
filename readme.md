# Jetpack Compose in Java

This app demonstrates how to use well established java jakarta practices to implement a reactive Android Jetpack Compose Application using java [^compose].

The following enterprise API are used

- [Microprofile config](https://microprofile.io/specifications/microprofile-config/) to store configuration settings in application.properties
- [Microprofile rest client](https://microprofile.io/specifications/microprofile-rest-client/) to consume rest - apis
- [Jakarta Dependency Injection](https://projects.eclipse.org/projects/ee4j.cdi) implemented with Dagger Hilt
- [RxJava](https://reactivex.io/) to handle the Single [Source Of Truth](https://redux.js.org/understanding/thinking-in-redux/motivation) design pattern (see also [Immer.java](./app/src/main/java/at/htl/leonding/util/immer/Immer.java))

We follow a Single [Source Of Truth Application Design](https://redux.js.org/understanding/thinking-in-redux/three-principles) using ideas from [immerjs](https://immerjs.github.io/immer/)

## Building

We do not check in gradle wrapper files, because this is not our source code.
You must have gradle installed.

``` bash
brew update
brew upgrade
brew install gradle
```
Before opening the project in Android Studio for the first time, run the following command once:

``` bash
gradle wrapper
```

Then you can built the project either from the command line with ./gradlew or use Andriod Studio.

[^compose]: except for the Composables where kotlin is used in the layout files instead of .xml
