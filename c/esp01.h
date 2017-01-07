/*
 * esp01.h
 *
 * Created: 05.12.2016 15:49:42
 *  Author: Vitaliy Gonchar
 */ 
#define F_CPU 8000000UL
#include <util/delay.h>


#define AT_STATUS			"AT\r"
#define AT_MODE				"AT+CWMODE=3\r"
#define AT_SSID				"AT+CWSAP=\"ESP-01\",\"12345678\",1,4\r"
#define AT_RESET			"AT+RST\r"
#define AT_ENABLE_SERVER	"AT+CIPMODE=0\r"
#define AT_MULTI_CONNECTION "AT+CIPMUX=0\r"
#define AT_START_SERVER		"AT+CIPSERVER=1,8888\r"
#define AT_SEND_MSG			"AT+CIPSEND=%d,%d\r"				//AT+CIPSEND=<id>,<length>
#define AT_ACTIVE_CONN		"AT+CIPSTATUS\r"


#define DELAY _delay_ms(200)

void start_server();
void send_command(char * message);
void send_data(char * data);

int8_t is_connection();

void print_input_msg();

void read_in_buffer();
void print_buffer();
void clean_buffer();
