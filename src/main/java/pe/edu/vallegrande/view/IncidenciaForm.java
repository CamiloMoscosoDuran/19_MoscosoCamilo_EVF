package pe.edu.vallegrande.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout; // Importar FlowLayout para el panel de botones
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class IncidenciaForm extends JFrame {

    // --- Componentes ---
    private JButton btnRegistrar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar; // Este botón ya estaba visible en tu imagen, lo mantendremos

    private JTextField txtId;
    private JComboBox<String> cmbTipo;
    private JTextField txtAula;
    private JTextField txtFecha; 
    private JComboBox<String> cmbEstado;
    private JTextField txtDescripcion;

    private JComboBox<String> cmbCriterioBusqueda;
    private JTextField txtValorBusqueda;

    private JTable tblIncidencias;
    private DefaultTableModel tableModel; // Ya no es necesario como atributo de la clase, se pasa al JTable

    public IncidenciaForm() {
        // --- Configuración inicial de la ventana ---
        setTitle("Sistema de Gestión de Incidencias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 600);
        setLocationRelativeTo(null);
        
        // --- Inicialización de Componentes Swing ---
        btnRegistrar = new JButton("Registrar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar Campos/Tabla");
        
        txtId = new JTextField(5);
        cmbTipo = new JComboBox<>();
        txtAula = new JTextField(15);
        txtFecha = new JTextField("AAAA-MM-DD", 10);
        cmbEstado = new JComboBox<>();
        txtDescripcion = new JTextField(30);
        
        cmbCriterioBusqueda = new JComboBox<>();
        txtValorBusqueda = new JTextField(15);

        // Inicializar la tabla y su modelo
        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Tipo", "Aula", "Fecha", "Estado", "Descripción"}, 0);
        tblIncidencias = new JTable(tableModel);
        
        
        // --- Layout y adición de componentes (Corregido y Mejorado) ---
        
        // Panel para datos de registro/edición
        JPanel panelDatos = new JPanel(new GridLayout(6, 2, 10, 5));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Registro/Edición de Incidencia"));
        
        panelDatos.add(new JLabel("ID (Autogen.):"));
        panelDatos.add(txtId);
        panelDatos.add(new JLabel("Tipo de Incidencia:"));
        panelDatos.add(cmbTipo);
        panelDatos.add(new JLabel("Aula/Ambiente:"));
        panelDatos.add(txtAula);
        panelDatos.add(new JLabel("Fecha (AAAA-MM-DD):"));
        panelDatos.add(txtFecha);
        panelDatos.add(new JLabel("Estado:"));
        panelDatos.add(cmbEstado);
        panelDatos.add(new JLabel("Descripción:"));
        panelDatos.add(txtDescripcion);
        
        // Panel para los botones de CRUD
        JPanel panelCrudBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Usar FlowLayout para botones
        panelCrudBotones.add(btnRegistrar);
        panelCrudBotones.add(btnModificar);
        panelCrudBotones.add(btnEliminar);
        // El botón Limpiar podría ir aquí o en otro lugar, según tu preferencia.
        // Lo movemos aquí para que esté con los de acción
        panelCrudBotones.add(btnLimpiar); // Añadido btnLimpiar al panel de botones CRUD
        
        // Panel para búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Búsqueda"));
        panelBusqueda.add(new JLabel("Buscar por:"));
        panelBusqueda.add(cmbCriterioBusqueda);
        panelBusqueda.add(new JLabel("Valor:"));
        panelBusqueda.add(txtValorBusqueda);
        panelBusqueda.add(btnBuscar);

        // Panel superior que agrupa datos, CRUD y búsqueda
        JPanel panelSuperior = new JPanel(new BorderLayout()); // Usamos BorderLayout para organizar dentro de este panel
        panelSuperior.add(panelDatos, BorderLayout.NORTH); // Datos arriba
        panelSuperior.add(panelCrudBotones, BorderLayout.CENTER); // Botones en el centro
        panelSuperior.add(panelBusqueda, BorderLayout.SOUTH); // Búsqueda abajo
        
        // Añadir el panel superior al JFrame en la posición NORTH
        this.add(panelSuperior, BorderLayout.NORTH);
        // Añadir la tabla al centro, dentro de un JScrollPane
        this.add(new JScrollPane(tblIncidencias), BorderLayout.CENTER);
        
        // pack(); // Descomentar si usas un diseño que se ajusta al contenido
    }

    // --- Getters necesarios para el Controlador (Estos se mantienen igual) ---
    
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }

    public JTextField getTxtId() { return txtId; }
    public JComboBox<String> getCmbTipo() { return cmbTipo; }
    public JTextField getTxtAula() { return txtAula; }
    public JTextField getTxtFecha() { return txtFecha; } 
    public JComboBox<String> getCmbEstado() { return cmbEstado; }
    public JTextField getTxtDescripcion() { return txtDescripcion; }
    
    public JTable getTblIncidencias() { return tblIncidencias; }

    public JComboBox<String> getCmbCriterioBusqueda() { return cmbCriterioBusqueda; }
    public JTextField getTxtValorBusqueda() { return txtValorBusqueda; }
}
