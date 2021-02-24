
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


## To Improve
Due to time constraints, I wish I could improve the app with the following:
- To Save the data words they learnt and words they failed to answers and also result
- Background service to sync new words and save in local database
- Introducing different levels

