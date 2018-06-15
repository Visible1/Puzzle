package appswing;


import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;


public class CommunityGUI {

    private JButton getCompaniesLookingForEmployees;
    private JTextField CompanySearchName;
    private JButton getActiveCompetitionsByCompany;
    private JButton getPersonwithHighestNumberOfProblemsSolved;
    private JButton getProblemsSolvedByMe;
    private JPanel Community;

    public CommunityGUI() {

           getCompaniesLookingForEmployees.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){
            try {
                ResultSet rs = SQL.getCompaniesLookingForEmployees(SQL.con);
                JTable table = new JTable(MainForm.buildTableModel(rs));
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
            } catch (Exception ex) {
                String errorMessage = ex.getMessage();
                JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Incorrect Script ", JOptionPane.ERROR_MESSAGE);
            }

        }

    });



        getActiveCompetitionsByCompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String CompanyName = CompanySearchName.getText();

                if (companyName != null && CompanyName.trim().length() > 0) {
                    try {
                        ResultSet rs = SQL.getActiveCompetitionsByCompany(SQL.con);
                        JTable table = new JTable(MainForm.buildTableModel(rs));

                        JOptionPane.showMessageDialog(null, new JScrollPane(table));

                    } catch (Exception ex) {
                        String errorMessage = ex.getMessage();
                        JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Incorrect Script ", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });


        getPersonwithHighestNumberOfProblemsSolved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = SQL.getPersonwithHighestNumberOfProblemsSolved(SQL.con);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));
                } catch (Exception ex) {
                    String errorMessage = ex.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Incorrect Script ", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        getProblemsSolvedByMe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = SQL.getProblemsSolvedByMe(SQL.con);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));
                } catch (Exception ex) {
                    String errorMessage = ex.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Incorrect Script ", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }


  managerFormButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Manager Search Form");
            frame.setContentPane(new ManagerForm().ManagerFormPanel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        }
    });


}













