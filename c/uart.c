/*
 * uart.c
 *
 * Created: 05.12.2016 22:17:14
 *  Author: Vitaliy Gonchar
 */ 

#include "uart.h"
#include <avr/io.h>
#include <avr/interrupt.h>
#include "fifo.h"
#include <stdlib.h>
#include <stdio.h>
FIFO( 64 ) uart_tx_fifo;
FIFO( 64 ) uart_rx_fifo;

FILE uart_stream = FDEV_SETUP_STREAM(uart_putc, uart_getc, _FDEV_SETUP_RW);

int uart_putc(  char c, FILE *file )
{
	int ret;
	cli();
	if( !FIFO_IS_FULL( uart_tx_fifo ) ) {
		FIFO_PUSH( uart_tx_fifo, c );
		UCSRB |= ( 1 << UDRIE );
		ret = 0;
	}
	else {
		ret = -1;
	}
	sei();
	return ret;
}

int uart_getc( FILE* file )
{
	int ret;
	cli();
	if( !FIFO_IS_EMPTY( uart_rx_fifo ) ) {
		ret = FIFO_FRONT( uart_rx_fifo );
		FIFO_POP( uart_rx_fifo );
	}
	else {
		ret = _FDEV_EOF;
	}
	sei();
	return ret;
}

void init_UART()
{
	stdout = stdin = &uart_stream;
	// uart speed
	UBRRL = UBRRL_value;
	UBRRH = UBRRL_value >> 8;
	// enable tx and rx
	UCSRB |= (1 << TXEN) | ( 1 << RXEN ) | (1 << RXCIE );
	// 8 bit, 1 stop-bit, without true control
	//UCSRB |= (1 << TXEN) | ( 1 << RXEN );
	UCSRC |= (1 << URSEL) | (1 << UCSZ0) | (1 << UCSZ1);
}


ISR( USART_RXC_vect )
{
	unsigned char rxbyte = UDR;
	if( !FIFO_IS_FULL( uart_rx_fifo ) ) {
		FIFO_PUSH( uart_rx_fifo, rxbyte );
	}
}

ISR( USART_UDRE_vect )
{
	if( FIFO_IS_EMPTY( uart_tx_fifo ) ) {
		UCSRB &= ~( 1 << UDRIE );
	}
	else {
		char txbyte = FIFO_FRONT( uart_tx_fifo );
		FIFO_POP( uart_tx_fifo );
		UDR = txbyte;
	}
}
