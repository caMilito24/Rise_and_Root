// Clase base abstracta para productos - Define la estructura común
abstract class Producto {
    // Atributos comunes a todos los productos
    private String nombre;
    private double precio;
    private String categoria;

    // Constructor que inicializa los atributos básicos
    public Producto(String nombre, double precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Método abstracto - cada tipo de producto debe implementarlo de forma específica
    public abstract void mostrarDetalles();

    // Métodos getter para acceder a los atributos privados
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
}


