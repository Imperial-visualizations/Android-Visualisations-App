# Android-Visualisations-App

An Android companion app for Imperial Visualisations for use in lectures and tutorials. 

Written in Kotlin.
Target SDK - 26

Current Features:
- A scrollable, dynamic list with all available visualisations where the the data is fetched from a hosted JSON file
- Some visualisation pages, eg 'Two Body Collisions' fit well onto the smaller screen
- Search functionality

TODO:
- Change CSS styling of pages to ensure they scale correctly on a phone screen, with excess information removed
- Move JSON datasource to server
- Update app to make use of the new visualisation 'suites' structure - significantly different from current structure
- Animated splashscreen on app launch while JSON data is fetched
- Check internet access status at app launch
- Mimic the iOS app's ability to 'Peak & Pop' each cell of the list to view a short of GIF of the visualisation before      committing to launch
