package org.example.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person_LazyVcEager")
public class Person_LazyVcEager {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public Person_LazyVcEager(){}

    public Person_LazyVcEager(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Item_LazyVcEager> items;

    public List<Item_LazyVcEager> getItems() {
        return items;
    }

    public void setItems(List<Item_LazyVcEager> items) {
        this.items = items;
    }

    public void addItem(Item_LazyVcEager item){
        if (this.items == null){
            this.items = new ArrayList<>();
        }
        this.items.add(item);
        item.setOwner(this);
    }

    @Override
    public String toString() {
        return "Person_LazyVcEager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
