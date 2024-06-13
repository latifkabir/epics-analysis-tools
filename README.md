# EPICS Analysis Tools

## Goal 
- Add scripting and analysis tools as an independent app to Phoebus
- Connect Java pvAccess client to a suitable java plotting/analysis library 
- Use groovy for scripting 
- Connect to Eclipse for runtime analysis using Groovy
- Integrate in Phoebus as an independent app

**Summary: EPICS Analysis Tools = pva client + Java plotting/analysis libs + Groovy + Eclipse**

## Java analysis library options
- jas4pp: [jas4pp paper](https://arxiv.org/abs/2011.05329). See also, [home page for jas4pp](https://atlaswww.hep.anl.gov/asc/jas4pp/)
- [https://jfxtras.org/](https://jfxtras.org/) ---> Do Not Use, Obsolete
- [Groot GitHub repo](https://github.com/gavalian/groot) ----> Use this for now.
- [JFreeChart](https://www.jfree.org/index.html)
- [DataMelt](https://datamelt.org/)

## Prepare java analysis library: `groot`

- Clone/download the repository for [this](https://github.com/gavalian/groot) repository.
- Source your environment to activete JDK 19 if you do not have it already.
- Generate jar package: `mvn package`
- Generate the documentation (for your reference) using the shell script provided. 
- Save the jar with dependencies and add them to your `CLASSPATH`.

## Prepare pvAccess java client

- If you have a local copy of Pheobus, add the following path to your `CLASSPATH`: `<phoebus build>/target/lib/`. Instead of the whole thing, you could just clone only the Phoebus core from [here](https://github.com/ControlSystemStudio/phoebus/tree/master/core) and make a jar file of the Phoebus core framework and then place it under the `CLASSPATH`.
- Try to compile and run [this](https://github.com/ControlSystemStudio/phoebus/blob/master/core/pv-pva/src/test/java/org/phoebus/pv/pva/PVACustomStructDemo.java) example after changing the PV name.

 
## Running EPICS Analysis

- Once, both pva client (from Phoebus) and plotting library (from groot) jar files are under your `CLASSPATH`, you should be able to explore/run the examples posted.
- The examples posted assumes that a demo IOC is already running in the background.


## To Do

- Make separate Java package for epics analysis tools
- Allow scripting using Groovy
