# Fix Hilt Plugin Not Found Error

The project is failing to sync because the Hilt plugin `com.google.dagger.hilt.android` is used without a version number and is not defined in the version catalog or the root build file. Additionally, Hilt dependencies are using hardcoded and inconsistent versions.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///D:/AndroidStudioProjects/jetpack-news-shorts-app/gradle/libs.versions.toml)
- Add Hilt version `2.60.1`.
- Define Hilt libraries (`hilt-android`, `hilt-compiler`) in the `[libraries]` section.
- Define the Hilt plugin in the `[plugins]` section.

#### [MODIFY] [build.gradle.kts (root)](file:///D:/AndroidStudioProjects/jetpack-news-shorts-app/build.gradle.kts)
- Add the Hilt plugin to the `plugins` block with `apply false` to manage its version centrally.

#### [MODIFY] [app/build.gradle.kts](file:///D:/AndroidStudioProjects/jetpack-news-shorts-app/app/build.gradle.kts)
- Replace the direct plugin ID with the version catalog alias.
- Update Hilt dependencies to use the version catalog definitions for consistency.

## Verification Plan

### Automated Tests
- Run `gradle sync` to ensure the project syncs successfully.
- Run `./gradlew :app:assembleDebug` to verify the build.
