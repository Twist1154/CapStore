package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.Users;
import za.ac.cput.util.Helper;

/**
 * Factory class for creating instances of {@link Cart}.
 * Provides static methods to create {@link Cart} objects from various inputs.
 *
 * @author Rethabile Ntsekhe
 * @date 24-Aug-24
 */
public class CartFactory {

    /**
     * Creates a {@link Cart} instance from various inputs.
     *
     * @param id    the ID of the cart
     * @param users  the {@link Users} entity associated with this cart
     * @param total the total cost of the cart
     * @return a new {@link Cart} object with properties set from the input parameters
     */
    public static Cart createCart(Long id,
                                  Users users,
                                  Double total
    ) {

        if (Helper.isNullOrEmpty(users) ||
                Helper.isDoubleNullOrEmpty(total)
        ) {

            throw new IllegalArgumentException("User cannot be null cart Factory can't be null");
        }

        return new Cart.Builder()
                .setId(id)
                .setUsers(users)
                .setTotal(total)
                .build();
    }
}