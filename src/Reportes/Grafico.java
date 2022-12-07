package Reportes;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Grafico {
    public static void Graficar(String fecha) {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "SELECT total FROM venta WHERE fecha = ?";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            
            DefaultPieDataset dataset = new DefaultPieDataset();
            
            int venta = 0;
            while(rs.next()) {
                dataset.setValue("Venta " + ++venta + ": $" + rs.getString("total"),rs.getDouble("total"));
            }
            
            JFreeChart jf = ChartFactory.createPieChart("Reporte de Ventas Diarias", dataset);
            ChartFrame f = new ChartFrame("Total de Ventas por dias", jf);
            f.setSize(1000, 500);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}