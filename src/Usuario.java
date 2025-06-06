import java.util.ArrayList;

// Clase que representa a los usuarios de la plataforma
class Usuario {
    // Información básica del usuario
    private String nombre;
    private String email;
    private String clave;
    // Listas para gestionar productos propios y carrito de compras
    private ArrayList<Producto> productos; // Productos que el usuario tiene para intercambiar
    private ArrayList<Producto> carrito;   // Productos que quiere comprar/intercambiar

    // Constructor que inicializa un usuario con sus listas vacías
    public Usuario(String nombre, String email, String clave) {
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.productos = new ArrayList<>();
        this.carrito = new ArrayList<>();
    }

    // Métodos para gestionar los productos del usuario
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    // Métodos para gestionar el carrito de compras
    public void agregarAlCarrito(Producto producto) {
        carrito.add(producto);
    }

    // Elimina un producto del carrito por su índice
    public void eliminarDelCarrito(int indice) {
        if (indice >= 0 && indice < carrito.size()) { // Verifica que el índice sea válido
            carrito.remove(indice);
        }
    }

    // Vacía completamente el carrito
    public void vaciarCarrito() {
        carrito.clear();
    }

    public ArrayList<Producto> getCarrito() {
        return carrito;
    }

    // Métodos getter para acceder a la información del usuario
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getClave() { return clave; }
}

