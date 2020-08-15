package com.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.entidades.Pais;


import java.util.List;


import javax.transaction.Transaction;


import com.entidades.Cliente;

public class Main {

	static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
			.build();
	
	static SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
	static Session session = sessionFactory.openSession();
	Transaction tx = (Transaction) session.beginTransaction();
	public static void main(String[] args) {
		
		Pais pais1 = new Pais(0,"Ecuador");
		Pais pais2 = new Pais(0,"Portugal");
		
		ingresarPais(pais1);
		ingresarPais(pais2);
		
		Cliente cli1 = new Cliente(0, "Reynaldo", "Zamora", "Pico");
		Cliente cli2 = new Cliente(0, "Tommy", "Medina", "Castro");
				
				
				
		ingresarCliente(cli1);
		ingresarCliente(cli2);
		
		List<Pais> pais= getPais();
		
		for(Pais temp:pais) {
			System.out.println(temp);
		}
		
	}

	static List<Pais> getPais(){
		
		Session session = sessionFactory.openSession();
		List<Pais> pais = session.createQuery("from Pais", Pais.class).list();
		return pais;
	}
	
	static List<Cliente> getCliente(){
		
		Session session = sessionFactory.openSession();
		List<Cliente> cliente = session.createQuery("from Cliente", Cliente.class).list();
		return cliente;
	}
	
    static void ingresarPais(Pais pais) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(pais);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println(pais.getId());
    }
    static void ingresarCliente(Cliente cliente) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(cliente);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println(cliente.getId());
    }
    	
}