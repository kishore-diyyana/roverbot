## Environment:
- Java version: 11
- Maven version: 3.*
- Spring Boot version: 2.2.1.RELEASE

## Input Data:
Sample Input in JSON object:
```
{
  "Position":{

		"Direction":"N",

		"X":10,

		"Y":10 
	     },

	     "Move":[

		{

		 "O":"1",

		 "L":90,

		 "F":10

		},

		{

		 "O":"2",

		 "R":180,

		 "B":20

		}

	  ]
}
```
## Output Data:
```
{
	"Position": {

	"Direction": "E",

	"X" : "-20",

	"Y": "10"

	}
}
```

## Requirement:
Create a REST Application to control a rover bot. 
Save the Input and Output in an XML file.

- First API: The input for the API is the current position of the Bot and directions. The output is final position of the Bot.

- Second API: read the current position from the file of the rover and return in API response.


## Assumptions
- No obstacles

- Rotate will be one of: 0, 90, 180, 270 or 360

## Key Data Elements
Direction:
----------
N: North

E: East

S: South

W: West


Movement:
----------
F : Forward

B: Backward


Rotation:
------------
R: Turn right

L: Turn Left

O: Order of the instruction

## Commands
- run: 
```bash
mvn clean spring-boot:run
```
- install: 
```bash
mvn clean install
```
- test: 
```bash  - -Dmaven.test.skip
mvn clean test
```
