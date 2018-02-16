# Speeding
A safe driving app

A very simple app that gets the speed limit of your current location and displays your current speed on an analogue dial. If you go over the limit a warning sound is made. Has a splashscreen and a very ugly color scheme. 

# API 

## Get Speed Limit
Uses the Open Street Map [API](https://wiki.openstreetmap.org/wiki/Overpass_API). Its very badly documented. In Java to get the speed limit of a location simply construct a URL like the below.

`"https://overpass-api.de/api/interpreter?data=[out:json][timeout:25];(way(around:RAD LAT, LON)[\"maxspeed\"];);out;"`

Where RAD is the radius, followed by Latitude and Longitude retrospectively.
 
## Speedometer

Uses [this](https://github.com/Pygmalion69/Gauge) api - its very well documented.
# Image

![Image](https://github.com/oscarmeanwell/SpeedingApp/blob/master/speedapp/img.png)


# APK?
Sure, [here](https://github.com/oscarmeanwell/SpeedingApp/blob/master/speedapp/speedingApp.apk) you go.

# Show me the code

Most the code is [here](https://github.com/oscarmeanwell/SpeedingApp/blob/master/speedapp/app/src/main/java/com/example/oscarmeanwell/sensorslab/MainActivity.java), take a look.
