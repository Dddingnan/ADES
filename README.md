# Assignment 3

## About The Project

```
This project aims to create a tool that allows users to figure out where they can travel with their airplanes on a single tank of fuel. Users provide their current location in terms of latitude and longitude and also specify the type of airplane they're using. The system then uses this information, along with data about the airplane's fuel capacity, fuel consumption rates, range, and the city geolocation as well, to calculate the furthest possible city the plane can travel without refueling.
```

---

## Built With

- [Java](<https://en.wikipedia.org/wiki/Java_(programming_language)>)

---

## Getting Started

### Prerequisites

- Java 17
- Eclipse
- Maven

### Installation

- [Here](https://www.java.com/en/download/help/download_options.html)

### How to run?

#### Eclipse

- Start Eclipse
- Go to File-> Import-> General-> Existing Projects Into Workspace
- Select the archive file option
- browse to the ADES zip file in this folder
- click Finish

#### Maven

- Without Test:

  1. mvn clean install -DskipTests
  2. mvn exec:java

- With Test:
  1. mvn clean install
  2. mvn exec:java

---

## Expected result

```
Hello! Welcome to the Airplane Destination Evaluation System!
--------------------------------------------------------
User current season: Summer
--------------------------------------------------------
Please select your current location:
1. Boston
2. New York
3. Los Angeles
4. Taiwan
5. Tokyo
6. Shanghai
7. Bangkok
8. Singapore
9. Ho Chi Minh City
10. Paris
11. Berlin
12. Madrid
13. Rome
14. London
15. Cairo
16. Johannesburg
17. Nairobi
Enter a number between 1 and 17: 1
Please select your airplane type:
1. Boeing 747
2. Airbus A380
Enter a number between 1 and 2: 1
Potential city destinations on a single tank of fuel:
1. New York (Estimated flight duration: 0.51 hours, Fuel consumption: 0.82 gallons)
2. Rome (Estimated flight duration: 10.98 hours, Fuel consumption: 17.69 gallons)
3. Cairo (Estimated flight duration: 14.53 hours, Fuel consumption: 23.42 gallons)
4. London (Estimated flight duration: 8.77 hours, Fuel consumption: 14.14 gallons)
5. Paris (Estimated flight duration: 9.22 hours, Fuel consumption: 14.86 gallons)
6. Madrid (Estimated flight duration: 9.12 hours, Fuel consumption: 14.69 gallons)
7. Los Angeles (Estimated flight duration: 6.95 hours, Fuel consumption: 11.20 gallons)
8. Berlin (Estimated flight duration: 10.13 hours, Fuel consumption: 16.33 gallons)
--------------------------------------------------------
Do you want to make another calculation? (yes/no)
no
See you next time~!
```

---

## To-Do List

---

- Display Flight Duration
  - include a calculation for estimated flight duration based on the speed of the chosen airplane and the distance to each potential destination.
- Fuel Consumption
  - Provide an estimate of fuel consumption for the trip to each potential destination. This could take into consideration the airplane's fuel efficiency, the distance, and even the season if it significantly affects fuel efficiency.
- CO2 Emissions Estimation
  - Given growing environmental consciousness, providing an estimate of the CO2 emissions for each flight could be an interesting addition. This could be calculated based on the fuel consumption and the specific CO2 emissions per liter of aviation fuel.
- Cost of Flight
  - obtain average costs of aviation fuel, you can estimate the cost of the flight based on the amount of fuel needed.
- Adding More Airplane Types
  - increase the number of airplane types for the user to select from. More options would add more depth to the calculations and results.

---

## Author

Ding-Nan, Hsu. - 2023/05/29 -
dingnan@bu.edu
