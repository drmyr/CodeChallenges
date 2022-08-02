package general;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class FindLowestPrice {

    /**
     * https://cybergeeksquad.co/2022/02/find-lowest-price-amazon-oa-solution.html
     *
     * An e-commerce company is currently celebrating ten years in business. They are having a sale to
     * honor their privileged members, those who have been using their services for the past five years.
     *
     * They receive the best discounts indicated by any discount tags attached to the product. Determine
     * the minimum cost to purchase all products listed.
     *
     * As each potential price is calculated, round it to the nearest integer before adding it to the total.
     *
     * Return the cost to purchase all items as an integer.
     *
     * There are three types of discount tags:
     *
     *     Type 0: discounted price, the item is sold for a given price.
     *     Type 1: percentage discount, the customer is given a fixed percentage discount from the retail price.
     *     Type 2: fixed discount, the customer is given a fixed amount off from the retail price.
     *
     * Example
     * products = [[’10’, d0′, ‘d1′], [’15’, ‘EMPTY’, ‘EMPTY’], [’20’, ‘d1’, ‘EMPTY’]]
     * discounts = [[‘d0’, ‘1, ’27’], [‘d1’, ‘2’, ‘5’]]
     *
     * The product array element are in the form [‘price’, ‘tag1’, ‘tag2’…..tagm-1.]
     * There maybe zero or more discount codes associated with the product.
     * Discount tags in product array maybe ‘EMPTY’ which is the same as NULL value.
     * The Discount Array elements are in form[‘tag’, ‘type’, ‘amount’]
     *
     * 1 If a privileged member buys product 1 listed at a price of 10 with two discount available:
     *
     * Under discount d0 of type 1 , the discounted price is 10- 10*0.27 = 7.30, round 7. Under discount of d1 of type 2, the discounted price price is 10-5 = 5
     *
     * The price to purchase the product :
     * 1 is the lowest of the two, or 5 in this case.
     * The second product is priced at 15 because there are no discounts available
     * The third product is priced at 20 .Using discount tag d1 of type 2, the discount price is 20-5 = 15
     * the total price to purchase the three item is 5+15+15 = 35.
     *
     * NOTES: Not all items will have the Maximum number of tags. Empty tags may just not exist in input or they maybe filled with the string Empty.
     * These are equivalent as demonstrated in the example above
     *
     * Function Description
     *
     * Complete the function. Find LowestPrice in the editor below.
     * [string] products[n][m]: a 2D array of product descriptors as strings:price followed up by up to m-1 discount tags.
     * [string] discounts[3] : a 2D array of tag descriptors as string: tag, type amount. int: the total amount paid in for all listed products,
     * discounts to privileged members pricing.
     */

    public static int findLowestPrice(final String[][] products, final String[][] discounts) {
        class Discount {
            final String id;
            final int type;
            final float discount;

            Discount(final String id, final int type, final float discount) {
                this.id = id;
                this.type = type;
                this.discount = discount;
            }

            int getPrice(final int basePrice) {
                if(type == 1) {
                    return (int)(basePrice - (basePrice * (discount / 100)));
                }
                if(type == 2) {
                    return (int)(basePrice - discount);
                }
                return basePrice;
            }
        }

        final Discount NO_DISCOUNT = new Discount(null, 0, 0.0f);

        class Product {
            final int price;
            final List<Discount> discounts;
            final int cheapestPrice;

            Product(final int price, final List<Discount> discounts) {
                this.price = price;
                this.discounts = discounts;
                this.cheapestPrice = discounts.stream().map(d -> d.getPrice(this.price)).min(Integer::compare).get();
            }
        }

        final Map<String, Discount> discountMap = new HashMap<>();
        for(final String[] discount : discounts) {
            discountMap.put(discount[0], new Discount(discount[0], Integer.parseInt(discount[1]), Float.parseFloat(discount[2])));
        }

        int minPrice = 0;
        for(final String[] product : products) {
            final int price = Integer.parseInt(product[0]);
            final String[] otherDiscounts = Arrays.copyOfRange(product, 1, product.length);
            final Product newProduct = new Product(price, Arrays.stream(otherDiscounts).map(id -> discountMap.getOrDefault(id, NO_DISCOUNT)).collect(toList()));
            minPrice += newProduct.cheapestPrice;
        }

        return minPrice;
    }
}
