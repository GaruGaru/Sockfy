# Sockfy

## Fluent, modular, multithreaded server socket library written in java


# Usage

## Create a running server
``` Java
    Sockfy.create()
            .onPort(7777)
            .run();
```

## Answer clients
``` Java
        Sockfy.create()
                .answer(message -> "got " + message)
                .onPort(7777)
                .run();
```

## Connections Filtering

``` Java
    Sockfy.create()
            .filter(socket -> socket.getInetAddress().isLoopbackAddress()) // Localhost only connections
            .answer(message -> message)
            .onPort(7777)
            .run();
```

# Requests processing
 ``` Java
        Sockfy.create()
                .process(String::toLowerCase)
                .process(message -> message.replace(",","."))
                .answer(message -> message)
                .onPort(7777)
                .run();
```

# Request execution

 ``` Java
        Sockfy.create()
                .execute((client, message) -> Runtime.getRuntime().exec(message))
                .answer(message -> "Executed " + message)
                .onPort(7777)
                .run();
```

# Request filtering

``` Java
    Sockfy.create()
            .filter(socket -> socket.getInetAddress().isLoopbackAddress()) // Localhost only connections
            .answer(message -> message)
            .onPort(7777)
            .run();

```

## Examples

### SocketConnection (Socket wrapper)
``` Java
        SocketConnection connection = new SocketConnection(socket);
        connection.send("Hi");
        connection.readOn(System.out::println);
```

### Echo server
``` Java
    Sockfy.create()
            .answer(message -> message)
            .onPort(7777)
            .run();
```