![Alt text](/cover.png?raw=true "New UI")

This is the software that runs the instrument cluster in my car. There are some main physical components that this software is integrated with. The physical parts of the automobile such as the small ac generator inside a transmission, that increases electricity as the output shaft spins faster. The wires connecting to the old instrument cluster, containing signals for turn signals, warning lights, etc. The raspberry pi which does the heavy lifting and hosts the java swing application. This project is basically all the software that I used to be able to replace my stock instrument gauge cluster of my car with a lcd screen.

Don't laugh, this was designed for personal use, and has not gone through the rigors of team use/code reviews, so a lot of the code is down and dirty just to get the job done quickly. I expiremented a lot with plugging and unplugging different pieces of software (and hardware!!!), so some classes may exist that are not even being used any more.

Lessons learned:
Don't re-invent your cars electrical system and instrument display (hehehe, the 21st centry version of 'don't reinvent the wheel')

I wanted something unique, and to tinker with. Something challenging which required integrating multiple systems together, both hardware and software.

There is a chance you will see disgusting code in this project. Some of it may be for performance reasons given the raspberry pi's memory limitations, and other dirty code may be because I refactored something somewhere along the way and decided I didn't have time to properly decouple the original something.

There is a way to fire up the instrument cluster locally, and injecting dummy data into it. You can do this by setting the usingArduino field of Manager.java to false. This will then run the Dummy Data Listener class in a thread instead and bypass all the serial communication.

Warning: The project may not initially compile. I removed the SSH Utility because I cannot remember if I built hat or if I borrowed it. But you should be able to see what I was doing in those places. It is not required for the project to run. It was more of a deployment tool to deploy the project to the raspberry pi.

The arduino code can be found in a single file:
/instrument-cluster/src/main/resources/instrument_cluster/instrument_cluster.ino

All that the arduino is used for in this project is pushing signals to the raspberry pi.

Note: To get this to work with the raspberry pi, I had to use a 3v to 5v logic level converter on the rx tx pins.
(Note: Buying this from china took almost 4 weeks to arrive, but the quality was good, so im happy!)

OLD: There is a folder called 'old' containing some old pattern of communicating between arduino and raspberry pi. 

Layout1: A very cool ui (using fun geo and trigonometric functions, that works awesome on fast computers, but sucks pretty bad on the raspberry pi, so I re-wrote it.

Layout2: The current layout I am using. It is much simpler and faster than layout1.
