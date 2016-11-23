/*
 *  Author: 4a4ik
 */ 


#define LCD_RST_set  PORTB |=  (1<<1)    //external reset input
#define LCD_RST_clr  PORTB &=~ (1<<1)

#define LCD_DC_set   PORTB |=  (1<<2)    //data/commande
#define LCD_DC_clr   PORTB &=~ (1<<2)

#define SDIN_set     PORTB |=  (1<<3)    //serial data input
#define SDIN_clr     PORTB &=~ (1<<3)

#define SCLK_set     PORTB |=  (1<<4)    //serial clock input
#define SCLK_clr     PORTB &= ~(1<<4)


void LCD_write_byte(unsigned char dat, unsigned char command);
void LCD_init();
void LCD_clear();
void LCD_set_XY(unsigned char X, unsigned char Y);
void LCD_write_char(unsigned char c);
void LCD_write_english_string(unsigned char X,unsigned char Y,char *s);

