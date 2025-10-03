**sample-java**

## はじめに
Javaのサンプルプロジェクトです。小規模のシンプルなレイヤリングアーキテクチャとして構成しています。

## 💻開発環境
|            | バージョン |
| ---------- | -------- |
| Java       | 21.0.7   |
| SpringBoot | 3.4.3    |
| MySQL      | 8        |

## 🏯ソフトウェアアーキテクチャ
```
├── src
|　　　├── main
|     |     ├── java
|     |     |     └── sample
|     |     |            ├── context
|     |     |            ├── controller                         ------------- プレゼンテーション層
|     |     |            |      ├── EmployeeController.java         ------------- 
|     |     |            |      ├── RestErrorAdvice.java            -------------
|     |     |            |      └── SampleController.java           -------------
|     |     |            ├── dto                                -------------
|     |     |            |      ├── request                         -------------
|     |     |            |      ├── response                        -------------
|     |     |            |      ├── ErrorDto.java                   -------------
|     |     |            |      ├── ResponseDto.java                -------------
|     |     |            |      └── ResultDto.java                  -------------
|     |     |            ├── model                              ------------- ドメイン層
|     |     |            |      ├── mapper                          -------------
|     |     |            |      └── Employee.java                   -------------
|     |     |            ├── repository                         ------------- リポジトリ層
|     |     |            |      ├── impl                            -------------
|     |     |            |      ├── sql                             -------------
|     |     |            |      └── EmployeeRepository.java         -------------
|     |     |            ├── service                            ------------- アプリケーション層
|     |     |            |      ├── impl                            -------------
|     |     |            |      └── EmployeeService.java            -------------
|     |     |            └── SampleApplication.java             ------------- アプリケーションエントリーポイント
|     |     └── resources                                   -------------
|     |           ├── application.properties                    -------------
|     |           ├── data.sql                                  -------------
|     |           └── message.properties                        -------------
|　　　└── test                                         -------------
|　　　      ├── employee                                    -------------
|　　　      ├── SampleApplicationTests.java                 -------------
|　　　      └── TestHelper.java                             -------------
├── build.gradle
└── settings.gradle
```

## ▶️起動
> 本プロジェクトはDevcontainerで動作を想定しています。

**前提**
* Visual Studio Code(vscode)がインストールされていること
* vscodeの拡張機能に「Dev Container」がインストールされていること
* 「Docker for Desktop」がインストールされていること

**手順**
1. プロジェクトのホームから、「<> Code」ボタンを押下
2. HTTPSのURLをコピー
3. vscode上で任意のフォルダにクローン
4. vscodeで、クローンしたリポジトリを開く
5. vscodeの左下の青いボタン（「><」のようなボタン）を押下
6. vscode上部に入力フォームが表示され、項目が複数出るため、「Reopen in Container」を押下
7. 少し待機した後、vscode下部に「Java: Ready」となっている箇所があることを確認する
8. 左端メニューバーからデバッグ（Debug）を押下し、「Run cammelia-api」の▶️を押下
9. 少し待機した後、「Started CamelliaApplication」が表示すれば起動完了

### Swagger UI
アプリケーション起動後、以下のURLにアクセスして動作確認可能
```
http://localhost:8080/swagger-ui/index.html
```