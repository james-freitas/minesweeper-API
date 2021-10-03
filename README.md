# minesweeper-API

## The Game
API for the classic game [Minesweeper](https://en.wikipedia.org/wiki/Minesweeper_(video_game))

## Features
 - API documented using Swagger
   - Access locally at http://localhost:8080 or in Heroku at https://minesweep-api.herokuapp.com/
 - API client library for the API can be found at: [Minesweeper client](https://github.com/james-freitas/minesweeper-lib)
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
 - A scheduler was added to health check the application on a 20-minute basis in order to avoid hibernation



## Todo
The following is a list of items (prioritized from most important to least important):
* Design and implement  a documented RESTful API for the game (think of a mobile app for your API)

* Implement an API client library for the API designed above. Ideally, in a different language, of your preference, to the one used for the API

* When a cell with no adjacent mines is revealed, all adjacent squares will be revealed (and repeat)
* Ability to 'flag' a cell with a question mark or red flag
* Detect when game is over
* Persistence
* Time tracking
* Ability to start a new game and preserve/resume the old ones
* Ability to select the game parameters: number of rows, columns, and mines
* Ability to support multiple users/accounts
 
## Deliverables we expect:
* URL where the game can be accessed and played (use any platform of your preference: heroku.com, aws.amazon.com, etc)
* Code in a public Github repo
* README file with the decisions taken and important notes

## Time Spent
You need to fully complete the challenge. We suggest not spending more than 3 days total.  Please make commits as often as possible so we can see the time you spent and please do not make one commit.  We will evaluate the code and time spent.
 
What we want to see is how well you handle yourself given the time you spend on the problem, how you think, and how you prioritize when time is sufficient to solve everything.

Please email your solution as soon as you have completed the challenge or the time is up
