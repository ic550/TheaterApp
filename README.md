
# 劇場ナビ

**劇場ナビ**は、東京の複数の劇場（東京宝塚劇場、電通四季劇場「海」、シアターオーブ、赤坂ACTシアター）で開催される公演情報を一覧・検索・編集・お気に入り登録できるWebアプリケーションです。
ダークテーマとグリーンアクセントを基調としたモダンなデザインを採用しています。

## 主な機能

- 公演一覧表示（劇場別）
- 公演詳細ページの表示
- 公演情報の管理（管理者のみ編集・削除可能）
- 公演のお気に入り登録機能（ユーザー別）
- 公演カレンダーから日付指定検索
- YouTubeの予告動画埋め込み
- 管理者・一般ユーザーのログイン機能（Spring Security）

## 技術構成

| 技術        | 内容                              |
|-------------|-----------------------------------|
| フレームワーク | Spring Boot (Java)              |
| テンプレート | Thymeleaf + Bootstrap（ダークテーマ） |
| データベース | MySQL                            |
| ORM         | MyBatis                           |
| セキュリティ | Spring Security (Role-Based Login) |
| フロント     | HTML, CSS, JavaScript, Bootstrap  |

## ファイル構成（抜粋）

```
src/
├── main/
│   ├── java/com/example/app/
│   │   ├── controller/
│   │   ├── domain/
│   │   ├── mapper/
│   │   ├── service/
│   │   ├── login/
│   ├── resources/
│   │   ├── static/
│   │   │   ├── css/
│   │   │   ├── images/
│   │   ├── templates/
│   │   │   ├── calendar.html
│   │   │   ├── login.html
│   │   │   ├── detail.html
│   │   │   ├── admin/
│   │   │   ├── favorite/
│   │   ├── application.yml
│   │   ├── mapper/*.xml
```

## セットアップ方法

1. MySQLで `theaterdb` を作成し、`schema.sql` に従ってテーブルを作成します。
2. `application.yml` でDB接続情報を設定します。
3. `TheaterAppApplication` を実行してアプリを起動します。
4. `http://localhost:8080` にアクセスします。

## 管理者アカウント

初期管理者アカウント（データベースに手動追加）：

```
username: admin
password: pass (BCryptでハッシュ化されたパスワード)
role: ADMIN
```

## 一般ユーザーアカウント

初期ユーザーアカウント（データベースに手動追加）：

```
username: user
password: pass (BCryptでハッシュ化されたパスワード)
role: USER
```

```
username: user2
password: pass (BCryptでハッシュ化されたパスワード)
role: USER
```

## カスタマイズ

- ロゴ画像は `static/images/logekijounavi3.png` に配置。
- ログイン画面左上にロゴ表示済み

## 今後追加予定の機能

- ユーザーの新規登録
- マイページの追加
- 公演一覧で、既にお気に入り登録されているものにマークを表示
- 劇場ごとの評価・レビュー投稿機能

---

© 2025 劇場ナビ
