package ui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import render3d.Point2i;

public class TextBox {
	private static final int maxlines = 10;
	private static final int spacing = 2;
	private final Point2i anchor;
	UnicodeFont font;
	ArrayList<String> lines;
	private final int fontsize = 16;

	@SuppressWarnings("unchecked")
	public TextBox() {
		Font awtFont = new Font("Courier New", Font.PLAIN, fontsize);
		font = new UnicodeFont(awtFont);
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(Color.white));
		try {
			font.loadGlyphs();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		anchor = new Point2i(spacing, 600 - fontsize * 2 - spacing * 2);
		lines = new ArrayList<String>();
	}

	public void draw() {
		for (int i = 0; i < maxlines && i < lines.size(); i++) {
			font.drawString(anchor.x(), anchor.y() - fontsize * i - spacing,
					lines.get(lines.size() - i - 1));
		}
	}

	public void addLine(String line) {
		lines.add(line);
	}
}
