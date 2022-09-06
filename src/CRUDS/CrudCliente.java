/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDS;

import POJOs.Cliente;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
/**

/**
 *
 * @author Usuario
 */
public class CrudCliente {
    public static boolean crear(String nombre){
        boolean flag =false;
        Date fecha=new Date();
        Session session=HibernateUtil.HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("nombre", nombre));
        Cliente insrt=(Cliente)criteria.uniqueResult();
        Transaction transaction=null;
        
        try {
            transaction=session.beginTransaction();
            if(insrt==null){
                insrt=new Cliente();
                insrt.setNombre(nombre);
                insrt.setFecha(fecha);
                session.save(insrt);
                flag=true;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("error"+e);
            
        }finally {
            session.close();
        }
        return flag; 
    }
}
