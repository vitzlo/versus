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
* Make first API call and first JSON to Class serialization
  * Hide Clash of Clans developer token in offline CSV
* Support basic Clash of Clash player info message 
  * Make all classes relevant to info message public temporarily
* Add error strings to utility classes

[06/12/22] [v0.3.1] Clean up
* Reformat player info message for neatness
  * Added utility method to neatly format key/value pairs in fields
* Made Clash of Clans class fields private again
  * Auto-generated getters for each field
* Added support for `<prefix>` placeholders in string responses
* More abstractions to utility classes

# Version 1: CLASH OF CLANS!

[06/13/22] [v1.0.0] CLASH!
* Enable registration of Clash of Clans accounts via Discord
  * Add file utility methods for checking for value presence and writing to CSVs
  * Add data access methods for checking user IDs and setting player tags
* Add library for easier file reading/writing and conversion into lists of strings
* Close HTTP responses to prevent leakage