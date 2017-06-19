package view.masterPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import view.masterPanel.Enums.SIMCategory;
import view.masterPanel.Enums.StudyYear;
import view.masterPanel.EventsAndListeners.AddMemberEvent;
import view.masterPanel.EventsAndListeners.AddMemberListener;

public class MemberAdder extends JDialog {

	private JPanel infoPanel;
	private JPanel confirmationPanel;
	private JLabel memberNameLabel;
	private JTextField memberNameField;
	private JLabel studyYearLabel;
	private JComboBox studyYearComboBox;
	private JLabel studyFieldLabel;
	private JTextField studyFieldField;
	private JLabel simCategoryLabel;
	private JComboBox simCategoryComboBox;
	private JLabel whatsAppNumerLabel;
	private JTextField whatsAppNumerField;
	private JButton submitButton;
	private JButton cancelButton;
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font buttonsFont_3 = new Font("Ara Aqeeq Bold", Font.PLAIN, 20);
	private AddMemberEvent event;
	private AddMemberListener listener;

	public MemberAdder(MasterPanel parent) {

		setVisible(true);
		setTitle("إضافة عضو");
		setLocationRelativeTo(parent);
		setLayout();
	}

	private void setLayout() {
		setLayout(new BorderLayout());
		setSize(500, 300);
		setMinimumSize(new Dimension(450, 300));
		setMaximumSize(new Dimension(500, 300));

		makeinfoPanel();
		add(infoPanel, BorderLayout.NORTH);
		makeConfirmationPanel();
		add(confirmationPanel, BorderLayout.SOUTH);
	}

	private void makeinfoPanel() {

		infoPanel = new JPanel();
		Border innerBorder = BorderFactory.createTitledBorder("");
		infoPanel.setBorder(innerBorder);

		memberNameLabel = new JLabel("اسم العضو");
		memberNameLabel.setFont(labelsFont);
		memberNameField = new JTextField(20);

		studyYearLabel = new JLabel("السنة الدراسية");
		studyYearLabel.setFont(labelsFont);
		studyYearComboBox = new JComboBox(StudyYear.values());

		studyFieldLabel = new JLabel("التخصص");
		studyFieldLabel.setFont(labelsFont);
		studyFieldField = new JTextField(20);

		simCategoryLabel = new JLabel("الشريحة");
		simCategoryLabel.setFont(labelsFont);
		simCategoryComboBox = new JComboBox(SIMCategory.values());

		whatsAppNumerLabel = new JLabel("رقم الوتسآآب");
		whatsAppNumerLabel.setFont(labelsFont);
		whatsAppNumerField = new JTextField(20);

		infoPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;

		//////////////////////////////////// firstRow////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy = 0;

		gc.gridheight = 1;
		gc.ipady = 10;

		infoPanel.add(memberNameField, gc);
		gc.gridx = 1;
		infoPanel.add(memberNameLabel, gc);

		////////////////////////////////// nextRow///////////////////////////////////////////
		gc.gridy++;

		gc.gridx = 0;

		infoPanel.add(studyYearComboBox, gc);
		gc.gridx = 1;
		infoPanel.add(studyYearLabel, gc);

		////////////////////////////////// nextRow///////////////////////////////////////////
		gc.gridy++;

		gc.gridx = 0;

		infoPanel.add(studyFieldField, gc);
		gc.gridx = 1;
		infoPanel.add(studyFieldLabel, gc);

		////////////////////////////////// nextRow///////////////////////////////////////////
		gc.gridy++;

		gc.gridx = 0;
		gc.ipady = 5;

		infoPanel.add(simCategoryComboBox, gc);
		gc.gridx = 1;
		infoPanel.add(simCategoryLabel, gc);

		////////////////////////////////// nextRow///////////////////////////////////////////
		gc.gridy++;

		gc.gridx = 0;
		gc.ipady = 10;

		infoPanel.add(whatsAppNumerField, gc);
		gc.gridx = 1;
		infoPanel.add(whatsAppNumerLabel, gc);

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
			
			Date date = new Date();
			java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addingDate =sdf.format(date);
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					event = new AddMemberEvent(this, memberNameField.getText(),
							studyYearComboBox.getSelectedItem().toString(), studyFieldField.getText(),
							simCategoryComboBox.getSelectedItem().toString(), whatsAppNumerField.getText(),addingDate);
					listener.fireAddingMember(event);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(submitButton,"عفوا ً لقد قمت بإدخال بيانات خاطئة",  "خطأ في البيانات",
							JOptionPane.WARNING_MESSAGE);
				}
				dispose();
			}

		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
	}

	public void setListener(AddMemberListener listener) {
		this.listener = listener;
	}

}
