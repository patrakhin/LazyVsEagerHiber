package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "Item_LazyVcEager")
public class Item_LazyVcEager {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person_LazyVcEager owner;

    public Item_LazyVcEager(){}

    public Item_LazyVcEager(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Person_LazyVcEager getOwner() {
        return owner;
    }

    public void setOwner(Person_LazyVcEager owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Item_LazyVcEager{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
