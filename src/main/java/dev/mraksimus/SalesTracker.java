package dev.mraksimus;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class SalesTracker {

    private final CopyOnWriteArrayList<Sale> sales = new CopyOnWriteArrayList<>();

    public void addSale(String productName, double price) {
        sales.add(new Sale(productName, price));
    }

    public void printSales() {

        if (sales.isEmpty()) {
            System.out.println("Продажи отсутствуют.");
            return;
        }

        System.out.println("Список проданных товаров:");

        for (Sale sale : sales) {
            System.out.printf("Товар: %s, Цена: %.2f%n", sale.getProductName(), sale.getPrice());
        }

    }

    public double getTotalSalesAmount() {
        return sales.stream().mapToDouble(Sale::getPrice).sum();
    }

    public String getMostPopularProduct() {

        if (sales.isEmpty()) {
            return "Нет проданных товаров.";
        }

        Map<String, Integer> productCounts = new HashMap<>();
        for (Sale sale : sales) {
            productCounts.put(sale.getProductName(),
                    productCounts.getOrDefault(sale.getProductName(), 0) + 1);
        }

        return productCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Нет популярных товаров.");
    }

    private static class Sale {

        private final String productName;
        private final double price;

        public Sale(String productName, double price) {
            this.productName = productName;
            this.price = price;
        }

        public String getProductName() {
            return productName;
        }

        public double getPrice() {
            return price;
        }

    }

    public static void main(String[] args) {

        SalesTracker tracker = new SalesTracker();

        tracker.addSale("Яблоко", 1.50);
        tracker.addSale("Банан", 2.00);
        tracker.addSale("Яблоко", 1.50);
        tracker.addSale("Апельсин", 3.00);
        tracker.addSale("Яблоко", 1.50);

        tracker.printSales();

        System.out.printf("Общая сумма продаж: %.2f%n", tracker.getTotalSalesAmount());

        System.out.println("Самый популярный товар: " + tracker.getMostPopularProduct());

    }

}
