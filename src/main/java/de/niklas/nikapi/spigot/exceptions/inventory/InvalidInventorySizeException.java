package de.niklas.nikapi.spigot.exceptions.inventory;

public class InvalidInventorySizeException extends Exception {

    public InvalidInventorySizeException() {
        super("The Inventory has an invalid size!");
    }
}
