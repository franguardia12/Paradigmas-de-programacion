package org.tp1.view;

import org.tp1.model.Items.Item;

public class ItemView {
    public ItemView() {
    }

    public void mostrarItem(int i, Item item) {
        System.out.printf("%d Item: %s, unidades: %s\n", i, item.getNombre(), item.getUnidades());
    }
}
