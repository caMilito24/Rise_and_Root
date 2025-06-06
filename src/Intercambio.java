// Clase para gestionar intercambios entre usuarios (sistema de trueque)
class Intercambio {
    private Usuario usuario;              // Usuario que ofrece el intercambio
    private Producto productoOfrecido;    // Producto que está dispuesto a intercambiar
    private String descripcionBuscada;    // Descripción de lo que busca a cambio

    // Constructor para crear una propuesta de intercambio
    public Intercambio(Usuario usuario, Producto productoOfrecido, String descripcionBuscada) {
        this.productoOfrecido = productoOfrecido;
        this.descripcionBuscada = descripcionBuscada;
    }

    // Métodos getter para acceder a la información del intercambio
    public Usuario getUsuario() { return usuario; }
    public Producto getProductoOfrecido() { return productoOfrecido; }
    public String getDescripcionBuscada() { return descripcionBuscada; }
}
