## Quotidian - Daily Quote App

Quotidian is a simple app that delivers a new quote every day to users. Whether you're looking for inspiration, motivation, or just a daily dose of wisdom, Quotidian has got you covered. It provides a new quote every day that you can swipe through and read at your own pace. The app's minimalist design makes it easy to focus on the quote without distractions.

## Demo

<img src="https://user-images.githubusercontent.com/65452331/221412388-085e2dd5-7a05-429b-aa97-d67aa3dc5099.png" width="45%"></img> <img src="https://user-images.githubusercontent.com/65452331/221412390-488a2c57-7b1e-4cd6-8025-754b11822481.gif" width="44%"></img> 

Click [here](https://appetize.io/app/qdldm4fp4jqjvieqyltswe3yv4) to try out our sample app without downloading or installing anything.

## Tech Stack

**Programming Language:** Kotlin

**UI:** Jetpack Compose

**Development Tools:** Android Studio IDE

**Libraries and Frameworks:**

 - Android Jetpack Components: Room, Navigation
 - WorkManager used to schedule PeriodicWorkRequets that run in the background. 
 - Retrofit and OkHttp for network requests and API communication

 - Coil for image loading and caching

 - Gson for JSON serialization and deserialization

 - Kotlin Coroutines for managing background tasks
 
 - Material Design guidelines and standards for UI design

 - Gradle for building and dependency management

**Database:** Room DB

**Version Control:** Git

**Testing:** 

 - JUnit and Mockito for unit testing
 - Espresso for UI testing

## Roadmap

- [ ] Personalization options
- [ ] Quote archives
- [ ]  Quote search


## Acknowledgements

I would like to acknowledge the [ZenQuotes.io](https://zenquotes.io/) for providing the data used in this project. The documentation can be found [here](https://docs.zenquotes.io/zenquotes-documentation/).
Also, I would like to thank Alex Styl for creating a Jetpack Compose Modifier for card [gestures](https://github.com/alexstyl/compose-tinder-card). 

## Development Setup

To build the app from this repository, follow these steps:

- Clone the repository to your local machine using HTTP: git clone https://github.com/lssarao/jetpack-compose-quotidian.git

- Open Android Studio on your local machine and click on open an existing Android Studio project.

- Browse to the directory where you cloned the repository and import it.

- Once the project is imported, you can build this app by clicking the run button in Android Studio.

- Select your device or an emulator from the available options and and install the app.

If you encounter any issues or errors during the setup or building process, feel free to raise an issue on the repository.


## Contribute

I will be more than happy to receive your PR, I am open to suggestions or modifications.

## Feedback
If you have any feedback, please reach out to us at lssarao411@gmail.com

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/lssarao/jetpack-compose-quotidian/blob/master/LICENSE) file for details.
