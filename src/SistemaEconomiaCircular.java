import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SistemaEconomiaCircular {
    private Map<String, Producto> productos;
    private Map<String, Usuario> usuarios;
    private List<EventoCircular> historial;
    private EstadisticasGlobales estadisticas;
    private Scanner scanner;

    public SistemaEconomiaCircular() {
        this.productos = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.historial = new ArrayList<>();
        this.estadisticas = new EstadisticasGlobales();
        this.scanner = new Scanner(System.in);
    }

    // Clase para representar un producto
    private class Producto {
        private String id;
        private String nombre;
        private String tipo;
        private String estado;
        private int ciclosCompletados;
        private List<String> historialFases;
        private double co2Ahorrado;

        public Producto(String id, String nombre, String tipo) {
            this.id = id;
            this.nombre = nombre;
            this.tipo = tipo;
            this.estado = "🆕 Registrado";
            this.ciclosCompletados = 0;
            this.historialFases = new ArrayList<>();
            this.co2Ahorrado = 0;
            this.historialFases.add("📝 Registro inicial: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }

        public void cambiarEstado(String nuevoEstado, double co2Ahorrado) {
            this.estado = nuevoEstado;
            this.historialFases.add(nuevoEstado + ": " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            this.co2Ahorrado += co2Ahorrado;
            if (nuevoEstado.equals("♻️ Reciclado")){
                this.ciclosCompletados++;
            }
        }
    }

    // Clase para representar un usuario
    private class Usuario {
        private String id;
        private String nombre;
        private int puntosEcologicos;
        private List<String> productosRegistrados;

        public Usuario(String id, String nombre) {
            this.id = id;
            this.nombre = nombre;
            this.puntosEcologicos = 0;
            this.productosRegistrados = new ArrayList<>();
        }

        public void agregarPuntos(int puntos) {
            this.puntosEcologicos += puntos;
        }
    }

    // Clase para eventos en el sistema
    private class EventoCircular {
        private String fecha;
        private String tipoEvento;
        private String productoId;
        private String usuarioId;
        private double co2Ahorrado;
        private int puntosGenerados;

        public EventoCircular(String tipoEvento, String productoId, String usuarioId,
                              double co2Ahorrado, int puntosGenerados) {
            this.fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            this.tipoEvento = tipoEvento;
            this.productoId = productoId;
            this.usuarioId = usuarioId;
            this.co2Ahorrado = co2Ahorrado;
            this.puntosGenerados = puntosGenerados;
        }
    }

    // Clase para estadísticas globales
    private class EstadisticasGlobales {
        private double totalCo2Ahorrado;
        private int totalProductosRegistrados;
        private int totalEventos;
        private Map<String, Integer> accionesPorTipo;

        public EstadisticasGlobales() {
            this.totalCo2Ahorrado = 0;
            this.totalProductosRegistrados = 0;
            this.totalEventos = 0;
            this.accionesPorTipo = new HashMap<>();
            this.accionesPorTipo.put("📝 Registro", 0);
            this.accionesPorTipo.put("♻️ Reciclaje", 0);
            this.accionesPorTipo.put("🔄 Reutilizacion", 0);
            this.accionesPorTipo.put("🔧 Reparacion", 0);
            this.accionesPorTipo.put("🤝 Trueque", 0);
            this.accionesPorTipo.put("🎁 Donacion", 0);
            this.accionesPorTipo.put("💰 Venta", 0);
        }

        public void registrarEvento(String tipo, double co2, int puntos) {
            this.totalCo2Ahorrado += co2;
            this.totalEventos++;
            this.accionesPorTipo.put(tipo, this.accionesPorTipo.getOrDefault(tipo, 0) + 1);
        }

        public void registrarProducto() {
            this.totalProductosRegistrados++;
        }
    }

    // Métodos principales del sistema
    public void iniciarSistema() {
        System.out.println("🌍 === SISTEMA DE ECONOMÍA CIRCULAR VIRTUAL ===");

        while (true) {
            System.out.println("\n📋 MENÚ PRINCIPAL:");
            System.out.println("1. 👤 Registrar usuario");
            System.out.println("2. 📦 Registrar producto");
            System.out.println("3. 🔄 Cambiar estado de producto");
            System.out.println("4. 📜 Ver historial de producto");
            System.out.println("5. 📊 Mostrar estadísticas globales");
            System.out.println("6. 📈 Visualizar gráficos");
            System.out.println("7. 💾 Exportar datos a CSV");
            System.out.println("8. ↩️ Volver al menú principal");
            System.out.print("👉 Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        registrarUsuario();
                        break;
                    case 2:
                        registrarProducto();
                        break;
                    case 3:
                        cambiarEstadoProducto();
                        break;
                    case 4:
                        verHistorialProducto();
                        break;
                    case 5:
                        mostrarEstadisticas();
                        break;
                    case 6:
                        mostrarGraficos();
                        break;
                    case 7:
                        exportarDatosCSV();
                        break;
                    case 8:
                        System.out.println("↩️ Volviendo al menú principal...");
                        return;
                    default:
                        System.out.println("❌ Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: Entrada no válida.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
    }

    private void registrarUsuario() {
        System.out.println("\n👤 --- REGISTRO DE USUARIO ---");
        System.out.print("📝 Ingrese ID de usuario: ");
        String id = scanner.nextLine();

        if (usuarios.containsKey(id)) {
            System.out.println("⚠️ Usuario ya registrado.");
            return;
        }

        System.out.print("👤 Ingrese nombre: ");
        String nombre = scanner.nextLine();

        usuarios.put(id, new Usuario(id, nombre));
        System.out.println("✅ Usuario registrado exitosamente!");
    }

    private void registrarProducto() {
        System.out.println("\n📦 --- REGISTRO DE PRODUCTO ---");

        if (usuarios.isEmpty()) {
            System.out.println("⚠️ No hay usuarios registrados. Registre un usuario primero.");
            return;
        }

        System.out.print("🆔 Ingrese ID de producto: ");
        String id = scanner.nextLine();

        if (productos.containsKey(id)) {
            System.out.println("⚠️ Producto ya registrado.");
            return;
        }

        System.out.print("📝 Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("📌 Ingrese tipo (plastico/metal/electronico/textil/organico): ");
        String tipo = scanner.nextLine().toLowerCase();

        System.out.print("👤 Ingrese ID de usuario que registra: ");
        String usuarioId = scanner.nextLine();

        if (!usuarios.containsKey(usuarioId)) {
            System.out.println("❌ Usuario no encontrado.");
            return;
        }

        Producto nuevoProducto = new Producto(id, nombre, tipo);
        productos.put(id, nuevoProducto);
        usuarios.get(usuarioId).productosRegistrados.add(id);

        // Registrar evento
        EventoCircular evento = new EventoCircular("📝 Registro", id, usuarioId, 0, 10);
        historial.add(evento);
        estadisticas.registrarEvento("📝 Registro", 0, 10);
        estadisticas.registrarProducto();
        usuarios.get(usuarioId).agregarPuntos(10);

        System.out.println("✅ Producto registrado exitosamente. 🌟 +10 puntos ecológicos!");
    }

    private void cambiarEstadoProducto() {
        System.out.println("\n🔄 --- CAMBIAR ESTADO DE PRODUCTO ---");

        if (productos.isEmpty()) {
            System.out.println("⚠️ No hay productos registrados.");
            return;
        }

        System.out.print("🆔 Ingrese ID de producto: ");
        String id = scanner.nextLine();

        if (!productos.containsKey(id)) {
            System.out.println("❌ Producto no encontrado.");
            return;
        }

        System.out.print("👤 Ingrese ID de usuario: ");
        String usuarioId = scanner.nextLine();

        if (!usuarios.containsKey(usuarioId)) {
            System.out.println("❌ Usuario no encontrado.");
            return;
        }

        System.out.println("📌 Seleccione nuevo estado:");
        System.out.println("1. 🔧 En reparación");
        System.out.println("2. 🔄 Reutilizado");
        System.out.println("3. 🤝 Listo para trueque");
        System.out.println("4. 🎁 Listo para donación");
        System.out.println("5. 💰 Listo para venta");
        System.out.println("6. ♻️ Reciclado");
        System.out.print("👉 Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        String nuevoEstado = "";
        double co2 = 0;
        int puntos = 0;
        String tipoEvento = "";

        switch (opcion) {
            case 1:
                nuevoEstado = "🔧 En reparacion";
                co2 = 1.5;
                puntos = 15;
                tipoEvento = "🔧 Reparacion";
                break;
            case 2:
                nuevoEstado = "🔄 Reutilizado";
                co2 = 2.0;
                puntos = 20;
                tipoEvento = "🔄 Reutilizacion";
                break;
            case 3:
                nuevoEstado = "🤝 Para trueque";
                co2 = 1.8;
                puntos = 18;
                tipoEvento = "🤝 Trueque";
                break;
            case 4:
                nuevoEstado = "🎁 Para donacion";
                co2 = 1.2;
                puntos = 12;
                tipoEvento = "🎁 Donacion";
                break;
            case 5:
                nuevoEstado = "💰 Para venta";
                co2 = 1.0;
                puntos = 10;
                tipoEvento = "💰 Venta";
                break;
            case 6:
                nuevoEstado = "♻️ Reciclado";
                co2 = 2.5;
                puntos = 25;
                tipoEvento = "♻️ Reciclaje";
                break;
            default:
                System.out.println("❌ Opción no válida.");
                return;
        }

        Producto producto = productos.get(id);
        producto.cambiarEstado(nuevoEstado, co2);

        // Registrar evento
        EventoCircular evento = new EventoCircular(tipoEvento, id, usuarioId, co2, puntos);
        historial.add(evento);
        estadisticas.registrarEvento(tipoEvento, co2, puntos);
        usuarios.get(usuarioId).agregarPuntos(puntos);

        System.out.printf("✅ Estado cambiado a '%s'. 🌟 +%d puntos (🌱 CO₂ ahorrado: %.2f kg)\n",
                nuevoEstado, puntos, co2);
    }

    private void verHistorialProducto() {
        System.out.println("\n📜 --- HISTORIAL DE PRODUCTO ---");

        System.out.print("🆔 Ingrese ID de producto: ");
        String id = scanner.nextLine();

        if (!productos.containsKey(id)) {
            System.out.println("❌ Producto no encontrado.");
            return;
        }

        Producto producto = productos.get(id);
        System.out.println("\n📌 === " + producto.nombre.toUpperCase() + " ===");
        System.out.println("🆔 ID: " + producto.id);
        System.out.println("📌 Tipo: " + producto.tipo);
        System.out.println("🔄 Estado actual: " + producto.estado);
        System.out.println("♻️ Ciclos completados: " + producto.ciclosCompletados);
        System.out.printf("🌱 CO₂ total ahorrado: %.2f kg\n", producto.co2Ahorrado);
        System.out.println("\n📅 Línea de vida:");

        for (String fase : producto.historialFases) {
            System.out.println("⏳ " + fase);
        }
    }

    private void mostrarEstadisticas() {
        System.out.println("\n📊 === ESTADÍSTICAS GLOBALES ===");
        System.out.println("📦 Total productos registrados: " + estadisticas.totalProductosRegistrados);
        System.out.printf("🌱 Total CO₂ ahorrado: %.2f kg\n", estadisticas.totalCo2Ahorrado);
        System.out.println("📅 Total eventos registrados: " + estadisticas.totalEventos);
        System.out.println("\n📌 Acciones por tipo:");

        for (Map.Entry<String, Integer> entry : estadisticas.accionesPorTipo.entrySet()) {
            System.out.printf("- %s: %d\n", entry.getKey(), entry.getValue());
        }

        // Mostrar top usuarios
        System.out.println("\n🏆 Top usuarios por puntos ecológicos:");
        usuarios.values().stream()
                .sorted((u1, u2) -> Integer.compare(u2.puntosEcologicos, u1.puntosEcologicos))
                .limit(5)
                .forEach(u -> System.out.printf("- 🥇 %s: %d pts\n", u.nombre, u.puntosEcologicos));
    }

    private void mostrarGraficos() {
        System.out.println("\n📈 === VISUALIZACIÓN DE DATOS ===");

        // Gráfico de CO2 ahorrado por mes
        System.out.println("\n🌱 CO₂ evitado por mes (kg):");
        Map<String, Double> co2PorMes = new HashMap<>();

        // Agrupar CO2 por mes
        for (EventoCircular evento : historial) {
            String mes = evento.fecha.substring(0, 7); // Formato YYYY-MM
            co2PorMes.put(mes, co2PorMes.getOrDefault(mes, 0.0) + evento.co2Ahorrado);
        }

        // Mostrar gráfico de barras
        for (Map.Entry<String, Double> entry : co2PorMes.entrySet()) {
            System.out.printf("📅 %-10s ", entry.getKey());
            int barras = (int) (entry.getValue() / 2); // Escala para visualización
            for (int i = 0; i < barras; i++) {
                System.out.print("▓");
            }
            System.out.printf(" %.2f kg\n", entry.getValue());
        }

        // Gráfico de pastel de acciones
        System.out.println("\n📌 Distribución de acciones:");
        int totalEventos = estadisticas.totalEventos;

        if (totalEventos > 0) {
            for (Map.Entry<String, Integer> entry : estadisticas.accionesPorTipo.entrySet()) {
                if (entry.getValue() > 0) {
                    int porcentaje = (int) Math.round((entry.getValue() * 100.0) / totalEventos);
                    int barras = (int) Math.round(porcentaje / 5.0); // Escala para visualización
                    System.out.printf("📊 %-12s ", entry.getKey());
                    for (int i = 0; i < barras; i++) {
                        System.out.print("▓");
                    }
                    System.out.printf(" %d%%\n", porcentaje);
                }
            }
        } else {
            System.out.println("⚠️ No hay eventos registrados para mostrar.");
        }

        // Gráfico de barras de usuarios
        System.out.println("\n🏆 Puntos ecológicos por usuario:");
        if (!usuarios.isEmpty()) {
            usuarios.values().stream()
                    .sorted((u1, u2) -> Integer.compare(u2.puntosEcologicos, u1.puntosEcologicos))
                    .limit(5) // Mostrar top 5
                    .forEach(u -> {
                        System.out.printf("👤 %-15s ", u.nombre);
                        int barras = (int) Math.ceil(u.puntosEcologicos / 10.0); // Escala para visualización
                        for (int i = 0; i < barras; i++) {
                            System.out.print("⭐");
                        }
                        System.out.printf(" %d pts\n", u.puntosEcologicos);
                    });
        } else {
            System.out.println("⚠️ No hay usuarios registrados.");
        }
    }

    private void exportarDatosCSV() {
        try {
            // Exportar productos
            FileWriter productosWriter = new FileWriter("productos.csv");
            productosWriter.write("ID,Nombre,Tipo,Estado,Ciclos,CO2_Ahorrado\n");
            for (Producto p : productos.values()) {
                productosWriter.write(String.format("%s,%s,%s,%s,%d,%.2f\n",
                        p.id, p.nombre, p.tipo, p.estado, p.ciclosCompletados, p.co2Ahorrado));
            }
            productosWriter.close();

            // Exportar eventos
            FileWriter eventosWriter = new FileWriter("eventos.csv");
            eventosWriter.write("Fecha,Tipo_Evento,Producto_ID,Usuario_ID,CO2_Ahorrado,Puntos\n");
            for (EventoCircular e : historial) {
                eventosWriter.write(String.format("%s,%s,%s,%s,%.2f,%d\n",
                        e.fecha, e.tipoEvento, e.productoId, e.usuarioId, e.co2Ahorrado, e.puntosGenerados));
            }
            eventosWriter.close();

            // Exportar usuarios
            FileWriter usuariosWriter = new FileWriter("usuarios.csv");
            usuariosWriter.write("ID,Nombre,Puntos_Ecologicos,Productos_Registrados\n");
            for (Usuario u : usuarios.values()) {
                usuariosWriter.write(String.format("%s,%s,%d,%d\n",
                        u.id, u.nombre, u.puntosEcologicos, u.productosRegistrados.size()));
            }
            usuariosWriter.close();

            System.out.println("✅ Datos exportados exitosamente a: 📄 productos.csv, 📄 eventos.csv, 📄 usuarios.csv");
        } catch (IOException e) {
            System.out.println("❌ Error al exportar datos: " + e.getMessage());
        }
    }
}
