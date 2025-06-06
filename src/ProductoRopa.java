// Clase específica para productos de ropa sostenible
class ProductoRopa extends Producto {
    // Atributos específicos de ropa
    private String talla;
    private String material;

    // Constructor con atributos específicos de ropa
    public ProductoRopa(String nombre, double precio, String categoria, String talla, String material) {
        super(nombre, precio, categoria);
        this.talla = talla;
        this.material = material;
    }

    // Implementación específica para mostrar detalles de ropa
    @Override
    public void mostrarDetalles() {
        System.out.println("👕 Tipo: Ropa sostenible");
        System.out.println("📏 Talla: " + talla);
        System.out.println("🧵 Material: " + material);
        System.out.println("🌿 Este producto promueve la moda sostenible.");
    }
}

