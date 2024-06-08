package draft;

import org.testng.annotations.Test;

public class ValidatePrice {

    AddItemsToCart addCart= new AddItemsToCart();


    @Test
    public void validatePrice() throws InterruptedException {
        addCart.loginInCart();
        addCart.AddToCart();
    }
}
