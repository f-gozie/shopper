/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servicecorp.web.services;

import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.servicecorp.web.model.Order;
/**
 *
 * @author favour
 */
public class OrderService {
    String persistenceUnitName = "com.servicecorp_serviceCorp_war_1.0-SNAPSHOTPU";

    public Order doesIdExist(int id, EntityManager manager) throws NotFoundException {
        Order obj = manager.find(Order.class, id);
        if (obj == null) {
            throw new NotFoundException("The provided ID was not found on this server");
        }
        return obj;
    }

    public Order create(Order order) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        Order newOrder = new Order();
        newOrder.setId(order.getId());
        newOrder.setUser_id(order.getUser_id());
        newOrder.setCompany_id(order.getCompany_id());
        newOrder.setQuantity(order.getQuantity());
        newOrder.setPrice_total(order.getPrice_total());
        newOrder.setUnit_price(order.getUnit_price());
        newOrder.setStatus(order.getStatus());

        manager.persist(newOrder);
        manager.getTransaction().commit();

        manager.close();
        emf.close();

        return newOrder;
    }

    public Order fetchById(int id) throws NotFoundException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager manager = emf.createEntityManager();
        
        Order order = doesIdExist(id, manager);
        return order;
    }

    public Order update(int id, Order order) throws NotFoundException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        Order existingOrder = doesIdExist(id, manager);
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setPrice_total(order.getPrice_total());
        existingOrder.setUnit_price(order.getUnit_price());
        existingOrder.setStatus(order.getStatus());

        manager.getTransaction().commit();
        manager.close();
        emf.close();

        return existingOrder;
    }

    public void delete(int id) throws NotFoundException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        Order order = doesIdExist(id, manager);
        manager.remove(order);
        manager.getTransaction().commit();
        manager.close();
        emf.close();
    }

    public List<Order> fetchAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager manager = emf.createEntityManager();

        Query query = manager.createQuery("SELECT e FROM Order e");
        return query.getResultList();
    }

}
