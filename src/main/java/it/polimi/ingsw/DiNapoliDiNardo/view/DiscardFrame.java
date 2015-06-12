
package it.polimi.ingsw.DiNapoliDiNardo.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.JButton;


public abstract class DiscardFrame {
	protected JLabel card1;
	protected JLabel card2;
	protected JLabel card3;
	protected JLabel textLabel;
	protected JLabel header;
	protected JFrame frame;
	protected JButton button;
	protected List<String> item;
	
	protected ToolTipCard tips = new ToolTipCard();
 
    
    public DiscardFrame(List<String> objects) {
        initComponents(objects);
    }

    protected void commonBuildUp(List<String> objects, Color color) {

    	frame = new JFrame();
        card1 = new JLabel();
        card2 = new JLabel();
        card3 = new JLabel();
        textLabel = new JLabel();
        header = new JLabel();
        button = new JButton("Don't discard");
        button.setBackground(Color.black);
        button.setForeground(color);
        button.setFocusable(false);
        JToolTip[] itemDescription = tips.setToolTip(objects);
        item = objects;
        
        card1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card1.setIcon(new ImageIcon("externalresources\\" + objects.get(0) + ".jpg"));
		card1.setToolTipText(itemDescription[0].getTipText());
        
        card2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card2.setIcon(new ImageIcon("externalresources\\" + objects.get(1) + ".jpg"));
		card2.setToolTipText(itemDescription[1].getTipText());
		
       
        card3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card3.setIcon(new ImageIcon("externalresources\\" + objects.get(2) + ".jpg"));
		card3.setToolTipText(itemDescription[2].getTipText());
		
        textLabel.setFont(new java.awt.Font("Impact", 0, 24)); 
        textLabel.setForeground(new java.awt.Color(204, 204, 204));
        textLabel.setText("Which card do you want to discard?");

        header.setIcon(new javax.swing.ImageIcon("externalresources\\header 2.jpg")); 

        
    }
   
    
    public List<JLabel> setCardHandler(){
		List<JLabel> cards = new ArrayList<JLabel>();
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		return cards;
		
	}
	
    protected abstract void initComponents(List<String> objects);
    
    public void enlightCard(int index){
		if (index == 1 && (item.get(0) != ""))
			card1.setIcon(new ImageIcon("externalresources\\" + item.get(0) + "Inv.jpg"));
		if (index == 2 && item.size()>1)
			card2.setIcon(new ImageIcon("externalresources\\" + item.get(1) + "Inv.jpg"));
		if (index == 3 && item.size()>2)
			card3.setIcon(new ImageIcon("externalresources\\" + item.get(2) + "Inv.jpg"));
	}

	public void endarkCard(int index){
		if (index == 1 && (item.get(0) != ""))
			card1.setIcon(new ImageIcon("externalresources\\" + item.get(0) + ".jpg"));
		if (index == 2 && item.size()>1)
			card2.setIcon(new ImageIcon("externalresources\\" + item.get(1) + ".jpg"));
		if (index == 3 && item.size()>2)
			card3.setIcon(new ImageIcon("externalresources\\" + item.get(2) + ".jpg"));
	}
	    
	public JFrame getFrame(){
		return this.frame;
	}
	
	public JButton getButtonNo(){
		return this.button;
	}
 
}
