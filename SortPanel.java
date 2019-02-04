/************************************************************
 *                                                          *
 *  CSCI 470/680     Assignment 5       Summer 2018         *
 *                                                          *
 *  Programmer:  Usman Hussain                              *
 *               Nicholas Swanson                           *
 *                                                          *
 *  Date Due:    07/30/2018                                 *
 *                                                          *
 *  Use:         SortPanel Class - Implementation           *
 *                                                          *
 ************************************************************/
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.Random;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SortPanel extends JPanel
{
  //Buttons
  private final JButton popArrBtn;
  private final JButton sortArrBtn;
  private final JComboBox<String> sortSelect;
  
  //Sorting Panel
  private SortAnimationPanel sortPanel;
  
  //String Array for ComboBox Selection
  private String[] sortAlgs = {"Selection Sort", "Insertion Sort", "Bubble Sort"};
  
  //Constructor
  public SortPanel()
  {
    //Layout for Panel
    //Buttons
    popArrBtn = new JButton("Populate Array");
    popArrBtn.addActionListener(new actionHandler());
    
    sortArrBtn = new JButton("Sort");
    sortArrBtn.setEnabled(false);
    sortArrBtn.addActionListener(new actionHandler());
    
    //ComboBox
    sortSelect = new JComboBox<String>(sortAlgs);
    //sortSelect.addActionListener(new actionHandler());
    
    //Panel
    sortPanel = new SortAnimationPanel();
    
    //Add in Components
    setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    
    //First Row - Panel
    gc.fill = GridBagConstraints.BOTH;
    
    gc.weightx = 1.0;
    gc.weighty = 1.0;
    gc.gridx = 0;
    gc.gridy = 0;
    gc.gridwidth = 3;
    add(sortPanel,gc);
    
    //Second Row - Buttons
    gc.fill = GridBagConstraints.NONE;
    gc.weightx = 0.5;
    gc.weighty = 0.0;
    gc.gridwidth = 1;
    gc.gridx = 0;
    gc.gridy = 1;
    add(popArrBtn,gc);
    
    gc.gridx = 1;
    gc.gridy = 1;
    add(sortArrBtn,gc);
    
    gc.gridx = 2;
    gc.gridy = 1;
    add(sortSelect,gc);
  }
  
  public class actionHandler implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      if(event.getSource() == popArrBtn)//Button
      {
        //Disable PopulateArray Button
        popArrBtn.setEnabled(false);
        
        //Create Array and Repaint Canvas
        sortPanel.createArray();
        
        //Enable Sort Button
        sortArrBtn.setEnabled(true);
      }
      if(event.getSource() == sortArrBtn)
      {
        //Disable Sort Button and ComboBox
        sortArrBtn.setEnabled(false);
        sortSelect.setEnabled(false);
        
        //Start the Sorting Thread
        new Thread(sortPanel).start();
      }
    }
  }
  
  //Nested Class
  private class SortAnimationPanel extends JPanel implements Runnable
  {
    private final int BAR_WIDTH = 2; 
    private final int SLEEP_TIME = 100;
    
    private final ArrayList<Integer> sortingArray = new ArrayList<Integer>();
    
    public void run()
    {
      //Determine the sort to run based on the sortSelected in GUI
      if(sortSelect.getSelectedItem().toString() == "Insertion Sort")
      {
        insertionSort(sortingArray);
      }
      else if(sortSelect.getSelectedItem().toString() == "Selection Sort")
      {
        selectionSort(sortingArray);
      }
      else
      {
        bubbleSort(sortingArray);
      }
      
      //Enable Buttons when complete
      popArrBtn.setEnabled(true);
      sortSelect.setEnabled(true);
    }
    
    public void createArray()
    {
      //Instantiate a new Random Class
      Random rand = new Random();
      sortingArray.clear();
      //Loop through the panelwidth/bar_width to add in elements to Array
      for(int i = 0; i < this.getWidth()/BAR_WIDTH; i++)
      {
        sortingArray.add(rand.nextInt(this.getHeight()));
      }
      
      //Paint the panel when complete
      repaint();
    }
    
    //Sorting Algorithms
    private void insertionSort(ArrayList<Integer> arr)
    {
      try
      {
        int i, j;
        for (i = 1; i < arr.size(); i++) {
          Integer tmp = arr.get(i);
          j = i;
          while ((j > 0) && (arr.get(j - 1) > tmp)) {
            arr.set(j, arr.get(j - 1));
            j--;
          }
          arr.set(j, tmp);
          Thread.sleep(SLEEP_TIME);
          repaint();
        }
      }
      catch(InterruptedException e)
      {
        System.err.println("Thread Error in Insertion Sort: " + e);
      }
    }
    
    //Selecion Sort
    private void selectionSort(ArrayList<Integer> arr)
    {
      try
      {
        for (int i = 0; i < arr.size(); i++)
        {
          int pos = i;
          for(int j = i; j < arr.size();j++)
          {
            if(arr.get(j) < arr.get(pos))
            {
              pos = j;
            }
          }
          int min = arr.get(pos);
          arr.set(pos, arr.get(i));
          arr.set(i,min);
          
          Thread.sleep(SLEEP_TIME);
          repaint();
        }
      }
      catch(InterruptedException e)
      {
        System.err.println("Thread Error in Selection Sort: " + e);
      }
    }
    //Bubble Sort
    private void bubbleSort(ArrayList<Integer> list) 
    {
      try
      {
      for (int size = list.size(); size != 1; --size)
      {
        for (int i = 0; i < size - 1; i++) 
        {
          int temp1 = list.get(i + 1);
          int temp2 = list.get(i);
          if (temp2 > temp1) 
          {
            list.set(i, temp1);
            list.set(i + 1, temp2);
          }
        }
        Thread.sleep(SLEEP_TIME);
        repaint();
      }
      }
      catch(InterruptedException e)
      {
        System.err.println("Thread Error in Bubble Sort: " + e);
      }
    }
    
    
    //Paint Component
    public void paintComponent(Graphics pen)
    {
      try
      {
        //Clear area and Set Background/Pen Color
        super.paintComponent(pen);
        this.setBackground(Color.WHITE);
        pen.setColor(Color.blue);
        
        //Create a Starting Position on the Panel
        int xPos = 0;//Start xPos
        
        //Paint if the Array is not Empty
        if(!(sortingArray.isEmpty()))
        {
          for(int i = 0; i < sortingArray.size(); i++)
          {
            pen.fillRect(xPos,this.getHeight()-sortingArray.get(i),BAR_WIDTH,sortingArray.get(i));
            xPos = xPos + BAR_WIDTH;
          }
        }
      }
      catch(NullPointerException e)
      {
        System.err.println("Null Pointer Error in paintComponent: " + e);
      }
    }
  }
}