# wga-scraper

A bot that automatically downloads all the images listed in the .xlsx Excel-Sheet and then lables them accordingly.

The source for the images can be found [here](https://www.wga.hu/).

## Usage 📲

- Head over to the [Releases Page](https://github.com/Optinux/wga-scraper/releases) and download the latest .zip
- Proceed to unzip an open up a terminal
- Move into the directory you unzipped the file into and run the following:
  `java -jar .\wga-scraper-0.1-jar-with-dependencies.jar`

  <sup><sup> Note: This has only been tested with [Java 17](https://adoptium.net/) and [Java 8](https://developer.ibm.com/languages/java/semeru-runtimes/downloads). Other versions might behave differntly. <sup><sup>

## Building from Source 🧱

- git clone the repo
- Navigate into ./studio.optinux.scraper/
- open up a terminal and run `mvn package` to compile the application
- you will find the result in `./studio.optinux.scraper/target/wga-scraper-0.1-jar-with-dependencies.jar`

## TODO: 📝

- implement the optional use of proxies in order to circumvent ratelimiting / ip-banning
- make all DEBUG stuff optional (silent switch)
- create a proper CLI for the .jar
- make it use the entire database in its raw form instead of relying on an already trimmed down version
