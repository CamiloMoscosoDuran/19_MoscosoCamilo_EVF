package pe.edu.vallegrande.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class IncidenciaForm extends JFrame {

    // --- Componentes ---
    private JButton btnRegistrar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private JTextField txtId;
    private JComboBox<String> cmbTipo; // Corregido el NullPointerException aquí
    private JTextField txtAula;
    private JTextField txtFecha;
    private JComboBox<String> cmbEstado; // Corregido el NullPointerException aquí
    private JTextField txtDescripcion;

    private JComboBox<String> cmbCriterioBusqueda; // Corregido el NullPointerException aquí
    private JTextField txtValorBusqueda;

    private JTable tblIncidencias;
    private DefaultTableModel tableModel;

    public IncidenciaForm() {
        // --- Configuración inicial de la ventana ---
        setTitle("Sistema de Gestión de Incidencias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 600);
        setLocationRelativeTo(null);

        // --- ¡SOLUCIÓN! Inicialización de Componentes Swing ---
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


        // --- Layout y adición de componentes (Ejemplo Básico) ---

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

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Búsqueda"));
        panelBusqueda.add(new JLabel("Buscar por:"));
        panelBusqueda.add(cmbCriterioBusqueda);
        panelBusqueda.add(new JLabel("Valor:"));
        panelBusqueda.add(txtValorBusqueda);
        panelBusqueda.add(btnBuscar);

        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelDatos, BorderLayout.WEST);
        panelNorte.add(panelBotones, BorderLayout.EAST);
        panelNorte.add(panelBusqueda, BorderLayout.SOUTH);

        // Contenedor principal
        this.add(panelNorte, BorderLayout.NORTH);
        this.add(new JScrollPane(tblIncidencias), BorderLayout.CENTER);

        // Empaquetar y hacer visible
        // pack(); // Descomentar si usas un diseño que se ajusta al contenido
    }

    // --- Getters necesarios para el Controlador ---

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