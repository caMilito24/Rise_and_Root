// Clase específica para productos alimenticios
class ProductoAlimentacion extends Producto {
    // Atributos específicos de alimentación
    private String fechaCaducidad;
    private boolean organico; // Indica si es producto orgánico

    // Constructor con atributos específicos de alimentación
    public ProductoAlimentacion(String nombre, double precio, String categoria, String fechaCaducidad, boolean organico) {
        super(nombre, precio, categoria);
        this.fechaCaducidad = fechaCaducidad;
        this.organico = organico;
    }

    // Implementación específica para mostrar detalles de alimentación
    @Override
    public void mostrarDetalles() {
        System.out.println("🍎 Tipo: Alimentación " + (organico ? "ecológica" : "convencional"));
        System.out.println("📅 Fecha de caducidad: " + fechaCaducidad);
        System.out.println("🌱 " + (organico ? "Certificado orgánico" : "Producto convencional"));
        System.out.println("♻️ Este producto promueve " + (organico ? "la agricultura ecológica." : "un consumo responsable."));
    }
}