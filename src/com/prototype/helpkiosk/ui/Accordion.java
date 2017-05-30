package com.prototype.helpkiosk.ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.javaswingcomponents.accordion.JSCAccordion;
import com.javaswingcomponents.accordion.TabOrientation;
import com.javaswingcomponents.accordion.listener.AccordionEvent;
import com.javaswingcomponents.accordion.listener.AccordionListener;
import com.javaswingcomponents.accordion.plaf.steel.SteelAccordionUI;
import com.javaswingcomponents.accordion.plaf.steel.SteelVerticalTabRenderer;
import com.javaswingcomponents.framework.painters.configurationbound.GradientColorPainter;
import com.javaswingcomponents.framework.painters.text.TextFormattingRuleBuilder;
import com.javaswingcomponents.framework.painters.text.TextFormattingInfo.Weight;
import com.prototype.helpkiosk.instruction.InstructionSingleton;

public class Accordion extends JPanel {
	private InstructionSingleton instructionSingleton = InstructionSingleton.getInstance();
	private int tabHeight = 50;
	private int transitionSpeed = 1000;
	private int tab_title_font_size = 20;
	
	private SearchPanel searchPanel;
	private LearnDoPanel learnDoPanel;
	
	private Color tab_text_normal = Color.DARK_GRAY;
	private Color tab_text_mouseover = new Color(0xD5D5D5);
	private Color tab_text_selected = Color.WHITE;
	private Color tab_bg_normal = new Color (0x518AC0);
	
	private Color focus_border_color = Color.WHITE;
	private Color default_grey = Color.WHITE;
	
	private JSCAccordion accordion;
	public Accordion(){
		accordion = new JSCAccordion();
		addTabs(accordion);
		customizeAccordion(accordion);
		customizeTab(accordion);
		setLayout(new GridLayout(1,1,10,10));
		listenForChanges(accordion);
		add(accordion);
	}
	
	public JSCAccordion getAccordion(){
		return accordion;
	}
	
	private void addTabs(JSCAccordion accordion){
		searchPanel = new SearchPanel();
		learnDoPanel = new LearnDoPanel();
		accordion.addTab("Step 2: Learn/Do", learnDoPanel.createPanel());
		accordion.addTab("Step 1: Find Info", searchPanel.createPanel());
		accordion.setSelectedIndex(1);
	}
	
	private void customizeAccordion(JSCAccordion accordion){
		SteelAccordionUI steelUI = new SteelAccordionUI();
		steelUI.setDuration(transitionSpeed);
		
		accordion.setTabOrientation(TabOrientation.VERTICAL);
		accordion.setTabHeight(tabHeight);
		
		//Set Accordion's background colour to default grey
		GradientColorPainter backgroundPainter = (GradientColorPainter) accordion.getBackgroundPainter();
		backgroundPainter.setStartColor(default_grey);
		backgroundPainter.setEndColor(default_grey);
	}
	
	private void customizeTab(JSCAccordion accordion){
		SteelVerticalTabRenderer tabRenderer = new SteelVerticalTabRenderer(accordion);
		tabRenderer.setShowIndex(false);
		
		//setting the tab background colour
		GradientColorPainter painter = new GradientColorPainter();
	    painter.setStartColor(tab_bg_normal);
	    painter.setEndColor(tab_bg_normal);
	    
	    painter.setGradientDirection(GradientColorPainter.GradientDirection.VERTICAL);
	    tabRenderer.setBackgroundPainter(painter);
	    tabRenderer.setFocusBorderColor(focus_border_color);
	    tabRenderer.setBorder(BorderFactory.createLineBorder(new Color(0x3B70A3)));
	    tabRenderer.setBorderPadding(2);
		
		//Apply normal text rules to determine how normal text is rendered:
		tabRenderer.setNormalTextFormattingRules(
		    new TextFormattingRuleBuilder().createRule()
		        .setFontSize(tab_title_font_size)
		        .setFontFamily(getFont().getFamily())
		        .setForeground(tab_text_normal)
		    .build()
		);
		
		//Apply mouse over text rules to determine how text is displayed when the mouse is over the tab:
		tabRenderer.setMouseOverTextFormattingRules(
		    new TextFormattingRuleBuilder().createRule()
		        .setFontSize(tab_title_font_size)
		        .setFontFamily(getFont().getFamily())
		        .setWeight(Weight.BOLD)
		        .setForeground(tab_text_mouseover)
		    .build()
		);

		//Apply selected text rules to determine how text is displayed when the tab is selected:
		tabRenderer.setSelectedTextFormattingRules(
		    new TextFormattingRuleBuilder().createRule()
		        .setFontSize(tab_title_font_size)
		        .setFontFamily(getFont().getFamily())
		        .setForeground(tab_text_selected)
		        .setWeight(Weight.BOLD)
		    .build()
		);
		
		accordion.setVerticalAccordionTabRenderer(tabRenderer);
		
	}
	
	private void listenForChanges(final JSCAccordion accordion) {
		accordion.addAccordionListener(new AccordionListener() {
			
			public void accordionChanged(AccordionEvent accordionEvent) {
				
				switch (accordionEvent.getEventType()) {
				case TAB_ADDED: {
					break;
				}
				case TAB_REMOVED: {
					break;					
				}
				case TAB_SELECTED: {
					break;					
				}
				
				}
			}
		});
	}
	
}


