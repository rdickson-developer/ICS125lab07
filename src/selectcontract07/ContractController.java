/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selectcontract07;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author ryandickson
 */
class ContractController {

    private ContractView theView;
    private ContractModel theModel;
    private NewContract theContracts;

    ContractController(ContractView theView, ContractModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theContracts = new NewContract(theView, true);

        this.theView.addPrevListener(new PrevButtonListener());
        this.theView.addBidListener(new BidButtonListener());
        this.theView.addNextListener(new NextButtonListener());
        this.theView.addMenuItemAddNewListener(new MenuItemAddNewContractListener());
        this.theView.addMenuItemQuitListener(new MenuItemQuitListener());

        this.theView.addComboBoxListener(new ComboListener());

        this.theContracts.addSaveListener(new SaveButtonListener());

        theView.setOriginCityList(theModel.getOriginCityList());
        setUpDisplay();
    }

    public void setUpDisplay() {
        try {
            if (theModel.foundContracts()) {

                Contract c = theModel.getTheContract();

                theView.setContractID(c.getContractID());
                theView.setDestCity(c.getDestCity());
                theView.setOriginCity(c.getOriginCity());
                theView.setOrderItem(c.getOrderItem());

            } else {
                theView.setContractID("???");
                theView.setDestCity("???");
                theView.setOriginCity("???");
                theView.setOrderItem("???");
            }
        } catch (Error ex) {
            System.out.println(ex);
            theView.displayErrorMessage(
                    "Error: There was a problem setting the contract.\n"
                    + "             Contract number: " + theModel.getTheContract());

        }
        theView.updateContractViewPanel(theModel.getCurrentContractNum(), theModel.getContractCount());
    }

    class MenuItemQuitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Quit clicked");
            System.exit(0);
        }
    }

    class MenuItemAddNewContractListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("New Contract Menu Item clicked");
            try {
                NewContract nc;
//                nc = new NewContract(theView, true);
                nc = theContracts;
                nc.setLocationRelativeTo(null);
                nc.setVisible(true);
            } catch (Exception ex) {
                // Provide an error message to the console output.
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error: The numbers entered must be integers.");
            } // end catch 
        } // end actionPerformed()
    } // end class

    class PrevButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // IF the currently dispaled contract is the first in the list
            // of contract, then you cannont view a non-existent contract 
            // behind it.
            if (theModel.getCurrentContractNum() == 0) {
                return;
            }

            try {
                // Retrieve the contract behind the currently displayed contract.
                theModel.prevContract();
            } catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error: There is a problem setting a previous contract.");
            }
            setUpDisplay();
        }
    }

    class NextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // IF the currently displayed contract is the last in the list
            // of contracts, then you cannont view a non-existent contract 
            // after it.
            if (theModel.getCurrentContractNum() == theModel.getContractCount() - 1) {
                return;
            }

            try {
                // Retrieve the contract ahead the currently displayed contract.
                theModel.nextContract();
            } catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error: There is a problem setting a next contract.");
            }
            setUpDisplay();
        }
    }

    class BidButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ConfirmBid cb;
                cb = new ConfirmBid(theView, true, theModel.getTheContract());
                cb.setLocationRelativeTo(null);
                cb.setVisible(true);
            } catch (Exception ex) {
                // Provide an error message to the console output.
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error: The numbers entered must be integers.");
            } // end catch
        } // end actionerPerformed
    } // end BidButtonListener

    class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (theContracts.checkValid()) {
                    System.out.println("All Valid");
                    theContracts.writeToFile("src/selectcontract07/Contracts.txt");
                    theModel.updateContracts();
                    setUpDisplay();

                } else {
                    System.out.println("Not All Valid");

                }

            } catch (Exception ex) {
                // Provide an error message to the console output.
                System.out.println(ex);
            } // end catch

        } // end actionPerformed
    }// end SaveButton listener

    class ComboListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            System.out.println(e.getItem().toString());
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedCity = e.getItem().toString();
                System.out.println(selectedCity);
                theModel.updateContractList(selectedCity);
                setUpDisplay();
            } // end if
        } // end itemSTateChanged
    } // end CombListener

}
