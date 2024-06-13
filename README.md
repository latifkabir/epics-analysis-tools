# EPICS Analysis Tools

## Goal 
- Add scripting and analysis tools as an independent app to Phoebus
- Connect Java pvAccess client to a suitable java plotting/analysis library (comparison with databrowser features)
- Use groovy for scripting 
- Connect to Eclipse for runtime analysis using Groovy
- Integrate in Phoebus as an independent app

**Summary: Analysis tool in Phoebus = pva client + Java plotting/analysis libs + Groovy + Eclipse**

## Java analysis library options
- jas4pp: [jas4pp paper](https://arxiv.org/abs/2011.05329). See also, [home page for jas4pp](https://atlaswww.hep.anl.gov/asc/jas4pp/)
- [https://jfxtras.org/](https://jfxtras.org/) ---> Do Not Use, Obsolete
- [Groot GitHub repo](https://github.com/gavalian/groot) ----> Use this for now.
- [JFreeChart](https://www.jfree.org/index.html)
- [DataMelt](https://datamelt.org/)

## Prepare java analysis library: `groot`

- Clone/download the repository for this [link](https://github.com/gavalian/groot).
- Source your environment to activete JDK 19.
- Generate jar package: `mvn package`
- Generate the documentation: 
- Save the jar with dependencies and add them to your `CLASSPATH`.

## Prepare pvAccess java client

- If you have a local copy of Pheobus, add the following path to your `CLASSPATH`: `<phoebus build>/target/lib/`. Instead of the whole thing, you could just clone only the Phoebus core from [here](https://github.com/ControlSystemStudio/phoebus/tree/master/core) and make a jar file of the Phoebus core framework and then place it under the `CLASSPATH`.

- Try to compile and run [this](https://github.com/ControlSystemStudio/phoebus/blob/master/core/pv-pva/src/test/java/org/phoebus/pv/pva/PVACustomStructDemo.java) example after changing the PV name.

 
## Running the Analysis

- Explore the examples posted.



