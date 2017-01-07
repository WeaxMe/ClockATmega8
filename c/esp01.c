/*
 *	@author Vitaliy Gonchar
 */

#include "esp01.h"
#include "uart.h"
#include "n5110.h"
#include <string.h>
#include <stdlib.h>

#define  BUFF_SIZE 50
#define AT_DISCONNECT		"AT+CIPCLOSE\r"

char buff[BUFF_SIZE] = {0};
	
void start_server() 
{
	clean_buffer();
	LCD_write_english_string(0, 0, "Server init...");
	puts(AT_STATUS);
	DELAY;
	print_input_msg();
	_delay_ms(1000);
	DELAY;
	puts(AT_SSID);
	DELAY;
	print_input_msg();
	_delay_ms(1000);
	puts(AT_RESET);
	DELAY;
	print_input_msg();
	_delay_ms(1000);
	puts(AT_ENABLE_SERVER);
	DELAY;
	print_input_msg();
	_delay_ms(1000);
	puts("AT+CIPMUX=1\r");
	DELAY;
	print_input_msg();
	_delay_ms(1000);
	puts(AT_START_SERVER);
	DELAY;
	print_input_msg();
	_delay_ms(1000);
	LCD_write_english_string(0, 0, "Done!          ");
	
}

void send_command(char * message)
{
	
	puts(strcat(message, "\r"));
	/*
	for (int i = 0; i < length; i++) 
	{
		send_sym(message[i]);
	}
	*/
}

void send_data(char * data)
{
	puts("AT+CIPSEND=0,10\r");
	puts(data);
	DELAY;
	puts(AT_DISCONNECT);
	//puts("AT+CIPCLOSE=0\r");
	DELAY;
}

int8_t is_connection()
{
	puts(AT_ACTIVE_CONN);
	DELAY;
	read_in_buffer();
	LCD_write_english_string(0, 1, buff);
	if (strstr(buff, "TCP") != NULL) return 1;
	
	return 0;
}

void clean_buffer()
{
	int i;
	for (i = 0; i < BUFF_SIZE; i++)
		buff[i] = 0;
}

void read_in_buffer()
{
	int i = 0;	
	int a = getchar();
	while(a != EOF)
	{
		if (a != 0x0D && a != 0x0A)
		{
			buff[i] = a;
			i++;
		}
		a = getchar();
		if (i == BUFF_SIZE - 1) break;
		
	}
	buff[i] = EOF;
}

void print_buffer()
{
	LCD_set_XY(0, 0);
	int i;
	for (i = 0; i < BUFF_SIZE; i++)
	{
		if (buff[i] == EOF) break;
		LCD_write_char(buff[i]);
	}
}

void print_input_msg()
{
	_delay_ms(100);
	LCD_clear();
	LCD_set_XY(0, 0);
	int a = getchar();
	while(a != EOF)
	{
		if (a != 0x0D && a != 0x0A)
		{
			LCD_write_char(a);
		}
		a = getchar();
	}
}
