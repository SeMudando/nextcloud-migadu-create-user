# Nextcloud + Migadu Create User

Small utility script to create an user both in a Nextcloud instance and at [Migadu](https://www.migadu.com/), a great email provider with good API support.

## Usage

### Install the gradle distribution

1. Clone this project
2. Execute `./gradlew install` (or `gradlew.bat install` on Windows)
3. Find the executable at build/install/nextcloud-migadu-create-user/bin

### Execute the command

Run the command
```
./nextcloud-migadu-create-user USERNAME DISPLAYNAME PASSWORD EMAIL NEXTCLOUDBASICAUTH NEXTCLOUDAPIURL MIGADUBASICAUTH MIGADUURL
```

Replacing:

| Argument           | Description                                                                                                                                |
|--------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| USERNAME           | username/userid to be used at nextcloud                                                                                                    |
| DISPLAYNAME        | full user name, will be used at both nextcloud and migadu                                                                                  |
| PASSWORD           | user's password, both for nextcloud and for the email (which should be used inside nextcloud, without the user knowing the email password) |
| EMAIL              | desired user email localpart@domain.tld                                                                                                    |
| NEXTCLOUDBASICAUTH | user:password for nextcloud admin encoded with Base64                                                                                      |
| NEXTCLOUDAPIURL    | url to connect to nextcloud i.e. https://cloud.example.com/ocs/v1.php/                                                                     |
| MIGADUBASICAUTH    | user:password for migadu admin encoded with Base64                                                                                         |
| MIGADUURL          | Url to connect to migadu i.e. https://api.migadu.com/v1/                                                                                   |