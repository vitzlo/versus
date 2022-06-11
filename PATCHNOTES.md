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