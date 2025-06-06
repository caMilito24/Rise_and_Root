// Clase específica para productos electrónicos reacondicionados
class ProductoElectronico extends Producto {
    // Atributos específicos de electrónicos
    private String marca;
    private int añoFabricacion;

    // Constructor que incluye atributos específicos además de los heredados
    public ProductoElectronico(String nombre, double precio, String categoria, String marca, int añoFabricacion) {
        super(nombre, precio, categoria); // Llama al constructor de la clase padre
        this.marca = marca;
        this.añoFabricacion = añoFabricacion;
    }

    // Implementación específica del método abstracto para electrónicos
    @Override
    public void mostrarDetalles() {
        System.out.println("📱 Tipo: Electrónico reacondicionado");
        System.out.println("🏷️ Marca: " + marca);
        System.out.println("📅 Año de fabricación: " + añoFabricacion);
        System.out.println("♻️ Este producto ayuda a reducir residuos electrónicos.");
    }
}

