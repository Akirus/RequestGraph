# RequestGraph

The tool for building request - response graphs in your application.

## What is it?
The tool consist of 3 separate components

* [ ] Library for logging request and responses
    * [ ] For Golang
    * [x] For Java (With a spring boot extension)

It's also not hard to implement the library for your desired language using the log files format described in a docs/logfile.md
* [ ] Standalone application for parsing log files and putting that into elastic search
* [ ] Standalone application working with elastic search to build graphs

The tool allows to query your requests, find out which of them are going to be missing etc.
