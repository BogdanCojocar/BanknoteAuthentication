Banknote authentication app

Developed in Python and Scala

1. Python script

The python script is self explanatory. It's created using a python notebook. It can be run from the console using the command:
	ipython notebook BanknoteAuthentication.ipynb

I've also converted the notebook into a pdf so it can be read without installing the environment.


2. Scala application

To create the app I've used the following dependencies:
	- gradle
	- scala 2.11.7
	- spark 1.6.0
	
2.1 Gradle
The project can be compiled from the command line using:
	gradle clean cleanEclipse eclipse

Or from eclipse using the gradle IDE:
	Right click on project -> Gradle -> Refresh All
	
To be able to create a fat jar with all the dependencies I've added the gradle-one-jar plugin in gradle. 
This way I'm able to run sparks apps from eclipse without starting a local job.

2.2 Project structure and classes
The project is organized into three folders:
	 - resources -> where the static resources are kept
	 - src -> source code
	 - test -> integration tests for the main components
	 
The source code is organized into four packages.

2.2.1 org.banknoteauth.data
This package contain the classes used to read the data. The file read is a csv file. I've used csv-spark to read the file into
a dataframe.

2.2.2 org.banknoteauth.driver
Contains the main classes. AppStarter.scala is the entrance point in the app, and SparkSetup.scala handles the control over 
the main spark components.

2.2.3 org.banknoteauth.ml
The package where all the machine learning algorithms are handled. In the BanknoteAuth.scala a model can be trained or new data 
can be tested. The ModelCreator.scala class handles all the work needed to train, evaluate and save a model so it can be used further.
To train the model I've used the Spark.ML higher order Spark.MLib, where I can create processing steps and feed them into a pipeline.
In this application two classifiers can be utilized: Logistic Regression and Random Forests. This classifiers can be made available
using the ModelFactory.scala.

2.2.4 org.banknoteauth.utility
Utility classes for logging and reading configurations from a config file.

