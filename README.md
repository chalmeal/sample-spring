# sample-java
SpringBoot 3.4


### memo

#### 1. 以下コマンド実行時、エラーが出た場合
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

#### 2. マイグレーションファイル更新時
> マイグレーションファイルを新しく作成した場合、Dockerを再起動しないと、新しいマイグレーションファイルが適用されない

**対応**
1. コンテナ確認
```
user@user-pc ~ % docker ps -a
CONTAINER ID   IMAGE                                                         COMMAND                   CREATED         STATUS         PORTS                               NAMES
6a86ad58697c   mcr.microsoft.com/vscode/devcontainers/java:21-jdk-bullseye   "/bin/sh -c 'echo Co…"   6 minutes ago   Up 6 minutes                                       sample-java_devcontainer-app-1
fef5784402e3   mysql:8                                                       "docker-entrypoint.s…"   6 minutes ago   Up 6 minutes   0.0.0.0:3306->3306/tcp, 33060/tcp   sample-java_devcontainer-db-1
```
2. コンテナ再起動
```
docker restart sample-java_devcontainer-app-1 sample-java_devcontainer-db-1
```