package pe.edu.vallegrande.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainView extends JFrame {

    private JButton btnRegistrar, btnModificar, btnEliminar;
    private JTable tablaIncidencias;
    private JComboBox<String> cmbCriterio;
    private JTextField txtValorBusqueda;
    private JButton btnBuscar;

    public MainView() {
        // --- Configuración de la Ventana ---
        setTitle("Gestión de Incidencias Técnicas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        // --- PANEL SUPERIOR COMPUESTO ---
        JPanel panelNorte = new JPanel(new BorderLayout());

        // Panel de Botones de CRUD
        JPanel panelCrud = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnRegistrar = new JButton("Registrar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        panelCrud.add(btnRegistrar);
        panelCrud.add(btnModificar);
        panelCrud.add(btnEliminar);

        // Panel de Búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Búsqueda"));

        cmbCriterio = new JComboBox<>(new String[]{"Tipo", "Aula", "Estado"});
        txtValorBusqueda = new JTextField(15);
        btnBuscar = new JButton("Buscar");

        panelBusqueda.add(new JLabel("Buscar por:"));
        panelBusqueda.add(cmbCriterio);
        panelBusqueda.add(new JLabel("Valor:"));
        panelBusqueda.add(txtValorBusqueda);
        panelBusqueda.add(btnBuscar);

        panelNorte.add(panelCrud, BorderLayout.NORTH);
        panelNorte.add(panelBusqueda, BorderLayout.SOUTH);
        add(panelNorte, BorderLayout.NORTH);

        // --- TABLA CENTRAL (Listado) ---
        tablaIncidencias = new JTable();
        tablaIncidencias.setModel(new DefaultTableModel(new Object[][]{},
                new String[]{"ID", "Tipo", "Aula", "Fecha", "Estado", "Descripción"}));

        tablaIncidencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaIncidencias);
        add(scrollPane, BorderLayout.CENTER);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    // --- Getters para el Controlador ---
    public JTable getTablaIncidencias() { return tablaIncidencias; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JComboBox<String> getCmbCriterio() { return cmbCriterio; }
    public JTextField getTxtValorBusqueda() { return txtValorBusqueda; }

    // Helper: Obtener ID seleccionado
    public int getIdSeleccionado() {
        int fila = tablaIncidencias.getSelectedRow();
        if (fila >= 0) {
            return (int) tablaIncidencias.getValueAt(fila, 0);
        }
        return -1;
    }
}