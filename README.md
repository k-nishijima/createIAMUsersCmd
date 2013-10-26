# これはなに？

AWSハンズオンのためのサポートツールです。
CSVファイルに従ってIAMアカウントを発行し、SignInパスワードを設定します。
まとめてアカウントを発行するときなど、ハンズオン以外の用途にも使えるかもしれません。

# 事前準備

* create-users.csvをプロジェクトルートに作成します。
* 書式はカンマ区切りCSVで、「グループ名、ユーザ名、パスワード」の並びで1行に1ユーザ記述します。パスワードは "rand" とすると自動で作成します。
* 所属させるグループは予め作成しておいて下さい。
* src/main/scala/CreateUsers.scala に管理者権限を持つ AccessKey と SecretKey を設定して下さい。


# 実行

$ sbt
> run

create-users-result.csv が出力されますのでこれを使ってアカウント配布などの作業を行って下さい。


# JAWS-UG沖縄をどうぞよろしく:)

http://jaws-ug.jp/bc/okinawa/
