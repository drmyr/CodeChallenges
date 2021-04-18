package design;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * https://javarevisited.blogspot.com/2016/06/design-vending-machine-in-java.html
 *
 * Problem Statement
 * You need to design a Vending Machine which
 *
 *      Accepts coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter.
 *      Allow user to select products Coke(25), Pepsi(35), Soda(45)
 *      Allow user to take refund by canceling the request.
 *      Return the selected product and remaining change if any
 *      Allow reset operation for vending machine supplier.
 */
public class VendingMachineImpl implements VendingMachinePublic, VendingMachineSupplier {

    private final Map<Class<? extends Product>, Integer> inventoryMap;
    private final Map<Integer, Class<? extends Product>> productTypeById;

    public VendingMachineImpl() {
        this.inventoryMap = new HashMap<>();
        this.productTypeById = new HashMap<>();
    }

    @Override
    public Selection select(final int id) {
        if(!this.productTypeById.containsKey(id)) {
            throw new IllegalArgumentException("Invalid selection " + id);
        }
        final Class<? extends Product> productClass = this.productTypeById.get(id);
        if(this.inventoryMap.get(productClass) < 1) {
            throw new IllegalArgumentException("No product left for " + id);
        }

        this.inventoryMap.merge(productClass, -1, Integer::sum);
        try {
            final Constructor<? extends Product> ctor = productClass.getConstructor();
            final Product product = ctor.newInstance(id);
            return new Selection(product);
        } catch (final NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Purchase> acceptFunds(final Selection selection, final Coin coin) {
        final Selection.AppliedFunds appliedFunds = selection.getAppliedFunds();
        appliedFunds.applyFunds(coin.getValue());
        if(appliedFunds.currentFunds() >= selection.getPrice()) {
            int diff = appliedFunds.currentFunds() - selection.getPrice();
            final List<Coin> coins = new ArrayList<>();
            // TODO optimize into a loop over a TreeSet<Coin> and call .ceiling()
            while(diff > 0) {
                if(diff - 25 >= 0) {
                    coins.add(new Coin(25));
                    diff -= 25;
                    continue;
                }
                if(diff - 10 >= 0) {
                    coins.add(new Coin(10));
                    diff -= 10;
                    continue;
                }
                if(diff - 5 >= 0) {
                    coins.add(new Coin(5));
                    diff -= 5;
                    continue;
                }
                coins.add(new Coin(1));
                diff -= 1;
            }
            return Optional.of(new Purchase(selection.getProduct(), coins));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int refund(final Selection selection) {
        this.inventoryMap.merge(selection.getProduct().getClass(), 1, Integer::sum);
        return selection.getAppliedFunds().refund();
    }

    @Override
    public void addProduct(final Product product) {
        this.inventoryMap.merge(product.getClass(), 1, Integer::sum);
        this.productTypeById.put(product.getId(), product.getClass());
    }

    @Override
    public void removeProduct(final Product product) {
        this.inventoryMap.remove(product.getClass());
        this.productTypeById.remove(product.getId());
    }
}

interface VendingMachinePublic {

    Selection select(final int id);

    Optional<Purchase> acceptFunds(final Selection selection, final Coin coin);

    int refund(final Selection selection);
}

interface VendingMachineSupplier {

    void addProduct(final Product product);

    void removeProduct(final Product product);

}

class Purchase {
    private final Product product;
    private final List<Coin> change;
    public Purchase(final Product product, final List<Coin> change) {
        this.product = product;
        this.change = change;
    }

    List<Coin> getChange() {
        return change;
    }

    Product getProduct() {
        return product;
    }
}

class Coin {
    private final int value;
    public Coin(final int value) {
        if(value != 25 || value != 10 || value != 5 || value != 1) {
            throw new IllegalArgumentException("Valid coin values are 25, 10, 5 and 1. Your value was invalid: " + value);
        }
        this.value = value;
    }

    int getValue() {
        return this.value;
    }
}

class Selection {
    private final Product product;

    private final AppliedFunds appliedFunds;

    public Selection(final Product product) {
        this.product = product;
        this.appliedFunds = new AppliedFunds(0);
    }

    AppliedFunds getAppliedFunds() {
        return this.appliedFunds;
    }

    Product getProduct() {
        return product;
    }

    int getPrice() {
        return product.getPrice();
    }

    class AppliedFunds {
        private int appliedFunds;

        public AppliedFunds(final int appliedFunds) {
            this.appliedFunds = appliedFunds;
        }

        int currentFunds() {
            return appliedFunds;
        }

        void applyFunds(final int money) {
            appliedFunds += money;
        }

        int refund() {
            final int temp = appliedFunds;
            appliedFunds = 0;
            return temp;
        }
    }
}

abstract class Product {
    protected final int id;
    protected Product(final int id) {
        this.id = id;
    }

    abstract int getId();
    abstract int getPrice();
}
class Coke extends Product {
    public Coke(final int id) {
        super(id);
    }

    public int getId() {
        return id;
    }
    public int getPrice() {
        return id;
    }
}
class Pepsi extends Product {
    public Pepsi(final int id) {
        super(id);
    }

    public int getId() {
        return id;
    }
    public int getPrice() {
        return id;
    }
}
class Soda extends Product {
    public Soda(final int id) {
        super(id);
    }

    public int getId() {
        return id;
    }
    public int getPrice() {
        return id;
    }
}
