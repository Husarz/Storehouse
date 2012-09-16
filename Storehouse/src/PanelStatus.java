import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.*;

import warehouse.*;

public class PanelStatus extends JPanel implements java.awt.event.ItemListener,
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final MainControl mainControl;
	NumberFormat formatter;
	JButton random;
	JCheckBox check1, check2, check3; // poprzeczna srodkowa powrotna
	JTextField sizeL, sizeH; // dlugosc magazynu - warehouse.L
								// ilosc segmentow - wysokosc magazynu -
								// warehouse.H
	JTextField rand, take;
	JButton increaseH, increaseL, decreaseH, decreaseL, decrease, increase,
			setB;

	SetPanel setPanel;

	public PanelStatus(MainControl mainControl) {
		super();
		formatter = new DecimalFormat("#0.0000");
		this.setLayout(new GridLayout(8, 2, 5, 5));
		this.mainControl = mainControl;
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		// ////////////////////////////////////////////////
		JPanel panel5 = new JPanel(new FlowLayout());
		decrease = new JButton("R-");
		increase = new JButton("R+");

		panel5.add(decrease);
		panel5.add(increase);

		increase.addActionListener(this);
		decrease.addActionListener(this);

		add(panel5);

		// /////////////////////////////////////////////////
		JPanel panel4 = new JPanel(new FlowLayout());
		random = new JButton("Rand");
		rand = new JTextField(3);

		panel4.add(random);
		panel4.add(rand);

		random.addActionListener(this);

		rand.setText(String.valueOf(this.mainControl.warehousePath.getSt()));

		add(panel4);

		// ////////////////////////////////////////////////
		JPanel panel7 = new JPanel(new FlowLayout());
		JLabel label = new JLabel("Pobrania");
		take = new JTextField(3);
		
		panel7.add(label);
		panel7.add(take);
		
		take.addActionListener(this);
		
		take.setText("1");
		
		add(panel7);
		
		// ////////////////////////////////////////////////
		JPanel panel2 = new JPanel(new GridLayout(3, 1, 2, 2));
		check1 = new JCheckBox("Shape");
		check2 = new JCheckBox("Return");
		check3 = new JCheckBox("Mid Point");
		panel2.add(check1);
		panel2.add(check2);
		panel2.add(check3);
		add(panel2);

		check1.setSelected(true);
		check2.setSelected(true);
		check3.setSelected(true);

		check1.addItemListener(this);
		check2.addItemListener(this);
		check3.addItemListener(this);

		// ///////////////////////////////////////////////
		JPanel panel1 = new JPanel(new BorderLayout());
		increaseH = new JButton("H +");
		decreaseH = new JButton("H -");
		increaseL = new JButton("L +");
		decreaseL = new JButton("L -");

		panel1.add(increaseH, BorderLayout.NORTH);
		panel1.add(decreaseH, BorderLayout.SOUTH);
		panel1.add(increaseL, BorderLayout.EAST);
		panel1.add(decreaseL, BorderLayout.WEST);

		add(panel1);

		increaseH.addActionListener(this);
		decreaseH.addActionListener(this);
		increaseL.addActionListener(this);
		decreaseL.addActionListener(this);

		// ////////////////////////////////////////////////
		JPanel panel3 = new JPanel(new FlowLayout());
		sizeL = new JTextField(3);
		sizeH = new JTextField(3);

		panel3.add(sizeL);
		panel3.add(sizeH);
		add(panel3);

		sizeL.addActionListener(this);
		sizeH.addActionListener(this);

		sizeL.setText(String.valueOf(this.mainControl.warehouse.getL()));
		sizeH.setText(String.valueOf(this.mainControl.warehouse.getH()-2));

		// /////////////////////////////////////////////////

		JPanel panel6 = new JPanel(new FlowLayout());

		setB = new JButton("Set");

		panel6.add(setB);
		add(panel6);

		setB.addActionListener(this);
	}

	@SuppressWarnings("serial")
	public class SetPanel extends JFrame {
		
		JButton setB;
		JTextField sK, f1, f2;

		public SetPanel() throws HeadlessException {
			super();
			
			this.setLayout(new GridLayout(4, 1, 5, 5));
			
			////////////////////////////////////////
			JPanel panel2 = new JPanel(new FlowLayout());
			
			sK = new JTextField(4);
			
			panel2.add(new JLabel("szerokosc korytarza roboczego"));
			panel2.add(sK); panel2.add(new JLabel("[cm]"));
			add(panel2);
			
			sK.setText(String.valueOf(mainControl.warehousePath.getLk()));
			
			sK.addActionListener(PanelStatus.this);
			////////////////////////////////////////

			JPanel panel3 = new JPanel(new FlowLayout());
			
			f1 = new JTextField(5);
			f2 = new JTextField(5);
			
			panel3.add(new JLabel("sredni czas jazdy wozka [ENTER]"));
			panel3.add(f1); panel3.add(new JLabel("[min/m]"));
			panel3.add(f2); panel3.add(new JLabel("[km/h]"));
			add(panel3);
			
			f1.setText(formatter.format(mainControl.warehousePath.getF1()));
			f2.setText(formatter.format(mainControl.warehousePath.getF2()));
			
			f1.addActionListener(PanelStatus.this);
			f2.addActionListener(PanelStatus.this);
			////////////////////////////////////////
			JPanel panel4 = new JPanel(new FlowLayout());
			
			setB = new JButton("Set");


			panel4.add(setB);
			add(panel4);
			
			setB.addActionListener(PanelStatus.this);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object source = arg0.getItemSelectable();

		if (source == check1) {
			if (check1.isSelected()) {
				mainControl.area1.setVisible(true);
			} else {
				mainControl.area1.setVisible(false);
			}
		}
		if (source == check2) {
			if (check2.isSelected()) {
				mainControl.area2.setVisible(true);
			} else {
				mainControl.area2.setVisible(false);
			}
		}
		if (source == check3) {
			if (check3.isSelected()) {
				mainControl.area3.setVisible(true);
			} else {
				mainControl.area3.setVisible(false);
			}
		}
		mainControl.mainView.changeView();
		mainControl.mainView.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();

		if (source == setB) {
			setPanel = new SetPanel();
			setPanel.setSize(250, 220);
			setPanel.setVisible(true);
			return;
		}

		if (setPanel != null &&  source == setPanel.sK) {
			int l = Integer.parseInt(setPanel.sK.getText());
			if (l>=100 && l<=10000){
				mainControl.warehousePath.setLk(l);
			}
			setPanel.sK.setText(String.valueOf(mainControl.warehousePath.getLk()));
		}
		
		if (setPanel != null &&  source == setPanel.f1) {
			double l = Double.parseDouble(setPanel.f1.getText());
			mainControl.warehousePath.setF1(l);
			setPanel.f2.setText(formatter.format(mainControl.warehousePath.getF2()));
			
		}
		
		if (setPanel != null &&  source == setPanel.f2) {
			double l = Double.parseDouble(setPanel.f2.getText());
			mainControl.warehousePath.setF2(l);
			setPanel.f1.setText(formatter.format(mainControl.warehousePath.getF1()));
			
		}
		
		if (setPanel != null &&  source == setPanel.setB){
			int l = Integer.parseInt(setPanel.sK.getText());
			if (l>=100 && l<=10000){
				mainControl.warehousePath.setLk(l);
			}
			mainControl.setAllPath();
			mainControl.setArea();
		}

		
		if (source == random) {
			mainControl.warehousePath.cleanKoms();
			mainControl.warehousePath.randKom(Integer.parseInt(rand.getText()));
			mainControl.setArea();
			return;
		}
		
		if (source == take){
			int p = Integer.parseInt(take.getText());
			if (p>0) mainControl.warehousePath.setP((p-1));
			take.setText("1");
			mainControl.setAllPath();
			mainControl.setArea();
		}

		if (source == decrease) {
			mainControl.area1.setA(mainControl.area1.getA() - 1);
			mainControl.area2.setA(mainControl.area2.getA() - 1);
			mainControl.area3.setA(mainControl.area3.getA() - 1);
			return;
		}
		if (source == increase) {
			mainControl.area1.setA(mainControl.area1.getA() + 1);
			mainControl.area2.setA(mainControl.area2.getA() + 1);
			mainControl.area3.setA(mainControl.area3.getA() + 1);
			return;
		}

		
		if (source == sizeL || source == sizeH) {
			int l = Integer.parseInt(sizeL.getText());
			int h = Integer.parseInt(sizeH.getText());
			this.mainControl.setWarehouse(new Warehouse((short) l, (short) (h+2)));

		}

		if (source == increaseH) {
			this.mainControl.setWarehouse(new Warehouse(mainControl.warehouse
					.getL(), (short) (mainControl.warehouse.getH() + 1)));

		}
		if (source == decreaseH) {
			this.mainControl.setWarehouse(new Warehouse(mainControl.warehouse
					.getL(), (short) (mainControl.warehouse.getH() - 1)));

		}
		if (source == increaseL) {
			this.mainControl.setWarehouse(new Warehouse(
					(short) (mainControl.warehouse.getL() + 1),
					mainControl.warehouse.getH()));

		}
		if (source == decreaseL) {
			this.mainControl.setWarehouse(new Warehouse(
					(short) (mainControl.warehouse.getL() - 1),
					mainControl.warehouse.getH()));

		}
		sizeL.setText(String.valueOf(this.mainControl.warehouse.getL()));
		sizeH.setText(String.valueOf(this.mainControl.warehouse.getH()-2));
	}

}
