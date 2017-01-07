/*
 * uart.h
 *
 * Created: 05.12.2016 22:17:02
 *  Author:  Vitaliy Gonchar
 */ 

#include <stdlib.h>
#include <stdio.h>
#define F_CPU		8000000UL
#define BAUD		9600L
#define UBRRL_value (F_CPU/(BAUD*16))-1

int uart_putc(  char c, FILE *file );
int uart_getc( FILE* file );
void init_UART();