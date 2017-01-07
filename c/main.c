/*
 * SmartClock.c
 *
 * Created: 20.11.2016 19:52:14
 * Author : Vitaliy Gonchar
 */ 
#define F_CPU 8000000UL
#include <avr/interrupt.h>
#include <string.h>
#include <util/delay.h>
#include "n5110.h"
#include "esp01.h"
#include "uart.h"
#define N_DIGITS		6
#define N_SYMBOLS		9
#define SYM				':'
 

#define TIMER_INTERRAPT_ON	TIMSK	= 0x01
#define TIMER_INTERRAPT_OFF TIMSK	= 0x00
#define TIMER_CLEAR			TCNT0	= 0x00

#define LED_CONFIG DDRD		|= 0x80
#define LED_SWITCH PORTD 	^= 0x80
#define LED_ON	   PORTD	= 0x80
#define LED_OFF	   PORTD 	= 0x00
void init();
void clock();
void secondsToTime();
void initSymbolTime();
char getStringNumber(int8_t number);

void send_time();

void settings();
void setClockTime(int8_t increment);

int8_t seconds	= 0;
int8_t minutes	= 0;
int8_t hours	= 0;
int8_t counter	= 0;

int8_t isClockSettings	= 0;
int8_t settingsState	= 0;

char time[N_SYMBOLS] = {'0', '0', ':', '0', '0', ':', '0', '0', '\r'};

int8_t is_display = 0;

int main(void)
{
	LED_CONFIG;
	LED_ON;
	_delay_ms(1000);
	LED_OFF;
	init();
//	clock();

	while( 1 )
	{

	}
}

void init()
{
	DDRB	= 0x1E;
	LCD_init();
	init_UART();
	start_server();


	TCCR0	= 0x05;
	MCUCR	= 0x0F;
	GICR	= 0xC0;

	asm("sei");
	TIMER_INTERRAPT_ON;
	TIMER_CLEAR;
}


ISR(INT0_vect)
{
	/*_delay_ms(50);
	if (settingsState == 2) settingsState = 0;
	else settingsState++;
	*/
}

ISR(INT1_vect)
{
/*	_delay_ms(50);
	if (!isClockSettings)
	{
		isClockSettings = 1;	
	}
	else isClockSettings = 0;
	*/
}

ISR(TIMER0_OVF_vect)
{
	/*if (!isClockSettings)
	{
		if (counter == 30) 
		{
			counter = 0;
			seconds++;
			if (is_connection()) {
				puts("AT+CIPSEND=0,10\r");
				puts(time);
				LCD_write_english_string(0, 5, "Connection   ");
			} else LCD_write_english_string(0, 5, "No connection");
		} 
		else counter++;
	}
	clock();
	*/
	/*if (!is_display)
	{
		LED_OFF;
		is_display = 1;
	}
	else
	{
		LED_ON;
		is_display = 0;
	}
*/
	if (counter == 30)
	{
		counter = 0;
		seconds++;
		if (is_connection())
				{
					send_data(time);
					LED_ON;
					LCD_write_english_string(0, 0, "Connection   ");
					is_display = 0;
				}
				else if (!is_display)
				{
					is_display = 1;
					LED_OFF;
					LCD_write_english_string(0, 0, "No connection");
				}
	}
	else counter++;


}

void send_time()
{
	puts("AT+CIPSEND=0,10\r");
	puts(time);
}

void clock()
{
	secondsToTime();
	initSymbolTime();
//	LCD_write_english_string(0, 0, time);
}

void secondsToTime()
{
	if (seconds == 60) {
		seconds = 0;
		minutes++;
	}
	if (minutes == 60) {
		minutes = 0;
		hours++;	
	}
	if (hours == 24) {
		hours = 0;
	}
}

void initSymbolTime() 
{
	time[0]	= getStringNumber(hours / 10);
	time[1] = getStringNumber(hours % 10);
	time[2] = SYM;
	time[3] = getStringNumber(minutes / 10);
	time[4] = getStringNumber(minutes % 10);
	time[5] = SYM;
	time[6] = getStringNumber(seconds / 10);
	time[7] = getStringNumber(seconds % 10);
}

char getStringNumber(int8_t number)
{
	char result = 0;
	switch(number)
	{
		case 1:
			result = '1';
			break;
		case 2:
			result = '2';
			break;
		case 3:
			result = '3';
			break;
		case 4:
			result = '4';
			break;
		case 5:
			result = '5';
			break;
		case 6:
			result = '6';
			break;
		case 7:
			result = '7';
			break;
		case 8:
			result = '8';
			break;
		case 9:
			result = '9';
			break;
		case 0:
			result = '0';
			break;
	}
	return result;	
}

void settings()
{
	int8_t but_inc = 0x01;
	int8_t but_dec = 0x02;
	int8_t temp = 0;
	while (isClockSettings)
	{
		temp = PIND;
		_delay_ms(50);
		
		if  (temp & but_inc)
		{
			setClockTime(1);
		}
		else if (temp & but_dec)	
		{
			setClockTime(0);
		}

	}
}

void setClockTime(int8_t increment)
{
	switch(settingsState)
	{
		case 0:
			if (increment)
			{
				if (seconds == 60) 
				{
					seconds = 0;
					minutes++;
				} 
				else seconds++;
			}
			else if (seconds > 0)
			{
				seconds--;
			}
			break;
		case 1:
			if (increment)
			{
				if (minutes == 60)
				{
					minutes = 0;
					hours++;
				}
				else minutes++;
			} 
			else if (minutes > 0)
			{
				minutes--;
			}
			break;
		case 2:
			if (increment)
			{
				if (hours == 24)
				{
					hours = 0;
				}
				else hours++;
			}
			else if (hours > 0)
			{
				hours--;
			}
			break;
	}
}
