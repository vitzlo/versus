# Version 0: It Works

[06/10/22] [v0.1.0] Ping pong
* Set up bot functionality
  * Implement basic implementations for the controller and responders
  * Implement VContent interface to represent a message which is a string, an Embed, or empty
  * Add temporary TestResponder to confirm bot is active
* Initialize this file

[06/11/22] [v0.1.1] Some help
* Create ClashResponder and add help message functionality
* Pull help message printing into an abstract class implementing Responder
* Add Responder requirement to provide a hex code for embeds
* Provide utility method for pruning commands after the optional server prefix

[06/11/22] [v0.2.0] Load in
* Add all serialized representations of Player information via the Clash of Clans `/players/{playerTag}` endpoint
* Add data access method to retrieve a pre-existing Clash of Clans player tag given a Discord user ID
  * Changed data representation to a CSV
  * Created FileUt and implemented a CSV parsing method

[06/12/22] [v0.3.0] Load up
* Make first API call and first JSON -> Class serialization
  * Hide Clash of Clans developer token in offline CSV
* Support basic Clash of Clash player info message 
  * Make all classes relevant to info message public temporarily
* Add error strings to utility classes
