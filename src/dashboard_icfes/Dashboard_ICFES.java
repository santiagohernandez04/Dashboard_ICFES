package dashboard_icfes;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BubbleXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;

public class Dashboard_ICFES extends JFrame {

    JPanel izq, panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, pane19, der;
    JSlider jsrango, jsglobal;
    JButton jbConsultar, jbLimpiar;
    JCheckBox JCconsultaFIJA1, JCconsultaFIJA2, JCconsultaFIJA3, JCconsultaFIJA4, JCconsultaFIJA5, JC2018, JC2019, JC2020;
    JList<String> JLdepartamento, JLAreas, JLestrato, JLgenero, JLnaturaliad;
    JFreeChart chart;
    ChartPanel graficoCFija;
    JTextField valorTextField, valorTextFieldglobal;
    int contGraficas = 0;
    int cont2018, cont2019, cont2020;

    ArrayList<Atributos> array = new ArrayList<>();

    public Dashboard_ICFES() {

        super("Dashboard ICFES");
        setSize(1100, 650);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// ventana maximizada
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        crearGUI();
        setVisible(true);
    }

    private void crearGUI() {
        carga_2018();
        carga_2019();
        carga_2020();
        //----------------------- Area de graficas -----------------------------
        der = new JPanel();// jpanel para los controles (botones, listas, checkbox, radio, ...) de consulta
        der.setLayout(null);
        der.setBackground(new Color(43, 105, 153));
        der.setBounds(400, 0, 1100, 650);
        add(der);

        //------------------- Area de control de consulta ----------------------
        izq = new JPanel();// jpanel para los controles (botones, listas, checkbox, radio, ...) de consulta
        izq.setLayout(null);
        izq.setBackground(new Color(151, 180, 206));
        izq.setBounds(0, 0, 400, 660);
        add(izq);

        panel1 = new JPanel();// jpanel para opciones de consulta
        panel1.setLayout(null);
        panel1.setBackground(new Color(227, 235, 242));
        panel1.setBounds(10, 20, 180, 100);// coord con respecto al panel izq
        panel1.setBorder(new TitledBorder("Areas evaluadas"));
        String Area[] = {"Matematicas", "Lectura critica", "Sociales y ciudadanias", "Ciencias Naturales", "Ingles"};
        JLAreas = new JList(Area);//recibe el arreglo
        JScrollPane js2 = new JScrollPane(JLAreas);
        js2.setBounds(10, 17, 160, 70);
        panel1.add(js2);
        izq.add(panel1);

        panel2 = new JPanel();// jpanel para opciones de consulta
        panel2.setLayout(null);
        panel2.setBackground(new Color(227, 235, 242));
        panel2.setBounds(200, 20, 180, 205);// coord con respecto al panel izq
        panel2.setBorder(new TitledBorder("Departamentos"));
        String depar[] = {"Amazonas", "Antioquia", "Arauca",
            "Atlantico", "Bogota", "Bolivar", "Boyaca", "Caldas", "Caqueta", "Casanare",
            "Cauca", "Cesar", "Choco", "Cordoba", "Cundinamarca", "Guainia", "Guaviare",
            "Huila", "La Guajira", "Magdalena", "Meta", "Nariño", "Norte de Santander",
            "Putumayo", "Quindio", "Risaralda", "San andres", "Santander", "Sucre", "Tolima", "Valle", "Vaupes", "Vichada"};
        JLdepartamento = new JList(depar);
        JScrollPane js1 = new JScrollPane(JLdepartamento);
        js1.setBounds(10, 17, 160, 180);
        panel2.add(js1);
        izq.add(panel2);

        panel3 = new JPanel();// jpanel para opciones de consulta
        panel3.setLayout(null);
        panel3.setBackground(new Color(227, 235, 242));
        panel3.setBounds(10, 130, 180, 95);// coord con respecto al panel izq
        panel3.setBorder(new TitledBorder("Estratos"));

        String estrato[] = {"Estrato 1", "Estrato 2", "Estrato 3",
            "Estrato 4", "Estrato 5", "Estrato 6", "Sin Estrato"};
        JLestrato = new JList(estrato);
        JScrollPane js4 = new JScrollPane(JLestrato);
        js4.setBounds(10, 17, 160, 70);
        panel3.add(js4);
        izq.add(panel3);

        panel4 = new JPanel();// jpanel para opciones de consulta
        panel4.setLayout(null);
        panel4.setBackground(new Color(227, 235, 242));
        panel4.setBounds(20, 235, 350, 70);// coord con respecto al panel izq
        panel4.setBorder(new TitledBorder("Rango de puntaje areas"));
        jsrango = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
        jsrango.setBounds(10, 20, 300, 40);
        jsrango.setMajorTickSpacing(10);
        jsrango.setMinorTickSpacing(5);
        jsrango.setPaintTicks(true);
        jsrango.setPaintLabels(true);
        valorTextField = new JTextField(100);
        valorTextField.setEditable(false);

        jsrango.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valorSlider = jsrango.getValue();
                valorTextField.setText(String.valueOf(valorSlider));
            }
        });
        valorTextField.setBounds(315, 20, 25, 25);
        valorTextField.setEditable(false);
        panel4.add(valorTextField);
        panel4.add(jsrango);
        izq.add(panel4);

        panel5 = new JPanel();// jpanel para opciones de consulta
        panel5.setLayout(null);
        panel5.setBackground(new Color(227, 235, 242));
        panel5.setBounds(20, 318, 110, 65);// coord con respecto al panel izq
        panel5.setBorder(new TitledBorder("Genero"));
        add(panel5);
        String genero[] = {"F", "M"};
        JLgenero = new JList(genero);
        //JScrollPane jsgenero = new JScrollPane(JLgenero);
        JLgenero.setBounds(10, 20, 80, 36);
        panel5.add(JLgenero);

        izq.add(panel5);

        panel6 = new JPanel();// jpanel para opciones de consulta
        panel6.setLayout(null);
        panel6.setBackground(new Color(227, 235, 242));
        panel6.setBounds(153, 318, 110, 65);// coord con respecto al panel izq
        panel6.setBorder(new TitledBorder("Naturalidad"));
        String naturalidad[] = {"Oficial", "No oficial"};
        JLnaturaliad = new JList(naturalidad);
        JLnaturaliad.setBounds(10, 20, 80, 36);

        panel6.add(JLnaturaliad);
        izq.add(panel6);

        pane19 = new JPanel();// jpanel para opciones de consulta
        pane19.setLayout(null);
        pane19.setBackground(new Color(227, 235, 242));
        pane19.setBounds(285, 310, 85, 80);// coord con respecto al panel izq
        pane19.setBorder(new TitledBorder("Años"));
        add(pane19);
        JC2018 = new JCheckBox("2018");
        JC2018.setBounds(10, 20, 60, 15);//coord con respecto al panel2
        pane19.add(JC2018);
        JC2019 = new JCheckBox("2019");
        JC2019.setBounds(10, 40, 60, 15);//coord con respecto al panel2
        pane19.add(JC2019);
        JC2020 = new JCheckBox("2020");
        JC2020.setBounds(10, 60, 60, 15);//coord con respecto al panel2
        pane19.add(JC2020);
        izq.add(pane19);

        panel7 = new JPanel();// jpanel para opciones de consulta
        panel7.setLayout(null);
        panel7.setBackground(new Color(227, 235, 242));
        panel7.setBounds(20, 395, 350, 70);// coord con respecto al panel izq
        panel7.setBorder(new TitledBorder("Puntaje global"));
        izq.add(panel7);
        jsglobal = new JSlider(SwingConstants.HORIZONTAL, 0, 500, 0);
        jsglobal.setBounds(10, 20, 300, 40);
        jsglobal.setMajorTickSpacing(50);
        jsglobal.setMinorTickSpacing(5);
        jsglobal.setPaintTicks(true);
        jsglobal.setPaintLabels(true);
        valorTextFieldglobal = new JTextField(500);
        valorTextFieldglobal.setEditable(false);
        jsglobal.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valorSlider = jsglobal.getValue();
                valorTextFieldglobal.setText(String.valueOf(valorSlider));
            }
        });

        valorTextFieldglobal.setBounds(315, 20, 25, 25);
        valorTextFieldglobal.setEditable(false);
        panel7.add(valorTextFieldglobal);
        panel7.add(jsglobal);
        panel8 = new JPanel();// jpanel para opciones de consulta
        panel8.setLayout(null);
        panel8.setBackground(new Color(227, 235, 242));
        panel8.setBounds(10, 472, 380, 125);// coord con respecto al panel izq

        panel8.setBorder(new TitledBorder("Consultas fijas 2018-2019-2020"));

        JCconsultaFIJA1 = new JCheckBox("Percentil hombres del Huila mayor a 70 en L.critica");
        JCconsultaFIJA1.setBounds(6, 20, 360, 15);//coord con respecto al panel panel1
        panel8.add(JCconsultaFIJA1);

        JCconsultaFIJA2 = new JCheckBox("Percentil mujeres de Antioquia mayor a 90 en C.naturales");
        JCconsultaFIJA2.setBounds(6, 40, 360, 15);//coord con respecto al panel panel1
        panel8.add(JCconsultaFIJA2);

        JCconsultaFIJA3 = new JCheckBox("Hombres de colegio oficial que obtuvieron A2 en ingles");
        JCconsultaFIJA3.setBounds(6, 60, 360, 15);//coord con respecto al panel panel1
        panel8.add(JCconsultaFIJA3);

        JCconsultaFIJA4 = new JCheckBox("Mujeres de colegio no oficial que obtuvieron A2 en ingles");
        JCconsultaFIJA4.setBounds(6, 80, 360, 15);//coord con respecto al panel panel1
        panel8.add(JCconsultaFIJA4);

        JCconsultaFIJA5 = new JCheckBox("Hombres del valle del cauca que presentaron el icfes");
        JCconsultaFIJA5.setBounds(6, 100, 360, 15);//coord con respecto al panel panel1
        panel8.add(JCconsultaFIJA5);

//        ButtonGroup bt = new ButtonGroup();
//        bt.add(JCconsultaFIJA1);
//        bt.add(JCconsultaFIJA2);
//        bt.add(JCconsultaFIJA3);
//        bt.add(JCconsultaFIJA4);
//        bt.add(JCconsultaFIJA5);
        izq.add(panel8);

        ImageIcon img1 = new ImageIcon(
                getClass().getResource("Img/consultar.png"));//para imagen demasiado grande
        ImageIcon img2 = new ImageIcon(
                img1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jbConsultar = new JButton("Consultar", img2);
        jbConsultar.setBounds(50, 603, 130, 40);
        jbConsultar.setBackground(Color.WHITE);
        jbConsultar.setBorderPainted(false);
        jbConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbConsulta();
            }
        });
        izq.add(jbConsultar);

        ImageIcon img3 = new ImageIcon(
                getClass().getResource("Img/limpiar.png"));//para imagen demasiado grande
        ImageIcon img4 = new ImageIcon(
                img3.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jbLimpiar = new JButton("Limpiar", img4);
        jbLimpiar.setBounds(200, 603, 130, 40);
        jbLimpiar.setBackground(Color.WHITE);
        jbLimpiar.setBorderPainted(false);
        jbLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbLimpiar();
            }
        });
        jbLimpiar.setEnabled(true);
        izq.add(jbLimpiar);

        JCconsultaFIJA1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // Llama a la función consultas_fijas cuando cambie el estado del checkbox

                consultas_fijas();
                limpiarSelecciones();

            }
        });
        JCconsultaFIJA2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // Llama a la función consultas_fijas cuando cambie el estado del checkbox
                consultas_fijas();
                limpiarSelecciones();
            }
        });
        JCconsultaFIJA3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // Llama a la función consultas_fijas cuando cambie el estado del checkbox
                consultas_fijas();
                limpiarSelecciones();
            }
        });
        JCconsultaFIJA4.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // Llama a la función consultas_fijas cuando cambie el estado del checkbox
                consultas_fijas();
                limpiarSelecciones();
            }
        });
        JCconsultaFIJA5.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // Llama a la función consultas_fijas cuando cambie el estado del checkbox
                consultas_fijas();
                limpiarSelecciones();
            }
        });

    }

    public void limpiarSelecciones() {
        JLAreas.clearSelection();
        JLdepartamento.clearSelection();
        JLestrato.clearSelection();
        JLgenero.clearSelection();
        JLnaturaliad.clearSelection();
        jsrango.setValue(0);
        jsglobal.setValue(0);
        JCconsultaFIJA1.setSelected(false);
        JCconsultaFIJA2.setSelected(false);
        JCconsultaFIJA3.setSelected(false);
        JCconsultaFIJA4.setSelected(false);
        JCconsultaFIJA5.setSelected(false);
    }

    public void evento_jbConsulta() {
        consultas_libres();
        limpiarSelecciones();
    }

    public void evento_jbLimpiar() {
        limpiarSelecciones();
        der.removeAll();
        der.revalidate();
        der.repaint();
        contGraficas = 0;
    }

    public void carga_2018() {
        FileReader Fr = null;
        boolean error = false;
        try {
            Fr = new FileReader("2018.csv");
        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
        }
        if (!error) {
            BufferedReader Br = new BufferedReader(Fr);
            String linea = "";
            try {
                while ((linea = Br.readLine()) != null) {
                    String columna[] = linea.split(";");
                    array.add(new Atributos(columna[0], columna[1], columna[2], columna[3],
                            Integer.parseInt(columna[4]), Integer.parseInt(columna[5]), Integer.parseInt(columna[6]),
                            Integer.parseInt(columna[7]), Integer.parseInt(columna[8]), Integer.parseInt(columna[9]),
                            Integer.parseInt(columna[10]), Integer.parseInt(columna[11]), Integer.parseInt(columna[12]),
                            Integer.parseInt(columna[13]), columna[14], Integer.parseInt(columna[15]),
                            Integer.parseInt(columna[16]), Integer.parseInt(columna[17])));
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo");
            }
            try {
                Fr.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo");
            }

        }

    }

    public void carga_2019() {
        FileReader Fr = null;
        boolean error = false;

        try {
            Fr = new FileReader("2019.csv");

        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
        }

        if (!error) {
            BufferedReader Br = new BufferedReader(Fr);
            String linea = "";

            try {
                while ((linea = Br.readLine()) != null) {
                    String columna[] = linea.split(";");

                    array.add(new Atributos(columna[0], columna[1], columna[2], columna[3], Integer.parseInt(columna[4]),
                            Integer.parseInt(columna[5]), Integer.parseInt(columna[6]), Integer.parseInt(columna[7]), Integer.parseInt(columna[8]), Integer.parseInt(columna[9]), Integer.parseInt(columna[10]),
                            Integer.parseInt(columna[11]), Integer.parseInt(columna[12]), Integer.parseInt(columna[13]), columna[14], Integer.parseInt(columna[15]), Integer.parseInt(columna[16]), Integer.parseInt(columna[17])));
                     // System.out.println(array);

                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo");
            }

            try {
                Fr.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo");
            }

        }

    }

    public void carga_2020() {
        FileReader Fr = null;
        boolean error = false;

        try {
            Fr = new FileReader("2020.csv");

        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
        }

        if (!error) {
            BufferedReader Br = new BufferedReader(Fr);
            String linea = "";

            try {
                while ((linea = Br.readLine()) != null) {
                    String columna[] = linea.split(";");

                    array.add(new Atributos(columna[0], columna[1], columna[2], columna[3], Integer.parseInt(columna[4]),
                            Integer.parseInt(columna[5]), Integer.parseInt(columna[6]), Integer.parseInt(columna[7]), Integer.parseInt(columna[8]), Integer.parseInt(columna[9]), Integer.parseInt(columna[10]),
                            Integer.parseInt(columna[11]), Integer.parseInt(columna[12]), Integer.parseInt(columna[13]), columna[14], Integer.parseInt(columna[15]), Integer.parseInt(columna[16]), Integer.parseInt(columna[17])));
                    //  System.out.println(array);

                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo");
            }

            try {
                Fr.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo");
            }

        }

    }

    public void consultas_fijas() {
        cont2018 = cont2019 = cont2020 = 0;
        Atributos AT;
        boolean verGrafica = false;
        for (int i = 0; i < array.size(); i++) {

            AT = array.get(i);

            if (JCconsultaFIJA1.isSelected()) {

                if (AT.getGenero().equalsIgnoreCase("M") && AT.getResidencia().equalsIgnoreCase("HUILA") && AT.getPer_Lcritica() > 70 && AT.getAño() == 2018) {
                    cont2018++;
                }
                if (AT.getGenero().equalsIgnoreCase("M") && AT.getResidencia().equalsIgnoreCase("HUILA") && AT.getPer_Lcritica() > 70 && AT.getAño() == 2019) {
                    cont2019++;
                }
                if (AT.getGenero().equalsIgnoreCase("M") && AT.getResidencia().equalsIgnoreCase("HUILA") && AT.getPer_Lcritica() > 70 && AT.getAño() == 2020) {
                    cont2020++;
                }

                verGrafica = true;

            }

            if (JCconsultaFIJA2.isSelected()) {
                if (AT.getGenero().equalsIgnoreCase("F") && AT.getResidencia().equalsIgnoreCase("ANTIOQUIA") && AT.getPer_Cnaturales() > 90 && AT.getAño() == 2018) {
                    cont2018++;
                }
                if (AT.getGenero().equalsIgnoreCase("F") && AT.getResidencia().equalsIgnoreCase("ANTIOQUIA") && AT.getPer_Cnaturales() > 90 && AT.getAño() == 2019) {
                    cont2019++;
                }
                if (AT.getGenero().equalsIgnoreCase("F") && AT.getResidencia().equalsIgnoreCase("ANTIOQUIA") && AT.getPer_Cnaturales() > 90 && AT.getAño() == 2020) {
                    cont2020++;
                }
                verGrafica = true;
            }

            if (JCconsultaFIJA3.isSelected()) {
                if (AT.getGenero().equalsIgnoreCase("M") && AT.getNaturaleza().equalsIgnoreCase("OFICIAL")
                        && AT.getNivel_ingles().equalsIgnoreCase("A2") && AT.getAño() == 2018) {

                    cont2018++;

                }
                if (AT.getGenero().equalsIgnoreCase("M") && AT.getNaturaleza().equalsIgnoreCase("OFICIAL")
                        && AT.getNivel_ingles().equalsIgnoreCase("A2") && AT.getAño() == 2019) {

                    cont2019++;

                }

                if (AT.getGenero().equalsIgnoreCase("M") && AT.getNaturaleza().equalsIgnoreCase("OFICIAL")
                        && AT.getNivel_ingles().equalsIgnoreCase("A2") && AT.getAño() == 2020) {

                    cont2020++;

                }
                verGrafica = true;
            }

            if (JCconsultaFIJA4.isSelected()) {
                if (AT.getGenero().equalsIgnoreCase("F") && AT.getNaturaleza().equalsIgnoreCase("NO OFICIAL")
                        && AT.getNivel_ingles().equalsIgnoreCase("A2") && AT.getAño() == 2018) {
                    cont2018++;
                }
                if (AT.getGenero().equalsIgnoreCase("F") && AT.getNaturaleza().equalsIgnoreCase("NO OFICIAL")
                        && AT.getNivel_ingles().equalsIgnoreCase("A2") && AT.getAño() == 2019) {
                    cont2019++;
                }
                if (AT.getGenero().equalsIgnoreCase("F") && AT.getNaturaleza().equalsIgnoreCase("NO OFICIAL")
                        && AT.getNivel_ingles().equalsIgnoreCase("A2") && AT.getAño() == 2020) {
                    cont2020++;
                }
                verGrafica = true;
            }
            if (JCconsultaFIJA5.isSelected()) {
                if (AT.getResidencia().equalsIgnoreCase("VALLE") && AT.getGenero().equalsIgnoreCase("M")
                        && AT.getAño() == 2018) {
                    cont2018++;
                }
                if (AT.getResidencia().equalsIgnoreCase("VALLE") && AT.getGenero().equalsIgnoreCase("M")
                        && AT.getAño() == 2019) {
                    cont2019++;
                }
                if (AT.getResidencia().equalsIgnoreCase("VALLE") && AT.getGenero().equalsIgnoreCase("M")
                        && AT.getAño() == 2020) {
                    cont2020++;
                }
                verGrafica = true;
            }

        }

        if (verGrafica) {
            contGraficas++;

            switch (contGraficas) {

                case 1:
                    grafico1();

                    break;

                case 2:
                    grafico2();

                    break;
                case 3:
                    grafico3();
                    break;
                case 4:
                    grafico4();
                    break;

                case 5:
                    grafico5();
                    break;

                case 6:
                    grafico6();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Limpia la interfaz para poder continuar");
                    jbLimpiar.setEnabled(true);

            }

        }

    }

    public void consultas_libres() {

        ArrayList<Atributos> aux = new ArrayList<>();
        Atributos AT;
        cont2018 = cont2019 = cont2020 = 0;

        String departamentoSeleccionado = JLdepartamento.getSelectedValue();
        String areaSeleccionada = JLAreas.getSelectedValue();
        String estratoSeleccionado = JLestrato.getSelectedValue();
        String generoSeleccionado = JLgenero.getSelectedValue();
        String naturalidadSeleccionado = JLnaturaliad.getSelectedValue();

        for (int i = 0; i < array.size(); i++) {
            AT = array.get(i);

            boolean cumpleDepartamento = JLdepartamento.isSelectionEmpty() || departamentoSeleccionado.equalsIgnoreCase(AT.getResidencia());
            boolean cumpleEstrato = JLestrato.isSelectionEmpty() || estratoSeleccionado.equalsIgnoreCase(AT.getEstrato());
            boolean cumpleGenero = JLgenero.isSelectionEmpty() || generoSeleccionado.equalsIgnoreCase(AT.getGenero());
            boolean cumplenaturalidad = JLnaturaliad.isSelectionEmpty() || naturalidadSeleccionado.equalsIgnoreCase(AT.getNaturaleza());

            boolean Matematicas = JLAreas.isSelectionEmpty() || areaSeleccionada.equalsIgnoreCase("Matematicas");
            boolean Pmatematicas = jsrango.getValue() == AT.getMatematicas();

            boolean Cnaturales = JLAreas.isSelectionEmpty() || areaSeleccionada.equalsIgnoreCase("Ciencias Naturales");
            boolean PCnaturales = jsrango.getValue() == AT.getCnaturales();

            boolean Csociales = JLAreas.isSelectionEmpty() || areaSeleccionada.equalsIgnoreCase("Sociales y ciudadanias");
            boolean PCsociales = jsrango.getValue() == AT.getCsociales();

            boolean Lcritica = JLAreas.isSelectionEmpty() || areaSeleccionada.equalsIgnoreCase("Lectura critica");
            boolean PLcritica = jsrango.getValue() == AT.getLcritica();

            boolean Ingles = JLAreas.isSelectionEmpty() || areaSeleccionada.equalsIgnoreCase("Ingles");
            boolean PIngles = jsrango.getValue() == AT.getIngles();

            boolean A2018 = JC2018.isSelected() && AT.getAño() == 2018;
            boolean A2019 = JC2019.isSelected() && AT.getAño() == 2019;
            boolean A2020 = JC2020.isSelected() && AT.getAño() == 2020;
           
            boolean global = !jsglobal.isEnabled() || jsglobal.getValue() == AT.getPGlobal();

            if ((A2018 || A2019 || A2020) && cumpleDepartamento && cumpleEstrato && cumpleGenero && cumplenaturalidad) {
                if (global) {
                    aux.add(AT);
                } else {
                    if ((Matematicas && Pmatematicas) || (Cnaturales && PCnaturales)
                            || (Lcritica && PLcritica) || (Csociales && PCsociales) || (Ingles && PIngles)) {
                        aux.add(AT);
                    }
                }
            }
        }

        for (int i = 0; i < aux.size(); i++) {
            AT = aux.get(i);
            if (AT.getAño() == 2018) {
                cont2018++;
            }
            if (AT.getAño() == 2019) {
                cont2019++;
            }
            if (AT.getAño() == 2020) {
                cont2020++;
            }

        }

        contGraficas++;

        switch (contGraficas) {

            case 1:
                grafico1();

                break;

            case 2:
                grafico2();

                break;
            case 3:
                grafico3();
                break;
            case 4:
                grafico4();
                break;

            case 5:
                grafico5();
                break;

            case 6:
                grafico6();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Limpia la interfaz para poder continuar");
                jbLimpiar.setEnabled(true);
        }
    }

    public void grafico1() {
        DefaultPieDataset data = new DefaultPieDataset();
        String titulo = "";

        if (jsglobal.getValue() == 0) {
            String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
            String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
            String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
            String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
            String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
            String rango = Objects.toString(jsrango.getValue());

            StringBuilder sbAños = new StringBuilder();
            if (JC2018.isSelected()) {
                sbAños.append(JC2018.getText()).append("-");
            }
            if (JC2019.isSelected()) {
                sbAños.append(JC2019.getText()).append("-");
            }
            if (JC2020.isSelected()) {
                sbAños.append(JC2020.getText()).append("-");
            }

            String años = sbAños.toString();
            if (sbAños.length() > 0) {
                años = años.substring(0, años.length() - 1);  // Eliminar el último guión
            }

            titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                    + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                    + "\nRango: " + rango + "\nPuntaje global: Todos" + "\nAños: " + años;
        } else {
            if (jsrango.getValue() == 0) {
                String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
                String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
                String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
                String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
                String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
                String rango = "Todos";
                String global = Objects.toString(jsglobal.getValue());

                StringBuilder sbAños = new StringBuilder();
                if (JC2018.isSelected()) {
                    sbAños.append(JC2018.getText()).append("-");
                }
                if (JC2019.isSelected()) {
                    sbAños.append(JC2019.getText()).append("-");
                }
                if (JC2020.isSelected()) {
                    sbAños.append(JC2020.getText()).append("-");
                }

                String años = sbAños.toString();
                if (sbAños.length() > 0) {
                    años = años.substring(0, años.length() - 1);  // Eliminar el último guión
                }

                titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                        + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                        + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;
            }
        }

        data.setValue("2018", cont2018);
        data.setValue("2019", cont2019);
        data.setValue("2020", cont2020);

        String chartTitle = titulo;
        if (JCconsultaFIJA1.isSelected()) {
            chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
        } else if (JCconsultaFIJA2.isSelected()) {
            chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
        } else if (JCconsultaFIJA3.isSelected()) {
            chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
        } else if (JCconsultaFIJA4.isSelected()) {
            chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
        } else if (JCconsultaFIJA5.isSelected()) {
            chartTitle = "Hombres del valle del cauca que presentaron el icfes";
        }

        chart = ChartFactory.createPieChart3D(
                chartTitle, // chart title
                data, // data
                false, // include legend
                true,
                false
        );

        chart.setBackgroundPaint(new Color(112, 146, 190));//Color de fondo de la ventana
        chart.getTitle().setPaint(Color.white); //Dar color al titulo
        TextTitle title = chart.getTitle();
        Font font = title.getFont(); // Obtener la fuente actual
        Font modifiedFont = new Font(font.getName(), Font.BOLD, 13); // Crear una nueva fuente modificada
        title.setFont(modifiedFont); // Establecer la nueva fuente

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelBackgroundPaint(Color.ORANGE);//Color de las etiquetas
        plot.setForegroundAlpha(0.60f);//Color de el fondo del grafico

        graficoCFija = new ChartPanel(chart, false);

        graficoCFija.setBounds(10, 25, 280, 290);
        this.der.add(graficoCFija);

        der.repaint();
    }

    public void grafico2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (jsglobal.getValue() == 0) {

            String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
            String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
            String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
            String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
            String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
            String rango = Objects.toString(jsrango.getValue());
            String global = "Todos";
            StringBuilder sbAños = new StringBuilder();
            if (JC2018.isSelected()) {
                sbAños.append(JC2018.getText()).append("-");
            }
            if (JC2019.isSelected()) {
                sbAños.append(JC2019.getText()).append("-");
            }
            if (JC2020.isSelected()) {
                sbAños.append(JC2020.getText()).append("-");
            }

            String años = sbAños.toString();
            if (sbAños.length() > 0) {
                años = años.substring(0, años.length() - 1);  // Eliminar el último guión
            }

            String titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                    + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                    + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;

            dataset.setValue(cont2018, "2018", "2018");
            dataset.setValue(cont2019, "2019", "2019");
            dataset.setValue(cont2020, "2020", "2020");

            String chartTitle = titulo;
            if (JCconsultaFIJA1.isSelected()) {
                chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
            } else if (JCconsultaFIJA2.isSelected()) {
                chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
            } else if (JCconsultaFIJA3.isSelected()) {
                chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
            } else if (JCconsultaFIJA4.isSelected()) {
                chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
            } else if (JCconsultaFIJA5.isSelected()) {
                chartTitle = "Hombres del valle del cauca que presentaron el icfes";
            }

            chart = ChartFactory.createBarChart3D(
                    chartTitle, //Nombre de la grafica
                    "", //Nombre del eje Horizontal
                    "", //Nombre del eje vertical
                    dataset, //Data
                    PlotOrientation.VERTICAL, //Orientacion HORIZONTAL o VERTICAL
                    false, //Incluir leyenda
                    true, //Informacion al pasar el mouse
                    true);                      //URls

        } else {

            if (jsrango.getValue() == 0) {
                String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
                String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
                String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
                String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
                String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
                String rango = "Todos";
                String global = Objects.toString(jsglobal.getValue());

                StringBuilder sbAños = new StringBuilder();
                if (JC2018.isSelected()) {
                    sbAños.append(JC2018.getText()).append("-");
                }
                if (JC2019.isSelected()) {
                    sbAños.append(JC2019.getText()).append("-");
                }
                if (JC2020.isSelected()) {
                    sbAños.append(JC2020.getText()).append("-");
                }

                String años = sbAños.toString();
                if (sbAños.length() > 0) {
                    años = años.substring(0, años.length() - 1);  // Eliminar el último guión
                }

                String titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                        + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                        + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;

                dataset.setValue(cont2018, "2018", "2018");
                dataset.setValue(cont2019, "2019", "2019");
                dataset.setValue(cont2020, "2020", "2020");

                String chartTitle = titulo;
                if (JCconsultaFIJA1.isSelected()) {
                    chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
                } else if (JCconsultaFIJA2.isSelected()) {
                    chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
                } else if (JCconsultaFIJA3.isSelected()) {
                    chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
                } else if (JCconsultaFIJA4.isSelected()) {
                    chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
                } else if (JCconsultaFIJA5.isSelected()) {
                    chartTitle = "Hombres del valle del cauca que presentaron el icfes";
                }

                chart = ChartFactory.createBarChart3D(
                        chartTitle, //Nombre de la grafica
                        "", //Nombre del eje Horizontal
                        "", //Nombre del eje vertical
                        dataset, //Data
                        PlotOrientation.VERTICAL, //Orientacion HORIZONTAL o VERTICAL
                        false, //Incluir leyenda
                        true, //Informacion al pasar el mouse
                        true);                      //URls

            }
        }

        chart.setBackgroundPaint(Color.GRAY);//Dar color al fondo del panel
        chart.getTitle().setPaint(Color.WHITE);//Dar color al titulo 

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);//Color del fondo del gr�fico

        plot.setDomainGridlinesVisible(true);//Lineas divisorias
        plot.setRangeGridlinePaint(Color.BLACK);//Color de lineas divisorias	    

        //Calculo de los valores en el eje x
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);//Asignar color de linea a las barras

        //Dar color a las barras
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

        TextTitle title = chart.getTitle();
        Font font = title.getFont(); // Obtener la fuente actual
        Font modifiedFont = new Font(font.getName(), Font.BOLD, 13); // Crear una nueva fuente modificada
        title.setFont(modifiedFont); // Establecer la nueva fuente

        graficoCFija = new ChartPanel(chart, false);
        graficoCFija.setBounds(300, 25, 280, 290);
        der.add(graficoCFija);
        der.repaint();
    }

    public void grafico3() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (jsglobal.getValue() == 0) {

            String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
            String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
            String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
            String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
            String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
            String rango = Objects.toString(jsrango.getValue());
            String global = "Todos";

            StringBuilder sbAños = new StringBuilder();
            if (JC2018.isSelected()) {
                sbAños.append(JC2018.getText()).append("-");
            }
            if (JC2019.isSelected()) {
                sbAños.append(JC2019.getText()).append("-");
            }
            if (JC2020.isSelected()) {
                sbAños.append(JC2020.getText()).append("-");
            }

            String años = sbAños.toString();
            if (sbAños.length() > 0) {
                años = años.substring(0, años.length() - 1);  // Eliminar el último guión
            }

            String titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                    + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                    + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;

            dataset.addValue(cont2018, "", "2018");//ventas de Mazda de Valeria
            dataset.addValue(cont2019, "", "2019");//ventas de Mazda de Sebastian
            dataset.addValue(cont2020, "", "2020");//ventas de Mazda de Valentina
            dataset.addValue(cont2018 + cont2019 + cont2020, "", "Total");

            String chartTitle = titulo;
            if (JCconsultaFIJA1.isSelected()) {
                chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
            } else if (JCconsultaFIJA2.isSelected()) {
                chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
            } else if (JCconsultaFIJA3.isSelected()) {
                chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
            } else if (JCconsultaFIJA4.isSelected()) {
                chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
            } else if (JCconsultaFIJA5.isSelected()) {
                chartTitle = "Hombres del valle del cauca que presentaron el icfes";
            }

            chart = ChartFactory.createWaterfallChart(chartTitle,
                    "",
                    "",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false, true, false);

        } else {

            if (jsrango.getValue() == 0) {
                String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
                String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
                String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
                String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
                String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
                String rango = "Todos";
                String global = Objects.toString(jsglobal.getValue());

                StringBuilder sbAños = new StringBuilder();
                if (JC2018.isSelected()) {
                    sbAños.append(JC2018.getText()).append("-");
                }
                if (JC2019.isSelected()) {
                    sbAños.append(JC2019.getText()).append("-");
                }
                if (JC2020.isSelected()) {
                    sbAños.append(JC2020.getText()).append("-");
                }

                String años = sbAños.toString();
                if (sbAños.length() > 0) {
                    años = años.substring(0, años.length() - 1);  // Eliminar el último guión
                }

                String titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                        + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                        + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;

                dataset.addValue(cont2018, "", "2018");//ventas de Mazda de Valeria
                dataset.addValue(cont2019, "", "2019");//ventas de Mazda de Sebastian
                dataset.addValue(cont2020, "", "2020");//ventas de Mazda de Valentina
                dataset.addValue(cont2018 + cont2019 + cont2020, "", "Total");

                String chartTitle = titulo;
                if (JCconsultaFIJA1.isSelected()) {
                    chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
                } else if (JCconsultaFIJA2.isSelected()) {
                    chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
                } else if (JCconsultaFIJA3.isSelected()) {
                    chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
                } else if (JCconsultaFIJA4.isSelected()) {
                    chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
                } else if (JCconsultaFIJA5.isSelected()) {
                    chartTitle = "Hombres del valle del cauca que presentaron el icfes";
                }

                chart = ChartFactory.createWaterfallChart(chartTitle,
                        "",
                        "",
                        dataset,
                        PlotOrientation.VERTICAL,
                        false, true, false);

            }
        }

        CategoryPlot p = chart.getCategoryPlot();
        //p.setRangeGridlinePaint(Color.red);
        p.setDomainGridlinesVisible(true);
        p.setDomainGridlinePaint(Color.black);
        chart.setBackgroundPaint(new Color(112, 146, 190));//Color de fonde de la ventana
        chart.getTitle().setPaint(Color.white); //Dar color al titulo

        TextTitle title = chart.getTitle();
        Font font = title.getFont(); // Obtener la fuente actual
        Font modifiedFont = new Font(font.getName(), Font.BOLD, 13); // Crear una nueva fuente modificada
        title.setFont(modifiedFont); // Establecer la nueva fuente

        graficoCFija = new ChartPanel(chart, false);
        graficoCFija.setBounds(590, 25, 280, 290);
        der.add(graficoCFija);
        der.repaint();

    }

    public void grafico4() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (jsglobal.getValue() == 0) {

            String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
            String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
            String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
            String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
            String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
            String rango = Objects.toString(jsrango.getValue());
            String global = "Todos";

            StringBuilder sbAños = new StringBuilder();
            if (JC2018.isSelected()) {
                sbAños.append(JC2018.getText()).append("-");
            }
            if (JC2019.isSelected()) {
                sbAños.append(JC2019.getText()).append("-");
            }
            if (JC2020.isSelected()) {
                sbAños.append(JC2020.getText()).append("-");
            }

            String años = sbAños.toString();
            if (sbAños.length() > 0) {
                años = años.substring(0, años.length() - 1);  // Eliminar el último guión
            }

            String titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                    + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                    + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;

            dataset.setValue(cont2018, "2018", "2018");
            dataset.setValue(cont2019, "2019", "2019");
            dataset.setValue(cont2020, "2020", "2020");

            String chartTitle = titulo;
            if (JCconsultaFIJA1.isSelected()) {
                chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
            } else if (JCconsultaFIJA2.isSelected()) {
                chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
            } else if (JCconsultaFIJA3.isSelected()) {
                chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
            } else if (JCconsultaFIJA4.isSelected()) {
                chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
            } else if (JCconsultaFIJA5.isSelected()) {
                chartTitle = "Hombres del valle del cauca que presentaron el icfes";
            }

            chart = ChartFactory.createBarChart3D(
                    chartTitle, //Nombre de la grafica
                    "", //Nombre del eje Horizontal
                    "", //Nombre del eje vertical
                    dataset, //Data
                    PlotOrientation.VERTICAL, //Orientacion HORIZONTAL o VERTICAL
                    false, //Incluir leyenda
                    true, //Informacion al pasar el mouse
                    true);                      //URls

        } else {

            if (jsrango.getValue() == 0) {
                String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
                String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
                String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
                String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
                String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
                String rango = "Todos";
                String global = Objects.toString(jsglobal.getValue());

                StringBuilder sbAños = new StringBuilder();
                if (JC2018.isSelected()) {
                    sbAños.append(JC2018.getText()).append("-");
                }
                if (JC2019.isSelected()) {
                    sbAños.append(JC2019.getText()).append("-");
                }
                if (JC2020.isSelected()) {
                    sbAños.append(JC2020.getText()).append("-");
                }

                String años = sbAños.toString();
                if (sbAños.length() > 0) {
                    años = años.substring(0, años.length() - 1);  // Eliminar el último guión
                }

                String titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                        + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                        + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;

                dataset.setValue(cont2018, "2018", "2018");
                dataset.setValue(cont2019, "2019", "2019");
                dataset.setValue(cont2020, "2020", "2020");

                String chartTitle = titulo;
                if (JCconsultaFIJA1.isSelected()) {
                    chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
                } else if (JCconsultaFIJA2.isSelected()) {
                    chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
                } else if (JCconsultaFIJA3.isSelected()) {
                    chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
                } else if (JCconsultaFIJA4.isSelected()) {
                    chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
                } else if (JCconsultaFIJA5.isSelected()) {
                    chartTitle = "Hombres del valle del cauca que presentaron el icfes";
                }

                chart = ChartFactory.createBarChart3D(
                        chartTitle, //Nombre de la grafica
                        "", //Nombre del eje Horizontal
                        "", //Nombre del eje vertical
                        dataset, //Data
                        PlotOrientation.VERTICAL, //Orientacion HORIZONTAL o VERTICAL
                        false, //Incluir leyenda
                        true, //Informacion al pasar el mouse
                        true);                      //URls

            }
        }

        chart.setBackgroundPaint(Color.gray);//Color de fonde de la ventana
        chart.getTitle().setPaint(Color.white); //Dar color al titulo

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);//Color del fondo del gr�fico

        plot.setDomainGridlinesVisible(true);//Lineas divisorias
        plot.setRangeGridlinePaint(Color.BLACK);//Color de lineas divisorias	    

        //Calculo de los valores en el eje x
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);//Asignar color de linea a las barras

        //Dar color a las barras
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

        TextTitle title = chart.getTitle();
        Font font = title.getFont(); // Obtener la fuente actual
        Font modifiedFont = new Font(font.getName(), Font.BOLD, 13); // Crear una nueva fuente modificada
        title.setFont(modifiedFont); // Establecer la nueva fuente   
        graficoCFija = new ChartPanel(chart, false);
        graficoCFija.setBounds(10, 335, 280, 290);
        der.add(graficoCFija);
        der.repaint();
    }

    public void grafico5() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (jsglobal.getValue() == 0) {

            String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
            String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
            String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
            String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
            String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
            String rango = Objects.toString(jsrango.getValue());
            String global = "Todos";

            StringBuilder sbAños = new StringBuilder();
            if (JC2018.isSelected()) {
                sbAños.append(JC2018.getText()).append("-");
            }
            if (JC2019.isSelected()) {
                sbAños.append(JC2019.getText()).append("-");
            }
            if (JC2020.isSelected()) {
                sbAños.append(JC2020.getText()).append("-");
            }

            String años = sbAños.toString();
            if (sbAños.length() > 0) {
                años = años.substring(0, años.length() - 1);  // Eliminar el último guión
            }

            String titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                    + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                    + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;

            dataset.addValue(cont2018, "", "2018");//ventas de Mazda de Valeria
            dataset.addValue(cont2019, "", "2019");//ventas de Mazda de Sebastian
            dataset.addValue(cont2020, "", "2020");//ventas de Mazda de Valentina
            dataset.addValue(cont2018 + cont2019 + cont2020, "", "Total");

            String chartTitle = titulo;
            if (JCconsultaFIJA1.isSelected()) {
                chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
            } else if (JCconsultaFIJA2.isSelected()) {
                chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
            } else if (JCconsultaFIJA3.isSelected()) {
                chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
            } else if (JCconsultaFIJA4.isSelected()) {
                chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
            } else if (JCconsultaFIJA5.isSelected()) {
                chartTitle = "Hombres del valle del cauca que presentaron el icfes";
            }

            chart = ChartFactory.createWaterfallChart(chartTitle,
                    "",
                    "",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false, true, false);

        } else {

            if (jsrango.getValue() == 0) {
                String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
                String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
                String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
                String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
                String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
                String rango = "Todos";
                String global = Objects.toString(jsglobal.getValue());

                StringBuilder sbAños = new StringBuilder();
                if (JC2018.isSelected()) {
                    sbAños.append(JC2018.getText()).append("-");
                }
                if (JC2019.isSelected()) {
                    sbAños.append(JC2019.getText()).append("-");
                }
                if (JC2020.isSelected()) {
                    sbAños.append(JC2020.getText()).append("-");
                }

                String años = sbAños.toString();
                if (sbAños.length() > 0) {
                    años = años.substring(0, años.length() - 1);  // Eliminar el último guión
                }

                String titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                        + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                        + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;

                dataset.addValue(cont2018, "", "2018");//ventas de Mazda de Valeria
                dataset.addValue(cont2019, "", "2019");//ventas de Mazda de Sebastian
                dataset.addValue(cont2020, "", "2020");//ventas de Mazda de Valentina
                dataset.addValue(cont2018 + cont2019 + cont2020, "", "Total");

                String chartTitle = titulo;
                if (JCconsultaFIJA1.isSelected()) {
                    chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
                } else if (JCconsultaFIJA2.isSelected()) {
                    chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
                } else if (JCconsultaFIJA3.isSelected()) {
                    chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
                } else if (JCconsultaFIJA4.isSelected()) {
                    chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
                } else if (JCconsultaFIJA5.isSelected()) {
                    chartTitle = "Hombres del valle del cauca que presentaron el icfes";
                }

                chart = ChartFactory.createWaterfallChart(chartTitle,
                        "",
                        "",
                        dataset,
                        PlotOrientation.VERTICAL,
                        false, true, false);

            }
        }

        CategoryPlot p = chart.getCategoryPlot();
        //p.setRangeGridlinePaint(Color.red);
        p.setDomainGridlinesVisible(true);
        p.setDomainGridlinePaint(Color.black);

        chart.setBackgroundPaint(new Color(112, 146, 190));//Color de fonde de la ventana
        chart.getTitle().setPaint(Color.white); //Dar color al titulo

        TextTitle title = chart.getTitle();
        Font font = title.getFont(); // Obtener la fuente actual
        Font modifiedFont = new Font(font.getName(), Font.BOLD, 13); // Crear una nueva fuente modificada
        title.setFont(modifiedFont); // Establecer la nueva fuente

        graficoCFija = new ChartPanel(chart, false);
        graficoCFija.setBounds(300, 335, 280, 290);
        der.add(graficoCFija);
        der.repaint();

    }

    public void grafico6() {
        DefaultPieDataset data = new DefaultPieDataset();

        if (jsglobal.getValue() == 0) {

            String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
            String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
            String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
            String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
            String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
            String rango = Objects.toString(jsrango.getValue());
            String global = "Todos";

            StringBuilder sbAños = new StringBuilder();
            if (JC2018.isSelected()) {
                sbAños.append(JC2018.getText()).append("-");
            }
            if (JC2019.isSelected()) {
                sbAños.append(JC2019.getText()).append("-");
            }
            if (JC2020.isSelected()) {
                sbAños.append(JC2020.getText()).append("-");
            }

            String años = sbAños.toString();
            if (sbAños.length() > 0) {
                años = años.substring(0, años.length() - 1);  // Eliminar el último guión
            }

            String titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                    + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                    + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;

            data.setValue("2018", cont2018);
            data.setValue("2019", cont2019);
            data.setValue("2020", cont2020);

            String chartTitle = titulo;
            if (JCconsultaFIJA1.isSelected()) {
                chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
            } else if (JCconsultaFIJA2.isSelected()) {
                chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
            } else if (JCconsultaFIJA3.isSelected()) {
                chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
            } else if (JCconsultaFIJA4.isSelected()) {
                chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
            } else if (JCconsultaFIJA5.isSelected()) {
                chartTitle = "Hombres del valle del cauca que presentaron el icfes";
            }

            chart = ChartFactory.createPieChart3D(
                    chartTitle, // chart title
                    data, // data
                    false, // include legend
                    true,
                    false
            );

        } else {

            if (jsrango.getValue() == 0) {
                String departamentoSeleccionado = Objects.toString(JLdepartamento.getSelectedValue(), "Todos");
                String areaSeleccionada = Objects.toString(JLAreas.getSelectedValue(), "Todas");
                String estratoSeleccionado = Objects.toString(JLestrato.getSelectedValue(), "Todos");
                String generoSeleccionado = Objects.toString(JLgenero.getSelectedValue(), "Ambos");
                String naturalidadSeleccionado = Objects.toString(JLnaturaliad.getSelectedValue(), "Ambos");
                String rango = "Todos";
                String global = Objects.toString(jsglobal.getValue());

                StringBuilder sbAños = new StringBuilder();
                if (JC2018.isSelected()) {
                    sbAños.append(JC2018.getText()).append("-");
                }
                if (JC2019.isSelected()) {
                    sbAños.append(JC2019.getText()).append("-");
                }
                if (JC2020.isSelected()) {
                    sbAños.append(JC2020.getText()).append("-");
                }

                String años = sbAños.toString();
                if (sbAños.length() > 0) {
                    años = años.substring(0, años.length() - 1);  // Eliminar el último guión
                }

                String titulo = "Departamento: " + departamentoSeleccionado + "\nEstrato: " + estratoSeleccionado
                        + "\nGenero: " + generoSeleccionado + "\nNaturalidad: " + naturalidadSeleccionado + "\nArea: " + areaSeleccionada
                        + "\nRango: " + rango + "\nPuntaje global: " + global + "\nAños: " + años;

                data.setValue("2018", cont2018);
                data.setValue("2019", cont2019);
                data.setValue("2020", cont2020);

                String chartTitle = titulo;
                if (JCconsultaFIJA1.isSelected()) {
                    chartTitle = "Percentil hombres del Huila mayor a 70 en L.critica";
                } else if (JCconsultaFIJA2.isSelected()) {
                    chartTitle = "Percentil mujeres de Antioquia mayor a 90 en C.naturales";
                } else if (JCconsultaFIJA3.isSelected()) {
                    chartTitle = "Hombres de colegio oficial que obtuvieron A2 en ingles";
                } else if (JCconsultaFIJA4.isSelected()) {
                    chartTitle = "Mujeres de colegio no oficial que obtuvieron A2 en ingles";
                } else if (JCconsultaFIJA5.isSelected()) {
                    chartTitle = "Hombres del valle del cauca que presentaron el icfes";
                }

                chart = ChartFactory.createPieChart3D(
                        chartTitle, // chart title
                        data, // data
                        false, // include legend
                        true,
                        false
                );

            }
        }

        chart.setBackgroundPaint(Color.gray);//Color de fonde de la ventana
        chart.getTitle().setPaint(Color.white); //Dar color al titulo

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelBackgroundPaint(Color.ORANGE);//Color de las etiquetas
        plot.setForegroundAlpha(0.60f);//Color de el fondo del grafico

        TextTitle title = chart.getTitle();
        Font font = title.getFont(); // Obtener la fuente actual
        Font modifiedFont = new Font(font.getName(), Font.BOLD, 13); // Crear una nueva fuente modificada
        title.setFont(modifiedFont); // Establecer la nueva fuente

        graficoCFija = new ChartPanel(chart, false);
        graficoCFija.setBounds(590, 335, 280, 290);
        der.add(graficoCFija);
        der.repaint();
    }

    public static void main(String[] args) {

        new Dashboard_ICFES();
    }
}
