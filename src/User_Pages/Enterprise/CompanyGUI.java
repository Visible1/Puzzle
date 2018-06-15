package User_Pages.Company;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;


public class CompanyGUI {
    private JButton goToAdvertisementCreationWindow; //  is an insert into the advertisements table.
    private JButton getNumberOfQuestionsSolvedBySpecifiedUser; // search user
    private JButton getUsersLookingForJobs;
    private JTextField searchUserTextField;
    private JButton getTopFiveUsersByRank;
    private JButton getPersonwithHighestNumberOfProblemsSolved;
    private JButton updateContestStatus;
    private JTextField updateContest;
    private JTextField updateStatus;


    private JPanel Company;

    public CompanyGUI() {

        getUsersLookingForJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = SQL.getUsersLookingForJobs(SQL.con);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));
                } catch (Exception ex) {
                    String errorMessage = ex.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Incorrect Script ", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        getNumberOfQuestionsSolvedBySpecifiedUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nameOfUser = searchUserTextField.getText();

                if (nameOfUser != null && nameOfUser.trim().length() > 0) {
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


        getTopFiveUsersByRank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = SQL.getTopFiveUsersByRank(SQL.con);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));
                } catch (Exception ex) {
                    String errorMessage = ex.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Incorrect Script ", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        goToAdvertisementCreationWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainForm.createAdvertisementGUI();
            }
        });


        // update status of advertisement or contest

        updateContestStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contestToUpdate = updateContest.getText();
                String updated = updateStatus.getText();

                if (contestToUpdate != null && updated.trim().length() > 0) {
                    if (updated != null && updated.trim().length() > 0 && (updated == "TRUE" || updated == "FALSE")) {
                        try {
                            ResultSet rs = SQL.updateContestStatus(SQL.con);
                            JTable table = new JTable(MainForm.buildTableModel(rs));

                            JOptionPane.showMessageDialog(null, new JScrollPane(table));

                        } catch (Exception ex) {
                            String errorMessage = ex.getMessage();
                            JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Incorrect Script ", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                //else print to screen that is it incorrect.
            }
        });
    }













