package view.masterPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import backEnd.DirectOperations.AddBook;
import view.masterPanel.EventsAndListeners.AddBookEvent;
import view.masterPanel.EventsAndListeners.AddBookListener;

public class BookAdder extends JDialog {

	private JPanel infoPanel;
	private JPanel confirmationPanel;
	private JLabel bookNameLabel;
	private JTextField bookTitleField;
	private JLabel writerNameLabel;
	private JTextField writerNameField;
	private JLabel publishingHouseLabel;
	private JTextField publishingHouseField;
	private JLabel copiesLabel;
	private JSpinner copiesSpinner;
	private SpinnerNumberModel copiesNumerModel;
	private JButton submitButton;
	private JButton cancelButton;
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font buttonsFont_3 = new Font("ara Jozoor", Font.PLAIN, 20);
	private AddBookListener listener;
	private AddBookEvent event;
	
	
	public BookAdder(MasterPanel parent) {

		setVisible(true);
		setTitle("إضافة كتاب");
		setLocationRelativeTo(parent);
		setLayout();
	}

	private void setLayout() {
		setLayout(new BorderLayout());
		setSize(500, 260);
		setMinimumSize(new Dimension(450, 260));
		setMaximumSize(new Dimension(500, 260));

		makeinfoPanel();
		add(infoPanel, BorderLayout.NORTH);
		makeConfirmationPanel();
		add(confirmationPanel, BorderLayout.SOUTH);
	}

	private void makeinfoPanel() {

		infoPanel = new JPanel();
		Border innerBorder = BorderFactory.createTitledBorder("");
		infoPanel.setBorder(innerBorder);

		bookNameLabel = new JLabel("اسم الكتاب");
		bookNameLabel.setFont(labelsFont);
		bookTitleField = new JTextField(20);

		writerNameLabel = new JLabel("الكاتب");
		writerNameLabel.setFont(labelsFont);
		writerNameField = new JTextField(20);

		publishingHouseLabel = new JLabel("دار النشر");
		publishingHouseLabel.setFont(labelsFont);
		publishingHouseField = new JTextField(20);

		copiesLabel = new JLabel("عدد النسخ");
		copiesLabel.setFont(labelsFont);
		copiesNumerModel = new SpinnerNumberModel(0, 0, 9999, 1);
		copiesSpinner = new JSpinner(copiesNumerModel);

		infoPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;

		//////////////////////////////////// firstRow////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy = 0;

		gc.gridheight = 1;
		gc.ipady = 10;

		infoPanel.add(bookTitleField, gc);
		gc.gridx = 1;
		infoPanel.add(bookNameLabel, gc);

		////////////////////////////////// nextRow///////////////////////////////////////////
		gc.gridy++;

		gc.gridx = 0;
		infoPanel.add(writerNameField, gc);
		gc.gridx = 1;
		infoPanel.add(writerNameLabel, gc);

		////////////////////////////////// nextRow///////////////////////////////////////////
		gc.gridy++;

		gc.gridx = 0;
		infoPanel.add(publishingHouseField, gc);
		gc.gridx = 1;
		infoPanel.add(publishingHouseLabel, gc);

		////////////////////////////////// nextRow///////////////////////////////////////////
		gc.gridy++;

		gc.gridx = 0;
		gc.ipady = 5;
		infoPanel.add(copiesSpinner, gc);
		gc.gridx = 1;
		infoPanel.add(copiesLabel, gc);

		////////////////////////////////////////////////////////////////////////////////////

	}

	private void makeConfirmationPanel() {
		confirmationPanel = new JPanel();
		confirmationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		submitButton = new JButton("تأكيد");
		submitButton.setFont(buttonsFont_3);

		cancelButton = new JButton("إلغاء");
		cancelButton.setFont(buttonsFont_3);

		confirmationPanel.add(submitButton);
		confirmationPanel.add(cancelButton);

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				long dt=new java.util.Date().getTime();
				java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addingDate =sdf.format(dt);
				
				event = new AddBookEvent(this, bookTitleField.getText(), writerNameField.getText(),
						publishingHouseField.getText(), Integer.parseInt(copiesSpinner.getValue().toString())
						,addingDate);

				listener.fireAddingBook(event);
				
			}

		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
	}

	public void setListener(AddBookListener listener) {
		this.listener = listener;
	}

}
