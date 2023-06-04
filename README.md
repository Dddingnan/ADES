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

### Installation

- [Here](https://www.java.com/en/download/help/download_options.html)

### How to run?

- Start Eclipse
- Go to File-> Import-> General-> Existing Projects Into Workspace
- Select the archive file option
- browse to the ADES zip file in this folder
- click Finish

---

## Expected result

```
Hello! Welcome to the Airplane Destination Evaluation System!
--------------------------------------------------------
Hello! Welcome to the Airplane Destination Evaluation System!
--------------------------------------------------------
User current season: Spring
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
Enter a number between 1 and 17:
1
Please select your airplane type:
1. Boeing 747
2. Airbus A380
Enter a number between 1 and 2: 1
Potential city destinations on a single tank of fuel:
1. New York
2. Los Angeles
3. Paris
4. Berlin
5. Madrid
6. Rome
7. London
--------------------------------------------------------
Do you want to make another calculation? (yes/no)
no
See you next time~!
```

---

## To-Do List

---

- Detect the current season and add the indices of current weather to calculate the city distance.

---

## How to run?

---

With Test:

1. mvn clean install -DskipTests
2. mvn exec:java

Without Test:

1. mvn clean install
2. mvn exec:java

## Author

Ding-Nan, Hsu. - 2023/05/29 -
dingnan@bu.edu
