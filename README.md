# minesweeper-API

## The Game
API for the classic game [Minesweeper](https://en.wikipedia.org/wiki/Minesweeper_(video_game))

## Features
 - API documented using Swagger
   - Access locally at http://localhost:8080 or in Heroku at https://minesweep-api.herokuapp.com/
 - API client library for the API can be found at: [Minesweeper client](https://github.com/james-freitas/minesweeper-lib)
 - When a cell with no adjacent mines is revealed, all adjacent squares will be revealed (and repeat)
 - Ability to 'flag' a cell with a question mark or red flag
 - Detect when game is over
 
## Run application locally
 - Add the following environment variable
 ```bash
 APPLICATION_BASE_URL=http://localhost:8080
 ```

## Functional Decision
 - At this moment the game supports only a 9 square game (3 bombs)
 - It was chosen to store all the cells of a game in a uni dimensional array for each game
 - The persistence is not implemented yet so the games are saved on memory inside a map
 - To the reveal cells and their no bomb adjacent neighbors was used recursion 

## Non Functional Decisions
 - Stack chosen: Java, SpringBoot
 - Pattern used: MVC
 - Test stack: JUnit, Mockito
 - API documented using Swagger
 - Implemented Continuous Delivery using Heroku

## Manual testing
 - After start the application you can use the postman collection on `src/main/postman` folder to test it using postman 

## Todo
The following is a list of items prioritized from most important to least important:

* Persistence
* Time tracking
* Ability to start a new game and preserve/resume the old ones
* Ability to select the game parameters: number of rows, columns, and mines
* Ability to support multiple users/accounts
