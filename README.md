# WhichAppUITest
By click on circle random number of coins (1..50) will be appear on screen and with an animation exit from left top corner of screen while a coin's sound playing.

 This test implemented by 2 methods inside 2 branches for clear code & further development:
 
 1-View Animator
 
 2-Object Animator
 
 
 *User can click a lot of times on circle & all the coins will be add to ui without UI performance breakdown...
 * the counter number & coin collector objects(Views) also animating with other UI elements simultaneously.
 * I implement methods for  clean code (redude code repeating )
 * Inside raw folder I put some sounds files for test
 * Well documented code in both branches.
 
 **Repo contains 2 Branches:
 
 1- Master branch : Using View Animator Method
 
 -Using Kotlin Coroutines for smooth ui & performance , while adding objects and playing sounds even in low-mid Android Devices in this method.
 
 -Delete objects immidiately after ending their animation & going out of screen due to handle performance
 
 -play coins sounds : if you wanna play sounds for every object that going out of screen must edit sound file length and choose a file with a shorter playing length in less than a seconds(in milliseconds )
 
 - show animation for counting by a Library
 ...
 
 Demo(mp4) :
 
 https://drive.google.com/file/d/1GXHA6EG5TUtSQVToDFZHu9C5818uQTDU/view?usp=sharing
 
 Android Apk Download Link for View Animator:
 
 https://drive.google.com/file/d/1kg8VTTEOVGy_4Tnh-2Mm_3OdSb8WbziR/view?usp=sharing
 
 2- object_animator branch :
 using Object Animator for impelemt Task and Animation
 
 -Scale objects
 
 -Smooth animations
 
 -play coin sounds
 
 -delete object after exit the screen
 
 -....
 
  Android Apk Download Link for object animator:
  
https://drive.google.com/file/d/1YKyz_D7N3Ii9ynclT1FRAYpsbS6-Q9Gn/view?usp=sharing
 
 
