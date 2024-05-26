# Concepts

## Lambda expression

A lambda expression in Java is a concise way to represent an anonymous function (a function without a name) that can be passed as an argument to a method or stored in a variable. It was introduced in Java 8 and provides a clear and concise way to write instances of single-method interfaces (functional interfaces).

### Syntax of Lambda Expressions

The syntax of a lambda expression consists of three parts:
1. **Parameters**: The list of parameters (in parentheses).
2. **Arrow Token**: The arrow token (`->`).
3. **Body**: The body of the lambda expression, which contains the code to be executed.

Here's a general form of a lambda expression:
```java
(parameters) -> expression
```
Or, with a block of statements:
```java
(parameters) -> { statements; }
```

### Examples

1. **No Parameters**:
   ```java
   () -> System.out.println("Hello, World!");
   ```

2. **One Parameter**:
   ```java
   x -> x * x
   ```

3. **Multiple Parameters**:
   ```java
   (x, y) -> x + y
   ```

4. **Block of Code**:
   ```java
   (x, y) -> {
       int result = x + y;
       return result;
   }
   ```

### Usage in Functional Interfaces

Lambda expressions are typically used to provide implementations for functional interfaces. A functional interface is an interface that has exactly one abstract method. For example, `Runnable` is a functional interface because it has only one abstract method, `run`.

### Example: Using Lambda Expression with Runnable

Before Java 8, you would create a `Runnable` using an anonymous inner class:
```java
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running in a thread");
    }
};
new Thread(runnable).start();
```

With a lambda expression, this can be simplified:
```java
Runnable runnable = () -> System.out.println("Running in a thread");
new Thread(runnable).start();
```

Or even more concise:
```java
new Thread(() -> System.out.println("Running in a thread")).start();
```

### Explanation in Context

In the provided code snippet:

```java
Thread simulationThread = new Thread(() -> {
    operationsQueue.addSimulation(totalNumberOfSimulation);
});
simulationThread.start();
```

- `() -> { operationsQueue.addSimulation(totalNumberOfSimulation); }` is a lambda expression.
- The parameters list is empty `()`, indicating that the lambda expression does not take any arguments.
- The arrow token `->` separates the parameters from the body.
- The body `{ operationsQueue.addSimulation(totalNumberOfSimulation); }` contains the code that the thread will execute, which is a call to the `addSimulation` method on the `operationsQueue` object.

This lambda expression provides a succinct and readable way to specify the task that the new thread should execute.

