package model;

public class PedidoCte {

	public static String ESTADO_NUEVO = "Nuevo";
	public static String ESTADO_CANCEL = "Cancelado"; //(estado final)
	public static String ESTADO_PENDIENTE_APROB_CRED = "Pendiente aprobacion crediticia";
	public static String ESTADO_APROB_CRED_RECH	= "Aprobacion crediticia rechazada";
	public static String ESTADO_APROB_CRED_APROB = "Aprobacion crediticia aprobada";
	public static String ESTADO_STOCK_PENDIENTE = "Pendiente de ingreso de stock";
	public static String ESTADO_STOCK_SUFICIENTE = "Stock suficiente para despacho";
	public static String ESTADO_DESPACHADO = "Pedido despachado"; // (estado final)
}
