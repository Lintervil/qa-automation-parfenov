package lesson_4;

public class Bowl {
    private int foodAmount;

    public Bowl() {
        this.foodAmount = 0;
    }

    public Bowl(int foodAmount) {
        this.foodAmount = Math.max(0, foodAmount);
    }

    public boolean decreaseFood(int amount) {
        if (amount > 0 && foodAmount >= amount) {
            foodAmount -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("В миску добавлено " + amount + " еды. Теперь: " + foodAmount);
        }
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}