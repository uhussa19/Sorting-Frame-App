/************************************************************
 *                                                          *
 *  CSCI 470/680     Assignment 6       Summer 2018         *
 *                                                          *
 *  Programmer:  Usman Hussain                              *
 *               Nicholas Swanson                           *
 *                                                          *
 *  Date Due:    07/30/2018                                 *
 *                                                          *
 *  Use:         SortingFrame Class - Implementation        *
 *                                                          *
 ************************************************************/
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class SortingFrame extends JFrame
{
  //Variables
  private SortPanel firstPanel;
  private SortPanel secondPanel;
  
  public SortingFrame(String title)
  {
    //Set Title and Make Content
    super(title);
    setLayout(new GridBagLayout());
    //setLayout(new BorderLayout());
    
    //Add Componenets
    firstPanel = new SortPanel();
    secondPanel = new SortPanel();
    
    //Add Panels to JFrame
    GridBagConstraints gc = new GridBagConstraints();
    //Fill and Weight will stay the same across panels
    gc.fill = GridBagConstraints.BOTH;
    gc.weightx = 1.0;
    gc.weighty = 1.0;
    
    //Left Panel
    gc.gridx = 0;
    gc.gridy = 0;
    add(firstPanel,gc);
    
    //Right Panel
    gc.gridx = 1;
    gc.gridy = 0;
    add(secondPanel,gc);
  }
  
  //Main Caller for application
  public static void main(String[] args)
  {
    //Declare and Initialize the SortingFrame
    SortingFrame sortFrame = new SortingFrame("Sorting Animation"); //instantiate the file frame
    //Set Window Params
    sortFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    sortFrame.setSize(700, 500);
    sortFrame.setLocationRelativeTo(null);
    sortFrame.setVisible(true); 
  }
}