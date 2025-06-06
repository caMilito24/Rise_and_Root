import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Variables globales para almacenar datos del sistema
    private static ArrayList<Producto> productosDisponibles = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Intercambio> propuestasIntercambio = new ArrayList<>();
    private static Usuario usuarioActual; // Usuario que está logueado actualmente
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarDatos(); // Cargar datos de prueba
        mostrarMenuPrincipal(); // Mostrar menú inicial
    }

    // Método estático para inicializar datos de prueba
    private static void inicializarDatos() {
        // Crear algunos productos predefinidos para demostración
        ProductoElectronico laptop = new ProductoElectronico("Laptop reacondicionada", 1200.0, "Tecnología", "Dell", 2020);
        ProductoRopa camisa = new ProductoRopa("Camisa orgánica", 35.0, "Ropa", "M", "Algodón orgánico");
        ProductoAlimentacion quinoa = new ProductoAlimentacion("Quinoa ecológica", 5.0, "Alimentación", "2023-12-31", true);
        ProductoElectronico telefono = new ProductoElectronico("Teléfono reacondicionado", 300.0, "Tecnología", "Samsung", 2019);
        ProductoRopa pantalon = new ProductoRopa("Pantalón reciclado", 25.0, "Ropa", "38", "Material reciclado");
        ProductoAlimentacion miel = new ProductoAlimentacion("Miel orgánica", 8.0, "Alimentación", "2024-06-30", true);

        // Agregar productos a la lista global
        productosDisponibles.add(laptop);
        productosDisponibles.add(camisa);
        productosDisponibles.add(quinoa);
        productosDisponibles.add(telefono);
        productosDisponibles.add(pantalon);
        productosDisponibles.add(miel);

        // Crear algunos usuarios de prueba
        Usuario usuario1 = new Usuario("Juan", "juan@email.com", "clave123");
        Usuario usuario2 = new Usuario("Maria", "maria@email.com", "clave456");
        Usuario usuario3 = new Usuario("Admin", "admin@riseroot.com", "admin123");

        // Agregar usuarios a la lista global
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        // Asignar algunos productos a usuarios para demostración
        usuario1.agregarProducto(telefono);
        usuario2.agregarProducto(pantalon);
        usuario3.agregarProducto(laptop);
        usuario3.agregarProducto(miel);

        // Crear propuestas de intercambio preestablecidas
        propuestasIntercambio.add(new Intercambio(usuario1, telefono, "Ropa ecológica talla M o S"));
        propuestasIntercambio.add(new Intercambio(usuario2, pantalon, "Productos electrónicos reacondicionados"));
        propuestasIntercambio.add(new Intercambio(usuario3, miel, "Productos de alimentación orgánica"));
    }

    // Menú principal del sistema
    private static void mostrarMenuPrincipal() {
        System.out.println("\n🌱=== MARKETPLACE SOSTENIBLE RISE & ROOT (ODS 12) ===🌱");
        System.out.println("1. 🛒 Marketplace");
        System.out.println("2. 🏪 Tienda");
        System.out.println("3. 🔄 Trueque");
        System.out.println("4. ♻️ Sistema de Economía Circular"); // Nueva opción
        System.out.println("5. ℹ️ Acerca de");

        // Mostrar diferentes opciones según si hay usuario logueado
        if (usuarioActual != null) {
            System.out.println("6. 👤 Cambiar usuario (" + usuarioActual.getNombre() + ")");
            System.out.println("7. 🚪 Salir");
        } else {
            System.out.println("6. 👤 Iniciar sesión/Registrarse");
            System.out.println("7. 🚪 Salir");
        }
        System.out.print("Seleccione una opción: ");

        // Leer opción del usuario
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Ejecutar acción según la opción seleccionada
        switch (opcion) {
            case 1:
                menuMarketplace();
                break;
            case 2:
                menuTienda();
                break;
            case 3:
                menuTrueque();
                break;
            case 4:
                iniciarSistemaEconomiaCircular();
                break;
            case 5:
                mostrarAcercaDe();
                break;
            case 6:
                if (usuarioActual != null) {
                    cambiarUsuario(); // Cerrar sesión
                } else {
                    menuAutenticacion(); // Ir a login/registro
                }
                break;
            case 7:
                System.out.println("🌿 Gracias por usar el Marketplace Sostenible Rise & Root. ¡Hasta pronto! 🌿");
                System.exit(0);
                break;
            default:
                System.out.println("❌ Opción no válida. Intente nuevamente.");
                mostrarMenuPrincipal();
        }
    }

    private static void iniciarSistemaEconomiaCircular() {
        SistemaEconomiaCircular sistema = new SistemaEconomiaCircular();
        sistema.iniciarSistema();
        // Al salir del sistema, regresa al menú principal
        mostrarMenuPrincipal();
    }

    // Menú para login y registro de usuarios
    private static void menuAutenticacion() {
        System.out.println("\n🔐=== AUTENTICACIÓN ===");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                iniciarSesion();
                break;
            case 2:
                registrarUsuario();
                break;
            case 3:
                mostrarMenuPrincipal();
                break;
            default:
                System.out.println("❌ Opción no válida.");
                menuAutenticacion();
        }
    }

    // Registrar un nuevo usuario en el sistema
    private static void registrarUsuario() {
        System.out.println("\n📝=== REGISTRO DE NUEVO USUARIO ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Contraseña: ");
        String clave = scanner.nextLine();

        // Verificar si el email ya existe en el sistema
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                System.out.println("❌ Este email ya está registrado. Por favor use otro.");
                menuAutenticacion();
                return;
            }
        }

        // Crear y agregar nuevo usuario
        Usuario nuevoUsuario = new Usuario(nombre, email, clave);
        usuarios.add(nuevoUsuario);
        System.out.println("✅ ¡Registro exitoso! Ahora puede iniciar sesión.");
        menuAutenticacion();
    }

    // Cerrar sesión del usuario actual
    private static void cambiarUsuario() {
        System.out.println("\n👤=== CAMBIAR USUARIO ===");
        usuarioActual = null; // Limpiar usuario actual
        System.out.println("Sesión cerrada. Por favor inicie sesión nuevamente.");
        menuAutenticacion();
    }

    // Menú del Marketplace (compra/venta)
    private static void menuMarketplace() {
        System.out.println("\n🛒=== MARKETPLACE ===");
        System.out.println("1. 🏷️ Vender producto");
        System.out.println("2. 🛍️ Comprar productos");
        System.out.println("3. 🛒 Ver carrito");
        System.out.println("4. ↩️ Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                venderProducto();
                break;
            case 2:
                comprarProductos();
                break;
            case 3:
                verCarrito();
                break;
            case 4:
                mostrarMenuPrincipal();
                break;
            default:
                System.out.println("❌ Opción no válida.");
                menuMarketplace();
        }
    }

    // Proceso para agregar un nuevo producto a la venta
    private static void venderProducto() {
        System.out.println("\n🏷️=== VENDER PRODUCTO ===");
        System.out.println("Tipo de producto:");
        System.out.println("1. 📱 Electrónico");
        System.out.println("2. 👕 Ropa");
        System.out.println("3. 🍎 Alimentación");
        System.out.print("Seleccione el tipo: ");

        int tipo = scanner.nextInt();
        scanner.nextLine();

        // Solicitar información básica del producto
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        Producto nuevoProducto = null;

        // Crear producto específico según el tipo seleccionado
        switch (tipo) {
            case 1: // Electrónico
                System.out.print("Marca: ");
                String marca = scanner.nextLine();
                System.out.print("Año de fabricación: ");
                int año = scanner.nextInt();
                scanner.nextLine();
                nuevoProducto = new ProductoElectronico(nombre, precio, categoria, marca, año);
                break;
            case 2: // Ropa
                System.out.print("Talla: ");
                String talla = scanner.nextLine();
                System.out.print("Material: ");
                String material = scanner.nextLine();
                nuevoProducto = new ProductoRopa(nombre, precio, categoria, talla, material);
                break;
            case 3: // Alimentación
                System.out.print("Fecha de caducidad: ");
                String caducidad = scanner.nextLine();
                System.out.print("Es orgánico (true/false): ");
                boolean organico = scanner.nextBoolean();
                scanner.nextLine();
                nuevoProducto = new ProductoAlimentacion(nombre, precio, categoria, caducidad, organico);
                break;
            default:
                System.out.println("❌ Tipo no válido.");
                menuMarketplace();
                return;
        }

        // Verificar que el usuario esté logueado
        if (usuarioActual == null) {
            System.out.println("🔒 Debe iniciar sesión para vender productos.");
            iniciarSesion();
        }

        // Agregar producto al usuario y a la lista global
        usuarioActual.agregarProducto(nuevoProducto);
        productosDisponibles.add(nuevoProducto);
        System.out.println("✅ ¡Producto agregado exitosamente!");
        menuMarketplace();
    }

    // Proceso para comprar productos
    private static void comprarProductos() {
        System.out.println("\n🛍️=== COMPRAR PRODUCTOS ===");
        listarProductosDisponibles(); // Mostrar todos los productos

        System.out.print("Seleccione el número de producto a comprar (0 para volver): ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        // Validar selección
        if (seleccion == 0) {
            menuMarketplace();
            return;
        }

        if (seleccion < 1 || seleccion > productosDisponibles.size()) {
            System.out.println("❌ Selección no válida.");
            comprarProductos();
            return;
        }

        Producto productoSeleccionado = productosDisponibles.get(seleccion - 1);

        // Verificar que el usuario esté logueado
        if (usuarioActual == null) {
            System.out.println("🔒 Debe iniciar sesión para comprar productos.");
            iniciarSesion();
        }

        // Agregar producto al carrito del usuario
        usuarioActual.agregarAlCarrito(productoSeleccionado);
        System.out.println("✅ Producto agregado al carrito.");

        // Opciones después de agregar al carrito
        System.out.println("\n1. Seguir comprando");
        System.out.println("2. Ver carrito");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                comprarProductos();
                break;
            case 2:
                verCarrito();
                break;
            case 3:
                mostrarMenuPrincipal();
                break;
            default:
                System.out.println("❌ Opción no válida. Volviendo al menú principal.");
                mostrarMenuPrincipal();
        }
    }

    // Menú de la Tienda (gestión de carrito y facturación)
    private static void menuTienda() {
        System.out.println("\n🏪=== TIENDA SOSTENIBLE RISE & ROOT ===");
        System.out.println("1. 📋 Ver productos disponibles");
        System.out.println("2. 🛒 Ver carrito de compras");
        System.out.println("3. 🧾 Generar factura");
        System.out.println("4. ↩️ Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                listarProductosDisponibles();
                menuTienda();
                break;
            case 2:
                verCarrito();
                break;
            case 3:
                generarFactura();
                break;
            case 4:
                mostrarMenuPrincipal();
                break;
            default:
                System.out.println("❌ Opción no válida.");
                menuTienda();
        }
    }

    // Mostrar y gestionar el carrito de compras
    private static void verCarrito() {
        // Verificar que el usuario esté logueado
        if (usuarioActual == null) {
            System.out.println("🔒 Debe iniciar sesión para ver el carrito.");
            iniciarSesion();
        }

        System.out.println("\n🛒=== CARRITO DE COMPRAS ===");
        ArrayList<Producto> carrito = usuarioActual.getCarrito();

        // Verificar si el carrito está vacío
        if (carrito.isEmpty()) {
            System.out.println("😕 El carrito está vacío.");
            System.out.println("\n1. Volver a comprar");
            System.out.println("2. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    comprarProductos();
                    break;
                case 2:
                    mostrarMenuPrincipal();
                    break;
                default:
                    System.out.println("❌ Opción no válida. Volviendo al menú principal.");
                    mostrarMenuPrincipal();
            }
            return;
        }

        // Mostrar productos en el carrito
        System.out.println("📋 Contenido del carrito:");
        for (int i = 0; i < carrito.size(); i++) {
            System.out.println((i + 1) + ". " + carrito.get(i).getNombre() + " - $" + carrito.get(i).getPrecio());
        }

        // Opciones para gestionar el carrito
        System.out.println("\n1. ❌ Eliminar producto");
        System.out.println("2. 💳 Comprar ahora");
        System.out.println("3. ↩️ Volver");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1: // Eliminar producto del carrito
                System.out.print("Ingrese el número de producto a eliminar: ");
                int numEliminar = scanner.nextInt();
                scanner.nextLine();

                if (numEliminar > 0 && numEliminar <= carrito.size()) {
                    usuarioActual.eliminarDelCarrito(numEliminar - 1);
                    System.out.println("✅ Producto eliminado del carrito.");
                } else {
                    System.out.println("❌ Número no válido.");
                }
                verCarrito();
                break;
            case 2: // Proceder a la compra
                generarFactura();
                break;
            case 3: // Volver al menú anterior
                menuTienda();
                break;
            default:
                System.out.println("❌ Opción no válida.");
                verCarrito();
        }
    }

    // Generar factura de compra y completar transacción
    private static void generarFactura() {
        // Verificar que el usuario esté logueado
        if (usuarioActual == null) {
            System.out.println("🔒 Debe iniciar sesión para generar una factura.");
            iniciarSesion();
        }

        ArrayList<Producto> carrito = usuarioActual.getCarrito();

        // Verificar que el carrito no esté vacío
        if (carrito.isEmpty()) {
            System.out.println("😕 El carrito está vacío. No se puede generar factura.");
            menuTienda();
            return;
        }

        // Generar e imprimir factura detallada
        System.out.println("\n🧾=== FACTURA RISE & ROOT ===🧾");
        System.out.println("=================================");
        System.out.println("          RISE & ROOT           ");
        System.out.println("  Marketplace Sostenible ODS 12  ");
        System.out.println("=================================");
        System.out.println("Cliente: " + usuarioActual.getNombre());
        System.out.println("Email: " + usuarioActual.getEmail());
        System.out.println("Fecha: " + java.time.LocalDate.now());
        System.out.println("=================================");
        System.out.println("📋 Productos:");

        // Calcular total y mostrar productos
        double total = 0;
        for (Producto producto : carrito) {
            System.out.printf("- %-25s $%.2f%n", producto.getNombre(), producto.getPrecio());
            total += producto.getPrecio();
        }

        System.out.println("=================================");
        System.out.printf("💰 TOTAL: $%.2f%n", total);
        System.out.println("=================================");
        System.out.println("🌿 ¡Gracias por su compra sostenible!");
        System.out.println("=================================");

        // Mensaje relacionado con ODS 12
        System.out.println("\nCon esta compra estás contribuyendo al Objetivo de Desarrollo Sostenible 12:");
        System.out.println("♻️ Producción y consumo responsables");
        System.out.println("🌎 Garantizar modalidades de consumo y producción sostenibles");
        System.out.println("=================================");
        System.out.println("💚 Rise & Root - Por un futuro sostenible");

        // Vaciar carrito después de la compra
        usuarioActual.vaciarCarrito();
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
        menuTienda();
    }

    // Menú de Trueque (intercambio de productos)
    private static void menuTrueque() {
        System.out.println("\n🔄=== TRUEQUE SOSTENIBLE ===");
        System.out.println("1. ➕ Ofrecer producto para trueque");
        System.out.println("2. 🔍 Buscar productos para trueque");
        System.out.println("3. 📋 Ver mis propuestas de trueque");
        System.out.println("4. ↩️ Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                ofrecerTrueque();
                break;
            case 2:
                buscarTrueques();
                break;
            case 3:
                verMisTrueques();
                break;
            case 4:
                mostrarMenuPrincipal();
                break;
            default:
                System.out.println("❌ Opción no válida.");
                menuTrueque();
        }
    }

    // Crear una nueva propuesta de trueque
    private static void ofrecerTrueque() {
        // Verificar que el usuario esté logueado
        if (usuarioActual == null) {
            System.out.println("🔒 Debe iniciar sesión para ofrecer trueques.");
            iniciarSesion();
        }

        System.out.println("\n➕=== OFRECER PRODUCTO PARA TRUEQUE ===");
        System.out.println("Tus productos disponibles para trueque:");

        ArrayList<Producto> misProductos = usuarioActual.getProductos();

        // Verificar que el usuario tenga productos
        if (misProductos.isEmpty()) {
            System.out.println("😕 No tienes productos para ofrecer en trueque.");
            menuTrueque();
            return;
        }

        // Mostrar productos del usuario
        for (int i = 0; i < misProductos.size(); i++) {
            System.out.println((i + 1) + ". " + misProductos.get(i).getNombre());
        }

        System.out.print("Seleccione el número de producto a ofrecer (0 para volver): ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        if (seleccion == 0) {
            menuTrueque();
            return;
        }

        // Validar selección
        if (seleccion < 1 || seleccion > misProductos.size()) {
            System.out.println("❌ Selección no válida.");
            ofrecerTrueque();
            return;
        }

        Producto productoOfrecido = misProductos.get(seleccion - 1);

        // Solicitar descripción de lo que busca a cambio
        System.out.print("Descripción de lo que buscas a cambio: ");
        String descripcion = scanner.nextLine();

        // Crear y agregar nueva propuesta de intercambio
        Intercambio nuevoIntercambio = new Intercambio(usuarioActual, productoOfrecido, descripcion);
        propuestasIntercambio.add(nuevoIntercambio);

        System.out.println("✅ ¡Propuesta de trueque creada exitosamente!");
        menuTrueque();
    }

    // Buscar y responder a propuestas de trueque
    private static void buscarTrueques() {
        System.out.println("\n🔍=== BUSCAR TRUEQUES ===");

        // Verificar que haya propuestas disponibles
        if (propuestasIntercambio.isEmpty()) {
            System.out.println("😕 No hay propuestas de trueque disponibles.");
            menuTrueque();
            return;
        }

        // Mostrar todas las propuestas de trueque
        for (int i = 0; i < propuestasIntercambio.size(); i++) {
            Intercambio intercambio = propuestasIntercambio.get(i);
            System.out.println((i + 1) + ". 👤 " + intercambio.getUsuario().getNombre() + " ofrece: " +
                    intercambio.getProductoOfrecido().getNombre() +
                    " - 🔎 Busca: " + intercambio.getDescripcionBuscada());
        }

        System.out.print("Seleccione el número de trueque que le interesa (0 para volver): ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        if (seleccion == 0) {
            menuTrueque();
            return;
        }

        // Validar selección
        if (seleccion < 1 || seleccion > propuestasIntercambio.size()) {
            System.out.println("❌ Selección no válida.");
            buscarTrueques();
            return;
        }

        Intercambio intercambioSeleccionado = propuestasIntercambio.get(seleccion - 1);

        // Verificar que el usuario esté logueado
        if (usuarioActual == null) {
            System.out.println("🔒 Debe iniciar sesión para realizar trueques.");
            iniciarSesion();
        }

        // Verificar que no sea su propia propuesta
        if (usuarioActual == intercambioSeleccionado.getUsuario()) {
            System.out.println("❌ No puedes intercambiar contigo mismo.");
            menuTrueque();
            return;
        }

        // Mostrar detalles del intercambio seleccionado
        System.out.println("\n🔄=== PROPUESTA DE INTERCAMBIO ===");
        System.out.println("👤 " + intercambioSeleccionado.getUsuario().getNombre() + " ofrece:");
        System.out.println("- " + intercambioSeleccionado.getProductoOfrecido().getNombre());
        System.out.println("\n🔎 Busca: " + intercambioSeleccionado.getDescripcionBuscada());

        System.out.println("\nTus productos disponibles para intercambiar:");
        ArrayList<Producto> misProductos = usuarioActual.getProductos();

        // Verificar que el usuario tenga productos para intercambiar
        if (misProductos.isEmpty()) {
            System.out.println("😕 No tienes productos para intercambiar.");
            menuTrueque();
            return;
        }

        // Mostrar productos del usuario actual
        for (int i = 0; i < misProductos.size(); i++) {
            System.out.println((i + 1) + ". " + misProductos.get(i).getNombre());
        }

        System.out.print("Seleccione el número de producto que desea ofrecer (0 para cancelar): ");
        int productoPropuesto = scanner.nextInt();
        scanner.nextLine();

        if (productoPropuesto == 0) {
            menuTrueque();
            return;
        }

        // Validar selección del producto a ofrecer
        if (productoPropuesto < 1 || productoPropuesto > misProductos.size()) {
            System.out.println("❌ Selección no válida.");
            buscarTrueques();
            return;
        }

        Producto productoSeleccionado = misProductos.get(productoPropuesto - 1);

        // Confirmar la propuesta de intercambio
        System.out.println("\n✅ ¡Propuesta de intercambio enviada!");
        System.out.println("💚 Ofreces: " + productoSeleccionado.getNombre());
        System.out.println("💚 A cambio de: " + intercambioSeleccionado.getProductoOfrecido().getNombre());
        System.out.println("\nEl otro usuario será notificado y podrá aceptar o rechazar tu propuesta.");

        // Aquí normalmente se enviaría una notificación al otro usuario
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
        menuTrueque();
    }

    // Ver las propuestas de trueque del usuario actual
    private static void verMisTrueques() {
        // Verificar que el usuario esté logueado
        if (usuarioActual == null) {
            System.out.println("🔒 Debe iniciar sesión para ver tus trueques.");
            iniciarSesion();
        }

        System.out.println("\n📋=== MIS PROPUESTAS DE TRUEQUE ===");
        boolean tieneTrueques = false;

        // Buscar y mostrar propuestas del usuario actual
        for (Intercambio intercambio : propuestasIntercambio) {
            if (intercambio.getUsuario() == usuarioActual) {
                System.out.println("💚 Ofreces: " + intercambio.getProductoOfrecido().getNombre());
                System.out.println("🔎 Buscas: " + intercambio.getDescripcionBuscada());
                System.out.println();
                tieneTrueques = true;
            }
        }

        if (!tieneTrueques) {
            System.out.println("😕 No tienes propuestas de trueque activas.");
        }

        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
        menuTrueque();
    }

    // Mostrar información sobre el sistema
    private static void mostrarAcercaDe() {
        System.out.println("\n🌍=== ACERCA DE RISE & ROOT ===");
        System.out.println("♻️ Marketplace Sostenible - ODS 12");
        System.out.println("=================================");
        System.out.println("Este programa está diseñado para fomentar el consumo responsable");
        System.out.println("y la economía circular, alineado con el Objetivo de Desarrollo");
        System.out.println("Sostenible 12 de las Naciones Unidas: \"Producción y consumo responsables\".");
        System.out.println("\nCaracterísticas principales:");
        System.out.println("- 🛒 Marketplace para compra/venta de productos sostenibles");
        System.out.println("- 🔄 Sistema de trueque para reutilizar productos");
        System.out.println("- ♻️ Productos categorizados por su impacto ambiental");
        System.out.println("\nVersión 1.0 - Desarrollado para promover los ODS");
        System.out.println("💚 Rise & Root - Por un futuro sostenible");

        System.out.println("\nPresione Enter para volver al menú principal...");
        scanner.nextLine();
        mostrarMenuPrincipal();
    }
    // Métodos auxiliares
    // Muestra todos los productos disponibles en la plataforma
    private static void listarProductosDisponibles() {
        System.out.println("\n📋=== PRODUCTOS DISPONIBLES ===");
        // Recorre la lista de productos y los muestra con numeración
        for (int i = 0; i < productosDisponibles.size(); i++) {
            Producto producto = productosDisponibles.get(i);
            System.out.println((i + 1) + ". " + producto.getNombre() + " - $" + producto.getPrecio());
            producto.mostrarDetalles(); // Llama al método específico de cada tipo de producto
            System.out.println();
        }
    }

    // Gestiona el proceso de autenticación de usuarios
    private static void iniciarSesion() {
        System.out.println("\n🔐=== INICIAR SESIÓN ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Contraseña: ");
        String clave = scanner.nextLine();

        // Busca el usuario en la lista de usuarios registrados
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getClave().equals(clave)) {
                usuarioActual = usuario; // Establece el usuario como activo
                System.out.println("✅ ¡Bienvenido, " + usuario.getNombre() + "!");
                mostrarMenuPrincipal();
                return;
            }
        }

        // Si las credenciales son incorrectas, ofrece opciones al usuario
        System.out.println("❌ Email o contraseña incorrectos.");
        System.out.println("1. Intentar nuevamente");
        System.out.println("2. Registrarse");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consume el salto de línea

        // Maneja las opciones del usuario tras fallo de login
        switch (opcion) {
            case 1:
                iniciarSesion(); // Recursión para intentar de nuevo
                break;
            case 2:
                registrarUsuario();
                break;
            case 3:
                mostrarMenuPrincipal();
                break;
            default:
                System.out.println("❌ Opción no válida. Volviendo al menú principal.");
                mostrarMenuPrincipal();
        }
    }
}
