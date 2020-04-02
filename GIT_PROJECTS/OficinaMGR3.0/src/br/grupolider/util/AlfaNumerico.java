package br.grupolider.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author rodrigues raffael
 */
public class AlfaNumerico extends PlainDocument {
	//numbers and capital letters - upper case

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if (str == null) {
			return;
		}
		str = str.replaceAll("[^A-Za-z0-9 ]", "");
		super.insertString(offs, str, a);
	}

}
