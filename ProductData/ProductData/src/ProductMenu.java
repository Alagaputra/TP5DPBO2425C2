import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductMenu extends JFrame {

    public static void main(String[] args) {
        ProductMenu menu = new ProductMenu();
        menu.setSize(700, 800);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.WHITE);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int selectedIndex = -1;
    private Database database;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel namaLabel;
    private javax.swing.JLabel hargaLabel;
    private javax.swing.JLabel kategoriLabel;
    private javax.swing.JLabel promoLabel;


    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JRadioButton iyaRadioButton;
    private JRadioButton tidakRadioButton;
    private ButtonGroup promoGroup;

    public ProductMenu() {
        database = new Database();

        productTable.setModel(setTable());
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        String[] kategoriData = {"???", "Elektronik", "Aksesoris", "Penyimpanan", "Jaringan", "Perangkat Kantor", "Furniture"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        deleteButton.setVisible(false);

        promoGroup = new ButtonGroup();
        promoGroup.add(iyaRadioButton);
        promoGroup.add(tidakRadioButton);
        tidakRadioButton.setSelected(true);

        // === Tombol tambah/update ===
        addUpdateButton.addActionListener(e -> {
            if (selectedIndex == -1) insertData();
            else updateData();
        });

        // === Tombol hapus ===
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Yakin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) deleteData();
        });

        // === Tombol batal/reset ===
        cancelButton.addActionListener(e -> clearForm());

        // === Klik baris tabel ===
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = productTable.getSelectedRow();

                String curId = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curNama = productTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curHarga = productTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curKategori = productTable.getModel().getValueAt(selectedIndex, 4).toString();
                String curPromo = productTable.getModel().getValueAt(selectedIndex, 5).toString();

                idField.setText(curId);
                namaField.setText(curNama);
                hargaField.setText(curHarga);
                kategoriComboBox.setSelectedItem(curKategori);

                if (curPromo.equals("Ya")) iyaRadioButton.setSelected(true);
                else tidakRadioButton.setSelected(true);

                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    // === Menampilkan data tabel dari database ===
    public final DefaultTableModel setTable() {
        Object[] cols = {"No", "ID Produk", "Nama", "Harga", "Kategori", "Promo"};
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        try {
            ResultSet rs = database.selectQuery("SELECT * FROM product");
            int i = 0;
            while (rs.next()) {
                Object[] row = {
                        ++i,
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga"),
                        rs.getString("kategori"),
                        rs.getBoolean("promo") ? "Ya" : "Tidak"
                };
                tmp.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data dari database!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return tmp;
    }

    // === INSERT ===
    public void insertData() {
        String id = idField.getText().trim();
        String nama = namaField.getText().trim();
        String hargaText = hargaField.getText().trim();
        String kategori = kategoriComboBox.getSelectedItem().toString();
        boolean promo = iyaRadioButton.isSelected();

        if (id.isEmpty() || nama.isEmpty() || hargaText.isEmpty() || kategori.equals("???")) {
            JOptionPane.showMessageDialog(null, "Semua kolom wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ResultSet check = database.selectQuery("SELECT * FROM product WHERE id='" + id + "'");
            if (check.next()) {
                JOptionPane.showMessageDialog(null, "ID \"" + id + "\" sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double harga = Double.parseDouble(hargaText);
            String sql = "INSERT INTO product (id, nama, harga, kategori, promo) VALUES ('" + id + "', '" + nama + "', " + harga + ", '" + kategori + "', " + (promo ? 1 : 0) + ")";
            database.insertUpdateDeleteQuery(sql);

            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menambahkan data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // === UPDATE ===
    public void updateData() {
        if (selectedIndex == -1) return;

        String id = idField.getText().trim();
        String nama = namaField.getText().trim();
        String hargaText = hargaField.getText().trim();
        String kategori = kategoriComboBox.getSelectedItem().toString();
        boolean promo = iyaRadioButton.isSelected();

        if (id.isEmpty() || nama.isEmpty() || hargaText.isEmpty() || kategori.equals("???")) {
            JOptionPane.showMessageDialog(null, "Semua kolom wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double harga = Double.parseDouble(hargaText);
            String sql = "UPDATE product SET nama='" + nama + "', harga=" + harga +
                    ", kategori='" + kategori + "', promo=" + (promo ? 1 : 0) +
                    " WHERE id='" + id + "'";
            database.insertUpdateDeleteQuery(sql);

            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengupdate data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // === DELETE ===
    public void deleteData() {
        if (selectedIndex == -1) return;
        String id = idField.getText().trim();

        try {
            database.insertUpdateDeleteQuery("DELETE FROM product WHERE id='" + id + "'");
            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // === CLEAR FORM ===
    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
        tidakRadioButton.setSelected(true);
    }
}
