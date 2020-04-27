## Babbel Falling Words Challenge
This is an Android application for (Falling Words Game) as part of the technical assessment by [Babbel](https://www.babbel.com/)

## Technologies and Libraries
- Kotlin
- LiveData
- ViewModel
- Coroutines
- Dagger2 for dependency injection
- Google Material Design
- Testing: using JUnit, Espresso, and Mockito

## Architecture
The data flow would be like 

Activity/Fragment -- ViewModel -- Use Cases --  data Repository -- Local/Remote data source

Here the business logic is completely decoupled from UI, that makes code easy to test and maintain .
The view model, contains the data required to the view. 
It uses observable data to notify the view about changes. 
It also allows us to pass events to the model. Use cases are interactors where business logic is performed and viewmodel receives its data. Repositories interact with data sources, interface lives in domain and implementation in data layer.   

## Time Distribution
- I've spent about 9 hours on the app.
- (45 MIN) for planning the concept, architecture and setup
- (2 hour) for building views and game UI controls.
- (2:30 hour) for developing logic and the data layer.
- (1:30 hour) for writing tests both unit-testing and UI-testing.
- (30 minutes) for documentation.

## To Improve
Due to time constraints, I wish I could improve the app with the following:
- To Save the data words they learnt and words they failed to answers and also result
- Background service to sync new words and save in local database
- Introducing different levels

