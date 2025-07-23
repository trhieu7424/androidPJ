package com.example.nguyentronghieu

object CartManager {


    private val _items = mutableListOf<CartItem>()
    val items: List<CartItem> get() = _items

    fun addItem(product: Product) {

        val existingItem = _items.find { it.product.name == product.name }

        if (existingItem != null) {
            existingItem.quantity++
        } else {

            _items.add(CartItem(product = product, quantity = 1))
        }
    }


    fun removeItem(cartItem: CartItem) {
        _items.remove(cartItem)
    }


    fun clearCart() {
        _items.clear()
    }


    fun getTotalPrice(): Double {
        return _items.sumOf { cartItem ->
            val priceValue = cartItem.product.price
                .replace(" triệu", "")
                .replace(" tỷ", "000")
                .toDoubleOrNull() ?: 0.0

            priceValue * cartItem.quantity
        }
    }
}