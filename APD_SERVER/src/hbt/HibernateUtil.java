package hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.AjusteEntity;
import entities.ArticuloEntity;
import entities.ClienteEntity;
import entities.CompraRealizadaEntity;
import entities.ItemPedidoCteEntity;
import entities.PedidoCteEntity;
import entities.RoturaEntity;
import entities.VentaRealizadaEntity;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(ArticuloEntity.class);
             config.addAnnotatedClass(ClienteEntity.class);
             config.addAnnotatedClass(PedidoCteEntity.class);
             config.addAnnotatedClass(ItemPedidoCteEntity.class);
//           config.addAnnotatedClass(MovimientoInventarioEntity.class);
             config.addAnnotatedClass(AjusteEntity.class);
             config.addAnnotatedClass(RoturaEntity.class);
             config.addAnnotatedClass(VentaRealizadaEntity.class);
             config.addAnnotatedClass(CompraRealizadaEntity.class);
             //TODO Agregar todas las entities----
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
