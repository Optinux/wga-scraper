# wga-scraper

A bot that automatically downloads all the images listed in the .xlsx Excel-Sheet and then lables them accordingly.

The source for the images can be found [here](https://www.wga.hu/).

## Usage 📲

- Head over to the [Releases Page](https://github.com/Optinux/wga-scraper/releases) and download the latest .zip
- Proceed to unzip an open up a terminal
- Move into the directory you unzipped the file into and run the following:

  `java -jar .\wga-scraper-0.2-jar-with-dependencies.jar`

  <sup><sup> Note: This has only been tested with [Java 17](https://adoptium.net/) and [Java 8](https://developer.ibm.com/languages/java/semeru-runtimes/downloads). Other versions might behave differently. <sup><sup>

## Building from Source 🧱

- `git clone` the repo
- Navigate into ./studio.optinux.scraper/
- Make sure that you have [Maven](https://maven.apache.org/index.html) installed
- Open up a terminal and run `mvn package` to compile the application
- You will find the result in `./studio.optinux.scraper/target`

## TODO: 📝

- Implement the optional use of proxies in order to circumvent ratelimiting / ip-banning
- Make it use the entire database in its raw form instead of relying on an already trimmed down version

 <details>
    <summary>      
</summary>this somehow took ~8h to do due to me having to pretty much learn 80% of this from scratch lol
 </details>
