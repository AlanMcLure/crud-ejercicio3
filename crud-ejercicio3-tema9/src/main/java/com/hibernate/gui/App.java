package com.hibernate.gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hibernate.dao.SerieDAO;
import com.hibernate.model.Serie;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {

	private JFrame frmCrudSeries;
	private JTable tableSeries;
	private JTextField textFieldId;
	private JTextField textFieldSerie;
	private JTextField textFieldTemporadas;
	private JTextField textFieldCapitulos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmCrudSeries.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrudSeries = new JFrame();
		frmCrudSeries.setTitle("CRUD series");
		frmCrudSeries.setBounds(100, 100, 650, 549);
		frmCrudSeries.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrudSeries.getContentPane().setLayout(null);

		SerieDAO serieDAO = new SerieDAO();
		List<Serie> series = serieDAO.selectAllSeries();

		DefaultTableModel modelSerie = new DefaultTableModel();

		modelSerie.addColumn("ID");
		modelSerie.addColumn("Título");
		modelSerie.addColumn("Nº temporadas");
		modelSerie.addColumn("Total episodios");

		for (Serie s : series) {
			Object[] row = new Object[4];
			row[0] = s.getId();
			row[1] = s.getNombre();
			row[2] = s.getNumTemporadas();
			row[3] = s.getNumCapitulos();
			modelSerie.addRow(row);
		}

		tableSeries = new JTable(modelSerie);
		frmCrudSeries.getContentPane().add(tableSeries);
		tableSeries.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane scrollPaneUsuarios = new JScrollPane(tableSeries);
		scrollPaneUsuarios.setBounds(50, 35, 539, 160);
		frmCrudSeries.getContentPane().add(scrollPaneUsuarios);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(50, 208, 45, 13);
		frmCrudSeries.getContentPane().add(lblId);

		JLabel lblSerie = new JLabel("Serie:");
		lblSerie.setBounds(50, 257, 45, 13);
		frmCrudSeries.getContentPane().add(lblSerie);

		JLabel lblTemporadas = new JLabel("Temporadas:");
		lblTemporadas.setBounds(50, 310, 84, 13);
		frmCrudSeries.getContentPane().add(lblTemporadas);

		JLabel lblCapitulos = new JLabel("Capítulos:");
		lblCapitulos.setBounds(50, 365, 62, 13);
		frmCrudSeries.getContentPane().add(lblCapitulos);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(282, 452, 104, 21);
		frmCrudSeries.getContentPane().add(btnActualizar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(105, 452, 85, 21);
		frmCrudSeries.getContentPane().add(btnGuardar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(477, 452, 85, 21);
		frmCrudSeries.getContentPane().add(btnBorrar);

		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setBounds(159, 205, 96, 19);
		frmCrudSeries.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);

		textFieldSerie = new JTextField();
		textFieldSerie.setColumns(10);
		textFieldSerie.setBounds(159, 254, 200, 19);
		frmCrudSeries.getContentPane().add(textFieldSerie);

		textFieldTemporadas = new JTextField();
		textFieldTemporadas.setColumns(10);
		textFieldTemporadas.setBounds(159, 307, 96, 19);
		frmCrudSeries.getContentPane().add(textFieldTemporadas);

		textFieldCapitulos = new JTextField();
		textFieldCapitulos.setColumns(10);
		textFieldCapitulos.setBounds(159, 362, 96, 19);
		frmCrudSeries.getContentPane().add(textFieldCapitulos);

		tableSeries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int indexSerie = tableSeries.getSelectedRow();
				TableModel modelSerie = tableSeries.getModel();

				textFieldId.setText(modelSerie.getValueAt(indexSerie, 0).toString());
				textFieldSerie.setText(modelSerie.getValueAt(indexSerie, 1).toString());
				textFieldTemporadas.setText(modelSerie.getValueAt(indexSerie, 2).toString());
				textFieldCapitulos.setText(modelSerie.getValueAt(indexSerie, 3).toString());
			}
		});

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Serie s = new Serie(textFieldSerie.getText(), Integer.parseInt(textFieldTemporadas.getText()),
						Integer.parseInt(textFieldCapitulos.getText()));
				serieDAO.insertSerie(s);
			}
		});

		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Serie s = serieDAO.selectSerieById(Integer.parseInt(textFieldId.getText()));
				s.setNombre(textFieldSerie.getText());
				s.setNumTemporadas(Integer.parseInt(textFieldTemporadas.getText()));
				s.setNumCapitulos(Integer.parseInt(textFieldCapitulos.getText()));
				serieDAO.updateSerie(s);
			}
		});

		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serieDAO.deleteSerie(Integer.parseInt(textFieldId.getText()));
			}
		});
	}
}
