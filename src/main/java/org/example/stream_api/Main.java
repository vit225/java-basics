package org.example.stream_api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Main {
    public static void main(String[] args) {
        // Инициализация продуктов
        Product p1 = new Product(1L, "Effective Java", "Books", new BigDecimal("150"));
        Product p2 = new Product(2L, "Clean Code", "Books", new BigDecimal("120"));
        Product p3 = new Product(3L, "Harry Potter", "Books", new BigDecimal("80"));
        Product p4 = new Product(4L, "Kids Toy", "Children's products", new BigDecimal("30"));
        Product p5 = new Product(5L, "Lego Set", "Toys", new BigDecimal("200"));
        Product p6 = new Product(6L, "Action Figure", "Toys", new BigDecimal("90"));
        Product p7 = new Product(7L, "Board Game", "Toys", new BigDecimal("50"));
        Product p8 = new Product(8L, "Kids Story Book", "Children's products", new BigDecimal("25"));
        Product p9 = new Product(9L, "Design Patterns", "Books", new BigDecimal("110"));

        // Инициализация заказов (каждый заказ содержит набор продуктов)
        Order o1 = new Order(101L, LocalDate.of(2021, 2, 10), LocalDate.of(2021, 2, 15), "Delivered",
                new HashSet<>(Arrays.asList(p1, p4)));
        Order o2 = new Order(102L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 20), "Delivered",
                new HashSet<>(Arrays.asList(p2, p5, p6)));
        Order o3 = new Order(103L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 22), "Processing",
                new HashSet<>(Arrays.asList(p3, p7)));
        Order o4 = new Order(104L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 25), "Delivered",
                new HashSet<>(Arrays.asList(p8, p9)));
        Order o5 = new Order(105L, LocalDate.of(2021, 2, 20), LocalDate.of(2021, 2, 28), "Delivered",
                new HashSet<>(Arrays.asList(p1, p2, p3)));
        Order o6 = new Order(106L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 19), "Delivered",
                new HashSet<>(Arrays.asList(p4, p5)));
        Order o7 = new Order(107L, LocalDate.of(2021, 3, 10), LocalDate.of(2021, 3, 15), "Delivered",
                new HashSet<>(Arrays.asList(p6, p7, p8)));
        Order o8 = new Order(108L, LocalDate.of(2021, 2, 5), LocalDate.of(2021, 2, 10), "Delivered",
                new HashSet<>(List.of(p9)));
        Order o9 = new Order(109L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 18), "Processing",
                new HashSet<>(Arrays.asList(p1, p2, p5)));
        Order o10 = new Order(110L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 21), "Delivered",
                new HashSet<>(Arrays.asList(p3, p6, p7)));

        // Инициализация клиентов (у каждого - набор заказов)
        Customer c1 = new Customer(1L, "Alice", 1L, new HashSet<>(Arrays.asList(o1, o2, o3, o4, o5)));
        Customer c2 = new Customer(2L, "Bob", 2L, new HashSet<>(Arrays.asList(o6, o7, o8, o9, o10)));
        Customer c3 = new Customer(3L, "Charlie", 2L, new HashSet<>(Arrays.asList(o1, o3, o5, o7, o9)));
        Customer c4 = new Customer(4L, "Diana", 3L, new HashSet<>(Arrays.asList(o2, o4, o6, o8, o10)));
        Customer c5 = new Customer(5L, "Eve", 1L, new HashSet<>(Arrays.asList(o1, o2, o4, o7, o10)));

        List<Customer> customers = Arrays.asList(c1, c2, c3, c4, c5);

//        Задание 1
        List<Product> productsBooks = customers.stream()
                .flatMap(customer2 -> customer2.getOrders().stream())
                .flatMap(order2 -> order2.getProducts().stream())
                .filter(product -> product.getCategory().equals("Books"))
                .filter(product -> product.getPrice().compareTo(new BigDecimal("100.0")) > 0)
                .distinct()
                .toList();
        System.out.println("Задача 1. Получите список продуктов из категории \"Books\" с ценой более 100. Ответ: " + productsBooks);

//        Задание 2
        List<Order> ordersChildren = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals("Children's products")))
                .distinct()
                .toList();
        System.out.println("Задача 2. Получите список заказов с продуктами из категории \"Children's products\". Ответ: " + ordersChildren);

//        Задание 3
        List<Product> productToys = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Toys"))
                .distinct()
                .map(product -> new Product(
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getPrice().multiply(BigDecimal.valueOf(0.1)).add(product.getPrice())
                ))
                .toList();
        BigDecimal sumTotalToys = productToys.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Задача 3. Получите список продуктов из категории \"Toys\" и примените скидку 10% и получите сумму всех продуктов. Ответ: " + productToys + " сумма всех продуктов: " + sumTotalToys);

//        Задание 4
        List<Product> productList = customers.stream()
                .filter(customer -> customer.getLevel() == 2L)
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> !order.getOrderDate().isBefore(LocalDate.of(2021, 2, 1))
                        && !order.getOrderDate().isAfter(LocalDate.of(2021, 4, 1)))
                .flatMap(order -> order.getProducts().stream())
                .toList();
        System.out.println("Задача 4. Получите список продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021. Ответ: " + productList);

//        Задача 5
        List<Product> topProducts = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Books"))
                .distinct()
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2L)
                .toList();
        System.out.println("Задача 5. Получите топ 2 самые дешевые продукты из категории \"Books\". Ответ: " + topProducts);

//        Задача 6
        List<Order> lastThreeOrders = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .sorted((order1, order2) -> order2.getOrderDate().compareTo(order1.getOrderDate()))
                .limit(3L)
                .toList();
        System.out.println("Задача 6. Получите 3 самых последних сделанных заказа. Ответ: " + lastThreeOrders);

//        Задача 7
        List<Product> orderList = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
                .peek(order -> System.out.println(order.getId()))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .toList();
        System.out.println("Задача 7. Получите список заказов, сделанных 15-марта-2021, выведите id заказов в консоль и затем верните список их продуктов. Ответ: " + orderList);

//        Задача 8
        BigDecimal totalAmount = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().getYear() == 2021
                        && order.getOrderDate().getMonthValue() == 2)
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Задача 8. Рассчитайте общую сумму всех заказов, сделанных в феврале 2021. Ответ: " + totalAmount);

//        Задача 9
        BigDecimal avgPayment = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 14)))
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long count = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 14)))
                .count();

        BigDecimal averagePayment = count > 0 ?
                avgPayment.divide(BigDecimal.valueOf(count), RoundingMode.HALF_UP) : BigDecimal.ZERO;
        System.out.println("Задача 9. Рассчитайте средний платеж по заказам, сделанным 14-марта-2021. Ответ: " + averagePayment);

//        Задача 10
        DoubleSummaryStatistics statistics = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getCategory().equals("Books"))
                .mapToDouble(product -> product.getPrice().doubleValue())
                .summaryStatistics();
        System.out.println("Задача 10. Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех продуктов категории \"Книги\". Ответ: ");
        System.out.println("Сумма: " + statistics.getSum());
        System.out.println("Среднее: " + statistics.getAverage());
        System.out.println("Максимум: " + statistics.getMax());
        System.out.println("Минимум: " + statistics.getMin());
        System.out.println("Количество: " + statistics.getCount());

//        Задача 11
        Map<Long, Integer> idValueOrders = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .collect(toMap(
                        Order::getId,
                        order -> order.getProducts().size()
                ));
        System.out.println("Задача 11. Получите данные Map<Long, Integer> → key - id заказа, value - кол-во товаров в заказе. Ответ :" + idValueOrders);

//        Задача 12
        Map<Customer, List<Order>> customerListOrders = customers.stream()
                .collect(toMap(customer -> customer,
                        customer -> customer.getOrders().stream().toList()));
        System.out.println("Задача 12. Создайте Map<Customer, List<Order>> → key - покупатель, value - список его заказов. Ответ: " + customerListOrders);

//        Задача 13
        Map<Order, Double> orderSumProduct = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .collect(toMap(order -> order,
                        order -> order.getProducts().stream()
                                .map(Product::getPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .doubleValue()
                ));
        System.out.println("Задача 13. Создайте Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа. Ответ: " + orderSumProduct);

//        Задача 14
        Map<String, List<String>> categoryNameProduct = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())
                ));
        System.out.println("Задача 14. Получите Map<String, List<String>> → key - категория, value - список названий товаров в категории. Ответ: " + categoryNameProduct);

//        Задача 15
        Map<String, Product> categoryExpensiveProduct = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.reducing((product1, product2) ->
                                product1.getPrice().compareTo(product2.getPrice()) > 0 ? product1 : product2)
                ))
                .entrySet()
                .stream()

                .collect(toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().orElse(null)
                ));
        System.out.println("Задача 15. Получите Map<String, Product> → самый дорогой продукт по каждой категории. Ответ: " + categoryExpensiveProduct);
    }
}
























