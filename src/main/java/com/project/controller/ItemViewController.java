package com.project.controller;

import java.awt.event.*;
import javax.swing.JOptionPane;

import com.project.model.Material;
import com.project.model.Model;
import com.project.model.Part;
import com.project.view.DashboardView;
import com.project.view.ItemView;
import com.project.view.LoginView;

public class ItemViewController implements ActionListener{
        public Model model;
        private LoginView loginView;
        private ItemView itemView;
        private DashboardView dashboard;
        private Part part;
        private Material material;

        public ItemViewController(Model model, LoginView loginView, ItemView itemView, DashboardView dashboard) {
            this.model = model;
            this.loginView = loginView;
            this.itemView = itemView;
            this.dashboard = dashboard;

            this.itemView.initializeComponents(this);
            
        }

         // method to get information of a part or material by its ID and display it
        public void displayItem(int partId, String searchType) {
            Object item = model.searchByID(partId,searchType); //return object
            if (item instanceof Part) {
                Part part = (Part) item;
                itemView.updatePartDetails(part);

            } else if (item instanceof Material) {
                Material material = (Material) item;
                itemView.updateMaterialDetails(material);
            }
        }

        // method to get information of a part or material by its name and display it
        public void displayItemByName(String partName, String searchType) {
            Object item = model.searchByName(partName,searchType); //return object
            if (item instanceof Part) {
                Part part = (Part) item;
                itemView.updatePartDetails(part);

            } else if (item instanceof Material) {
                Material material = (Material) item;
                itemView.updateMaterialDetails(material);
            }
        }
        // button click events for the item view page
        public void actionPerformed(ActionEvent e) {
        
            // back button click event. Returnst to dashboard
            if (e.getSource().equals(itemView.btnReturn)) {
                dashboard.window.setContentPane(dashboard.dashboard);
                dashboard.window.revalidate();
                dashboard.window.repaint();
            }
        }
}
