package hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.AjusteEntity;
import entities.ArticuloEntity;
import entities.ClienteEntity;
import entities.CompraRealizadaEntity;
import entities.FacturaEntity;
import entities.ItemFacturaEntity;
import entities.ItemPedidoCteEntity;
import entities.ItemRemitoEntity;
import entities.MovimientoInventarioEntity;
import entities.NotaCreditoEntity;
import entities.OrdenDeCompraEntity;
import entities.PagoEntity;
import entities.PedidoCteEntity;
import entities.ProductoEntity;
import entities.ProveedorEntity;
import entities.RemitoEntity;
import entities.ReservaArticuloEntity;
import entities.RoturaEntity;
import entities.UsuarioEntity;
import entities.VentaRealizadaEntity;
import testTest.ArmarioEntity;
import testTest.EstanteEntity;

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
             config.addAnnotatedClass(MovimientoInventarioEntity.class);
             config.addAnnotatedClass(AjusteEntity.class);
             config.addAnnotatedClass(RoturaEntity.class);
             config.addAnnotatedClass(VentaRealizadaEntity.class);
             config.addAnnotatedClass(CompraRealizadaEntity.class);
             config.addAnnotatedClass(FacturaEntity.class);
             config.addAnnotatedClass(PagoEntity.class);
             config.addAnnotatedClass(NotaCreditoEntity.class);
             config.addAnnotatedClass(ItemFacturaEntity.class);
             config.addAnnotatedClass(ArmarioEntity.class);
             config.addAnnotatedClass(EstanteEntity.class);
             config.addAnnotatedClass(ProveedorEntity.class);
             config.addAnnotatedClass(ProductoEntity.class);
             config.addAnnotatedClass(RemitoEntity.class);
             config.addAnnotatedClass(ItemRemitoEntity.class);
             config.addAnnotatedClass(UsuarioEntity.class);
             config.addAnnotatedClass(ReservaArticuloEntity.class);
             config.addAnnotatedClass(OrdenDeCompraEntity.class);
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
