package algorithms;

public class KnapsackBruteForce {

    public static void main(String[] args) {
        Item[] items = {
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
        };
        int capacity = 50;

        int maxValue = knapsack(items, capacity);
        System.out.println("Maximum value: " + maxValue);
    }

    static class Item {
        int value;
        int weight;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    /**
     * Returns the maximum value that can be put in a knapsack with the given capacity.
     * Each item can only be selected once. If you pack an item it consumes its weight in the capacity
     * Your algorithm should implement a brute-force approach with a time complexity
     * of O(2^n) where n is the number of items.
     * @param items
     * @param capacity
     * @return
     */
    public static int knapsack(Item[] items, int capacity) {

        return knapsack(items, capacity, 0, 0);

    }
    public static int knapsack(Item[] items, int remainingCapacity, int index, int currentValue){
        if (index == items.length || remainingCapacity <= 0) {
            return currentValue;
        }
        int excludeValue = knapsack(items, remainingCapacity, index + 1, currentValue);
        int includeValue = 0;
        if (remainingCapacity >= items[index].weight) {
            includeValue = knapsack(items, remainingCapacity - items[index].weight, index + 1, currentValue + items[index].value);
        }
        return Math.max(excludeValue, includeValue);
    }
}
