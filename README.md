# Speeding
A safe driving app

An app that gets the speed limit of your current location and displays your current speed on an analogue dial. If you go over the limit a warning sound is made. Has a splashscreen and a very ugly color scheme.

# API 

Uses the Open Street Map [API](https://wiki.openstreetmap.org/wiki/Overpass_API). Its very badly documented. In Java to get the speed limit of a location simply construct a URL like the below.

`"https://overpass-api.de/api/interpreter?data=[out:json][timeout:25];(way(around:RAD LAT, LON)[\"maxspeed\"];);out;"`

Where RAD is the radius, followed by Latitude and Longitude retrospectively.

![Image](https://github.com/oscarmeanwell/SpeedingApp/blob/master/speedapp/img.png)
