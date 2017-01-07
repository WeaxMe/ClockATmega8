HEADERS = esp01.h n5110.h english_font.h uart.h fifo.h  
SOURCES = esp01.c n5110.c uart.c main.c

default: hex

objects:
	avr-gcc -mmcu=atmega8 -I. -gdwarf-2 -DF_CPU=8000000UL -Os $(SOURCES) $(HEADERS) -o main.o

hex: objects
	avr-objcopy -O ihex main.o main.hex
	
write: hex
	avrdude -p m8 -c usbasp -P usb -U flash:w:main.hex