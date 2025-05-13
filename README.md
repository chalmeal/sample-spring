# sample-java
SpringBoot 3.4


### memo
**以下コマンド実行時、エラーが出た場合**
```
vscode ➜ \~/workspace (master) \$ ./gradlew bootRun

bash: ./gradlew: /bin/sh: bad interpreter: Permission denied
```
**対応**
```
chmod +x gradlew
```
**再実行**
```
vscode ➜ ~/workspace (master) $ ./gradlew bootRun

Welcome to Gradle 8.12.1!

Here are the highlights of this release:
 - Enhanced error and warning reporting with the Problems API
 - File-system watching support on Alpine Linux
 - Build and test Swift 6 libraries and apps
```