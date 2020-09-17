#Java OOP
NOTE: 
Homework must be posted in ZIP file. 
The ZIP file MUST NOT contain .class files; Only source files are permitted
Requirements

All Vehicles (e.g car, bike, horse, boat, plane) have the following behavior:

they can be started, stopped and be driven for a number of kilometers
 

Implement a hierarchy of Car classes where:



Each concrete Car child should have the following properties (some inherited and some of their own):

properties of a car model which can not change per instance: fuelTankSize, fuelType (PETROL, DIESEL, etc), maxGearNumber (e.g. 7, th),  - static properties
properties that will be modified through instance methods: availableFuel, currentGear(-1 0...7), currentConsumptionPer100Km (i.e 4.7) - instance properties
properties that are instance specific: chassisNumber (and cannot be changed) - this should be final
Behavior: 

a car can be driven for N kms in the current gear
currentConsumptionPer100Km is the consumption of the car in the current gear, each gear should have a specific fuel consumption.
Between a start() and a stop() the car keeps track of the how much it has consumed.
A car cannot start if it hasn't stopped yet (an internal status should be maintained on the instance).
API methods:

#1. start()

cannot start if fuel is 0
cannot start it hasn't stopped yet (if the car is started already)
#2. shiftGear(int gear)

cannot shift into a gear if the car is not started
cannot shift into a gear higher than the max gear
min gear is -1 (reverse)
in order to go into reverse (gear -1), the car must be first placed into gear 0 (e.g. from gear 3 -> 0 -> -1).
The gear shift will produce a change in the fuel consumption as the author desires. (e.g. for Mercedes C-Klasse DIESEL when you shift from 4th to 5th the consumption decreases from 4.6 to 4, and for Mercedes AMG C43 Coupe in the same circumstances the consumption decreases from 8 to 7)
#3. driveForDistance(double distance)

distance must be a positive number
cannot drive for a bigger distance than the current fuel permits according to the fuel consumption per gear
driving the car will lower the available fuel
#4. stop()

stopping the car will make it impossible to change gear or drive it (you must start it to be able to drive it)
#5. addFuel(double quantity)

a car must be stopped in order to be able to add fuel
#6. getAvailableFuel()

provides the available fuel at any point in time calculated based on all the user actions from the instantiation on. (take into account driveForDistance, shiftGear and addFuel
#7. getAverageFuelConsumption()

The average fuel consumption for the whole car lifetime (as getAvailableFuel).

Task:

Create at least 2 car brands with 2 models for each (e.g Volkswagen: VWGolf, VWPassat; Mercedes: CKlasse, SKlasse).

You should have at least one interface and one abstract class.

One should not be able to create an instance of Volkswagen, Car or Vehicle.

