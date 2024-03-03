# Kuro EncrypterTool
### Description: 
Simple algorithm for encrypting text, based on the hexadecimal system. This console application works as a password manager using the kuro algorithm to encrypt text and store it in a database (Through the Api Rest).

In this new version, the console application needs to connect to a Rest API, in order to centralize the information and be used with different platforms.

### Features:
* Custom Algorithm to encrypt/decrypt text
* API REST connection
* DB direct connection
* OFFLINE mode

### Technologies:
JAVA 8, MySQL

### Preview:
<p> <img src="https://kuronneko.github.io/assets/img/portfoliokurov2.png" width="450"> </p>

### How to install
* Load project with IntelliJ IDEA
* Rename .config.properties.example to .config.properties
* Compile in a .jar file (Optional)
#### Connect to the API REST
* Set API_KEY=
* Set BASE_URL=
* Use valid credentials to connect to the API REST [2] through the console
* How to get the API REST credentials: https://github.com/kuronneko/kuro2etv2-web
#### Connect to the DATABASE
* Create database
```
CREATE TABLE `filek2et` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `text` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
PRIMARY KEY (`id`));
```
* Set USER=
* Set PASSWORD=
* Set SERVER=
* Set DATABASE=
* Select [3] to direct connect to the DATABASE
#### Offline mode
* Select [1]
### Deprecated old versions
* DB console edition - https://github.com/kuronneko/kuro2et
* GUI Local editon - https://github.com/kuronneko/Hex-to-String
