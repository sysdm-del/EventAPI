# EventAPI

EventAPI is a Spigot / Bukkit API for minecraft plugin developers.

### Usage

Now, let's go through some basic usage.

To listen to an event, there is an static method in the `Events` class called `listen`. All you do then is put in a class that extends `org.bukkit.Event`.

```java
Events.listen(PlayerJoinEvent.class);
```
That will listen to the event, but it won't do anything! To make it do stuff when you listen to the event, just put `.handler()`.

```java
Events.listen(PlayerJoinEvent.class)
  .handler(e -> } //This will be our consumer in this case (a lambda statement where e is event), but you can also just put any regular consumer there instead of the statement.
    e.setJoinMessage("Welcome " + e.getPlayer().getName() + " to the server!);
  });
```

If you want to set the EventPriority or ignore when the event is cancelled, do this

```java
Events.listen(PlayerJoinEvent.class)
  .handler(e -> }
    e.setJoinMessage("Welcome " + e.getPlayer().getName() + " to the server!);
  }, EventPriority.LOW, false);

```
This project was inspierd by Lucko's event helper, and Conclures EventBuilder.

## Contact
Discord: sysdm#0001

## Contributions
This project is open for any pull requests that has reasonable changes.
