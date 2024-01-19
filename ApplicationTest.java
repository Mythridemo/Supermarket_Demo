package com.bt.cdo.supermarket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupermarketPricing
{
    @Test
    public void testIndividualPricing() {
        SupermarketPricing supermarket = new SupermarketPricing();

        // Test individual pricing for item A
        assertEquals(50, supermarket.calculateTotal("A", 1));

        // Test individual pricing for item B
        assertEquals(20, supermarket.calculateTotal("B", 1));

        // Test individual pricing for item C
        assertEquals(5, supermarket.calculateTotal("C", 1));
    }

    @Test
    public void testSpecialPricing() {
        SupermarketPricing supermarket = new SupermarketPricing();

        // Test special pricing for item A
        assertEquals(130, supermarket.calculateTotal("A", 3));
        assertEquals(180, supermarket.calculateTotal("A", 4));  // 3 for 130 + 1 for 50 (individual)

        // Test special pricing for item B
        assertEquals(38, supermarket.calculateTotal("B", 2));
        assertEquals(58, supermarket.calculateTotal("B", 3));  // 2 for 38 + 1 for 20 (individual)

        // Test special pricing for item C
        assertEquals(50, supermarket.calculateTotal("C", 3));  // Special pricing
    }

    @Test
    public void testMixedPricing() {
        SupermarketPricing supermarket = new SupermarketPricing();

        // Test a mix of individual and special pricing
        assertEquals(100, supermarket.calculateTotal("A", 2));  // 2 * 50 (individual)
        assertEquals(58, supermarket.calculateTotal("B", 3));   // 2 for 38 + 1 for 20 (individual)
        assertEquals(90, supermarket.calculateTotal("C", 5));   // 2 * 5 (individual) + 3 for 50 (special)
    }

    @Test
    public void testZeroQuantity() {
        SupermarketPricing supermarket = new SupermarketPricing();

        // Test zero quantity for each item
        assertEquals(0, supermarket.calculateTotal("A", 0));
        assertEquals(0, supermarket.calculateTotal("B", 0));
        assertEquals(0, supermarket.calculateTotal("C", 0));
    }

    @Test
    public void testInvalidItem() {
        SupermarketPricing supermarket = new SupermarketPricing();

        // Test an invalid item
        assertEquals(-1, supermarket.calculateTotal("D", 1));  // Assuming -1 is used to indicate an error or invalid item
    }

    
}
    }

