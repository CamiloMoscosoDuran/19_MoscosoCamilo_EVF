package pe.edu.vallegrande.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import pe.edu.vallegrande.model.Incidencia;
import pe.edu.vallegrande.model.IncidenciaDAO;
import pe.edu.vallegrande.view.IncidenciaForm;

public class IncidenciaController implements ActionListener {

    private final IncidenciaForm vista;
    private final IncidenciaDAO modelo;
    private final DefaultTableModel tableModel;

    public IncidenciaController(IncidenciaForm vista, IncidenciaDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.tableModel = (DefaultTableModel) vista.getTblIncidencias().getModel();

        // Inicialización y asignación de escuchadores
        inicializarComponentes();
        this.vista.getBtnRegistrar().addActionListener(this);
        this.vista.getBtnModificar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnBuscar().addActionListener(this);
        this.vista.getBtnLimpiar().addActionListener(this);

        // Listener para cargar datos al hacer clic en la tabla (Error corregido: El método ahora existe)
        this.vista.getTblIncidencias().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });
    }

    public void iniciar() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        cargarTabla(modelo.listarTodas()); // Error corregido: El método ahora existe
    }

    // --- Métodos Auxiliares (Definiciones que faltaban o se solicitaban) ---

    private void inicializarComponentes() {
        // Llenar JComboBox Tipo y Estado (Asegúrate que estos componentes existen en IncidenciaForm)
        vista.getCmbTipo().addItem("PC");
        vista.getCmbTipo().addItem("Proyector");
        vista.getCmbTipo().addItem("Conectividad");
        vista.getCmbTipo().addItem("Impresora");
        vista.getCmbTipo().addItem("Otro");

        vista.getCmbEstado().addItem("Pendiente");
        vista.getCmbEstado().addItem("Procesando");
        vista.getCmbEstado().addItem("Resuelto");

        // Llenar JComboBox Criterio de Búsqueda
        vista.getCmbCriterioBusqueda().addItem("tipo");
        vista.getCmbCriterioBusqueda().addItem("aula");
        vista.getCmbCriterioBusqueda().addItem("estado");

        vista.getTxtId().setEditable(false);
    }

    private void cargarTabla(List<Incidencia> lista) {
        tableModel.setRowCount(0); // Limpiar filas anteriores

        for (Incidencia inc : lista) {
            Object[] fila = new Object[6];
            fila[0] = inc.getId();
            fila[1] = inc.getTipo();
            fila[2] = inc.getAula();
            fila[3] = inc.getFecha();
            fila[4] = inc.getEstado();
            fila[5] = inc.getDescripcion();
            tableModel.addRow(fila);
        }
    }

    private void limpiarCampos() {
        vista.getTxtId().setText("");
        vista.getCmbTipo().setSelectedIndex(0);
        vista.getTxtAula().setText("");
        vista.getTxtFecha().setText("");
        vista.getCmbEstado().setSelectedIndex(0);
        vista.getTxtDescripcion().setText("");
        vista.getTxtValorBusqueda().setText("");
    }

    private void seleccionarFila() {
        int fila = vista.getTblIncidencias().getSelectedRow();
        if (fila >= 0) {
            // Cargar datos seleccionados en los campos de texto
            vista.getTxtId().setText(vista.getTblIncidencias().getValueAt(fila, 0).toString());
            vista.getCmbTipo().setSelectedItem(vista.getTblIncidencias().getValueAt(fila, 1).toString());
            vista.getTxtAula().setText(vista.getTblIncidencias().getValueAt(fila, 2).toString());
            vista.getTxtFecha().setText(vista.getTblIncidencias().getValueAt(fila, 3).toString());
            vista.getCmbEstado().setSelectedItem(vista.getTblIncidencias().getValueAt(fila, 4).toString());
            vista.getTxtDescripcion().setText(vista.getTblIncidencias().getValueAt(fila, 5).toString());
        }
    }

    private Incidencia obtenerIncidenciaDeCampos() {
        // --- VALIDACIONES DE CAMPOS OBLIGATORIOS ---
        if (vista.getTxtAula().getText().trim().isEmpty() || vista.getTxtFecha().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Aula y Fecha son campos obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Incidencia inc = new Incidencia();

        // Manejo de la Fecha
        try {
            // Se asume el formato "AAAA-MM-DD"
            Date fecha = Date.valueOf(vista.getTxtFecha().getText().trim());
            inc.setFecha(fecha);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(vista, "Formato de fecha inválido. Use AAAA-MM-DD.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Si hay ID (para Modificar)
        if (!vista.getTxtId().getText().isEmpty()) {
            inc.setId(Integer.parseInt(vista.getTxtId().getText()));
        }

        // Resto de los campos
        inc.setTipo(vista.getCmbTipo().getSelectedItem().toString());
        inc.setAula(vista.getTxtAula().getText().trim());
        inc.setEstado(vista.getCmbEstado().getSelectedItem().toString());
        inc.setDescripcion(vista.getTxtDescripcion().getText().trim());

        return inc;
    }

    // --- Manejo de Eventos (ActionListener) ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegistrar()) {
            Incidencia inc = obtenerIncidenciaDeCampos();
            if (inc != null) {
                if (modelo.registrar(inc)) {
                    JOptionPane.showMessageDialog(vista, "Incidencia registrada con éxito.");
                    limpiarCampos();
                    cargarTabla(modelo.listarTodas());
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al registrar la incidencia. Verifique la conexión/BD.", "Error BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (e.getSource() == vista.getBtnModificar()) {
            if (vista.getTxtId().getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Seleccione una incidencia de la tabla para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Incidencia inc = obtenerIncidenciaDeCampos();
            if (inc != null) {
                if (modelo.modificar(inc)) {
                    JOptionPane.showMessageDialog(vista, "Incidencia modificada con éxito.");
                    limpiarCampos();
                    cargarTabla(modelo.listarTodas());
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al modificar la incidencia.", "Error BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (e.getSource() == vista.getBtnEliminar()) {
            if (vista.getTxtId().getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Seleccione una incidencia de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = Integer.parseInt(vista.getTxtId().getText());
            int confirm = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar la incidencia ID: " + id + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (modelo.eliminar(id)) {
                    JOptionPane.showMessageDialog(vista, "Incidencia eliminada con éxito.");
                    limpiarCampos();
                    cargarTabla(modelo.listarTodas());
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar la incidencia.", "Error BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (e.getSource() == vista.getBtnBuscar()) {
            String criterio = vista.getCmbCriterioBusqueda().getSelectedItem().toString();
            String valor = vista.getTxtValorBusqueda().getText().trim();

            if (valor.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese un valor para la búsqueda.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                cargarTabla(modelo.listarTodas());
                return;
            }

            List<Incidencia> resultados = modelo.buscar(criterio, valor);
            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "No se encontraron coincidencias.", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }
            cargarTabla(resultados);
        }
        else if (e.getSource() == vista.getBtnLimpiar()) {
            limpiarCampos();
            cargarTabla(modelo.listarTodas());
        }
    }
}