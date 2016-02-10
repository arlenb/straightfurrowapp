Getting Your Project Started

1. Copy the outer 'blank-android' folder to your desired working location.
2. Open 'blank-android/.idea/.name' in a text editor and change the text to be your desired project name.
3. Rename the 'blank-android' folder to your desired project name.
4. Open Android Studio and select 'Import Project (Eclipse ADT, Gradle, etc.)'
5. Browse to your project folder and select import.
6. Let Android Studio do it's thing. 
7. The java folder under manifests should contain com.example.blank package.
8. Right-Click 'java' folder and select 'new'-> 'package'
9. Select option'src\main\java' and click 'OK.' 
10. Type a new package name (e.g. 'com.mysite.MyApp') and click ok.
11. Copy all five items inside com.example.blank to your new package.
12. Delete the example.blank package.
13. Open 'AndroidManifest.xml' and change the package name on lines 3 & 11 to match the new package.
14. Open 'res'->'values'->'strings.xml' to change the app name.
15. Open 'res'->'layout'->'activity_main.xml' and change the package name at the top and bottom of the new one.
16. Try to run the application. There should be an import error in every .java file (import com.example.blank.R;). Change each one to the new package.
17. After changing each import statement to the new one, it should now run.