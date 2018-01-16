# PizzaOrder

## Maven Commands
```
mvn clean
```
```
mvn compile
```
```
mvn clean install
```
```
mvn test -DargLine="-Dsource.path=/Users/vthumati/pizza_orders.txt -Ddestination.path=/Users/vthumati/sorted_pizza_orders.txt"
```
```
mvn package -DargLine="-Dsource.path=/Users/vthumati/pizza_orders.txt -Ddestination.path=/Users/vthumati/sorted_pizza_orders.txt"
```
```
java -jar target/pizzaorder.jar --source.path=/Users/vthumati/pizza_orders.txt --destination.path=/Users/vthumati/sorted_pizza_orders.txt --spring.profiles.active=dev,qa
```
