package org.example;

import org.example.model.Item_LazyVcEager;
import org.example.model.Person_LazyVcEager;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person_LazyVcEager.class)
                .addAnnotatedClass(Item_LazyVcEager.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            /*Person_LazyVcEager personLazyVcEager = session.get(Person_LazyVcEager.class, 1);
            System.out.println("Получили человека");

            // all list items get (Lazy)
            System.out.println(personLazyVcEager.getItems());*/

            /*//Eager loading
            Item_LazyVcEager itemLazyVcEager = session.get(Item_LazyVcEager.class, 1);
            System.out.println("Получили товар");

            //Not query db. Person had been into object Item
            System.out.println(itemLazyVcEager.getOwner());*/

            /*//add Eager into Person
            Person_LazyVcEager personLazyVcEager = session.get(Person_LazyVcEager.class, 1);
            System.out.println("Получили человека из таблицы");

            //query db do not execute . Items had already been into object Person
            System.out.println(personLazyVcEager.getItems());*/

            /*//Use Hiber.initialize
            Person_LazyVcEager personLazyVcEager = session.get(Person_LazyVcEager.class, 1);
            System.out.println("Получили человека из таблицы");
            System.out.println(personLazyVcEager);

            //list object loading
            Hibernate.initialize(personLazyVcEager.getItems());*/


            Person_LazyVcEager personLazyVcEager = session.get(Person_LazyVcEager.class, 1);
            System.out.println("Получили человека из таблицы");

            session.getTransaction().commit();
            System.out.println("session had been closed");


            //session and transaction reopened anywhere else
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("into second session");
            // При использовании второго метода эта строка не нужна, т.к.  получаем айди выполняя запрос к БД
            personLazyVcEager = (Person_LazyVcEager) session.merge(personLazyVcEager);
            //Первый метод
            Hibernate.initialize(personLazyVcEager.getItems());

           /* //Или второй метод написать вручную HQL код
            List<Item_LazyVcEager> itemLazyVcEagers = session.createQuery("select i from Item_LazyVcEager i where i.owner.id=:personId", Item_LazyVcEager.class)
                            .setParameter("personId", personLazyVcEager.getId()).getResultList();*/

            session.getTransaction().commit();
            System.out.println("second session closed");

            // it's working, items relation had been loaded
            System.out.println(personLazyVcEager.getItems());

        }
    }
}
