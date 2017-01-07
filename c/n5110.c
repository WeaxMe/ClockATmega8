/*
 *  Author: 4a4ik
 */ 
#define F_CPU 8000000UL
#include <avr/io.h>
#include <util/delay.h>
#include "english_font.h"
#include "n5110.h"


void LCD_write_byte(unsigned char dat, unsigned char command)
{
	unsigned char i;
	
	if (command == 1)
	LCD_DC_clr;
	else
	LCD_DC_set;

	for(i=0;i<8;i++)
	{
		if(dat&0x80)
		SDIN_set;
		else
		SDIN_clr;
		SCLK_clr;
		dat = dat << 1;
		SCLK_set;
	}
}

void LCD_init() 
{
	LCD_RST_clr;
	_delay_us(1);
	LCD_RST_set;
	_delay_us(1);
	LCD_write_byte(0x21, 1);	// set LCD mode
	LCD_write_byte(0xc8, 1);	// set bias voltage
	LCD_write_byte(0x06, 1);	// temperature correction
	LCD_write_byte(0x13, 1);	// 1:48
	LCD_write_byte(0x20, 1);	// use bias command, vertical
	LCD_write_byte(0x0c, 1);	// set LCD mode,display normally
	LCD_clear();	                // clear the LCD
}

void LCD_clear() 
{
	unsigned int i;

	LCD_write_byte(0x0c, 1);
	LCD_write_byte(0x80, 1);

	for (i=0; i<504; i++)
	{
		LCD_write_byte(0, 0);
	}
}

void LCD_set_XY(unsigned char X, unsigned char Y)
{
	LCD_write_byte(0x40 | Y, 1);	// column
	LCD_write_byte(0x80 | X, 1);    // row
}

void LCD_write_char(unsigned char c)
{
	unsigned char line;

	c -= 32;

	for (line=0; line<6; line++)
	LCD_write_byte(font6x8[c][line], 0);
}

void LCD_write_english_string(unsigned char X,unsigned char Y,char *s)
{
	LCD_set_XY(X,Y);
	while (*s)
	{
		LCD_write_char(*s);
		s++;
	}
}