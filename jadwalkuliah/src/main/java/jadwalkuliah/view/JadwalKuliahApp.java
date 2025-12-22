/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jadwalkuliah.view;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.toedter.calendar.JDateChooser; 
import java.text.SimpleDateFormat;  
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import jadwalkuliah.reminder.ReminderService;
import java.time.LocalTime;

/**
 *
 * @author User
 */
public class JadwalKuliahApp extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(227, 241, 251); // Blue Creame #BBDFFF
    private static final Color ACCENT_COLOR = new Color(255, 255, 255);    // Milky White #FFDDA5
    private static final Color TEXT_COLOR = Color.BLACK;                   // Semua teks hitam
    private static final Color TABLE_SELECTION_COLOR = new Color(248, 187, 208); // Biru muda untuk seleksi
    
    private JTextField txtNama, txtDosen, txtJamMulai, txtJamSelesai;
    private JTextField txtRuang; 
    private JSpinner spSKS;
    private JComboBox<String> cbHari;
    private JDateChooser dcTanggal; 
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblStatus, lblJumlah;
    private JCheckBox cbReminder;


    private int selectedRow = -1;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy"); 

    public JadwalKuliahApp() {
        setTitle("Aplikasi Pengelolaan Jadwal Kuliah");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.add("Form Input", formInputPanel());
        tabPane.add("Kalender View", kalenderPanel()); 

        add(tabPane, BorderLayout.CENTER);
        add(statusBar(), BorderLayout.SOUTH);
        
        applyTheme();
    }
  private void applyTheme() {
        // Warna untuk TabbedPane
        JTabbedPane tabPane = (JTabbedPane) getContentPane().getComponent(0);
        tabPane.setBackground(BACKGROUND_COLOR);
        tabPane.setForeground(TEXT_COLOR);
        // Atur warna untuk setiap tab
        for (int i = 0; i < tabPane.getTabCount(); i++) {
            tabPane.setBackgroundAt(i, BACKGROUND_COLOR);
            tabPane.setForegroundAt(i, TEXT_COLOR);
        }
    }

    private JPanel formInputPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(createTitledBorder("Form Jadwal Kuliah"));
        formPanel.setBackground(BACKGROUND_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        txtNama = new JTextField(30);
        txtDosen = new JTextField(30);
        txtRuang = new JTextField(20);
        spSKS = new JSpinner(new SpinnerNumberModel(2, 1, 6, 1));
        cbHari = new JComboBox<>(new String[]{"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"});
        dcTanggal = new JDateChooser(); // 
        dcTanggal.setDateFormatString("dd-MM-yyyy"); // Format tanggal di text field
        txtJamMulai = new JTextField("08:00", 10);
        txtJamSelesai = new JTextField("10:00", 10);
        cbReminder = new JCheckBox("Aktifkan Reminder");
        
        Color inputBgColor = Color.WHITE;
        txtNama.setBackground(inputBgColor);
        txtDosen.setBackground(inputBgColor);
        txtRuang.setBackground(inputBgColor);
        txtJamMulai.setBackground(inputBgColor);
        txtJamSelesai.setBackground(inputBgColor);
        cbHari.setBackground(inputBgColor);
        spSKS.setBackground(inputBgColor);
        dcTanggal.setBackground(inputBgColor);
        dcTanggal.setDateFormatString("dd-MM-yyyy");
        cbReminder.setForeground(TEXT_COLOR);

        int y = 0;
        addField(formPanel, gbc, y++, "Nama Mata Kuliah:", txtNama);
        addField(formPanel, gbc, y++, "Dosen:", txtDosen);
        addField(formPanel, gbc, y++, "SKS:", spSKS);
        addField(formPanel, gbc, y++, "Hari:", cbHari);
        addField(formPanel, gbc, y++, "Tanggal:", dcTanggal); 
        addField(formPanel, gbc, y++, "Ruang:", txtRuang); 
        addField(formPanel, gbc, y++, "Jam Mulai:", txtJamMulai);
        addField(formPanel, gbc, y++, "Jam Selesai:", txtJamSelesai);
        addField(formPanel, gbc, y++, "Reminder:", cbReminder);

        JButton btnSimpan = new JButton("Simpan Jadwal");
        styleButton(btnSimpan);

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(btnSimpan, gbc);

        tableModel = new DefaultTableModel(
                new String[]{"Tanggal", "Hari", "Jam", "Ruang", "Mata Kuliah", "Dosen", "SKS"}, 0
        );
        table = new JTable(tableModel);
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(createTitledBorder("Daftar Jadwal Kuliah"));
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        
        JButton btnEdit = new JButton("Edit");
        styleButton(btnEdit);

        JButton btnHapus = new JButton("Hapus");
        styleButton(btnHapus);

        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false); // Biarkan transparan agar warna primer kelihatan
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        btnSimpan.addActionListener(e -> simpanJadwal());
        btnEdit.addActionListener(e -> editJadwal());
        btnHapus.addActionListener(e -> hapusJadwal());

        table.getSelectionModel().addListSelectionListener(e -> isiFormDariTabel());

        return panel;
    }

    // KALENDER VIEW 
    private JPanel kalenderPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(createTitledBorder("Kalender Jadwal Kuliah"));

        DefaultTableModel kalenderModel = new DefaultTableModel(
                new String[]{"Tanggal", "Hari", "Jam", "Ruang", "Mata Kuliah", "Dosen", "SKS"}, 0);
        JTable kalenderTable = new JTable(kalenderModel);
        styleTable(kalenderTable);

        JButton btnRefresh = new JButton("Tampilkan ke Kalender");
        styleButton(btnRefresh);

        btnRefresh.addActionListener(e -> {
            kalenderModel.setRowCount(0);
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                kalenderModel.addRow(new Object[]{
                        tableModel.getValueAt(i, 0),
                        tableModel.getValueAt(i, 1),
                        tableModel.getValueAt(i, 2),
                        tableModel.getValueAt(i, 3),
                        tableModel.getValueAt(i, 4),
                        tableModel.getValueAt(i, 5),
                        tableModel.getValueAt(i, 6),

                });
            }
        });

        panel.add(new JScrollPane(kalenderTable), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(btnRefresh);
        panel.add(bottom, BorderLayout.SOUTH);
        
        return panel;
    }
