
# BLOOD-BANK-APP

## How to use?
> For IOS APP - https://github.com/abdulbasitha/BLOOD-BANK-APP-IOS
1. Download or clone this repo.

2. Open it in Android Studio.
```
Android Studio. File >> Open...
```
3. Build the project.
```
Build >> Build Apk(s)
```
4. Setup sign in with Google with your own credentials.
  [https://developers.google.com/identity/sign-in/android/start-integrating]
  
    Q) How to get the SHA-1 fingerprint certificate in Android Studio for debug mode? <br>
    Ans) <a href="https://stackoverflow.com/questions/27609442/how-to-get-the-sha-1-fingerprint-certificate-in-android-studio-for-debug-mode(https://stackoverflow.com/questions/27609442/how-to-get-the-sha-1-fingerprint-certificate-in-android-studio-for-debug-mode)"> SHA-1 fingerprint certificate</a>
  
6. Select the `API` folder,  set it up in a webserver or localhost, create a database and import `blood_bank_app.sql` into Phpmyadmin.

7. Set String `path = "your-url-paste-here" (eg:http://localhost/bloodbank/) or <your-api-files-directory-in-webserver>`  in `JsonAct.java` 

8. There are a total of 4 activities in this application :
          
          `Splash`: Splash Screen Activity,
          `SignInWithGoogle`: SignIn Activity,
          `MainActivity`: Donor Registration,
          `NeedBlood`: Retrieve Donors List 
          


## Preview
<div>
<img src="https://github.com/abdulbasitha/BLOOD-BANK-APP/blob/master/preview/1.jpg" width="30%" height="30%" >
<img src="https://github.com/abdulbasitha/BLOOD-BANK-APP/blob/master/preview/2.jpg" width="30%" height="30%">
<img src="https://github.com/abdulbasitha/BLOOD-BANK-APP/blob/master/preview/3.jpg" width="30%" height="30%">
</div>
<div>
<img src="https://github.com/abdulbasitha/BLOOD-BANK-APP/blob/master/preview/4.jpg" width="30%" height="30%">
<img src="https://github.com/abdulbasitha/BLOOD-BANK-APP/blob/master/preview/5.jpg" width="30%" height="30%">
<img src="https://github.com/abdulbasitha/BLOOD-BANK-APP/blob/master/preview/6.jpg" width="30%" height="30%">
</div>

## Support

In case of any queries or issues, please contact us at:
[info@techzia.in](mailto:info@techzia.in)


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

### Happy Coding 💖
