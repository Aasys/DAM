Ashish Shreshta
Assignment 3: Implementing a System Highlighting a Non-Trivial Modern Architecture
12/06/2017
-----------------------------------------

Project Topic: 
	Digital Asset Mangaer

Description: 
	Track the current value of all your crypto currency from a single place. (Not a wallet)
	
Implementation: 
	Webapp (Responsive and Mobile)

Availavle for demo on (Drexel network only or VPN):
	http://resin.cci.drexel.edu:8080/~as3828/
	(note: images seem to take a lot of time to load or not load at all on resin)

Video:
	https://youtu.be/4AIgmsM9NeE

Source code and instuctions available at : 
	https://github.com/Aasys/DAM.git

Demo User:

    email is: joedrexel@drexel.net 
     password is: default

Project Design - Responsive WebApp
----------------
 - Object oriented design through java and Google web toolkit for web
	      Object oriented design philosophy is heavily used in this project. I love OOP designs, as with my previous work experiences, 
		  companies that uses OOP versus functional (experience with big Scala projects), there are very few developers than can dive in and
		  help out in functional programming development. Logic is has better readiblity and the code reuse and extension is also easier in OOP
 - Responsive Design for Mobile
		Using the Material Design Framework work made development easier, UI coding is sometimes daunting due a lot of trial and testing, I had nice
		and experience with GWT Material Design Framework.
 - 	RESTful services
		Languages such as pyhton would have a better time designing and working with RESTfull services, with native dictionary support for JSON data.
		Thankfully, jersey client and gson made fetching and parsing json from coinmarketcap a breeze
		
	 
Code Briefing - maven project
------------------
This web app used Google Web Toolkit as the backbone framework - v2.7.0
   http://www.gwtproject.org/
1. /server/.. contains server side code
2. /shared/.. contains definition of objects shared between client and server
3. /client/.. contains client side code
   
For client side ui we use GWT Material Design Framework - v1.3.3
    http://gwt-material-demo.herokuapp.com/
    
Postgres SQL: [twilbury.cs.drexel.edu] (Need to be on Drexel Network to access)
dbconfig.properties - server\src\main\resources\com\aasys\sts
-------------------------
database connection can be configured from this file

Rest Client: Jersey
JSON Library: gson


Coin Pricing:
rest api proviced by https://coinmarketcap.com/



For DevRunning via Maven in GWT Dev Mode
---------------------------------
1. Compile

     mvn clean install


2. Run locally via tomcat - accessible via http://127.0.0.1:8082/parent/

    mvn tomcat7:run-war-only


3. Start GWT Dev Mode via Maven

    mvn gwt:run -pl web
    

Deploying 
---------------------------------
Method 1: (web server)
     compile and copy the war file form web/target to your webapps folder

Method 2: tux - resin

     compile project
     inflate the war file to ~/resin
     set permission
     goto the resin-url from your browser
	 
	 




