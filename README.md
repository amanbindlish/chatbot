# Chatbot

You have seen chat bots in various apps like Zomato, Swiggy etc. This chatbot app gives a working demo of how chatbot works. A single screen app  
with support to chat with multiple users with a single bot.

## App Screens
<p align="center">
    <img src="/media/chatbot_demo_one.gif" width="473" height="1000">   <img src="/media/chatbot_demo_two.gif" width="473" height="1000">
</p>

## App features
* App shows a single chat screen just like WhatsApp which is used to chat with bot.
* App has multi users chat support from navigation drawer.
* Complete code is written in <b>Kotlin</b>.
* App follows <b>MVVM (Model View ViewModel)</b> architecture.
* App works in both landscape and portrait modes.
* Room library is used to store chat data locally for offline support.
* App stores past chat history both sent and received and shows on launch.
* Backend api is also written in python, which just respond back the same message with 'sup?' prepended.

## Api used
You can find the api file at root level in project, file named 'get.py'.
To run the api just install python on local machine and run below command in terminal:
    - python get.py
This will start a local server Running on http://0.0.0.0:5000/  in development mode.

Final GET api looks like this: http://192.168.1.3:5000/api/chat/
along with below params to be passed,
    * <b>apiKey: 6nt5d1nJHkqbkphe</b> : apikey to be sent to the server (you can use the provided key). 
    * <b>chatBotId: 63906</b> : chatBotId for bot id (you can use the provided id).
    * <b>message: Hello</b> : message to be sent to the bot.
    * <b>externalID: aman123</b> : unique external id to differentiate speaker from others.

## App structure
App follows below structure:
* <b>di</b>
    * Dependency injection related classes (modules, components etc.)
* <b>model</b>
    * Local db and repository and api related classes.
* <b>utils</b>
    * Utility classes along with connection receiver. 
* <b>view</b>
    * Contains activity, fragment and adapter classes.
* <b>viewmodel</b>
    * Contains viewModel classes where actual business logic is written.
    
## App Specs
- Minimum SDK 16
- [Java8](https://java.com/en/download/faq/java8.xml)
- [Kotlin](https://kotlinlang.org/)
- MVVM Architecture
- Android Architecture Components (LiveData, Lifecycle, ViewModel, Room Persistence Library, ConstraintLayout)
- [Lifecycle-aware components](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [Dagger 2](https://google.github.io/dagger/) for dependency injection.
- [Retrofit 2](https://square.github.io/retrofit/) for API integration.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) for data observation.
- [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel) for data container.
- [Room](https://developer.android.com/topic/libraries/architecture/room) for data persistence.
- [Gson](https://github.com/google/gson) for serialisation.
- [Okhhtp3](https://github.com/square/okhttp) for implementing interceptor, logging and mocking web server.
